package com.example.trainmitra;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText nameInput, emailInput, passwordInput, idInput;
    RadioGroup roleGroup;
    Button registerButton;
    TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.input_name);
        emailInput = findViewById(R.id.input_email);
        passwordInput = findViewById(R.id.input_password);
        idInput = findViewById(R.id.input_id);
        roleGroup = findViewById(R.id.role_group);
        registerButton = findViewById(R.id.button_register);
        loginLink = findViewById(R.id.login_link);

        idInput.setVisibility(View.GONE); // Hide ID by default

        roleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_crew || checkedId == R.id.radio_tc) {
                idInput.setVisibility(View.VISIBLE);
            } else {
                idInput.setVisibility(View.GONE);
            }
        });

        registerButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String role = "";
            String id = idInput.getText().toString().trim();

            int selectedId = roleGroup.getCheckedRadioButtonId();
            if (selectedId == R.id.radio_passenger) role = "Passenger";
            else if (selectedId == R.id.radio_crew) role = "Crew";
            else if (selectedId == R.id.radio_tc) role = "TicketCollector";

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if ((role.equals("Crew") || role.equals("TicketCollector")) && id.isEmpty()) {
                Toast.makeText(this, "Please enter your ID", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Add Firebase/SQLite storing logic here

            Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();
            finish(); // or redirect to Login
        });

        loginLink.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        });
    }
}