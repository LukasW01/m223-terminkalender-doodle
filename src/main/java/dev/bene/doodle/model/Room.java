package dev.bene.doodle.model;

public class Room {
    private String name;
    private String location;
    private int capacity;
    private int number;

    public Room(String name, String location, int capacity, int number) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.number = number;
    }

    public Room() {
        this.name = "EG 103";
        this.location = "Benedict";
        this.capacity = 20;
        this.number = 103;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
