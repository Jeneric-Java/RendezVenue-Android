package com.jeneric.eventappfrontend.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.databinding.ActivityMainBinding;
import com.jeneric.eventappfrontend.ui.create.CreateFragment;
import com.jeneric.eventappfrontend.ui.explore.ExploreFragment;
import com.jeneric.eventappfrontend.ui.home.HomeFragment;
import com.jeneric.eventappfrontend.ui.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());

        binding.setClickHandler(new MainActivityClickHandlers(this));

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.menu_search) {
                replaceFragment(new ExploreFragment());
            } else if (item.getItemId() == R.id.menu_add) {
                replaceFragment(new CreateFragment());
            }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}