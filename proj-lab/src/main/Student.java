package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.Scanner;

/**
 * Represents a Student character in a game. Each student has a unique name and can participate in game activities.
 * This class provides basic functionalities specific to the student, such as setting a unique name and managing their turn.
 */
public class Student extends Entity implements Serializable {
    private String uniqueName;
    /**
     * Constructs a Student with a unique identifier and links it to the game environment.
     *
     * @param uid  the unique identifier for the student, used internally to track the student
     * @param game the game context that this student is a part of
     */
    public Student(int uid, Game game) {
        super(uid, game);
    }
    /**
     * Sets a unique name for the student. This name is used to identify the student during the game.
     *
     * @param uniqueName the new name to assign to the student
     */
    public void SetUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    @Override
    public void HandleTurn() {
        /*
         *  Possible operations (only one each turn):
         *      - list rooms => list neighbouring rooms
         *      - list items => list items in room
         *      - place <item_index_in_inventory> => place an item from inventory
         *      - pickup <item_index_in_room_inventory> => pick up an item from the room
         *      - skip => no op
         */
        Scanner in = new Scanner(System.in);
        String[] cmd = in.nextLine().split(" ");
        String out = "";

        switch(cmd[0])
        {
            case "list":
                if(cmd.length < 2) out += "Incorrect command, missing argument";
                else if(cmd[1].equals("rooms")){
                    out += "neighbouring rooms: ";
                    int i = 0;
                    for(Room r : containingRoom.GetNeighbours()){
                        out += String.format("%d:\"%s\" ",i,r.toString());
                    }
                }
                else if(cmd[1].equals("items")){
                    out += "items in room: ";
                    int i = 0;
                    for(Item item : containingRoom.GetItems()){
                        out += String.format("%d:\"%s\" ",i,item.GetName());
                    }
                }
                else{
                    out += "Incorrect command, unknown argument";
                }
                break;
            case "place":
                if(cmd.length < 2) out += "Incorrect command, missing argument";
                else if(items.size() + 1 < Integer.parseInt(cmd[1])){
                    out += "Incorrect argument, index out of range";
                }
                else{
                    items.get(Integer.parseInt(cmd[1])).Placed(this, containingRoom);
                }
                break;
            case "pickup":
                if(cmd.length < 2) out += "Incorrect command, missing argument";
                else if(containingRoom.GetItems().size() + 1 < Integer.parseInt(cmd[1])){
                    out += "Incorrect argument, index out of range";
                }
                else{
                    containingRoom.GetItems().get(Integer.parseInt(cmd[1])).PickedUp(this, containingRoom);
                }
                break;
            case "skip":
                break;
        }
        System.out.println(out);
    }


    /**
     * Returns the student's unique name.
     *
     * @return the unique name of the student
     */
    @Override
    public String GetName() {
        return String.format("%s", uniqueName);
    }

    /**
     * Saves the Students attributes into a JsonObject, then returns it
     *
     * @return json - A JsonObject made from the Cleaner
     */
    @Override
    public JsonObject Serialize() {
        JsonObject json = new JsonObject();
        json.add("id", new JsonPrimitive(String.format("entity#%d", GetUID())));
        json.add("type", new JsonPrimitive("Student"));

        JsonArray itemsJson = new JsonArray();
        for (Item item : items) {
            itemsJson.add(String.format("item#%d", item.GetUID()));
        }
        json.add("items", itemsJson);

        JsonArray protectionsJson = new JsonArray();
        for (Protection protection : activeProtections) {
            protectionsJson.add(String.format("protection#%d", protection.GetUid()));
        }
        json.add("activeProtections", protectionsJson);

        return json;
    }

}
