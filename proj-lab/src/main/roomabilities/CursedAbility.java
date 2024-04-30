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
<<<<<<< Updated upstream
    private ArrayList<Room> hiddenNeighbours = new ArrayList<>();
    @Override
    public void Activate(Room where) {

    }

    @Override
    public String GetTypeString() {
        return "cursed_ability";
=======
<<<<<<< Updated upstream
    public ArrayList<Room> hiddenNeighbours = new ArrayList<>();
=======
    private ArrayList<Room> hiddenNeighbours = new ArrayList<>();
    /**
     * Activates the cursed room ability, make some doors hidden and/or some hidden doors visible again.
     *
     * @param where The room in which the cursed ability is activated.
     */
>>>>>>> Stashed changes
    @Override
    public void Activate(Room room) {
        System.out.println("CursedAbility.Activate");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Did the ablility select a room to hide?\n 1-yes 2-no");
        if (scanner.nextLine().equals("1"))
            room.RemoveNeighbour(new Room(0));

<<<<<<< Updated upstream
        System.out.println("Did the ablility select a room to reveal?\n 1-yes 2-no");
        if (scanner.nextLine().equals("1"))
            room.AddNeighbour(new Room(0));
=======
    /**
     * Retrieves a string representation of the cursed type of the room abilities.
     *
     * @return A string representing the type of cursed ability.
     */
    @Override
    public String GetTypeString() {
        return "cursed_ability";
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }
}
