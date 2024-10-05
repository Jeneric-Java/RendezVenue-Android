package com.jeneric.eventappfrontend.ui.create;

import android.content.Context;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.ui.create.dialogues.TimePickerFragment;

public class CreateFragmentClickHandlers {

    NavController navController;
    Context context;

    public CreateFragmentClickHandlers(NavController navController, Context context) {
        this.navController = navController;
        this.context = context;
    }

    public void onTimeFieldClicked(View view) {
        navController.navigate(R.id.action_createFragment_to_timePickerFragment);
    }

    public void onDateFieldClicked(View view) {
        navController.navigate(R.id.action_createFragment_to_datePickerFragment);
    }
    public void onSubmitButtonClicked(View view) {
        navController.navigate(R.id.action_createFragment_to_submitDialogueFragment);
    }

}
