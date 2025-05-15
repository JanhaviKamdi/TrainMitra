package com.example.trainmitra.ui.tickectinfo;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trainmitra.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;

public class TicketInfoFragment extends Fragment {

    private TextView ticketInfoText;
    private String ticketContent = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket_info, container, false);
        ticketInfoText = view.findViewById(R.id.ticket_info_text);

        Button downloadBtn = view.findViewById(R.id.btnDownloadPDF);

        // Get PNR from SharedPreferences
        SharedPreferences prefs = requireActivity().getSharedPreferences("TrainMitraPrefs", getContext().MODE_PRIVATE);
        String pnr = prefs.getString("pnr_number", "N/A");

        // Firebase reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("tickets").child(pnr);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String trainNo = snapshot.child("trainNo").getValue(String.class);
                    String from = snapshot.child("from").getValue(String.class);
                    String to = snapshot.child("to").getValue(String.class);
                    String date = snapshot.child("date").getValue(String.class);
                    String seat = snapshot.child("seat").getValue(String.class);

                    ticketContent = "PNR: " + pnr + "\n"
                            + "Name: " + name + "\n"
                            + "Train No: " + trainNo + "\n"
                            + "From: " + from + "\n"
                            + "To: " + to + "\n"
                            + "Date: " + date + "\n"
                            + "Seat: " + seat;

                    ticketInfoText.setText(ticketContent);
                } else {
                    ticketContent = "No ticket info found for PNR: " + pnr;
                    ticketInfoText.setText(ticketContent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                ticketContent = "Error fetching data.";
                ticketInfoText.setText(ticketContent);
            }
        });

        // PDF button
        downloadBtn.setOnClickListener(v -> generatePDF(ticketContent));

        return view;
    }

    private void generatePDF(String content) {
        try {
            File path = new File(requireContext().getExternalFilesDir(null), "TrainMitra");
            if (!path.exists()) path.mkdirs();

            File file = new File(path, "ticket_info.pdf");
            FileOutputStream outputStream = new FileOutputStream(file);

            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();

            Paint paint = new Paint();
            paint.setTextSize(12);

            String[] lines = content.split("\n");
            int y = 25;
            for (String line : lines) {
                canvas.drawText(line, 10, y, paint);
                y += 20;
            }

            document.finishPage(page);
            document.writeTo(outputStream);
            document.close();

            Toast.makeText(getContext(), "PDF saved in: " + file.getPath(), Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getContext(), "PDF Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}