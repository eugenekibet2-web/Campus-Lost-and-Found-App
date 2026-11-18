package com.example.campuslostandfoundapp1; // KEEP YOUR PACKAGE NAME

public class Item {
    private String name;
    private String description;
    private String location;
    private String date;
    private String contact;
    private String category;
    private String status; // "Lost", "Found", or "Claimed/Returned"

    public Item(String name, String description, String location, String date, String contact, String category, String status) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.contact = contact;
        this.category = category;
        this.status = status;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public String getContact() { return contact; }
    public String getCategory() { return category; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}