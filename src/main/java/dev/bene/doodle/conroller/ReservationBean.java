package dev.bene.doodle.conroller;

import dev.bene.doodle.model.Info;
import dev.bene.doodle.MongoDB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;

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

    public String submit() {
        mongoDB.setCollection(info.toBson());
        return "index.xhtml?faces-redirect=true";
    }
}
