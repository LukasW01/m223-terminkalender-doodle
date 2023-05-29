package dev.bene.doodle.conroller;

import com.mongodb.client.FindIterable;
import dev.bene.doodle.model.Info;
import dev.bene.doodle.MongoDB;
import org.bson.Document;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

    public FindIterable<Document> getCollectionByPublicID(String id) {
        return mongoDB.getReservationByPublicID(id);
    }

    public String submit() {
        mongoDB.setCollection(info.toBson());
        return "index.xhtml?faces-redirect=true";
    }

    public String delete(Integer id) {
        mongoDB.removeCollection(info.toBson(), id);
        return "index.xhtml?faces-redirect=true";
    }

    public String update(Integer id) {
        mongoDB.updateCollection(info.toBson(), id);
        return "index.xhtml?faces-redirect=true";
    }

}
