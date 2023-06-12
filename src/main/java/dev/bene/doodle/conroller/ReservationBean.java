package dev.bene.doodle.conroller;

import dev.bene.doodle.model.Info;
import dev.bene.doodle.MongoDB;

import org.bson.Document;
import com.mongodb.client.FindIterable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
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

    public String submit() {
        if (info.toBson() == null) {
            return "error.xhtml?faces-redirect=true";
        } else {
            mongoDB.setCollection(info.toBson());
            return "secrets.xhtml";
        }
    }

    public String add() {
        info = new Info();
        return "add.xhtml?faces-redirect=true";
    }

    public String delete(String id) {
        mongoDB.removeCollection(id);
        return "index.xhtml?faces-redirect=true";
    }

    public String update(String id) {
        if (info.toBson() == null) {
            return "error.xhtml?faces-redirect=true";
        } else {
            mongoDB.updateCollection(info.toBson(), id);
            return "edit.xhtml?faces-redirect=true";
        }
    }

    public String editPrivate(String id) {
        info = new Info();

        if (!info.fromBSON(mongoDB.getReservationByPrivateID(id))) {
            return "error.xhtml?faces-redirect=true";
        } else {
            Document doc = mongoDB.getReservationByPrivateID(id);
            info.fromBSON(doc);
            return "edit.xhtml?faces-redirect=true";
        }
    }

    public String viewPublic(String id) {
        info = new Info();

       if (!info.fromBSON(mongoDB.getReservationByPublicID(id))) {
           return "error.xhtml?faces-redirect=true";
       } else {
           Document doc = mongoDB.getReservationByPublicID(id);
           info.fromBSON(doc);
           return "view.xhtml?faces-redirect=true";
       }
    }
}
