package xdroid.example;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import xdroid.enumformat.EnumFormat;
import xdroid.enumformat.EnumString;

public class ExampleEnumFormatDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle state) {
        MyEnum[] values = MyEnum.values();
        CharSequence[] items = new CharSequence[values.length];

        // use single instance
        EnumFormat enumFormat = EnumFormat.getInstance();

        for (int i = 0, n = values.length; i < n; ++i) {
            MyEnum e = values[i];

            // format enum constant to localized string
            items[i] = enumFormat.format(e);
        }

        return new AlertDialog.Builder(getActivity())
                .setItems(items, null)
                .create();
    }

    public enum MyEnum {
        @EnumString(R.string.one)
        VALUE_ONE,
        // EnumFormat will try to find resource id R.string.value_two
        VALUE_TWO,
        // This field will not be localized
        VALUE_THREE,
    }
}
