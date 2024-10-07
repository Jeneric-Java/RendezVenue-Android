package com.jeneric.eventappfrontend.ui.home;

import android.app.Activity;
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
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.FragmentHomeBinding;
import com.jeneric.eventappfrontend.model.EventModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private LinearLayout eventContainer;
    private List<EventModel> userEventList = new ArrayList<>();
    private FragmentHomeBinding binding;
    private NavController navController;



    private Context context;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        // init NavController
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);

        // Setup event container and other init
        eventContainer = binding.homeEventContainer;
        loadUserEvents();
        displayUserEvents();

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.setClickhandlers(new HomeClickHandlers(navController, context));
    }


    private void loadUserEvents() {
        userEventList.add(new EventModel(1, "Event 1", "This is the first event", "London", "www.google.com", "misc", "9 Oct", "19.30pm"," ", " "));
        userEventList.add(new EventModel(1, "Event 2", "This is the second event", "Manchester", "www.github.com", "misc", "11 Oct", "9.00am"," ", " "));
        userEventList.add(new EventModel(1, "Event 3", "This is the second event", "Manchester", "www.github.com", "misc", "11 Oct", "9.00am"," ", " "));
    }


    private void displayUserEvents() {
        eventContainer.removeAllViews();

        if(!userEventList.isEmpty()) {
            for(int i = 0; i < Math.min(userEventList.size(), 2); i++) {
                EventModel event = userEventList.get(i);

                View eventCard = LayoutInflater.from(context).inflate(R.layout.event_item, eventContainer, false);

                TextView title = eventCard.findViewById(R.id.textview_event_title);
                TextView location = eventCard.findViewById(R.id.textview_event_location);
                TextView startDate = eventCard.findViewById(R.id.textview_event_date);
                TextView startTime = eventCard.findViewById(R.id.textview_event_time);

                title.setText(event.getTitle());
                location.setText(event.getLocation());
                startDate.setText(event.getStartDate());
                startTime.setText(event.getStartTime());

                eventCard.setOnClickListener(v -> {
                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToEventDetailsFragment(event));
                });

                eventContainer.addView(eventCard);
            }
        }
        else {
            View noEventCard = LayoutInflater.from(context).inflate(R.layout.no_event_item, eventContainer, false);
            eventContainer.addView(noEventCard);
        }
    }
}

