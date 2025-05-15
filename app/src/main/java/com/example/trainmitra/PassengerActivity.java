package com.example.trainmitra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class PassengerActivity extends AppCompatActivity {

    EditText pnrEditText;
    Button submitPNRButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge (compatible with older APIs too)
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_passenger);

        // Apply padding to handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.passengerLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pnrEditText = findViewById(R.id.pnr_enter);
        submitPNRButton = findViewById(R.id.buttonSubmitPNR);

        submitPNRButton.setOnClickListener(view -> {
            String pnr = pnrEditText.getText().toString().trim();

            if (pnr.isEmpty()) {
                Toast.makeText(this, "Please enter your PNR number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pnr.matches("\\d{10}")) {
                Toast.makeText(this, "Invalid PNR. It must be a 10-digit number.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "PNR Submitted: " + pnr, Toast.LENGTH_SHORT).show();

            getSharedPreferences("TrainMitraPrefs", MODE_PRIVATE)
                    .edit()
                    .putString("pnr_number", pnr)
                    .apply();

            Intent intent = new Intent(PassengerActivity.this, HomePagePassenger.class);
            startActivity(intent);
        });
    }
}