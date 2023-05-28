package dev.bene.doodle.model;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Info {
    private Integer id;
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
        SimpleDateFormat dateFormatHHMM = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

        date = dateFormatYYYYMMDD.format(new Date());
        from = dateFormatHHMM.format(new Date());
        to = dateFormatHHMM.format(new Date(new Date().getTime() + 180 * 60 * 1000));

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

        id = (int) (Math.random() * 1000000);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        doc.append("id", id);
        doc.append("date", date);
        doc.append("from", from);
        doc.append("to", to);
        doc.append("rooms", rooms.toString());
        doc.append("participants", participants.toString());
        doc.append("comment", comment);
        return doc;
    }
}
