package com.jeneric.eventappfrontend.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.service.EventApiService;
import com.jeneric.eventappfrontend.service.RetrofitInstance;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {
    private MutableLiveData<List<EventModel>> mutableLiveData= new MutableLiveData<>();
    private Application application;

    public EventRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<EventModel>> getMutableLiveData(String geoHashEnc) {
        EventApiService eventApiService = RetrofitInstance.getService();
        Call<List<EventModel>> call = eventApiService.getAllEvents(geoHashEnc);

        call.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {
                List<EventModel> eventList = response.body();
                mutableLiveData.setValue(eventList);
            }

            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable throwable) {
                Log.e("Http Failure", throwable.getMessage());
            }
        });
        return mutableLiveData;
    }
    public void addNewEvent(EventModel event) {
        EventApiService eventApiService = RetrofitInstance.getService();
        Call<EventModel> call = eventApiService.addNewEvent(event);

        call.enqueue(new Callback<EventModel>() {
            @Override
            public void onResponse(Call<EventModel> call, Response<EventModel> response) {
                Toast.makeText(application.getApplicationContext(),
                                "Event added to database",
                                Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onFailure(Call<EventModel> call, Throwable throwable) {
                Toast.makeText(application.getApplicationContext(),
                                "Unable to add event to database",
                                Toast.LENGTH_SHORT)
                        .show();
                Log.e("Add Event failed", throwable.getMessage());
            }
        });
    }
    public void updateEvent(long id, EventModel event) {
        EventApiService eventApiService = RetrofitInstance.getService();
        Call<EventModel> call = eventApiService.updateEvent(id, event);

        call.enqueue(new Callback<EventModel>() {
            @Override
            public void onResponse(Call<EventModel> call, Response<EventModel> response) {
                Toast.makeText(application.getApplicationContext(),
                                "Event updated",
                                Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onFailure(Call<EventModel> call, Throwable throwable) {
                Toast.makeText(application.getApplicationContext(),
                                "Unable to update event",
                                Toast.LENGTH_SHORT)
                        .show();
                Log.e("Put Request failed", throwable.getMessage());
            }
        });
    }
    public void deleteEvent(long id) {
        EventApiService eventApiService = RetrofitInstance.getService();
        Call<ResponseBody> call = eventApiService.deleteEvent(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(application.getApplicationContext(),
                                response.message(),
                                Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Toast.makeText(application.getApplicationContext(),
                                "Unable to delete event",
                                Toast.LENGTH_SHORT)
                        .show();
                Log.e("Delete Request failed", throwable.getMessage());
            }
        });
    }
}
