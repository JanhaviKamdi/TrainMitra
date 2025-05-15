package com.example.trainmitra.ui.crewinstructions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrewMessagesAdapter extends RecyclerView.Adapter<CrewMessagesAdapter.MessageViewHolder> {

    private List<CrewMessage> messagesList;

    public CrewMessagesAdapter(List<CrewMessage> messagesList) {
        this.messagesList = messagesList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        CrewMessage message = messagesList.get(position);
        holder.messageTextView.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}