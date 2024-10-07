package com.jeneric.eventappfrontend.ui.create.dialogues;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.jeneric.eventappfrontend.R;

import java.util.Calendar;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DateTimePickerListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DateTimePickerListener) {
            listener = (DateTimePickerListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement PickerListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String stringDate = day + "/" + (month + 1) + "/" + year;
        if (listener != null) {
            listener.onStartDateSelected(year, month, day);
        }
        EditText editText = requireActivity().findViewById(R.id.editTextDate);
        editText.setText(stringDate);

        dismiss();
    }
}
