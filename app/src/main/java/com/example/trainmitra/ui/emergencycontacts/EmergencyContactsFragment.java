package com.example.trainmitra.ui.emergencycontacts;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.trainmitra.R;


public class EmergencyContactsFragment extends Fragment {

    public EmergencyContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emergency_contacts, container,false);
    }
}
