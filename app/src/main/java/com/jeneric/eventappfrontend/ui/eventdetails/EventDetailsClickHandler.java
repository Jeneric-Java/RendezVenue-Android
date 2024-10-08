package com.jeneric.eventappfrontend.ui.eventdetails;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.view.View;

import androidx.navigation.NavController;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.ui.main.MainActivityViewModel;

import java.util.Calendar;

public class EventDetailsClickHandler {

    Context context;
    EventModel event;
    MainActivityViewModel mainActivityViewModel;
    NavController navController;

    public EventDetailsClickHandler(NavController navController, Context context, EventModel event, MainActivityViewModel mainActivityViewModel) {
        this.navController = navController;
        this.context = context;
        this.event = event;
        this.mainActivityViewModel = mainActivityViewModel;
    }

    public void onButtonClick(View view) {
        onAddToCalClicked(view);
        onAddToUserEventClicked();
        navController.navigate(R.id.action_eventDetailsFragment_to_homeFragment);
    }

    public void onAddToCalClicked(View view) {

        String stringStartDate = event.getStartDate();
        String stringStartTime = event.getStartTime();
        String stringEndDate = event.getEndDate();
        String stringEndTime = event.getEndTime();

        int StartYear = Integer.parseInt(stringStartDate.substring(0,4));
        int StartMonth = Integer.parseInt(stringStartDate.substring(5,7));
        int StartDay = Integer.parseInt(stringStartDate.substring(8,10));
        int StartHour = Integer.parseInt(stringStartTime.substring(0,2));
        int StartMinute = Integer.parseInt(stringStartTime.substring(3,5));


        Calendar beginTime = Calendar.getInstance();
        beginTime.set(StartYear,(StartMonth - 1),StartDay,StartHour,StartMinute);
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2024,9,31,10,30);

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())

                .putExtra(CalendarContract.Events.TITLE, event.getTitle())
                .putExtra(CalendarContract.Events.DESCRIPTION, event.getDescription() + "\n" + event.getType())
                .putExtra(CalendarContract.Events.EVENT_LOCATION, event.getLocation());

        context.startActivity(intent);

    }

    public void onAddToUserEventClicked () {
        mainActivityViewModel.addEventToUserList(event);
    }

    public void onRemoveEventButtonClicked() {
        mainActivityViewModel.deleteEventFromUserList(event);
        navController.navigateUp();
    }
}
