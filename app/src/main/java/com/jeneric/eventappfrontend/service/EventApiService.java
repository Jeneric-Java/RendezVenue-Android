package com.jeneric.eventappfrontend.service;

import com.jeneric.eventappfrontend.model.EventModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventApiService {

    @GET("events")
    Call<List<EventModel>> getAllEvents(@Query("geoHashEnc") String geoHashEnc);

    @POST("events")
    Call<EventModel> addNewEvent(@Body EventModel event);

    @PUT("events/{id}")
    Call<EventModel> updateEvent(@Path("id") long id, @Body EventModel event);

    @DELETE("events/{id}")
    Call<ResponseBody> deleteEvent(@Path("id") long id);

}
