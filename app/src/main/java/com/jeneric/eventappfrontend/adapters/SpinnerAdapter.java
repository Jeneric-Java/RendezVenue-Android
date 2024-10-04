package com.jeneric.eventappfrontend.adapters;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

public class SpinnerAdapter {

    @BindingAdapter("spinnerSelectedValue")
    public static void setSelectedValue(Spinner spinner, String selectedValue) {
        if (selectedValue != null) {
            int position = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(selectedValue);
            spinner.setSelection(position);
        }
    }
    @InverseBindingAdapter(attribute = "spinnerSelectedValue")
    public static String getSelectedValue(Spinner spinner) {
        return (String) spinner.getSelectedItem();
    }
    @BindingAdapter("spinnerSelectedValueAttrChanged")
    public static void getSpinnerListeners(Spinner spinner, final InverseBindingListener listener) {
        if (listener != null) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    listener.onChange();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }
}
