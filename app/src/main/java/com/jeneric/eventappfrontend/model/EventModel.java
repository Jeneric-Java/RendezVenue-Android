package com.jeneric.eventappfrontend.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.jeneric.eventappfrontend.BR;


public class EventModel extends BaseObservable implements Parcelable {

    @SerializedName("id")
    private long id;
    @SerializedName("eventTitle")
    private String eventTitle;
    @SerializedName("eventDescription")
    private String eventDescription;
    @SerializedName("eventLocation")
    private String eventLocation;
    @SerializedName("eventURL")
    private String eventURL;
    @SerializedName("eventType")
    private String eventType;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("endDate")
    private String endDate;
    @SerializedName("endTime")
    private String endTime;

    public EventModel(long id, String eventTitle, String eventDescription, String eventLocation, String eventURL, String eventType, String startDate, String startTime, String endDate, String endTime) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventURL = eventURL;
        this.eventType = eventType;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public EventModel() {
    }

    protected EventModel(Parcel in) {
        id = in.readLong();
        eventTitle = in.readString();
        eventDescription = in.readString();
        eventLocation = in.readString();
        eventURL = in.readString();
        eventType = in.readString();
        startDate = in.readString();
        startTime = in.readString();
        endDate = in.readString();
        endTime = in.readString();
    }

    public static final Creator<EventModel> CREATOR = new Creator<EventModel>() {
        @Override
        public EventModel createFromParcel(Parcel in) {
            return new EventModel(in);
        }

        @Override
        public EventModel[] newArray(int size) {
            return new EventModel[size];
        }
    };

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
        notifyPropertyChanged(BR.eventTitle);
    }
    @Bindable
    public String getEventDescription() {
        return eventDescription;
    }


    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
        notifyPropertyChanged(BR.eventDescription);
    }

    @Bindable
    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
        notifyPropertyChanged(BR.eventLocation);
    }
    @Bindable
    public String getEventURL() {
        return eventURL;
    }

    public void setEventURL(String eventURL) {
        this.eventURL = eventURL;
        notifyPropertyChanged(BR.eventURL);
    }

    @Bindable
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
        notifyPropertyChanged(BR.eventType);
    }

    @Bindable
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
        notifyPropertyChanged(BR.startDate);
    }

    @Bindable
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        notifyPropertyChanged(BR.startTime);
    }

    @Bindable
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
        notifyPropertyChanged(BR.endDate);
    }

    @Bindable
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        notifyPropertyChanged(BR.endTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(eventTitle);
        dest.writeString(eventDescription);
        dest.writeString(eventLocation);
        dest.writeString(eventURL);
        dest.writeString(eventType);
        dest.writeString(startDate);
        dest.writeString(startTime);
        dest.writeString(endDate);
        dest.writeString(endTime);
    }

}
