package dev.bene.doodle;

import dev.bene.doodle.model.Room;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "roomConverter")
public class RoomConverter implements Converter<Room> {
    @Override
    public Room getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return new Room(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Room room) {
        return room.getName();
    }
}
