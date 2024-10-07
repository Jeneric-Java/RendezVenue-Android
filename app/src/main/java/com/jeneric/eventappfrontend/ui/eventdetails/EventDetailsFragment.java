package com.jeneric.eventappfrontend.ui.eventdetails;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.FragmentEventdetailsBinding;
import com.jeneric.eventappfrontend.model.EventModel;

public class EventDetailsFragment extends Fragment {

    private FragmentEventdetailsBinding binding;
    private EventModel event;

    public EventDetailsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_eventdetails, container, false);
        if (getArguments() != null) {
            event = EventDetailsFragmentArgs.fromBundle(getArguments()).getEvent();

            binding.setEvent(event);
        }

        return binding.getRoot();
    }
}