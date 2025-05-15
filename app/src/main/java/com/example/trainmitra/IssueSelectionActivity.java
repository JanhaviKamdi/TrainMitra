package com.example.trainmitra;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;

public class IssueSelectionActivity extends AppCompatActivity {

    private Spinner issueSpinner;
    private Button btnUploadPhoto;
    private ImageButton btnTriggerAlert;
    private EditText etOtherIssue;
    private TextView tvAskIssue;
    private ImageView imageView;

    private Uri selectedImageUri = null;

    // Replaces deprecated startActivityForResult
    private final ActivityResultLauncher<Intent> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        imageView.setImageURI(selectedImageUri);
                        imageView.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "Photo Selected", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_selection);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.issue_constraintlayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        issueSpinner = findViewById(R.id.spinner_issue);
        btnTriggerAlert = findViewById(R.id.btn_trigger_alert);
        btnUploadPhoto = findViewById(R.id.btn_upload_photo);
        etOtherIssue = findViewById(R.id.et_other_issue);
        tvAskIssue = findViewById(R.id.text_ask_issue);
        imageView = findViewById(R.id.img_preview);

        String[] issues = {"Fire", "Theft", "Harassment", "Medical", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, issues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        issueSpinner.setAdapter(adapter);

        issueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedIssue = parent.getItemAtPosition(position).toString();
                etOtherIssue.setVisibility(selectedIssue.equals("Other") ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnTriggerAlert.setOnClickListener(v -> {
            String selectedIssue = issueSpinner.getSelectedItem().toString();
            if (selectedIssue.equals("Other")) {
                String customIssue = etOtherIssue.getText().toString().trim();
                if (customIssue.isEmpty()) {
                    Toast.makeText(this, "Please describe your issue", Toast.LENGTH_SHORT).show();
                    return;
                }
                selectedIssue = customIssue;
            }

            // You can add code to send this alert data to your backend
            Toast.makeText(this, "Alert Triggered for: " + selectedIssue, Toast.LENGTH_SHORT).show();
        });

        btnUploadPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);
        });
    }
}