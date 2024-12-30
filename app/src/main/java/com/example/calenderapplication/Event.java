package com.example.calenderapplication;

public class Event {
    private String date;
    private String title;
    private String description;

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

    public void setDate(String date){
        this.date = date;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
