package com.example.trainmitra.ui.crewinstructions;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.example.trainmitra.R;

public class CrewInstructionsFragment extends Fragment {

    private RecyclerView recyclerView;
    private com.example.trainmitra.ui.crewinstructions.CrewMessagesAdapter adapter;
    private List<CrewMessage> messageList;

    public CrewInstructionsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crew_instructions, container, false);

        recyclerView = view.findViewById(R.id.rvCrewInstructions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // For now, create dummy messages
        messageList = new ArrayList<>();
        messageList.add(new CrewMessage("Welcome aboard! Please follow safety instructions."));
        messageList.add(new CrewMessage("Maintain social distancing during your journey."));
        messageList.add(new CrewMessage("In case of emergency, contact the crew immediately."));

        adapter = new CrewMessagesAdapter(messageList);
        recyclerView.setAdapter(adapter);

        return view;
    }

}