package com.jeneric.eventappfrontend.ui.explore;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.adapters.EventAdapter;
import com.jeneric.eventappfrontend.databinding.FragmentExploreBinding;
import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.ui.main.RecyclerViewInterface;

import java.util.List;


public class ExploreFragment extends Fragment implements RecyclerViewInterface{

    private RecyclerView recyclerView;
    private Context context;
    private ExploreFragmentViewModel viewModel;
    private EventAdapter adapter;
    private FragmentExploreBinding binding;
    private NavController navController;
    private List<EventModel> eventList;
    private List<EventModel> filteredEventList;
    private SearchView searchView;
    private final String geoHashEnc = "hWYBaFlaw35f4WikCh0fMA==\n";

    public ExploreFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
        recyclerView = binding.searchFragmentRecyclerView;

        viewModel = new ViewModelProvider(this).get(ExploreFragmentViewModel.class);

        getAllEvents();

        Bundle bundle = getArguments();
        if (bundle != null) {
            String category = bundle.getString("category");
            if (category != null) {
                displayEventsByCategory(category);
            }
        }

        return binding.getRoot();
    }

    private void getAllEvents() {
        viewModel.getAllEvents(geoHashEnc).observe(getViewLifecycleOwner(), new Observer<List<EventModel>>() {
            @Override
            public void onChanged(List<EventModel> eventsFromLiveData) {
                if (eventsFromLiveData != null && !eventsFromLiveData.isEmpty()) {
                    eventList = eventsFromLiveData;
                    Log.d("ExploreFragment", "Data received: " + eventList.toString()); // Log the data
                    displayInRecyclerView();
                } else {
                    Log.d("ExploreFragment", "No data received.");
                }
            }
        });
    }

    private void displayInRecyclerView() {
        recyclerView = binding.searchFragmentRecyclerView;
        adapter = new EventAdapter(context, eventList, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
    }
    private void displayEventsByCategory(String category) {
//        if (!filteredEventList.isEmpty()) {
//            for (int i = 0; i < Math.min(filteredEventList.size(), 2); i++) {
//                EventModel event = filteredEventList.get(i);
//
//                View eventCard = LayoutInflater.from(context).inflate(R.layout.event_item, recyclerView, false);
//
//                TextView title = eventCard.findViewById(R.id.textview_event_title);
//                TextView location = eventCard.findViewById(R.id.textview_event_location);
//                TextView startDate = eventCard.findViewById(R.id.textview_event_date);
//                TextView startTime = eventCard.findViewById(R.id.textview_event_time);
//
//                title.setText(event.getEventTitle());
//                location.setText(event.getEventLocation());
//                startDate.setText(event.getStartDate());
//                startTime.setText(event.getStartTime());
//
//                recyclerView.addView(eventCard);
//            }
//        } else {
//            View noEventCard = LayoutInflater.from(context).inflate(R.layout.no_event_item, recyclerView, false);
//            recyclerView.addView((noEventCard));
//        filteredEventList = new ArrayList<>();
//
//        for (EventModel event : eventList) {
//            if (event.getEventType().equalsIgnoreCase(category)) {
//                filteredEventList.add(event);
//            }
//        }
//        if (filteredEventList.isEmpty()) {
//            //add the no_event_item layout
//        } else {
////            adapter.setFilteredList(filteredEventList);
//        }
//        }
    }

    @Override
    public void onItemClick(int position) {
        navController.navigate(R.id.action_exploreFragment_to_eventDetailsFragment);
    }
}