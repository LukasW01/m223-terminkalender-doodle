package dev.bene.doodle.model;

import java.util.ArrayList;
import java.util.Date;

public class Info {
    private Date date;
    private Date from;
    private Date to;
    private ArrayList<Room> rooms;
    private ArrayList<People> participants;
    private String comment;


    public Info(Date date, Date from, Date to, ArrayList<Room> rooms, String comment, ArrayList<People> participants) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.rooms = rooms;
        this.participants = participants;
        this.comment = comment;
    }

    public Info() {
        date = new Date();
        from = new Date();
        to = new Date();
        rooms = new ArrayList<>();
        participants = new ArrayList<>();
        People people = new People();
        Room room = new Room();

        rooms.add(new Room("EG 103"));
        rooms.add(new Room("EG 106"));
        rooms.add(new Room("OG 134"));
        rooms.add(new Room("OG 138"));

        participants.add(new People("Lukas"));
        participants.add(new People("Elin"));
        participants.add(new People("Jessica"));
        participants.add(new People("Annabel"));
        participants.add(new People("Ale"));
        participants.add(new People("Ada"));
        participants.add(new People("Ari"));
        participants.add(new People("Aya"));
        participants.add(new People("Aywin"));
        participants.add(new People("Emilio"));
        participants.add(new People("Aleks"));
        participants.add(new People("Kieran"));
        participants.add(new People("Maor"));
        participants.add(new People("Martin"));
        participants.add(new People("Nikola"));
        participants.add(new People("Nils"));
        participants.add(new People("Ryan"));
        participants.add(new People("Shay"));
        participants.add(new People("Usman"));
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
}