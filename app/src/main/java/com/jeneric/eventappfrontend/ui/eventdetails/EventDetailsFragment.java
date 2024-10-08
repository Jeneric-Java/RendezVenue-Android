package com.jeneric.eventappfrontend.ui.eventdetails;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.FragmentEventdetailsBinding;
import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.ui.main.MainActivityViewModel;

import java.util.List;

public class EventDetailsFragment extends Fragment {

    private FragmentEventdetailsBinding binding;
    private EventModel event;
    private MainActivityViewModel mainActivityViewModel;
    private Context context;

    public EventDetailsFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_eventdetails, container, false);
        if (getArguments() != null) {
            event = EventDetailsFragmentArgs.fromBundle(getArguments()).getEvent();
            binding.setEvent(event);
        }
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        List<EventModel> userEventList = mainActivityViewModel.getUserEventList();
        Button addToCalButton = getView().findViewById(R.id.eventdetails_button_add_event);
        Button deleteFromCalButton = getView().findViewById(R.id.eventdetails_button_remove_event);

        binding.setClickHandler(new EventDetailsClickHandler(navController, context, event, mainActivityViewModel));

        for (EventModel userEvent : userEventList) {
            if (event.getTitle().equals(userEvent.getTitle())
                    && event.getStartDate().equals(userEvent.getStartDate())
                    && event.getLocation().equals(userEvent.getLocation())) {
                addToCalButton.setVisibility(View.GONE);
                deleteFromCalButton.setVisibility(View.VISIBLE);
            }
        }

        binding.eventdetailsButtonRemoveEvent.setOnClickListener(v -> {
            mainActivityViewModel.deleteEventFromUserList(event);
            navController.navigateUp();
        });
        binding.eventdetailsButtonAddEvent.setOnClickListener(v -> {
            mainActivityViewModel.addNewEvent(event);
            navController.navigateUp();
        });

    }
}