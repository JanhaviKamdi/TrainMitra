package com.example.trainmitra.ui.reportissue;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.trainmitra.R;

public class ReportIssueFragment extends Fragment {

    private Spinner issueSpinner;
    private Button btnTakePhoto;
    private ImageButton btnTriggerAlert;
    private EditText etOtherIssue;
    private TextView tvAskIssue;
    private ImageView imageView;

    private ActivityResultLauncher<Intent> cameraLauncher;

    public ReportIssueFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_report_issue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        issueSpinner = view.findViewById(R.id.spinner_issue);
        btnTriggerAlert = view.findViewById(R.id.btn_trigger_alert);
        btnTakePhoto = view.findViewById(R.id.btn_upload_photo);
        etOtherIssue = view.findViewById(R.id.et_other_issue);
        tvAskIssue = view.findViewById(R.id.text_ask_issue);
        imageView = view.findViewById(R.id.img_preview);

        String[] issues = {"Fire", "Theft", "Harassment", "Medical", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, issues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        issueSpinner.setAdapter(adapter);

        issueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etOtherIssue.setVisibility(issues[position].equals("Other") ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnTriggerAlert.setOnClickListener(v -> {
            String selectedIssue = issueSpinner.getSelectedItem().toString();
            if (selectedIssue.equals("Other")) {
                String customIssue = etOtherIssue.getText().toString().trim();
                if (customIssue.isEmpty()) {
                    Toast.makeText(requireContext(), "Please describe your issue", Toast.LENGTH_SHORT).show();
                    return;
                }
                selectedIssue = customIssue;
            }

            // TODO: Replace this with backend alert logic
            Toast.makeText(requireContext(), "Alert Triggered for: " + selectedIssue, Toast.LENGTH_SHORT).show();
        });

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                            imageView.setVisibility(View.VISIBLE);
                            Toast.makeText(requireContext(), "Photo Captured", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        btnTakePhoto.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(cameraIntent);
        });
    }

}