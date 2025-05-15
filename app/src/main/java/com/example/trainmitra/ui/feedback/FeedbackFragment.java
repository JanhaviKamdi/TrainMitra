package com.example.trainmitra.ui.feedback;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.trainmitra.R;


public class FeedbackFragment extends Fragment {
    EditText etName, etFeedback;
    RatingBar ratingBar;
    Button btnSubmit;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        etName = view.findViewById(R.id.etName);
        etFeedback = view.findViewById(R.id.etFeedback);
        ratingBar = view.findViewById(R.id.ratingBar);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String feedback = etFeedback.getText().toString();
            float rating = ratingBar.getRating();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(feedback)) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // For now, just show confirmation (you can save to DB later)
                Toast.makeText(getContext(),
                        "Thank you, " + name + "! Feedback submitted. Rating: " + rating,
                        Toast.LENGTH_LONG).show();

                // Clear fields
                etName.setText("");
                etFeedback.setText("");
                ratingBar.setRating(0);
            }
        });

        return view;
    }

}
