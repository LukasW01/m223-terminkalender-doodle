package dev.bene.doodle.model;

import java.util.ArrayList;
import java.util.Date;

import dev.bene.doodle.model.People;
import dev.bene.doodle.model.Room;

public class Reservation {
    private Date date;
    private Date from;
    private Date to;
    private ArrayList<Room> rooms;
    private ArrayList<People> participants;
    private String comment;
    private People people;
    private Room room;


    public Reservation(Date date, Date from, Date to, ArrayList<Room> rooms, String comment, ArrayList<People> participants) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.rooms = rooms;
        this.participants = participants;
        this.comment = comment;
    }

    public Reservation() {
        date = new Date();
        from = new Date();
        to = new Date();
        rooms = new ArrayList<>();
        participants = new ArrayList<>();

        rooms.add(new Room("EG 103", "Benedict - Altstetten", 20, 1));
        rooms.add(new Room("EG 106", "Benedict - Altstetten", 20, 2));
        rooms.add(new Room("OG 134", "Benedict - Altstetten", 20, 3));
        rooms.add(new Room("OG 138", "Benedict - Altstetten", 20, 4));
    }

    public Date getDate() {
        return date;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<People> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<People> participants) {
        this.participants = participants;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addRoom(Room room) {
    	this.rooms.add(room);
    }
}
