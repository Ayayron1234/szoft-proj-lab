package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import main.actions.Poisoner;
import main.roomabilities.CursedAbility;
import main.roomabilities.PoisonAbility;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Iterator;

/**
 * Represents a room within a game environment. Entities and items can be at this room.
 * Each room has a unique identifier (UID) and a maximum capacity to hold entities.
 * Rooms can have neighbors and also abilities.
 */
public class Room implements TimerSubscriber {
    private int                     uid;
    private int                     capacity;

    private ArrayList<Room>         neighbours = new ArrayList<>();
    private ArrayList<Entity>       entities = new ArrayList<>();
    private ArrayList<Item>         items = new ArrayList<>();

    boolean                         isSticky = false;
    private ArrayList<RoomAbility>  abilities = new ArrayList<>();

    private int                     makeSticky = 0;

    /**
     * Retrieves the Room Number (UID).
     *
     * @return the unique identifier of the room.
     */
    public int GetRoomNumber() { return uid; }

    /**
     * Retrieves the items that can be found in the room.
     *
     * @return the list of items that are in the room.
     */
    public ArrayList<Item> GetItems() {
        return items;
    }

    /**
     * Places item to room, adds it to list of items in room
     * @param item - the item placed
     */
    public void PlaceItem(Item item) {
        items.add(item);
    }

    /**
     * This is the getter of the capacity.
     *
     * @return the max capacity of the room.
     */
    public int GetCapacity() {
        return capacity;
    }

    /**
     * Sets the value of capacity (how many entities fit into the room)
     * @param capacity - capacity of room
     */
    public void SetCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * This is the getter of the neighbours.
     *
     * @return a list with the room's neighbours (room).
     */
    public ArrayList<Room> GetNeighbours() {
        return neighbours;
    }

    /**
     * Adds a new neighbour of the room's list of neighbours
     * @param neighbour - new neighbour of the room
     */
    public void AddNeighbour(Room neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * You can remove a single neighbour of a room.
     *
     * @param neighbour the room's ex neighbour.
     * @return - the boolean value of the success of removal
     */
    public boolean RemoveNeighbour(Room neighbour) {
        return neighbours.remove(neighbour);
    }

    /**
     * Checks if given entity can step into the room
     * if not throws RuntimeException
     * if it can, adds it to room's list of entities
     * @param entity - the entity who wants to step into the room
     */
    public void AcceptEntity(Entity entity) {
        if (!CanStepInto(entity))
            throw new RuntimeException("main.Entity count exceeds maximum");
        entities.add(entity);
    }

    /**
     * Remove the ability type of room, but check it before if the room has it or not.
     *
     * @param abilityClass an abilityClass (it can be CursedAbility or PoisonAbility)
     */
    public void RemoveAbilityType(Class abilityClass) {
        abilities.removeIf(ability -> ability.getClass().equals(abilityClass));
    }

    /**
     * Retrieves the space left in the room.
     *
     * @return an int the space left in the room.
     */
    public int GetSpaceLeft() {
        return capacity - entities.size();
    }

    /**
     * Constructor of the room class with 2 parameters.
     *
     * @param uid an int, the unique identifier of the room.
     * @param capacity an int, the max capacity of the room.
     */
    public Room(int uid, int capacity) {
        this.uid = uid;
        this.capacity = capacity;
    }

    /**
     * You can pick up a single Item from the room with this.
     *
     * @param item which you pick up from the room.
     */
    public boolean PickUpItem(Item item) {
        if(item.IsSticky())
            return false;

        if (!items.remove(item))
            throw new RuntimeException("Room doesn't contain provided item. ");

        return true;
    }

    /**
     * Adds ability to the list of abilities of the room
     * @param ability - the ability added
     */
    public void AddAbility(RoomAbility ability) {
        System.out.println("Room.AddAbility");
        abilities.add(ability);
    }


    /**
     * if we can remove the entity from list of entities, it does
     * if we cannot, it throws a RunTimeException
     * @param entity - the entity which we want to remove
     */

    public void RemoveEntity(Entity entity) {
        if (!entities.remove(entity))
            throw new RuntimeException("main.Room failed to remove entity.");
    }


    /**
     * This is the getter of the entities that are in this room.
     *
     * @return a list of the entities that are currently in this room.
     */

    public ArrayList<Entity> GetEntities() {
        return entities;
    }

    /**
     * Checks if the entity can step into the room.
     *
     * @param who the entity that wants to step into the room.
     * @return a bool value, true if the entity can step into the room, otherwise it's false.
     */
    public boolean CanStepInto(Entity who) {
        return entities.size() < capacity;
    }

    /**
     * This is the cleaning's effects on the room.
     * The room becomes not sticky and also the poison ability is removed.
     */
    public void Clean() {
        isSticky = false;
        for (Item item: GetItems()
             ) {
            item.SetSticky(false);
        }
        RemoveAbilityType(Poisoner.class);
    }

    /**
     * Retrieves a string representation of the room with its uid.
     *
     * @return A string in the format of room#uid.
     */
    public String toString(){
        return "room#" + Integer.toString(uid);
    }

    @Override
    public void StartRound(TimerEvent data) {
        if(makeSticky == 3) {
            isSticky = true;
            for (Item item: GetItems()
                 ) {
                item.SetSticky(true);
            }
            makeSticky = 0;
        }

        ArrayList<RoomAbility> _abilities = new ArrayList<RoomAbility>();
        _abilities.addAll(abilities);

        Iterator<RoomAbility> iter = _abilities.iterator();
        while (iter.hasNext()) {
            iter.next().Activate(this);
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
     * Saves the Room's attributes into a JsonObject, then returns it
     *
     * @return json - A JsonObject made from the Room's attributes
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
     * Deserialize json object into a Room object.
     * @param game
     * @param json
     * @return
     */
    public static Room Deserialize(Game game, JsonObject json) {
        if (!json.has("id") || !json.has("capacity"))
            throw new RuntimeException("");

        int uid = Integer.parseInt(json.get("id").getAsString().split("#")[1]);

        Room room = game.GetOrCreateDeserializedObjectReference(json.get("id").getAsString(),
                () -> game.CreateRoom(json.get("capacity").getAsInt()));
        room.uid = uid;

        room.capacity = json.get("capacity").getAsInt();

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

                item.SetLocationIfNeeded(room);
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
