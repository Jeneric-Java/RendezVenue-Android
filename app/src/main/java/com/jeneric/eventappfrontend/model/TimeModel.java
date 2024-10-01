package com.jeneric.eventappfrontend.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.jeneric.eventappfrontend.BR;

public class TimeModel extends BaseObservable implements Parcelable {

    @SerializedName("id")
    private Long id;
    @SerializedName("year")
    private int year;
    @SerializedName("month")
    private int month;
    @SerializedName("dayOfMonth")
    private int dayOfMonth;
    @SerializedName("hourOfDay")
    private int hourOfDay;
    @SerializedName("minute")
    private int minute;

    public TimeModel(Long id, int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
    }

    protected TimeModel(Parcel in) {
        id = in.readLong();
        year = in.readInt();
        month = in.readInt();
        dayOfMonth = in.readInt();
        hourOfDay = in.readInt();
        minute = in.readInt();
    }

    public static final Creator<TimeModel> CREATOR = new Creator<TimeModel>() {
        @Override
        public TimeModel createFromParcel(Parcel in) {
            return new TimeModel(in);
        }

        @Override
        public TimeModel[] newArray(int size) {
            return new TimeModel[size];
        }
    };

    @Bindable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
        notifyPropertyChanged(BR.year);
    }

    @Bindable
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
        notifyPropertyChanged(BR.month);
    }

    @Bindable
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        notifyPropertyChanged(BR.dayOfMonth);
    }

    @Bindable
    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
        notifyPropertyChanged(BR.hourOfDay);
    }

    @Bindable
    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        notifyPropertyChanged(BR.minute);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(year);
        dest.writeInt(month);
        dest.writeInt(dayOfMonth);
        dest.writeInt(hourOfDay);
        dest.writeInt(minute);
    }
}
