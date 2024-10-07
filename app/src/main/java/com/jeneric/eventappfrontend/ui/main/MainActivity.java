package com.jeneric.eventappfrontend.ui.main;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

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
import com.jeneric.eventappfrontend.service.location.utilities.LocationParser;
import com.jeneric.eventappfrontend.ui.create.CreateFragment;
import com.jeneric.eventappfrontend.ui.create.dialogues.DateTimePickerListener;
import com.jeneric.eventappfrontend.ui.explore.ExploreFragment;
import com.jeneric.eventappfrontend.ui.home.HomeFragment;
import com.jeneric.eventappfrontend.ui.settings.SettingsFragment;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity implements DateTimePickerListener {

    ActivityMainBinding binding;
    MainActivityClickHandlers mainActivityClickHandlers;

    TimeConvertor timeConvertor = new TimeConvertor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        try {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            assert location != null;
            LocationParser.toGeoHashEnc(location.getLatitude(), location.getLongitude());
        } catch (SecurityException | IllegalArgumentException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException | IOException | BadPaddingException | InvalidKeyException e) {
            Log.d("GPS Status", "GPS Disabled");
        }


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

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }
    };
}