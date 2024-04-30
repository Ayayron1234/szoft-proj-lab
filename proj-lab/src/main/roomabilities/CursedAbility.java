package main.roomabilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import main.Room;
import main.RoomAbility;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a room ability that curses the room when activated, affecting the visibility of certain doors.
 * When activated, this ability may hide some doors or reveal hidden doors within the room.
 */
public class CursedAbility implements RoomAbility {
    private ArrayList<Room> hiddenNeighbours = new ArrayList<>();

    /**
     * Activates the cursed room ability, make some doors hidden and/or some hidden doors visible again.
     *
     * @param where The room in which the cursed ability is activated.
     */
    @Override
    public void Activate(Room where) {

    }

    /**
     * Retrieves a string representation of the cursed type of the room abilities.
     *
     * @return A string representing the type of cursed ability.
     */
    @Override
    public String GetTypeString() {
        return "cursed_ability";
    }
}