package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import main.actions.SoulDrainer;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * The Teacher class represents a character in a game known as a Teacher. This character has special abilities,
 * including the power to drain souls from other entities in the same room.
 */
public class Teacher extends Entity implements Serializable {
    private int uid;
    /**
     * Creates a Teacher character. Each Teacher is identified by a unique ID.
     *
     * @param uid  a unique identifier for this Teacher
     * @param game the game instance to which this Teacher belongs
     */
    public Teacher(int uid, Game game) {
        super(uid, game);
    }

    /**
     * Activates the Teacher's ability to drain souls. This will affect all other entities in the same room,
     * applying a soul-draining effect to each one. It’s a powerful move that can drop out entities from the game.
     */
    public void DrainSouls() {
        System.out.printf("%s drains soul\n", GetName());

        SoulDrainer drainer = new SoulDrainer();

        Iterator<Entity> iter = containingRoom.GetEntities().iterator();
        while(iter.hasNext()) {
            Entity entity = iter.next();
            entity.ApplyAction(drainer);
        }
    }

    /**
     * Teachers can't drop out of the game
     */
    @Override
    public void DropOutOfGame() {
        // Teachers can't drop out of the game
        return;
    }

    @Override
    public void HandleTurn() {
        /*
         *  Possible operations (only one each turn):
         *      - list rooms => list neighbouring rooms
         *      - list items => list items in room
         *      - place <item_index_in_inventory> => place an item from inventory
         *      - pickup <item_index_in_room_inventory> => pick up an item from the room
         *      - drainsoul => drain the soul of students inside the room
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
            case "souldrain":
                DrainSouls();
                break;
            case "skip":
                break;
        }
        System.out.println(out);
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
        json.add("type", new JsonPrimitive("Teacher"));

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
