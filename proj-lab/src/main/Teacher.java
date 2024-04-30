package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import main.actions.SoulDrainer;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

<<<<<<< Updated upstream
/**
 * The Teacher class represents a character in a game known as a Teacher. This character has special abilities,
 * including the power to drain souls from other entities in the same room.
 */
public class Teacher extends Entity implements Serializable {
    private int uid;
=======
public class Teacher extends Entity {

>>>>>>> Stashed changes
    /**
     * Creates a Teacher character. Each Teacher is identified by a unique ID.
     *
     * @param uid  a unique identifier for this Teacher
     * @param game the game instance to which this Teacher belongs
     */
<<<<<<< Updated upstream
    public Teacher(int uid, Game game) {
        super(uid, game);
=======
    public Teacher() {

>>>>>>> Stashed changes
    }

    /**
     * Activates the Teacher's ability to drain souls. This will affect all other entities in the same room,
     * applying a soul-draining effect to each one. It’s a powerful move that can drop out entities from the game.
     */
<<<<<<< Updated upstream
    public void DrainSouls() {
        System.out.printf("%s drains soul\n", GetName());
=======
    public Teacher(Game game) {
        super(game);
        System.out.println("Teacher.Teacher");
    }

    /**
     * This method is resetting the autoincremented UID.
     */
    public static void ResetUIDs() {
        System.out.println("Teacher.ResetUIDs");
    }

    /**
     * This method is where the teacher tries to drain the soul of every students who is in the same room as him/her
     */
    public void DrainSouls() {
        System.out.println("Teacher.DrainSouls");
>>>>>>> Stashed changes

        new Room(0).GetEntities();
        Student student = new Student("");

        System.out.println("new SoulDrainer");
        SoulDrainer drainer = new SoulDrainer();

<<<<<<< Updated upstream
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
=======
        student.ApplyAction(drainer);
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        JsonArray itemsJson = new JsonArray();
        for (Item item : items) {
            itemsJson.add(String.format("item#%d", item.GetUID()));
        }
        json.add("items", itemsJson);
=======
    /**
     * Operations during the turn of this entity.
     * Possible operations (only one each turn):
     *      - list rooms => list neighbouring rooms
     *      - list items => list items in room
     *      - place <item_index_in_inventory> => place an item from inventory
     *      - pickup <item_index_in_room_inventory> => pick up an item from the room
     *      - drainsoul => drain the soul of students inside the room
     *      - step <neighbour_index> => step to a neighbouring room by their index in the containingRoom's neighbours list
     *      - skip => no op
     */

    @Override
<<<<<<< Updated upstream
    public void EndRound(TimerEvent data) {
        System.out.println("Teacher.EndRound");
    }
>>>>>>> Stashed changes

        JsonArray protectionsJson = new JsonArray();
        for (Protection protection : activeProtections) {
            protectionsJson.add(String.format("protection#%d", protection.GetUid()));
        }
        json.add("activeProtections", protectionsJson);

<<<<<<< Updated upstream
        return json;
=======
    @Override
    public void EndTurn(TimerEvent data) {
        System.out.println("Teacher.EndTurn");
    }

    /**
     * This method is to get the name of the Teacher in a form of Oktató<UID>
     * @return the formatted string of that
=======
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
        Scanner in = game.GetScanner();    //
        String[] cmd = in.nextLine().split(" ");
        String out = "";

        switch(cmd[0])
        {
            case "list":
                if(cmd.length < 2) out += "Incorrect command, missing argument";
                else if(cmd[1].equals("rooms")){
                    out += "neighbouring rooms:";
                    int i = 0;
                    for(Room r : containingRoom.GetNeighbours()){
                        out += String.format(" %d:%s",i,r.toString());
                    }
                }
                else if(cmd[1].equals("items")){
                    out += "items in room:";
                    int i = 0;
                    for(Item item : containingRoom.GetItems()){
                        out += String.format(" %d:\"%s\"",i,item.GetName());
                    }
                }
                else{
                    out += "Incorrect command, unknown argument";
                }
                break;
            case "place":
                if(cmd.length < 2) out += "Incorrect command, missing argument\n";
                else if(items.size() <= Integer.parseInt(cmd[1])){
                    out += "Incorrect argument, index out of range\n";
                }
                else{
                    PlaceItem(containingRoom.GetItems().get(Integer.parseInt(cmd[1])));
//                    items.get(Integer.parseInt(cmd[1])).Placed(this, containingRoom);
                }
                break;
            case "pickup":
                if(cmd.length < 2) out += "Incorrect command, missing argument\n";
                else if(containingRoom.GetItems().size() <= Integer.parseInt(cmd[1])){
                    out += "Incorrect argument, index out of range\n";
                }
                else{
                    PickUpItem(containingRoom.GetItems().get(Integer.parseInt(cmd[1])));
//                    containingRoom.GetItems().get(Integer.parseInt(cmd[1])).PickedUp(this, containingRoom);
                }
                break;
            case "drainsoul":
                DrainSouls();
                break;
            case "step":
                if(cmd.length < 2) out += "Incorrect command, missing argument";
                else if(containingRoom.GetNeighbours().size() <= Integer.parseInt(cmd[1])){
                    out += "Incorrect argument, index out of range";
                }
                else{
                    this.Step(containingRoom.GetNeighbours().get(Integer.parseInt(cmd[1])));
                }
                break;
            case "skip":
                break;
            case "exit":
                game.Exit(); break;
        }
        System.out.print(out);
    }

    /**
     * Saves the Teacher's attributes into a JsonObject, then returns it
     *
     * @return json - A JsonObject made from the Teacher's attributes
>>>>>>> Stashed changes
     */
    @Override
    public String GetName() {
        System.out.println("Teacher.GetName");
        return "";
    }

    /**
     * This method is to make sure a teacher can't drain a soul of another teacher
     * @return we return false everytime because of that
     */
    @Override
    public boolean DropOutOfGame() {
        System.out.println("Teacher.DropOutOfGame");
        return false;
>>>>>>> Stashed changes
    }

}
