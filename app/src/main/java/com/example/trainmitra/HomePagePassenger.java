package com.example.trainmitra;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.trainmitra.databinding.ActivityHomePagePassengerBinding;

public class HomePagePassenger extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomePagePassengerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String pnr = getSharedPreferences("TrainMitraPrefs", MODE_PRIVATE)
                .getString("pnr_number", "Not Available");

// Now use pnr anywhere, or show it:
        Toast.makeText(this, "Welcome! PNR: " + pnr, Toast.LENGTH_SHORT).show();

        binding = ActivityHomePagePassengerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        final DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Set up NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page_passenger);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_report_issue,
                R.id.nav_track_train,
                R.id.nav_crew_instructions,
                R.id.nav_ticket_info,
                R.id.nav_emergency_contacts,
                R.id.nav_feedback
        ).setOpenableLayout(drawer).build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_report_issue) {
                navController.navigate(R.id.nav_report_issue);
            } else if (id == R.id.nav_track_train) {
                navController.navigate(R.id.nav_track_train);
            } else if (id == R.id.nav_crew_instructions) {
                navController.navigate(R.id.nav_crew_instructions);
            } else if (id == R.id.nav_ticket_info) {
                navController.navigate(R.id.nav_ticket_info);
            } else if (id == R.id.nav_emergency_contacts) {
                navController.navigate(R.id.nav_emergency_contacts);
            } else if (id == R.id.nav_feedback) {
                navController.navigate(R.id.nav_feedback);
            } else if (id == R.id.nav_logout) {
                Toast.makeText(this, "Logout selected", Toast.LENGTH_SHORT).show();
                finish(); // or navigate to login activity
                drawer.closeDrawers();
                return true; // return early to skip navigating
            }
            drawer.closeDrawers();
            return true;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_page_passenger, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page_passenger);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}