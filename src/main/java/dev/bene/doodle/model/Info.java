package dev.bene.doodle.model;

import dev.bene.doodle.MongoDB;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Info {
    private String id_public;
    private String id_private;
    private String date;
    private String from;
    private String to;
    private ArrayList<Room> rooms;
    private ArrayList<People> participants;
    private String comment;
    private final MongoDB mongoDB;
    private final SimpleDateFormat dateFormatHHMM = new SimpleDateFormat("HH:mm");
    private final SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    public Info() {
        mongoDB = new MongoDB();
        rooms = new ArrayList<>();
        participants = new ArrayList<>();

        placeholders();
    }

    public void placeholders() {
        date = dateFormatYYYYMMDD.format(new Date());
        from = dateFormatHHMM.format(new Date());
        to = dateFormatHHMM.format(new Date(new Date().getTime() + 180 * 60 * 1000));

        FindIterable<Document> roomNames = mongoDB.getCollectionRooms();
        for (Document roomName : roomNames) {
            String roomNameString = roomName.getString("roomName");
            rooms.add(new Room(roomNameString));
        }

        FindIterable<Document> participantNames = mongoDB.getCollectionParticipant();
        for (Document participantName : participantNames) {
            String participantNameString = participantName.getString("participantName");
            participants.add(new People(participantNameString));
        }

        comment = "-";

        id_public = UUID.randomUUID().toString();
        id_private = UUID.randomUUID().toString();
    }

    public String getId_public() {
        return id_public;
    }

    public void setId_public(String id_public) {
        this.id_public = id_public;
    }

    public String getId_private() {
        return id_private;
    }

    public void setId_private(String id_private) {
        this.id_private = id_private;
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

    public Document toBson() {
        Document doc = new Document();
        doc.append("reservation", true);
        doc.append("id_public", id_public);
        doc.append("id_private", id_private);
        doc.append("date", date);
        doc.append("from", from);
        doc.append("to", to);
        doc.append("rooms", rooms.toString());
        doc.append("participants", participants.toString());
        doc.append("comment", comment);
        return doc;
    }
}
