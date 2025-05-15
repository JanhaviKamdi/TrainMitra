package com.example.trainmitra;

import android.os.Bundle;
import android.view.View;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editId, editEmail, editPassword;
    Button btnPassenger, btnCrew, btnTC, btnLogin;
    private String selectedRole = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize buttons
        btnPassenger = findViewById(R.id.butPassenger);
        btnCrew = findViewById(R.id.butCrew);
        btnTC = findViewById(R.id.butTC);
        btnLogin = findViewById(R.id.butLogin);
        editEmail = findViewById(R.id.e_mail);
        editPassword = findViewById(R.id.password);
        editId = findViewById(R.id.crew_tc_id);


        // Handle role selection
        View.OnClickListener roleClickListener = view -> {
            resetButtonStyles();
            view.setBackgroundColor(Color.parseColor("#6200EE"));
            ((Button) view).setTextColor(Color.WHITE);

            int id = view.getId();
            if (id == R.id.butPassenger) {
                selectedRole = "Passenger";
                editId.setVisibility(View.GONE); // Hide ID field
            } else if (id == R.id.butCrew) {
                selectedRole = "Crew";
                editId.setVisibility(View.VISIBLE); // Show ID field
            } else if (id == R.id.butTC) {
                selectedRole = "TicketCollector";
                editId.setVisibility(View.VISIBLE); // Show ID field
            }
        };

        btnPassenger.setOnClickListener(roleClickListener);
        btnCrew.setOnClickListener(roleClickListener);
        btnTC.setOnClickListener(roleClickListener); // fixed!

        // Handle login
        btnLogin.setOnClickListener(view -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();
            String idInput = editId.getText().toString().trim();

            if (selectedRole == null) {
                Toast.makeText(this, "Please select a role before login", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
                return;
            }

            if ((selectedRole.equals("Crew") || selectedRole.equals("TicketCollector")) && idInput.isEmpty()) {
                Toast.makeText(this, "Please enter your ID", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent;
            switch (selectedRole) {
                case "Passenger":
                    intent = new Intent(MainActivity.this, PassengerActivity.class);
                    break;
                case "Crew":
                    intent = new Intent(MainActivity.this, CrewActivity.class);
                    break;
                case "TicketCollector":
                    intent = new Intent(MainActivity.this, TicketCollectorActivity.class);
                    break;
                default:
                    Toast.makeText(this, "Invalid role", Toast.LENGTH_SHORT).show();
                    return;
            }

            startActivity(intent);
        });
        TextView registerText = findViewById(R.id.register_text);
        registerText.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void resetButtonStyles() {
        int defaultColor = Color.parseColor("#D1C4E9");
        int defaultTextColor = Color.BLACK;

        btnPassenger.setBackgroundColor(defaultColor);
        btnCrew.setBackgroundColor(defaultColor);
        btnTC.setBackgroundColor(defaultColor);

        btnPassenger.setTextColor(defaultTextColor);
        btnCrew.setTextColor(defaultTextColor);
        btnTC.setTextColor(defaultTextColor);
    }
}