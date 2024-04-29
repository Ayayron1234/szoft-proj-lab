package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import main.actions.Poisoner;
import main.roomabilities.CursedAbility;
import main.roomabilities.PoisonAbility;

import java.util.ArrayList;
/**
 * Represents a room in the game environment. Rooms can contain entities, items, and abilities. Rooms also have the capacity to
 * connect with other rooms (neighbours). This class handles various functionalities such as adding and removing entities,
 * placing items, and serializing/deserializing room data.
 */
public class Room implements TimerSubscriber {
    private int                     uid;
    private int                     capacity;

    private ArrayList<Room>         neighbours = new ArrayList<>();
    private ArrayList<Entity>       entities = new ArrayList<>();
    private ArrayList<Item>         items = new ArrayList<>();

    boolean                         isSticky = false;
    private ArrayList<RoomAbility>  abilities = new ArrayList<>();

    /**
     * Removes a specific type of ability from the room.
     * @param abilityClass The class type of the ability to remove.
     */
    public void RemoveAbilityType(Class abilityClass) {
        abilities.removeIf(ability -> ability.getClass().equals(abilityClass));
    }

    /**
     * Calculates and returns the available space left in the room based on its capacity and the number of entities currently in it.
     * @return The number of additional entities this room can accommodate.
     */
    public int GetSpaceLeft() {
        return capacity - entities.size();
    }

    /**
     * Constructs a room with a unique identifier and a specific capacity.
     * @param uid Unique identifier for the room.
     * @param capacity Maximum number of entities the room can hold.
     */
    public Room(int uid, int capacity) {
        this.uid = uid;
        this.capacity = capacity;
    }

    /**
     * Returns the unique identifier of the room.
     * @return The unique identifier of the room.
     */
    public int GetRoomNumber() { return uid; }

    /**
     * Retrieves a list of items currently in the room.
     * @return A list of items in the room.
     */
    public ArrayList<Item> GetItems() {
        return items;
    }

    /**
     * Places an item into the room.
     * @param item The item to be placed in the room.
     */
    public void PlaceItem(Item item) {
        items.add(item);
    }

    /**
     * Removes a specified item from the room.
     * @param item The item to remove.
     */
    public void PickUpItem(Item item) {
        if (!items.remove(item))
            throw new RuntimeException("Room doesn't contain provided item. ");
    }

    /**
     * Retrieves the room's capacity.
     * @return The maximum number of entities that the room can hold.
     */
    public int GetCapacity() {
        return capacity;
    }

     /**
     * Sets a new capacity for the room.
     * @param capacity The new capacity to set.
     */
    public void SetCapacity(int capacity) {
        this.capacity = capacity;
    }

     /**
     * Retrieves a list of neighboring rooms.
     * @return A list of rooms directly accessible from this room.
     */
    public ArrayList<Room> GetNeighbours() {
        return neighbours;
    }

    
    /**
     * Adds a neighboring room to this room.
     * @param neighbour The room to add as a neighbour.
     */
    public void AddNeighbour(Room neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * Removes a neighboring room.
     * @param neighbour The room to remove from the neighbours list.
     * @return true if the neighbour was removed, false otherwise.
     */
    public boolean RemoveNeighbour(Room neighbour) {
        return neighbours.remove(neighbour);
    }

     /**
     * Accepts an entity into the room if there is space available.
     * @param entity The entity to be added to the room.
     */
    public void AcceptEntity(Entity entity) {
        if (!CanStepInto(entity))
            throw new RuntimeException("main.Entity count exceeds maximum");
        entities.add(entity);
    }

    /**
     * Removes an entity from the room.
     * @param entity The entity to remove.
     */
    public void RemoveEntity(Entity entity) {
        if (!entities.remove(entity))
            throw new RuntimeException("main.Room failed to remove entity.");
    }

    /**
     * Retrieves a list of entities currently in the room.
     * @return A list of entities in the room.
     */
    public ArrayList<Entity> GetEntities() {
        return entities;
    }

     /**
     * Checks if an entity can enter the room based on available capacity.
     * @param who The entity attempting to enter.
     * @return true if the entity can enter, false otherwise.
     */
    public boolean CanStepInto(Entity who) {
        return entities.size() < capacity;
    }

     /**
     * Cleans the room by resetting specific states like stickiness and removing certain abilities.
     */
    public void Clean() {
        isSticky = false;
        RemoveAbilityType(Poisoner.class);
    }


    /**
     * Retrieves a room's name and uid in string format
     * @return A string of the room's name
     */
    public String toString(){
        return "room#" + Integer.toString(uid);
    }

    @Override
    public void StartRound(TimerEvent data) {
        for(RoomAbility ra : abilities){
            ra.Activate(this);
        }
    }

    @Override
    public void EndRound(TimerEvent data) {

    }

    @Override
    public void StartTurn(TimerEvent data) {

    }

    @Override
    public void EndTurn(TimerEvent data) {

    }

     /**
     * Converts the room state to a JSON object for serialization.
     * @return A JsonObject representing the room's state.
     */
    public JsonObject Serialize() {
        JsonObject json = new JsonObject();
        json.add("id", new JsonPrimitive(String.format("item#%d", uid)));

        json.add("capacity", new JsonPrimitive(capacity));

        JsonArray neigboursJson = new JsonArray();
        for (Room room : neighbours)
            neigboursJson.add(String.format("room#%d", room.GetRoomNumber()));
        json.add("neighbours", neigboursJson);

        JsonArray entitiesJson = new JsonArray();
        for (Entity entity : entities)
            entitiesJson.add(String.format("entity#%d", entity.GetUID()));
        json.add("containedEntities", entitiesJson);

        JsonArray itemsJson = new JsonArray();
        for (Item item : items)
            itemsJson.add(String.format("item#%d", item.GetUID()));
        json.add("items", itemsJson);

        json.add("isSticky", new JsonPrimitive(isSticky));

        JsonArray abilitiesJson = new JsonArray();
        for (RoomAbility ability : abilities)
            abilitiesJson.add(ability.GetTypeString());
        json.add("abilities", abilitiesJson);

        return json;
    }

     /**
     * Reconstructs a room object from its JSON representation.
     * @param game The game context to which this room belongs.
     * @param json The JSON object containing the room data.
     * @return The deserialized room object.
     */
    public static Room Deserialize(Game game, JsonObject json) {
        if (!json.has("id") || !json.has("capacity"))
            throw new RuntimeException("");

        int uid = Integer.parseInt(json.get("id").getAsString().split("#")[1]);

        Room room = game.GetOrCreateDeserializedObjectReference(json.get("id").getAsString(),
                () -> game.CreateRoom(json.get("capacity").getAsInt()));
        room.uid = uid;

        if (json.has("neighbours")) {
            JsonArray neighboursJson = json.get("neighbours").getAsJsonArray();
            for (int i = 0; i < neighboursJson.size(); ++i) {
                Room neighbour = game.GetOrCreateDeserializedObjectReference(neighboursJson.get(i).getAsString(),
                        () -> game.CreateRoom(-1));

                room.neighbours.add(neighbour);
            }
        }

        if (json.has("containedEntities")) {
            JsonArray entitiesJson = json.get("containedEntities").getAsJsonArray();
            for (int i = 0; i < entitiesJson.size(); ++i) {
                Entity entity = game.GetDeserializedObjectReference(entitiesJson.get(i).getAsString());
                entity.Teleport(room);
            }
        }

        if (json.has("items")) {
            JsonArray itemsJson = json.get("items").getAsJsonArray();
            for (int i = 0; i < itemsJson.size(); ++i) {
                Item item = game.GetDeserializedObjectReference(itemsJson.get(i).getAsString());

                room.items.add(item);
            }
        }

        if (json.has("isSticky"))
            room.isSticky = json.get("isSticky").getAsBoolean();

        if (json.has("abilities")) {
            JsonArray abilitiesJson = json.get("abilities").getAsJsonArray();
            for (int i = 0; i < abilitiesJson.size(); ++i) {
                String abilityStr = abilitiesJson.get(i).getAsString();
                if (abilityStr.equals(new CursedAbility().GetTypeString()))
                    room.abilities.add(new CursedAbility());
                if (abilityStr.equals(new PoisonAbility().GetTypeString()))
                    room.abilities.add(new PoisonAbility());
            }
        }

        return room;
    }

}
