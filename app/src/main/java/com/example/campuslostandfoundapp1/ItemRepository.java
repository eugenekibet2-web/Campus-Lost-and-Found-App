package com.example.campuslostandfoundapp1; // KEEP YOUR PACKAGE NAME

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private static final List<Item> itemList = new ArrayList<>();

    static {
        // Pre-loaded sample items so the list isn't empty during class demo
        itemList.add(new Item("Student ID Card", "Blue lanyard attached, Reg No. CS/2024", "Main Library 2nd Floor", "17/11/2026", "0712-345-678", "Documents", "Found"));
        itemList.add(new Item("Black Dell Charger", "65W USB-C charger left near socket", "Computer Lab B", "18/11/2026", "alex@campus.edu", "Electronics", "Lost"));
        itemList.add(new Item("Navy Blue Backpack", "Contains a physics notebook and water bottle", "Student Cafeteria", "16/11/2026", "0798-765-432", "Bags", "Found"));
    }

    public static List<Item> getItems() {
        return itemList;
    }

    public static void addItem(Item item) {
        itemList.add(0, item);
    }
}
