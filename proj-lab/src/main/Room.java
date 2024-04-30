package main;

<<<<<<< Updated upstream
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import main.actions.Poisoner;
import main.roomabilities.CursedAbility;
import main.roomabilities.PoisonAbility;

import java.util.ArrayList;
=======
import main.roomabilities.CursedAbility;
import main.roomabilities.PoisonAbility;
>>>>>>> Stashed changes

import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.Random;
import java.util.Scanner;
=======
import java.util.Iterator;
>>>>>>> Stashed changes

/**
 * Represents a room within a game environment. Entities and items can be at this room.
 * Each room has a unique identifier (UID) and a maximum capacity to hold entities.
 * Rooms can have neighbors and also abilities.
 */
public class Room implements TimerSubscriber {
<<<<<<< Updated upstream
    private int                     uid;
    private int                     capacity;
=======
//    private int capacity;
//    private static int lastUid = 0;
//    private int uid;
>>>>>>> Stashed changes

    private ArrayList<Room>         neighbours = new ArrayList<>();
    private ArrayList<Entity>       entities = new ArrayList<>();
    private ArrayList<Item>         items = new ArrayList<>();

    boolean                         isSticky = false;
    private ArrayList<RoomAbility>  abilities = new ArrayList<>();

<<<<<<< Updated upstream
    public void RemoveAbilityType(Class abilityClass) {
        abilities.removeIf(ability -> ability.getClass().equals(abilityClass));
    }

    public int GetSpaceLeft() {
        return capacity - entities.size();
    }

    public Room(int uid, int capacity) {
        this.uid = uid;
        this.capacity = capacity;
    }

    public int GetRoomNumber() { return uid; }

    public ArrayList<Item> GetItems() {
        return items;
    }

    public void PlaceItem(Item item) {
        items.add(item);
    }

    public void PickUpItem(Item item) {
        if (!items.remove(item))
            throw new RuntimeException("Room doesn't contain provided item. ");
    }

    public int GetCapacity() {
        return capacity;
    }

    public void SetCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Room> GetNeighbours() {
        return neighbours;
    }

    public void AddNeighbour(Room neighbour) {
        neighbours.add(neighbour);
    }

    public boolean RemoveNeighbour(Room neighbour) {
        return neighbours.remove(neighbour);
    }

    public void AcceptEntity(Entity entity) {
        if (!CanStepInto(entity))
            throw new RuntimeException("main.Entity count exceeds maximum");
        entities.add(entity);
    }

=======
    /**
<<<<<<< Updated upstream
     * Constructs a Room with the given parameter
     * @param capacity - capacity of room (how many entities can fit)
     */
    public Room(int capacity) {
//        this.uid = lastUid++;
//        this.capacity = capacity;
    }

    /**
     * Sets the lastUid attribute to 0
     */
    public static void ResetUIDs() {
        System.out.println("Room.ResetUIDs");
//        lastUid = 0;
    }

    /**
     * @return the unique identifier (uid) of room
     */
    public int GetRoomNumber() {
        System.out.println("Room.GetRoomNumber");
        return 0;
    }

    /**
     * @return the list of items in the room
=======
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
     * Retrieves the Room Number (UID).
     *
     * @return the unique identifier of the room.
     */
    public int GetRoomNumber() { return uid; }

    /**
     * Retrieves the items that can be found in the room.
     *
     * @return the list of items that are in the room.
>>>>>>> Stashed changes
     */
    public ArrayList<Item> GetItems() {
        System.out.println("Room.GetItems");
        return new ArrayList<>();
    }

    /**
<<<<<<< Updated upstream
     * Places item to room, adds it to list of items in room
     * @param item - the item placed
=======
     * You can place down a single Item in the room with this.
     *
     * @param item which you place down in the room.
>>>>>>> Stashed changes
     */
    public void PlaceItem(Item item) {
        System.out.println("Room.PlaceItem");
    }

    /**
<<<<<<< Updated upstream
     * Picks up item from room, removes it from list of items in room
     * @param item - the item picked up
     */
    public void PickUpItem(Item item) {
        System.out.println("Room.PickUpItem");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Could the item be found in the room entity is standing in?\n 1-yes 2-no");

        String answer = scanner.nextLine();
        if(answer.equals("2")) {
            System.out.println("Room doesn't contain provided item.");
        } else
            System.out.println("Picked up item from room");
    }

    /**
     * @return the capacity of room
=======
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
     * This is the getter of the capacity.
     *
     * @return the max capacity of the room.
>>>>>>> Stashed changes
     */
    public int GetCapacity() {
        System.out.println("Room.GetCapacity");
        return 0;
    }

    /**
<<<<<<< Updated upstream
     * Sets the value of capacity (how many entities fit into the room)
     * @param capacity - capacity of room
=======
     * This is the setter of the capacity.
     *
     * @param capacity the max capacity of the room.
>>>>>>> Stashed changes
     */
    public void SetCapacity(int capacity) {
        System.out.println("Room.SetCapacity");
    }

<<<<<<< Updated upstream
    /**
     * Adds ability to the list of abilities of the room
     * @param ability - the ability added
     */
    public void AddAbility(RoomAbility ability) {
        System.out.println("Room.AddAbility");
        abilities.add(ability);
    }

    public void ActivateAbilities() {
        System.out.println("Room.ActivateAbilities");

        System.out.println("Is the ability a curse(1) ability or a poison(2) ability?");

        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("1")) {
            CursedAbility cursedAbility = new CursedAbility();
            cursedAbility.Activate(this);
        } else {
            PoisonAbility poison = new PoisonAbility();
            poison.Activate(this);
        }
    }

    /**
     * @return neighbours - the list of neighbours that the room has
=======

    /**
     * This is the getter of the neighbours.
     *
     * @return a list with the room's neighbours (room).
>>>>>>> Stashed changes
     */
    public ArrayList<Room> GetNeighbours() {
        System.out.println("Room.GetNeighbours");
        return new ArrayList<>();
    }

    /**
<<<<<<< Updated upstream
     * Adds a new neighbour of the room's list of neighbours
     * @param neighbour - new neighbour of the room
=======
     * You can add a single neighbour to a room.
     *
     * @param neighbour the room's new neighbour.
>>>>>>> Stashed changes
     */
    public void AddNeighbour(Room neighbour) {
        System.out.println("Room.AddNeighbour");
    }

    /**
<<<<<<< Updated upstream
     * @param neighbour - the neighbour which we want to remove
     * @return - the boolean value of the success of removal
=======
     * You can remove a single neighbour of a room.
     *
     * @param neighbour the room's ex neighbour.
>>>>>>> Stashed changes
     */
    public boolean RemoveNeighbour(Room neighbour) {
        System.out.println("Room.RemoveNeighbour");
        return true;
    }

    /**
<<<<<<< Updated upstream
     * Checks if given entity can step into the room
     * if not throws RuntimeException
     * if it can, adds it to room's list of entities
     * @param entity - the entity who wants to step into the room
=======
     * You can add a single entity to a room.
     *
     * @param entity the entity that steps into this room.
>>>>>>> Stashed changes
     */
    public void AcceptEntity(Entity entity) {
        System.out.println("Room.AcceptEntity");
    }

    /**
<<<<<<< Updated upstream
     * if we can remove the entity from list of entities, it does
     * if we cannot, it throws a RunTimeException
     * @param entity - the entity which we want to remove
=======
     * You can remove a single entity from a room.
     *
     * @param entity the entity that steps out from this room.
>>>>>>> Stashed changes
     */
>>>>>>> Stashed changes
    public void RemoveEntity(Entity entity) {
        if (!entities.remove(entity))
            throw new RuntimeException("main.Room failed to remove entity.");
    }

<<<<<<< Updated upstream
=======
    /**
<<<<<<< Updated upstream
     * @return entities - the list of entities currently in the room
=======
     * This is the getter of the entities that are in this room.
     *
     * @return a list of the entities that are currently in this room.
>>>>>>> Stashed changes
     */
>>>>>>> Stashed changes
    public ArrayList<Entity> GetEntities() {
        return entities;
    }

<<<<<<< Updated upstream
    public boolean CanStepInto(Entity who) {
        return entities.size() < capacity;
    }

=======
    /**
<<<<<<< Updated upstream
     *
     * @param who - entity who wants to step into the room
     * @return boolean value of whether we can step into the room or not
=======
     * Checks if the entity can step into the room.
     *
     * @param who the entity that wants to step into the room.
     * @return a bool value, true if the entity can step into the room, otherwise it's false.
>>>>>>> Stashed changes
     */
    public boolean CanStepInto(Entity who) {
        System.out.println("Room.CanStepInto");
        return true;
    }

<<<<<<< Updated upstream
    public void SplitRoom() {
        System.out.println("Room.SplitRoom");

        Room newRoom = new Room(0);

        GetNeighbours();
        newRoom.AddNeighbour(this);
        RemoveNeighbour(this);

        newRoom.AddAbility(new PoisonAbility());
        newRoom.SetCapacity(0);
    }

    public static void MergeRoom(Room a, Room b) {
        System.out.println("Room.MergeRoom");

        a.GetNeighbours();
        b.GetNeighbours();

        System.out.println("For neighbour in a.GetNeighbours() and b.GetNeigbours");
        new Room(0).RemoveNeighbour(a);
        new Room(0).RemoveNeighbour(b);

        System.out.println("new Room");
        Room newRoom = new Room(0);

        newRoom.AddAbility(new PoisonAbility());
        newRoom.SetCapacity(0);
=======
    /**
     * This is the cleaning's effects on the room.
     * The room becomes not sticky and also the poison ability is removed.
     */
>>>>>>> Stashed changes
    public void Clean() {
        isSticky = false;
        RemoveAbilityType(Poisoner.class);
    }

<<<<<<< Updated upstream
    public String toString(){
        return "room#" + Integer.toString(uid);
=======
    /**
     * Retrieves a string representation of the room with its uid.
     *
     * @return A string in the format of room#uid.
     */
    public String toString(){
        return "room#" + Integer.toString(uid);
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }

    @Override
    public void StartRound(TimerEvent data) {
<<<<<<< Updated upstream
        for(RoomAbility ra : abilities){
            ra.Activate(this);
        }
=======
<<<<<<< Updated upstream
        System.out.println("Room.StartRound");
=======
        ArrayList<RoomAbility> _abilities = new ArrayList<RoomAbility>();
        _abilities.addAll(abilities);

        Iterator<RoomAbility> iter = _abilities.iterator();
        while (iter.hasNext()) {
            iter.next().Activate(this);
        }
//        for (RoomAbility ra : abilities){
//            ra.Activate(this);
//        }
>>>>>>> Stashed changes
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

=======
<<<<<<< Updated upstream
=======

    /**
     * Saves the Room's attributes into a JsonObject, then returns it
     *
     * @return json - A JsonObject made from the Room's attributes
     */
>>>>>>> Stashed changes
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
