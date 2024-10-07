package com.jeneric.eventappfrontend.ui.create.dialogues;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.jeneric.eventappfrontend.R;

import java.util.Calendar;
import java.util.Locale;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

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
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        String stringTime = String.format(Locale.UK ,"%02d:%02d", hour, minute);
        if (listener != null) {
            listener.onStartTimeSelected(hour, minute);
        }
        EditText editText = requireActivity().findViewById(R.id.editTextStartTime);
        editText.setText(stringTime);

        dismiss();
    }
}
