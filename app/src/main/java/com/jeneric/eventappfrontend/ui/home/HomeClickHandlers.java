package com.jeneric.eventappfrontend.ui.home;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.SearchView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.FragmentHomeBinding;

public class HomeClickHandlers {

    private NavController navController;
    Context context;

    public HomeClickHandlers(NavController navController, Context context) {
        this.navController = navController;
        this.context = context;
    }

    public void onSearchBarClick(View view) {
        navController.navigate(R.id.exploreFragment);
        NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment_container);

    }

}
