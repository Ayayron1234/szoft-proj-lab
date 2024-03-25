package main;

import java.util.ArrayList;

public class Room implements TimerSubscriber {
    private int capacity;
    private static int lastUid = 0;
    private int uid;

    private ArrayList<Room> neighbours = new ArrayList<>();
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    private ArrayList<RoomAbility> abilities = new ArrayList<>();

    /**
     * Constructs a Room with the given parameter
     * @param capacity - capacity of room (how many entities can fit)
     */
    public Room(int capacity) {
        this.uid = lastUid++;
        this.capacity = capacity;
    }

    /**
     * Sets the lastUid attribute to 0
     */
    public static void ResetUIDs() {
        lastUid = 0;
    }

    /**
     * @return the unique identifier (uid) of room
     */
    public int GetRoomNumber() { return uid; }

    /**
     * @return the list of items in the room
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
     * Picks up item from room, removes it from list of items in room
     * @param item - the item picked up
     */
    public void PickUpItem(Item item) {
        if (!items.remove(item))
            throw new RuntimeException("Room doesn't contain provided item. ");
    }

    /**
     * @return the capacity of room
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
     * Adds ability to the list of abilities of the room
     * @param ability - the ability added
     */
    public void AddAbility(RoomAbility ability) {
        abilities.add(ability);
    }

    /**
     * @return neighbours - the list of neighbours that the room has
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
     * @param neighbour - the neighbour which we want to remove
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
     * if we can remove the entity from list of entities, it does
     * if we cannot, it throws a RunTimeException
     * @param entity - the entity which we want to remove
     */
    public void RemoveEntity(Entity entity) {
        if (!entities.remove(entity))
            throw new RuntimeException("main.Room failed to remove entity.");
    }

    /**
     * @return entities - the list of entities currently in the room
     */
    public ArrayList<Entity> GetEntities() {
        return entities;
    }

    /**
     *
     * @param who - entity who wants to step into the room
     * @return boolean value of whether we can step into the room or not
     */
    public boolean CanStepInto(Entity who) {
        return entities.size() < capacity;
    }


    @Override
    public void StartRound(TimerEvent data) {

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
}
