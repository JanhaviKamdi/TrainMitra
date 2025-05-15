package com.example.trainmitra.ui.crewinstructions;

public class CrewMessage {
    private String message;
    private long timestamp;

    // Constructor
    public CrewMessage(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();  // current time in milliseconds
    }

    // Optional constructor with timestamp (if you want to set manually)
    public CrewMessage(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}