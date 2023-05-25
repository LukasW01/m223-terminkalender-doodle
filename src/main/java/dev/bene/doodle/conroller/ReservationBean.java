package dev.bene.doodle.conroller;

import dev.bene.doodle.model.People;
import dev.bene.doodle.model.Reservation;
import dev.bene.doodle.model.Room;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;

@ManagedBean
@SessionScoped
public class ReservationBean {

    Reservation reservation;

    public ReservationBean() {
        reservation = new Reservation();
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void setDate(Date date) {
        reservation.setDate(date);
    }

    public Date getDate() {
        return reservation.getDate();
    }

    public void setFrom(Date from) {
        reservation.setFrom(from);
    }

    public Date getFrom() {
        return reservation.getFrom();
    }

    public void setTo(Date to) {
        reservation.setTo(to);
    }

    public Date getTo() {
        return reservation.getTo();
    }

    public void setComment(String comment) {
        reservation.setComment(comment);
    }

    public String getComment() {
        return reservation.getComment();
    }

    public void setRooms(ArrayList<Room> rooms) {
        reservation.setRooms(rooms);
    }

    public ArrayList<Room> getRooms() {
        return reservation.getRooms();
    }

    public void setParticipants(ArrayList<People> participants) {
        reservation.setParticipants(participants);
    }

    public ArrayList<People> getParticipants() {
        return reservation.getParticipants();
    }

    public String submit() {
        System.out.println("submitting reservation: " + reservation);
        return "success";
    }

}
