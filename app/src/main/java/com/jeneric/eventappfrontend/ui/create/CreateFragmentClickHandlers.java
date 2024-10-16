package com.jeneric.eventappfrontend.ui.create;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.model.TimeWizard;
import com.jeneric.eventappfrontend.ui.main.MainActivityViewModel;

public class CreateFragmentClickHandlers {

    EventModel eventModel;
    TimeWizard timeWizard;
    NavController navController;
    Context context;

    MainActivityViewModel viewModel;

    public CreateFragmentClickHandlers(NavController navController, Context context, EventModel eventModel, TimeWizard timeWizard, MainActivityViewModel viewModel) {
        this.navController = navController;
        this.context = context;
        this.eventModel = eventModel;
        this.timeWizard = timeWizard;
        this.viewModel = viewModel;
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
                    eventModel.getClosestCity(),
                    eventModel.getStartDate(),
                    eventModel.getStartTime(),
                    eventModel.getEndDate(),
                    eventModel.getEndTime(),
                    eventModel.getImageUrl()
            );
//            timeConvertor.getStartYear();

            CreateFragmentDirections.ActionCreateFragmentToSubmitDialogueFragment action = CreateFragmentDirections.actionCreateFragmentToSubmitDialogueFragment(
                    event.getTitle(),
                    event.getDescription(),
                    event.getLocation(),
                    event.getType(),

                    timeWizard.getStartYear(),
                    timeWizard.getStartMonth(),
                    timeWizard.getStartDay(),
                    timeWizard.getStartHour(),
                    timeWizard.getStartMinute(),
                    timeWizard.getEndYear(),
                    timeWizard.getEndMonth(),
                    timeWizard.getEndDay(),
                    timeWizard.getEndHour(),
                    timeWizard.getEndMinute()
            );
            viewModel.addEventToUserList(event);
            viewModel.addNewEvent(event);
            navController.navigate(action);
        }
    }

}
