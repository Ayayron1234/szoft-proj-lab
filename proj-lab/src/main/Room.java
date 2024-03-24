package main;

import java.util.ArrayList;

public class Room implements TimerSubscriber {
    private int capacity;
    private ArrayList<Room> neighbours = new ArrayList<>();
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private static int lastUid = 0;
    private int uid;

    private ArrayList<RoomAbility> abilities = new ArrayList<>();

    public Room(int capacity) {
        this.uid = lastUid++;
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

    public void RemoveEntity(Entity entity) {
        if (!entities.remove(entity))
            throw new RuntimeException("main.Room failed to remove entity.");
    }

    public ArrayList<Entity> GetEntities() {
        return entities;
    }

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
