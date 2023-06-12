package dev.bene.doodle.conroller;

import dev.bene.doodle.MongoDB;
import dev.bene.doodle.model.Info;
import dev.bene.doodle.model.People;
import dev.bene.doodle.model.Room;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class InfoBean {
    private final MongoDB mongoDB;
    private Info info;

    public InfoBean() {
        mongoDB = new MongoDB();
        info = new Info();
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        FindIterable<Document> roomNames = mongoDB.getCollectionRooms();
        for (Document roomName : roomNames) {
            String roomNameString = roomName.getString("roomName");
            rooms.add(new Room(roomNameString));
        }
        return rooms;
    }

    public List<People> getAllParticipants() {
        List<People> participants = new ArrayList<>();
        FindIterable<Document> participantNames = mongoDB.getCollectionParticipant();
        for (Document participantName : participantNames) {
            String participantNameString = participantName.getString("participantName");
            participants.add(new People(participantNameString));
        }
        return participants;
    }

    public void submitRoom() {
        mongoDB.setRoom(info.getRoomName());
    }

    public void submitParticipant() {
        mongoDB.setParticipant(info.getParticipantName());
    }

}
