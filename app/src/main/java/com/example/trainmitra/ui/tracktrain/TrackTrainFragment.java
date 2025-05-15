package com.example.trainmitra.ui.tracktrain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trainmitra.R;

public class TrackTrainFragment extends Fragment {

    private EditText pnrInput;
    private TextView statusOutput;
    private Button checkStatusButton;

    public TrackTrainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_track_train, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pnrInput = view.findViewById(R.id.pnrInput);
        statusOutput = view.findViewById(R.id.statusOutput);
        checkStatusButton = view.findViewById(R.id.checkStatusButton);

        checkStatusButton.setOnClickListener(v -> {
            String pnr = pnrInput.getText().toString().trim();
            if (pnr.isEmpty()) {
                statusOutput.setText("Please enter a valid PNR.");
            } else {
                // TODO: Replace with real train status logic or API
                statusOutput.setText("Fetching status for PNR: " + pnr);
            }
        });
    }

}
