package com.jeneric.eventappfrontend.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.jeneric.eventappfrontend.BR;

public class TimeModel extends BaseObservable implements Parcelable {

    private int year;

    private int month;

    private int dayOfMonth;

    private int hourOfDay;

    private int minute;

    public TimeModel(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
    }

    protected TimeModel(Parcel in) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(year);
        dest.writeInt(month);
        dest.writeInt(dayOfMonth);
        dest.writeInt(hourOfDay);
        dest.writeInt(minute);
    }
}
