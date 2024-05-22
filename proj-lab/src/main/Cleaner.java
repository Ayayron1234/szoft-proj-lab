package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import main.actions.SoulDrainer;

import java.util.*;

/**
 * It represents an entity that is responsible for cleaning rooms within the game.
 * It extends the Entity class and inherits its properties and behaviors.
 * Cleaners participate in the game and contribute to maintaining the cleanliness of rooms.
 */
public class Cleaner extends Entity implements Serializable {
    private int             uid;
    private final Random    randomGenerator = new Random();

    /**
     * This is the constructor of the Cleaner class with 2 parameters. It just calls the base class's constructor.
     *
     * @param uid is the unique identitifier of the Entity. (It has to be unique of course).
     * @param game is the game that the entity participates in.
     */
    public Cleaner(int uid, Game game) {
        super(uid, game);
    }

    /**
     * Cleans the current room and moves entities to connected rooms.
     * If the containing room is null, the method returns without performing the cleaning action.
     * Entities within the room, except the cleaner itself, will be moved to connected rooms.
     * The selection of the destination room for each entity is random among the connected rooms with available space.
     * If it isn't any available space in the connected rooms then the entity/entities can stay in the same room.
     */
    public void CleanRoom() {
        if (containingRoom == null)
            return;

        System.out.printf("%s sends out people from the room\n", GetName());

        containingRoom.Clean();

        Iterator<Entity> iter = containingRoom.GetEntities().iterator();

        while(iter.hasNext()) {
            Entity entity = iter.next();

            // Cleaner doesn't send themself out
            if (entity.equals(this))
                continue;

            // Filter full rooms
            List<Room> availableRooms = containingRoom.GetNeighbours().stream().filter(room -> room.GetSpaceLeft() > 0).toList();
            if (availableRooms.isEmpty())
                continue;

            // Select room to step into
            int index = randomGenerator.nextInt(availableRooms.size());
            Room roomToSendEntities = availableRooms.get(index);

            entity.Step(roomToSendEntities);
        }
    }

    /**
     * This entity can't be removed from the game.
     */
    @Override
    public void DropOutOfGame() {
        // Cleaners can't drop out of the game
    }

    @Override
    public void StartRound(TimerEvent timerEvent) {
        CleanRoom();
    }

    /**
     * Saves the Cleaners attributes into a JsonObject, then returns it
     *
     * @return json - A JsonObject made from the Cleaner
     */
    @Override
    public JsonObject Serialize() {
        JsonObject json = new JsonObject();
        json.add("id", new JsonPrimitive(String.format("entity#%d", GetUID())));
        json.add("type", new JsonPrimitive("Cleaner"));

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


    /**
     * This method is to get the name of the Teacher in a form of Oktató<UID>
     * @return the formatted string of that
     */
    @Override
    public void HandleTurn() {
//        Scanner in = game.GetScanner();    //
//        String[] cmd = in.nextLine().split(" ");
//        String out = "";
//
//        switch(cmd[0])
//        {
//            case "list":
//                if(cmd.length < 2) out += "Incorrect command, missing argument";
//                else if(cmd[1].equals("rooms")){
//                    out += "neighbouring rooms:";
//                    int i = 0;
//                    for(Room r : containingRoom.GetNeighbours()){
//                        out += String.format(" %d:%s",i,r.toString());
//                    }
//                }
//                else if(cmd[1].equals("items")){
//                    out += "items in room:";
//                    int i = 0;
//                    for(Item item : containingRoom.GetItems()){
//                        out += String.format(" %d:\"%s\"",i,item.GetName());
//                    }
//                }
//                else{
//                    out += "Incorrect command, unknown argument";
//                }
//                break;
//            case "place":
//                if(cmd.length < 2) out += "Incorrect command, missing argument\n";
//                else if(items.size() <= Integer.parseInt(cmd[1])){
//                    out += "Incorrect argument, index out of range\n";
//                }
//                else{
//                    PlaceItem(containingRoom.GetItems().get(Integer.parseInt(cmd[1])));
////                    items.get(Integer.parseInt(cmd[1])).Placed(this, containingRoom);
//                }
//                break;
//            case "pickup":
//                if(cmd.length < 2) out += "Incorrect command, missing argument\n";
//                else if(containingRoom.GetItems().size() <= Integer.parseInt(cmd[1])){
//                    out += "Incorrect argument, index out of range\n";
//                }
//                else{
//                    PickUpItem(containingRoom.GetItems().get(Integer.parseInt(cmd[1])));
////                    containingRoom.GetItems().get(Integer.parseInt(cmd[1])).PickedUp(this, containingRoom);
//                }
//                break;
//            case "step":
//                if(cmd.length < 2) out += "Incorrect command, missing argument";
//                else if(containingRoom.GetNeighbours().size() <= Integer.parseInt(cmd[1])){
//                    out += "Incorrect argument, index out of range";
//                }
//                else{
//                    this.Step(containingRoom.GetNeighbours().get(Integer.parseInt(cmd[1])));
//                }
//                break;
//            case "skip":
//                break;
//            case "exit":
//                game.Exit(); break;
//        }
//        System.out.print(out);
        CleanRoom();
        Random R = new Random();
            //step
            Room room = this.GetContainingRoom();
            ArrayList<Room> neighbors = room.GetNeighbours();
            boolean stepped = false;
            Room whichRoom = room;
            do{
                if(neighbors.size() == 0) {
                    stepped = true;
                    break;
                }
                whichRoom = neighbors.get(R.nextInt(neighbors.size())); //itt még belefuthat végtelen ciklusba de ki is szedhetem a listából az adott szobát átmenetileg
                neighbors.remove(whichRoom);
            } while(whichRoom.GetSpaceLeft() > 0);
            if(!stepped) this.Step(whichRoom);
    }

}

