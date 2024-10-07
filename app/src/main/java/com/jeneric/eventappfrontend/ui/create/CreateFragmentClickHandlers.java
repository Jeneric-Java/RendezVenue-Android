package com.jeneric.eventappfrontend.ui.create;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.model.TimeConvertor;
import com.jeneric.eventappfrontend.ui.create.dialogues.TimePickerFragment;

public class CreateFragmentClickHandlers {

    EventModel eventModel;
    TimeConvertor timeConvertor;
    NavController navController;
    Context context;

    public CreateFragmentClickHandlers(NavController navController, Context context, EventModel eventModel, TimeConvertor timeConvertor) {
        this.navController = navController;
        this.context = context;
        this.eventModel = eventModel;
        this.timeConvertor = timeConvertor;
    }

    public void onTimeFieldClicked(View view) {
        navController.navigate(R.id.action_createFragment_to_timePickerFragment);
    }
    public void onEndTimeFieldClicked(View view) {
        navController.navigate(R.id.action_createFragment_to_endTimePickerFragment);
    }

    public void onDateFieldClicked(View view) {
        navController.navigate(R.id.action_createFragment_to_datePickerFragment);
    }
    public void onSubmitButtonClicked(View view) {
        if (eventModel.getTitle() == null) {
            Toast.makeText(context, "Make sure all required Fields are filled", Toast.LENGTH_SHORT).show();
        } else {
            EventModel event = new EventModel(
                    eventModel.getId(),
                    eventModel.getTitle(),
                    eventModel.getDescription(),
                    eventModel.getLocation(),
                    eventModel.getUrl(),
                    eventModel.getType(),
                    eventModel.getStartDate(),
                    eventModel.getStartTime(),
                    eventModel.getEndDate(),
                    eventModel.getEndTime()
            );
//            timeConvertor.getStartYear();

            //TODO: Call method to save album
            CreateFragmentDirections.ActionCreateFragmentToSubmitDialogueFragment action = CreateFragmentDirections.actionCreateFragmentToSubmitDialogueFragment(
                    event.getTitle(),
                    timeConvertor.getStartYear(),
                    timeConvertor.getStartMonth(),
                    timeConvertor.getStartDay(),
                    timeConvertor.getStartHour(),
                    timeConvertor.getStartMinute(),
                    timeConvertor.getEndYear(),
                    timeConvertor.getEndMonth(),
                    timeConvertor.getEndDay(),
                    timeConvertor.getEndHour(),
                    timeConvertor.getEndMinute());
            navController.navigate(action);
        }
    }

}
