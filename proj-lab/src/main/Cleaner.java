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
    public void HandleTurn() {
        //CleanRoom();
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

}

