package com.example.calenderapplication;

import androidx.annotation.NonNull;

public class Event {
    private final String date;
    private final String title;
    private final String description;

    public Event(String date, String title, String description){
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public String getDate(){
        return date;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    @NonNull
    @Override
    public String toString() {
        return "Event{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
