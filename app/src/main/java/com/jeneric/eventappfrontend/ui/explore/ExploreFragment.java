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
import android.widget.TextView;
import android.widget.Toast;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.adapters.EventAdapter;
import com.jeneric.eventappfrontend.databinding.FragmentExploreBinding;
import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.ui.main.MainActivity;
import com.jeneric.eventappfrontend.ui.main.MainActivityViewModel;
import com.jeneric.eventappfrontend.ui.main.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;


public class ExploreFragment extends Fragment implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private Context context;
    private MainActivityViewModel viewModel;
    private EventAdapter adapter;
    private FragmentExploreBinding binding;
    private NavController navController;
    private List<EventModel> eventList;
    private List<EventModel> filteredEventList;
    private SearchView searchView;


    private String geoHashEnc;

    public ExploreFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        if (context instanceof MainActivity) {
            this.geoHashEnc = ((MainActivity) context).getUserLocation();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
        recyclerView = binding.searchFragmentRecyclerView;

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        eventList = new ArrayList<>();
        filteredEventList = new ArrayList<>();

        searchView = binding.searchBar;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterSearch(newText);
                return false;
            }
        });

        String category = getArguments() != null ? getArguments().getString("category") : "";

        if (category != null && !category.isEmpty()) {
            TextView categoryTitle = binding.exploreCategoryTitle;
            categoryTitle.setText("Category: " + (category.equalsIgnoreCase("ART_THEATRE") ? "ARTS & THEATRE" : category) );
            categoryTitle.setVisibility(View.VISIBLE);
            getAllEventsByCategory(category);
        } else {
            getAllEvents();
        }

        return binding.getRoot();
    }

    private void getAllEvents() {
        viewModel.getAllEvents(geoHashEnc).observe(getViewLifecycleOwner(), new Observer<List<EventModel>>() {
            @Override
            public void onChanged(List<EventModel> eventsFromLiveData) {
                if (eventsFromLiveData != null && !eventsFromLiveData.isEmpty()) {
                    eventList = eventsFromLiveData;
                    displayInRecyclerView();
                }
            }
        });
    }

    private void getAllEventsByCategory(String category) {
        viewModel.getAllEvents(geoHashEnc).observe(getViewLifecycleOwner(), new Observer<List<EventModel>>() {
            @Override
            public void onChanged(List<EventModel> eventsFromLiveData) {
                if (eventsFromLiveData != null && !eventsFromLiveData.isEmpty()) {
                    eventList.clear();
                    for (EventModel event : eventsFromLiveData) {
                        Log.d("ExploreFragment", "Event Type: " + event.getType());
                        if (event.getType().equalsIgnoreCase(category)) {
                            eventList.add(event);
                        }
                    }
                    displayInRecyclerView();
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

    private void filterSearch(String newText) {
        newText = newText.toLowerCase();
        filteredEventList = new ArrayList<>();
        for (EventModel event : eventList) {
            if (event.getTitle().toLowerCase().contains(newText)
                    || event.getType().toLowerCase().contains(newText)) {
                filteredEventList.add(event);
            }
        }
        if (filteredEventList.isEmpty() && !newText.isBlank()) {
            Toast.makeText(context, "No event found", Toast.LENGTH_SHORT).show();
        } else if (!filteredEventList.isEmpty()) {
            adapter.setFilteredList(filteredEventList);
        }
    }

    @Override
    public void onItemClick(int position) {
        EventModel selectedEvent = filteredEventList.isEmpty() ? eventList.get(position) : filteredEventList.get(position);
        navController.navigate(ExploreFragmentDirections.actionExploreFragmentToEventDetailsFragment(selectedEvent));
    }
}