package main;

import main.actions.Poisoner;

import java.util.ArrayList;

public class Room implements TimerSubscriber {
    private int                     uid;
    private int                     capacity;

    private ArrayList<Room>         neighbours = new ArrayList<>();
    private ArrayList<Entity>       entities = new ArrayList<>();
    private ArrayList<Item>         items = new ArrayList<>();

    boolean                         isSticky = false;
    private ArrayList<RoomAbility>  abilities = new ArrayList<>();

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

    public void Clean() {
        isSticky = false;
        RemoveAbilityType(Poisoner.class);
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
