package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Entity implements TimerSubscriber {
    Room containingRoom;
    ArrayList<Protection> activeProtections = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    Game game = null;
    int missedRoundsLeft = 0;

    public Entity() { }

    public Entity(Game game) {
        this.game = game;
    }

    // Returns true if successful otherwise false. Only works on connected rooms, if destination is not connected, use the Teleport method.
    public boolean Step(Room destination) {
        // Check if destination is reachable from the room in which the entity currently is
        ArrayList<Room> neighbours = containingRoom.GetNeighbours();
        if (!neighbours.contains(destination))
            return false;

        return MoveToRoom(destination);
    }

    public abstract String GetName();

    public boolean Teleport(Room destination) {
        return MoveToRoom(destination);
    }

    public ArrayList<Protection> GetActiveProtections() {
        return activeProtections;
    }

    public boolean HasProtectionType(ProtectionType type) {
        for (var protection : activeProtections)
            if (protection.GetType().equals(type))
                return true;
        return false;
    }

    public void AddProtection(Protection protection) {
        activeProtections.add(protection);
    }

    public void RemoveProtection(Protection protection) {
        if (!activeProtections.remove(protection))
            throw new RuntimeException("Trying to remove a protection which the entity does not have. ");
    }

    public void PickUpItem(Item item) {
        if (containingRoom == null || !containingRoom.GetItems().contains(item))
            throw new RuntimeException("Can't pick up item");

        containingRoom.PickUpItem(item);
        item.PickedUp(this, containingRoom);
        items.add(item);
    }

    public void PlaceItem(Item item) {
        if (containingRoom == null || !items.contains(item))
            throw new RuntimeException("Can't place item");

        containingRoom.PlaceItem(item);
        item.Placed(this, containingRoom);
        items.remove(item);
    }

    public void DropItem(Item item) {
        if (containingRoom == null || !items.contains(item))
            throw new RuntimeException("Can't drop item");

        containingRoom.PlaceItem(item);
        items.remove(item);
    }


    public ArrayList<Item> GetItems() {
        return items;
    }

    public void DropAllItems() {
        for (Item item : items)
            DropItem(item);
    }

    public void ApplyAction(Action action) {
        action.Execute(this);
    }

    private boolean MoveToRoom(Room destination) {
        // Check whether room has enough capacity to accept entity
        if (!destination.CanStepInto(this))
            return false;

        if (containingRoom != null)
            containingRoom.RemoveEntity(this);
        destination.AcceptEntity(this);
        containingRoom = destination;
        return true;
    }

    public boolean DropOutOfGame() {
        System.out.printf("%s dropped out of game.\n", GetName());
        containingRoom.RemoveEntity(this);
        if (game != null)
            game.RemoveEntity(this);
        return true;
    }

    public boolean MissRounds(int roundCount) {
        System.out.printf("%s misses %d rounds.\n", GetName(), roundCount);
        missedRoundsLeft = roundCount;
        return true;
    }
}
