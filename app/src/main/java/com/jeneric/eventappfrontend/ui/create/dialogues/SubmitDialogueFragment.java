package com.jeneric.eventappfrontend.ui.create.dialogues;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.model.EventModel;

import java.util.Calendar;

public class SubmitDialogueFragment extends DialogFragment {

    Context context;
    NavController navController;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);

        builder.setMessage(R.string.add_event_to_cal);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SubmitDialogueFragmentArgs args = SubmitDialogueFragmentArgs.fromBundle(getArguments());
                String eventTitle = args.getEventTitle();
                String eventDescription = args.getEventDescription();
                String eventLocation = args.getEventLocation();
                String eventType = args.getEventType();

                int startYear = args.getStartYear();
                int startMonth = args.getStartMonth();
                int startDay = args.getStartDay();
                int startHour = args.getStartHour();
                int startMinute = args.getStartMinute();

                int endYear = args.getEndYear();
                int endMonth = args.getEndMonth();
                int endDay = args.getEndDay();
                int endHour = args.getEndHour();
                int endMinute = args.getEndMinute();

                Calendar startTime = Calendar.getInstance();
                startTime.set(startYear,startMonth,startDay,startHour,startMinute,0);

                Calendar endTime = Calendar.getInstance();
                endTime.set(endYear,endMonth,endDay, endHour, endMinute, 0);

                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, eventTitle)
                        .putExtra(CalendarContract.Events.DESCRIPTION, eventDescription + "\n" + eventType)
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, eventLocation)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());

                context.startActivity(intent);
                navController.navigate(R.id.homeFragment);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                navController.navigate(R.id.homeFragment);

            }
        });
           return builder.create();
    }
}
