package com.jeneric.eventappfrontend.ui.main;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.ActivityMainBinding;
import com.jeneric.eventappfrontend.model.TimeWizard;
import com.jeneric.eventappfrontend.service.location.utilities.LocationParser;
import com.jeneric.eventappfrontend.ui.create.dialogues.DateTimePickerListener;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

import android.Manifest;

public class MainActivity extends AppCompatActivity implements DateTimePickerListener {

    ActivityMainBinding binding;
    MainActivityClickHandlers mainActivityClickHandlers;

    String userLocation;

    TimeWizard timeWizard = new TimeWizard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });

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
    public TimeWizard getTimeConvertor() {
        return timeWizard;
    }

    public String getUserLocation() { return userLocation;}

    @Override
    public void onStartDateSelected(int year, int month, int day) {
        timeWizard.setStartYear(year);
        timeWizard.setStartMonth(month);
        timeWizard.setStartDay(day);

        timeWizard.setEndYear(year);
        timeWizard.setEndMonth(month);
        timeWizard.setEndDay(day);
    }

    @Override
    public void onStartTimeSelected(int hour, int minute) {
        timeWizard.setStartHour(hour);
        timeWizard.setStartMinute(minute);
    }

    @Override
    public void onEndTimeSelected(int hour, int minute) {
        timeWizard.setEndHour(hour);
        timeWizard.setEndMinute(minute);
    }

    private String encryptUSL(double latitude, double longitude) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        InputStream inputStreamPSW = getAssets().open("password.txt");

        String PSWString = new String(inputStreamPSW.readAllBytes(), StandardCharsets.UTF_8);

        InputStream inputStreamIVPS = getAssets().open("iv.txt");

        String IVPSString = new String(inputStreamIVPS.readAllBytes(), StandardCharsets.UTF_8);

        String[] byteStrMat = IVPSString.split("\\x20");

        byte[] iv = new byte[16];

        for (int i = 0; i < byteStrMat.length; i++) {
            iv[i] = Byte.parseByte(byteStrMat[i]);
        }

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        return LocationParser.toGeoHashEnc(latitude, longitude, PSWString.substring(0, 24), PSWString.substring(25), ivParameterSpec);

    }


    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_FINE_LOCATION, false);
                        Boolean coarseLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_COARSE_LOCATION,false);
                        if (fineLocationGranted != null && fineLocationGranted) {
                            // Precise location access granted.
                            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                            try {
                                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                assert location != null;
                                String geoHashEnc = encryptUSL(location.getLatitude(), location.getLongitude());
                                this.userLocation = geoHashEnc;
                                Log.d("GPS Status", "Precise GPS Enabled");
                            } catch (SecurityException | IllegalArgumentException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException | IOException | BadPaddingException | InvalidKeyException e) {
                                Log.d("AES Exception", e.toString());
                                Log.d("GPS Status", "Precise GPS Failure");
                            }
                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
                            // Only approximate location access granted.
                            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                            try {
                                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                assert location != null;
                                String geoHashEnc = encryptUSL(location.getLatitude(), location.getLongitude());
                                this.userLocation = geoHashEnc;
                                Log.d("GPS Status", "Approximate GPS Enabled");
                            } catch (SecurityException | IllegalArgumentException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException | IOException | BadPaddingException | InvalidKeyException e) {
                                Log.d("AES Exception", e.toString());
                                Log.d("GPS Status", "Approximate GPS Failed");
                            }
                        } else {
                            // No location access granted.
                            try {
                                String geoHashEnc = encryptUSL(53.4720116514883, -2.237826735345036);
                                this.userLocation = geoHashEnc;
                            } catch (SecurityException | IllegalArgumentException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | NoSuchAlgorithmException | InvalidKeySpecException | IOException | BadPaddingException | InvalidKeyException e) {
                                Log.d("GPS Status", "GPS Denied");
                            }
                        }
                    }
            );


}