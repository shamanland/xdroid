package xdroid.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.WrapperListAdapter;

import java.io.Serializable;

import xdroid.adapter.AdapterExt;
import xdroid.adapter.CursorAdapterExt;
import xdroid.adapter.IAdapter;
import xdroid.adapter.ViewBinder;
import xdroid.adapter.ViewTypeResolver;
import xdroid.collections.Indexed;
import xdroid.collections.Prototypes;
import xdroid.core.ParcelUtils;
import xdroid.core.ReflectUtils;
import xdroid.core.Strings;

public class ListViewExt extends ListView {
    public static final int ADAPTER_DATA_NONE = 0;
    public static final int ADAPTER_DATA_ARRAY_LIST = 1;
    public static final int ADAPTER_DATA_LINKED_LIST = 2;
    public static final int ADAPTER_DATA_CURSOR = 3;

    public ListViewExt(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ListViewExt(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ListViewExt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, @SuppressWarnings("unused") int defStyle) {
        if (attrs == null) {
            return;
        }

        if (context == null) {
            return;
        }

        Resources resources = context.getResources();
        if (resources == null) {
            return;
        }

        TypedArray a = resources.obtainAttributes(attrs, R.styleable.ListViewExt);
        if (a == null) {
            return;
        }

        try {
            switch (a.getInt(R.styleable.ListViewExt_adapterData, ADAPTER_DATA_NONE)) {
                case ADAPTER_DATA_ARRAY_LIST:
                    initAdapter(context, a, new AdapterExt(), Prototypes.newArrayList());
                    break;
                case ADAPTER_DATA_LINKED_LIST:
                    initAdapter(context, a, new AdapterExt(), Prototypes.newLinkedList());
                    break;
                case ADAPTER_DATA_CURSOR:
                    initCursorAdapter(context, a);
                    break;
            }
        } finally {
            a.recycle();
        }
    }

    private void initAdapter(Context context, TypedArray a, IAdapter adapter) {
        int layoutId = a.getResourceId(R.styleable.ListViewExt_adapterLayoutId, 0);
        if (layoutId != 0) {
            adapter.setLayoutId(layoutId);
        }

        int layoutIdsArray = a.getResourceId(R.styleable.ListViewExt_adapterLayoutIdsArray, 0);
        if (layoutIdsArray != 0) {
            TypedArray layouts = context.getResources().obtainTypedArray(layoutIdsArray);
            try {
                int count = layouts.length();
                for (int i = 0; i < count; ++i) {
                    adapter.putLayoutId(i, layouts.getResourceId(i, 0));
                }
            } finally {
                layouts.recycle();
            }
        }

        String binderClass = a.getString(R.styleable.ListViewExt_adapterBinderClass);
        if (Strings.isNotEmpty(binderClass)) {
            //noinspection unchecked
            adapter.setBinder(ReflectUtils.<ViewBinder>newInstanceByClassName(ReflectUtils.fullClassName(context, binderClass)));
        }

        String viewTypeResolverClass = a.getString(R.styleable.ListViewExt_adapterViewTypeResolverClass);
        if (Strings.isNotEmpty(viewTypeResolverClass)) {
            //noinspection unchecked
            adapter.setViewTypeResolver(ReflectUtils.<ViewTypeResolver>newInstanceByClassName(ReflectUtils.fullClassName(context, viewTypeResolverClass)));
        }
    }

    @SuppressWarnings("unchecked")
    private void initAdapter(Context context, TypedArray a, AdapterExt adapter, Indexed data) {
        adapter.setData(data);

        initAdapter(context, a, adapter);
        setAdapter(adapter);
    }

    @SuppressWarnings("unchecked")
    private void initCursorAdapter(Context context, TypedArray a) {
        String uri = a.getString(R.styleable.ListViewExt_adapterCursorQuery);
        if (Strings.isEmpty(uri)) {
            return;
        }

        Cursor cursor = context.getContentResolver().query(Uri.parse(uri), null, null, null, null);
        if (cursor == null) {
            return;
        }

        CursorAdapterExt adapter = new CursorAdapterExt(context, cursor, a.getBoolean(R.styleable.ListViewExt_adapterCursorAutoRequery, true));
        initAdapter(context, a, adapter);
        setAdapter(adapter);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        ss.onRestoreInstanceState(this);
    }

    public AdapterExt getRawAdapter() {
        Adapter adapter = getAdapter();
        if (adapter instanceof WrapperListAdapter) {
            adapter = ((WrapperListAdapter) adapter).getWrappedAdapter();
        }

        if (adapter instanceof AdapterExt) {
            return (AdapterExt) adapter;
        }

        return null;
    }

    static class SavedState extends BaseSavedState {
        final int firstVisiblePosition;
        final int topItemPosition;
        final Object data;

        public static int getTopItemPosition(ListView listView) {
            View topItem = listView.getChildAt(listView.getHeaderViewsCount());
            return topItem == null ? 0 : topItem.getTop();
        }

        public static Object getData(ListViewExt listView) {
            AdapterExt adapter = listView.getRawAdapter();
            if (adapter != null) {
                Indexed data = adapter.getData();
                if (data instanceof Parcelable || data instanceof Serializable) {
                    return data;
                }
            }

            return null;
        }

        public SavedState(Parcelable superState, ListViewExt listView) {
            super(superState);
            firstVisiblePosition = listView.getFirstVisiblePosition();
            topItemPosition = getTopItemPosition(listView);
            data = getData(listView);
        }

        @SuppressWarnings("unchecked")
        public void onRestoreInstanceState(ListViewExt listView) {
            listView.setSelectionFromTop(firstVisiblePosition, topItemPosition);

            AdapterExt adapter = listView.getRawAdapter();
            if (adapter != null && data instanceof Indexed) {
                adapter.setData((Indexed) data);
            }
        }

        @Override
        public void writeToParcel(@SuppressWarnings("NullableProblems") Parcel out, int flags) {
            out.writeInt(firstVisiblePosition);
            out.writeInt(topItemPosition);
            ParcelUtils.writeParcelableOrSerializable(out, flags, data);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        private SavedState(Parcel in) {
            super(in);

            ClassLoader cl = ((Object) this).getClass().getClassLoader();

            firstVisiblePosition = in.readInt();
            topItemPosition = in.readInt();
            data = ParcelUtils.readParcelableOrSerializable(in, cl);
        }
    }
}
