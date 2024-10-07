package com.jeneric.eventappfrontend.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.ActivityMainBinding;
import com.jeneric.eventappfrontend.model.TimeConvertor;
import com.jeneric.eventappfrontend.ui.create.CreateFragment;
import com.jeneric.eventappfrontend.ui.create.dialogues.DateTimePickerListener;
import com.jeneric.eventappfrontend.ui.explore.ExploreFragment;
import com.jeneric.eventappfrontend.ui.home.HomeFragment;
import com.jeneric.eventappfrontend.ui.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity implements DateTimePickerListener {

    ActivityMainBinding binding;
    MainActivityClickHandlers mainActivityClickHandlers;

    TimeConvertor timeConvertor = new TimeConvertor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_container);

        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        mainActivityClickHandlers = new MainActivityClickHandlers(this);
        binding.setClickHandler(mainActivityClickHandlers);

//        this.handleBottomNavigationSelections(bottomNavigationView, navController);

    }

//    void handleBottomNavigationSelections(BottomNavigationView bottomNavigationView, NavController navController) {
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            int itemId = item.getItemId();
//
//            if (itemId == R.id.homeFragment) {
//                navController.navigate(R.id.homeFragment);
//                return true;
//            } else if (itemId == R.id.createFragment) {
//                navController.navigate(R.id.createFragment);
//                return true;
//            } else if (itemId == R.id.exploreFragment) {
//                navController.navigate(R.id.exploreFragment);
//                return true;
//            }
//            return false;
//        });
//    }
    public TimeConvertor getTimeConvertor() {
        return timeConvertor;
    }

    @Override
    public void onStartDateSelected(int year, int month, int day) {
        timeConvertor.setStartYear(year);
        timeConvertor.setStartMonth(month);
        timeConvertor.setStartDay(day);

        timeConvertor.setEndYear(year);
        timeConvertor.setEndMonth(month);
        timeConvertor.setEndDay(day);
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