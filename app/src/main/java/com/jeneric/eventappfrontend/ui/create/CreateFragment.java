package com.jeneric.eventappfrontend.ui.create;

import android.content.Context;
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

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.FragmentCreateBinding;
import com.jeneric.eventappfrontend.model.EventModel;
import com.jeneric.eventappfrontend.model.TimeWizard;
import com.jeneric.eventappfrontend.ui.main.MainActivityViewModel;
import com.jeneric.eventappfrontend.ui.main.MainActivity;


public class CreateFragment extends Fragment {

    private Context context;
    private EventModel event;
    TimeWizard timeWizard;

    MainActivityViewModel viewModel;

    public CreateFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof MainActivity) {
            this.timeWizard = ((MainActivity) context).getTimeConvertor();
        }
    }

    NavController navController;
    FragmentCreateBinding binding;

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create, container, false);
         navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);

         viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
         return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        event = new EventModel();
        binding.setClickHandler(new CreateFragmentClickHandlers(navController, context, event, timeWizard, viewModel));
        binding.setEvent(event);
    }

}