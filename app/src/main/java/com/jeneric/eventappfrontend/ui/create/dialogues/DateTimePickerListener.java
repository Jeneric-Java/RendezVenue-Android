package com.jeneric.eventappfrontend.ui.create.dialogues;

public interface DateTimePickerListener {
    void onStartDateSelected(int year, int month, int day);
    void onStartTimeSelected(int hour, int minute);
    void onEndTimeSelected(int hour, int minute);
}
