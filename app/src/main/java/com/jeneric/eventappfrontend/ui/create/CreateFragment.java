package com.jeneric.eventappfrontend.ui.create;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.FragmentCreateBinding;
import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.model.TimeConvertor;
import com.jeneric.eventappfrontend.ui.create.dialogues.DateTimePickerListener;
import com.jeneric.eventappfrontend.ui.main.MainActivity;


public class CreateFragment extends Fragment implements DateTimePickerListener {

    private Context context;
    private EventModel event;
    TimeConvertor timeConvertor;

    public CreateFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    NavController navController;
    FragmentCreateBinding binding;

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create, container, false);
         navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        event = new EventModel();
        binding.setClickHandler(new CreateFragmentClickHandlers(navController, context, event));
        binding.setEvent(event);
    }

    @Override
    public void onStartDateSelected(int year, int month, int day) {
        timeConvertor.setStartYear(year);
        timeConvertor.setStartMonth(month);
        timeConvertor.setStartDay(day);
    }

    @Override
    public void onStartTimeSelected(int hour, int minute) {
        timeConvertor.setStartHour(hour);
        timeConvertor.setStartMinute(minute);
    }

    @Override
    public void onEndTimeSelected(int hour, int minute) {
        timeConvertor.setEndHour(hour);
        timeConvertor.setEndMinute(minute);
    }
}