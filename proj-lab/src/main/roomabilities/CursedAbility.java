package main.roomabilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import main.Room;
import main.RoomAbility;

import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Represents a "cursed" ability within a room in the game. This ability can manipulate room properties or affect game
 * dynamics.
 */
public class CursedAbility implements RoomAbility {
    private ArrayList<Room> hiddenNeighbours = new ArrayList<>();
    /**
     * Activates the cursed ability in a specified room.
     *
     * @param where the room in which the ability is activated
     */
    @Override
    public void Activate(Room where) {

    }

     /**
     * Returns a string identifier for this type of room ability. 
     *
     * @return the type identifier of this ability, "cursed_ability"
     */
    @Override
    public String GetTypeString() {
        return "cursed_ability";
    }
}
