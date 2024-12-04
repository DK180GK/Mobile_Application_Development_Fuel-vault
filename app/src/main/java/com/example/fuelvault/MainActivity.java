package com.example.fuelvault;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottom_navigation;
    private FrameLayout frameLayout;
    private Fragment currentFragment;

    // Method to load the selected fragment into the FrameLayout
    private void loadFragment(Fragment fragment) {

        if (currentFragment == null || !currentFragment.getClass().equals(fragment.getClass())) {
            // If the fragment is different, load the new fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Set custom animations for fragment transitions
            fragmentTransaction.setCustomAnimations(
                    R.anim.fragment_enter, // Animation when fragment enters
                    R.anim.fragment_exit // Animation when current fragment exits
            );

            // Replace the current fragment with the new one
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.addToBackStack(null);  // Optional: add to back stack if you want to manage back button
            fragmentTransaction.commit();

            currentFragment = fragment;  // Update currentFragment to the newly loaded one
        }
        // Check if the fragment is already loaded
        if (currentFragment == null || !currentFragment.getClass().equals(fragment.getClass())) {
            // If the fragment is different, load the new fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragment);  // Replace the current fragment
            fragmentTransaction.addToBackStack(null);  // Optional: add to back stack if you want to manage back button
            fragmentTransaction.commit();
            currentFragment = fragment;  // Update currentFragment to the newly loaded one
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views after setContentView
        bottom_navigation = findViewById(R.id.bottom_navigation);
        frameLayout = findViewById(R.id.frameLayout);

        // Set the default fragment when the app is first opened
        if (savedInstanceState == null) {
            // Load the default fragment
            loadFragment(new MileageCalculator());
        }
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    loadFragment(new MileageCalculator());
                } else if (itemId == R.id.fuel) {
                    loadFragment(new FuelPrice());
                } else if(itemId == R.id.history) {
                    loadFragment(new Report());
                }
                else {
                    loadFragment(new DistanceCalculator());
                }

                return true;
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            getWindow().getInsetsController().hide(android.view.WindowInsets.Type.systemBars());
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }

        findViewById(R.id.main).setOnApplyWindowInsetsListener((v, insets) -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    v.setPadding(insets.getInsets(WindowInsets.Type.systemBars()).left,
                            insets.getInsets(WindowInsets.Type.systemBars()).top,
                            insets.getInsets(WindowInsets.Type.systemBars()).right,
                            insets.getInsets(WindowInsets.Type.systemBars()).bottom);
                }
            }
            return insets;
        });
    }

    }


