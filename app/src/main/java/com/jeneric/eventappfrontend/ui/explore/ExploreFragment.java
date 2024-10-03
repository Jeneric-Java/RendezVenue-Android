package com.jeneric.eventappfrontend.ui.explore;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.FragmentExploreBinding;


public class ExploreFragment extends Fragment {

    private Context context;

    private NavController navController;

    public ExploreFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    FragmentExploreBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container);
        return binding.getRoot();
    }

}