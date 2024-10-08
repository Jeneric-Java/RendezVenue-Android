package com.jeneric.eventappfrontend.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    EventRepository eventRepository;
    private List<EventModel> userEventList = new ArrayList<>();


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.eventRepository = new EventRepository(application);
    }

    public LiveData<List<EventModel>> getAllEvents(String geoHashEnc) {
        return eventRepository.getMutableLiveData(geoHashEnc);
    }

    public void addNewEvent(EventModel event) {
        eventRepository.addNewEvent(event);
    }

    public void updateEvent(long id, EventModel event) {
        eventRepository.updateEvent(id, event);
    }

    public void deleteEvent(long id) {
        eventRepository.deleteEvent(id);
    }

    public List<EventModel> getUserEventList() {
        return userEventList;
    }

    public void addEventToUserList(EventModel event) {
        userEventList.add(event);
    }

    public void deleteEventFromUserList(EventModel event) {
        userEventList.remove(event);
    }


}