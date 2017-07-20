package com.scholefield.lee.databasehelperexample;

/**
 * A simple model
 */
public class Event {

    private int id;
    private final String title;
    private final String type;
    private final long timestamp; // unix epoch

    public Event(String title, String type, long timestamp) {
        this.title = title;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Event(int id, String title, String type, long timestamp) {
        this(title, type, timestamp);
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
