package main.roomabilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import main.Room;
import main.RoomAbility;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CursedAbility implements RoomAbility {
    private ArrayList<Room> hiddenNeighbours = new ArrayList<>();
    @Override
    public void Activate(Room where) {

    }

    @Override
    public String GetTypeString() {
        return "cursed_ability";
    }
}
