package dev.bene.doodle.model;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class Info {
    private String date;
    private String from;
    private String to;
    private ArrayList<Room> rooms;
    private ArrayList<People> participants;
    private String comment;


    public Info(String date, String from, String to, ArrayList<Room> rooms, String comment, ArrayList<People> participants) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.rooms = rooms;
        this.participants = participants;
        this.comment = comment;
    }

    public Info() {
        date = new Date().toString();
        from = new Date().toString();
        to = new Date().toString();

        rooms = new ArrayList<>();
        participants = new ArrayList<>();

        String[] roomNames = {"EG 103", "EG 106", "OG 134", "OG 138"};
        for (String roomName : roomNames) {
            rooms.add(new Room(roomName));
        }

        String[] participantNames = {
                "Lukas", "Elin", "Jessica", "Annabel", "Ale", "Ada", "Ari", "Aya", "Aywin",
                "Emilio", "Aleks", "Kieran", "Maor", "Martin", "Nikola", "Nils", "Ryan", "Shay", "Usman"
        };
        for (String participantName : participantNames) {
            participants.add(new People(participantName));
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
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

    @Override
    public String toString(){
        return date + " " + from + " " + to + " " + rooms.toString() + " " + participants.toString() + " " + comment;
    }

    public Document toBson() {
        Document doc = new Document();
        doc.append("date", date);
        doc.append("from", from);
        doc.append("to", to);
        doc.append("rooms", rooms.toString());
        doc.append("participants", participants.toString());
        doc.append("comment", comment);
        return doc;
    }
}
