package com.jeneric.eventappfrontend.ui.explore;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.repository.EventRepository;

import java.util.List;

public class ExploreFragmentViewModel extends AndroidViewModel {
    EventRepository eventRepository;


    public ExploreFragmentViewModel(@NonNull Application application) {
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
        eventRepository.deleteAlbum(id);
    }
}