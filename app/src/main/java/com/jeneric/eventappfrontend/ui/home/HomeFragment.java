package com.jeneric.eventappfrontend.ui.home;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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

import com.bumptech.glide.Glide;
import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.FragmentHomeBinding;
import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.ui.main.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private LinearLayout eventContainer;
    private List<EventModel> userEventsList;
    private FragmentHomeBinding binding;
    private NavController navController;
    private MainActivityViewModel mainActivityViewModel;



    private Context context;
    public HomeFragment() {
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);


        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);


        eventContainer = binding.homeEventContainer;
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        userEventsList = mainActivityViewModel.getUserEventList();
        displayUserEvents();

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.setClickhandlers(new HomeClickHandlers(navController, context));
    }

    private void displayUserEvents() {
        eventContainer.removeAllViews();

        if(!userEventsList.isEmpty()) {
            for(int i = 0; i < Math.min(userEventsList.size(), 2); i++) {
                EventModel event = userEventsList.get(i);

                View eventCard = LayoutInflater.from(context).inflate(R.layout.event_item, eventContainer, false);

                TextView title = eventCard.findViewById(R.id.textview_event_title);
                TextView location = eventCard.findViewById(R.id.textview_event_location);
                TextView startDate = eventCard.findViewById(R.id.textview_event_date);
                TextView startTime = eventCard.findViewById(R.id.textview_event_time);
                ImageView imageView = eventCard.findViewById(R.id.event_img);

                title.setText(event.getTitle());
                location.setText(event.getLocation());
                startDate.setText(event.getStartDate());
                startTime.setText(event.getStartTime());
                Glide.with(context)
                        .load(event.getImageUrl())
                        .placeholder(R.mipmap.img_placeholder_outdoor)
                        .error(R.mipmap.img_placeholder)
                        .into(imageView);

                eventContainer.addView(eventCard);

                eventCard.setOnClickListener(v -> {
                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToEventDetailsFragment(event));
                });
            }
        }
        else {
            View noEventCard = LayoutInflater.from(context).inflate(R.layout.no_event_item, eventContainer, false);
            eventContainer.addView(noEventCard);
        }
    }
}

