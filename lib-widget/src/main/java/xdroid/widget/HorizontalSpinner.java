package xdroid.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class HorizontalSpinner extends FrameLayout {
    private HorizontalScrollView mScrollView;
    private LinearLayout mLinearLayout;

    private Adapter mAdapter;
    private View mCurrentView;
    private int mCurrentIndex;
    private boolean mOpened;

    private Listener mListener;

    private final DataSetObserver mObserver;
    private final OnClickListener mOnClickListener;
    private final OnClickListener mOnSwitchListener;

    public HorizontalScrollView getScrollView() {
        return mScrollView;
    }

    public LinearLayout getLinearLayout() {
        return mLinearLayout;
    }

    public Adapter getAdapter() {
        return mAdapter;
    }

    public View getCurrentView() {
        return mCurrentView;
    }

    public void setCurrentView(View currentView) {
        mCurrentView = currentView;
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }

    public boolean isOpened() {
        return mOpened;
    }

    public void setOpened(boolean opened) {
        mOpened = opened;
    }

    public Listener getListener() {
        return mListener;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public HorizontalSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HorizontalSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        mObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                onDataSetChanged();
            }
        };

        mOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked(v);
            }
        };

        mOnSwitchListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                onSwitchClicked();
            }
        };
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mLinearLayout = new LinearLayout(getContext());
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        mScrollView = new HorizontalScrollView(getContext());
        mScrollView.addView(mLinearLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mScrollView.setHorizontalScrollBarEnabled(isHorizontalScrollBarEnabled());

        addView(mScrollView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        updateState();
    }

    @Override
    public void setHorizontalScrollBarEnabled(boolean horizontalScrollBarEnabled) {
        super.setHorizontalScrollBarEnabled(horizontalScrollBarEnabled);

        if (mScrollView != null) {
            mScrollView.setHorizontalScrollBarEnabled(horizontalScrollBarEnabled);
        }
    }

    public void setAdapter(Adapter adapter) {
        if (mAdapter != null) {
            mAdapter.unregisterDataSetObserver(mObserver);
        }

        mAdapter = adapter;

        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(mObserver);
        }

        onDataSetChanged();
    }

    protected void onDataSetChanged() {
        int oldCount = mLinearLayout.getChildCount();
        int newCount = mAdapter.getCount();

        for (int i = 0, n = Math.max(oldCount, newCount); i < n; ++i) {
            View oldChild = mLinearLayout.getChildAt(i);
            if (oldChild != null) {
                oldChild.setOnClickListener(null);
            }

            View newChild = mAdapter.getView(i, oldChild, mLinearLayout);
            if (newChild != null) {
                newChild.setOnClickListener(mOnClickListener);

                if (oldChild != newChild) {
                    if (i < oldCount) {
                        mLinearLayout.removeViewAt(i);
                        mLinearLayout.addView(newChild, i);
                    } else {
                        mLinearLayout.addView(newChild);
                    }
                }
            }
        }

        updateCurrentView();
        updateState();
    }

    protected void onItemClicked(View view) {
        setCurrentItem(mLinearLayout.indexOfChild(view));

        // NOTE this may be customized by attributes
        switchState();
    }

    protected void onSwitchClicked() {
        switchState();
    }

    public void switchState() {
        mOpened = !mOpened;

        if (mListener != null) {
            mListener.onSwitched(this, mOpened);
        }

        updateState();
    }

    private void updateState() {
        if (mOpened) {
            if (mCurrentView != null) {
                mCurrentView.setVisibility(GONE);
            }

            mScrollView.setVisibility(VISIBLE);
        } else {
            if (mCurrentView != null) {
                mCurrentView.setVisibility(VISIBLE);
            }

            mScrollView.setVisibility(GONE);
        }
    }

    public void setCurrentItem(int index) {
        final int oldPosition = mCurrentIndex;
        mCurrentIndex = index;

        if (oldPosition != index) {
            if (mListener != null) {
                mListener.onCurrentItemChanged(this, index);
            }

            updateCurrentView();
            updateCurrentChildViews(oldPosition);
        }
    }

    private void updateCurrentView() {
        if (mCurrentView != null) {
            mCurrentView.setOnClickListener(null);
        }

        mCurrentView = mAdapter.getView(mCurrentIndex, mCurrentView, this);

        if (mCurrentView != null) {
            if (mCurrentView.getParent() == null) {
                addView(mCurrentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            mCurrentView.setOnClickListener(mOnSwitchListener);
        }
    }

    private void updateCurrentChildViews(int oldIndex) {
        refreshChildItem(oldIndex);
        refreshChildItem(mCurrentIndex);
    }

    private void refreshChildItem(int index) {
        View convertView = mLinearLayout.getChildAt(index);
        View view = mAdapter.getView(index, convertView, mLinearLayout);

        if (convertView != null) {
            if (convertView != view) {
                mLinearLayout.removeViewAt(index);
                mLinearLayout.addView(view, index);
            }
        } else {
            mLinearLayout.addView(view, index);
        }
    }

    public interface Listener {
        void onCurrentItemChanged(HorizontalSpinner parent, int index);

        void onSwitched(HorizontalSpinner parent, boolean opened);
    }
}
