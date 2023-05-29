package dev.bene.doodle.conroller;

import com.mongodb.client.FindIterable;
import dev.bene.doodle.model.Info;
import dev.bene.doodle.MongoDB;
import dev.bene.doodle.model.Room;
import org.bson.Document;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ReservationBean {

    private Info info;
    private final MongoDB mongoDB;


    public ReservationBean() {
        info = new Info();
        mongoDB = new MongoDB();
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public FindIterable<Document> getCollectionEvents() {
        return mongoDB.getCollectionEvents();
    }

    public Info getCollectionByPublicID(String id) {
        final Info info = new Info();
        info.fromBSON(mongoDB.getReservationByPublicID(id));

        return info;
    }

    public Info getCollectionByPrivateID(String id) {
        final Info info = new Info();
        info.fromBSON(mongoDB.getReservationByPrivateID(id));

        return info;
    }

    public String submit() {
        mongoDB.setCollection(info.toBson());
        return "index.xhtml";
    }

    public String delete(String id) {
        mongoDB.removeCollection(id);
        return "index.xhtml?faces-redirect=true";
    }

    public String update(String id) {
        mongoDB.updateCollection(info.toBson(), id);
        return "edit.xhtml?faces-redirect=true";
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

    public String edit(String id) {
        Document doc = mongoDB.getReservationByPrivateID(id);
        info.fromBSON(doc);
        return "edit.xhtml?faces-redirect=true";
    }
}
