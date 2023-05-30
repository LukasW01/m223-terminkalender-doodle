package dev.bene.doodle.model;

import dev.bene.doodle.MongoDB;

import org.bson.Document;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class Info {
    private String id_public;
    private String id_private;
    private String roomName;
    private String participantName;
    private String searchPublic;
    private String searchPrivate;
    private String date;
    private String from;
    private String to;
    private Room room;
    private List<People> participants;
    private String comment;
    private final MongoDB mongoDB;
    private final SimpleDateFormat dateFormatHHMM = new SimpleDateFormat("HH:mm");
    private final SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    public Info() {
        mongoDB = new MongoDB();
        participants = new ArrayList<>();

        placeholders();
    }

    public void placeholders() {
        date = dateFormatYYYYMMDD.format(new Date());
        from = dateFormatHHMM.format(new Date());
        to = dateFormatHHMM.format(new Date(new Date().getTime() + 180 * 60 * 1000));

        comment = "-";
        searchPrivate = "Private key";
        searchPublic = "Public key";

        try {
            KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
            Base64.Encoder enc = Base64.getEncoder();
            id_private = new String(enc.encode(keyPair.getPrivate().getEncoded()));
            id_public = new String(enc.encode(keyPair.getPublic().getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("RSA is not a know generator");
        }
    }

    public String getSearchPublic() {
        return searchPublic;
    }

    public void setSearchPublic(String searchPublic) {
        this.searchPublic = searchPublic;
    }

    public String getSearchPrivate() {
        return searchPrivate;
    }

    public void setSearchPrivate(String searchPrivate) {
        this.searchPrivate = searchPrivate;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
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

    public List<People> getParticipants() {
        return participants;
    }

    public void setParticipants(List<People> participants) {
        this.participants = participants;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Document toBson() {
        try {
            Document doc = new Document();
            doc.append("reservation", true);
            doc.append("id_public", id_public);
            doc.append("id_private", id_private);
            doc.append("date", date);
            doc.append("from", from);
            doc.append("to", to);
            doc.append("room", room != null ? room.toString() : "");
            doc.append("participants", participants.toString());
            doc.append("comment", comment);
        return doc;
        } catch (Exception e) {
            System.err.println("Error while creating BSON");
            return null;
        }
    }


    public boolean fromBSON(Document doc) {
        try {
            final String participantsString = doc.getString("participants");
            participants = Arrays.stream(participantsString.substring(1, participantsString.length() - 1).split(",")).map(People::new).collect(Collectors.toList());
            id_public = doc.getString("id_public");
            id_private = doc.getString("id_private");
            date = doc.getString("date");
            from = doc.getString("from");
            to = doc.getString("to");
            room = new Room(doc.getString("room"));
            comment = doc.getString("comment");
            return true;
        } catch (Exception e) {
            System.err.println("Error while parsing BSON");
            return false;
        }
    }
}
