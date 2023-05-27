package dev.bene.doodle.conroller;

import dev.bene.doodle.model.Info;
import dev.bene.doodle.MongoDB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    public String submit() {
        mongoDB.setCollection(info.toBson());
        return "index.xhtml?faces-redirect=true";
    }

    public String delete(Integer id) {
        mongoDB.removeCollection(info.toBson(), id);
        return "index.xhtml?faces-redirect=true&delete=true";
    }

    public String update(Integer id) {
        mongoDB.updateCollection(info.toBson(), id);
        return "view.xhtml?faces-redirect=true&edit=true";
    }

    public String redirToEdit() {
        return "view.xhtml?faces-redirect=true&edit=true";
    }

    public String redirToIndex() {
        return "index.xhtml?faces-redirect=true";
    }

    public String redirToView() {
        return "view.xhtml?faces-redirect=true";
    }

    public String redirToAdd() {
        return "add.xhtml?faces-redirect=true";
    }

}
