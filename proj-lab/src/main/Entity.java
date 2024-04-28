package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents a generic entity in the game, providing a foundation for characters or objects that can
 * interact within the game world. This abstract class includes basic functionalities such as moving between
 * rooms, picking up and dropping items, and managing protections.
 */
public abstract class Entity implements TimerSubscriber {
    private int uid;

    Room containingRoom;
    ArrayList<Protection> activeProtections = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    Game game = null;
    int missedRoundsLeft = 0;

    public Entity(int uid, Game game) {
        this.uid = uid;
        this.game = game;
    }

    /**
     * Attempts to move the entity to an adjacent (connected) room.
     * Only succeeds if the destination room is directly reachable from the current room.
     *
     * @param destination The room to move to.
     * @return True if the move is successful, false otherwise.
     */
    // Returns true if successful otherwise false. Only works on connected rooms, if destination is not connected, use the Teleport method.
    public boolean Step(Room destination) {
        // Check if destination is reachable from the room in which the entity currently is
        ArrayList<Room> neighbours = containingRoom.GetNeighbours();
        if (!neighbours.contains(destination))
            return false;

        return MoveToRoom(destination);
    }

    /**
     * Provides the name of the entity. Must be implemented by subclasses.
     *
     * @return The name of the entity.
     */
    public String GetName() {
          return String.format("entity#%d", uid);
    }

    public abstract void HandleTurn();

    @Override
    public void StartTurn(TimerEvent timerEvent) {
        if (missedRoundsLeft > 0) {
            System.out.printf("%s missis their turn\n");
            --missedRoundsLeft;
        }

        System.out.printf("it's %s's turn\n", GetName());
    }

    @Override
    public void EndTurn(TimerEvent timerEvent) { }

    @Override
    public void StartRound(TimerEvent timerEvent) { }

    @Override
    public void EndRound(TimerEvent timerEvent) { }

    /**
     * Teleports the entity to any room, bypassing adjacency checks.
     *
     * @param destination The room to teleport to.
     * @return True if the teleportation is successful, false otherwise.
     */
    public boolean Teleport(Room destination) {
        return MoveToRoom(destination);
    }

    /**
     * Retrieves a list of active protections on the entity.
     *
     * @return An ArrayList of {@link Protection} objects.
     */
    public ArrayList<Protection> GetActiveProtections() {
        return activeProtections;
    }

    /**
     * Checks if the entity has a specific type of protection.
     *
     * @param type The type of protection to check for.
     * @return True if the entity has the specified protection, false otherwise.
     */
    public boolean HasProtectionType(ProtectionType type) {
        for (var protection : activeProtections)
            if (protection.GetType().equals(type))
                return true;
        return false;
    }

    // can be null
    public Protection GetProtectionWithType(ProtectionType type) {
        for (var protection : activeProtections)
            if (protection.GetType().equals(type))
                return protection;
        return null;
    }

    /**
     * Adds a protection effect to the entity.
     *
     * @param protection The protection to add.
     */
    public void AddProtection(Protection protection) {
        activeProtections.add(protection);
    }

    /**
     * Removes a protection effect from the entity.
     *
     * @param protection The protection to remove.
     */
    public void RemoveProtection(Protection protection) {
        if (!activeProtections.remove(protection))
            throw new RuntimeException("Trying to remove a protection which the entity does not have. ");
    }

    public Room GetContainingRoom() {
        return containingRoom;
    }

    /**
     * Allows the entity to pick up an item from its current room.
     *
     * @param item The item to pick up.
     */
    public void PickUpItem(Item item) {
        if (containingRoom == null || !containingRoom.GetItems().contains(item))
            throw new RuntimeException("Can't pick up item");

        System.out.printf("%s picked up \"%s\"", GetName(), item.GetName());
        containingRoom.PickUpItem(item);
        item.PickedUp(this, containingRoom);
        items.add(item);
    }

    /**
     * Places an item from the entity's inventory into its current room.
     *
     * @param item The item to place down.
     */
    public void PlaceItem(Item item) {
        if (containingRoom == null || !items.contains(item))
            throw new RuntimeException("Can't place item");

        System.out.printf("%s placed \"%s\"", GetName(), item.GetName());
        containingRoom.PlaceItem(item);
        item.Placed(this, containingRoom);
        items.remove(item);
    }

    /**
     * Drops an item from the entity's inventory without placing it in a specific room.
     *
     * @param item The item to drop.
     */
    public void DropItem(Item item) {
        if (containingRoom == null || !items.contains(item))
            throw new RuntimeException("Can't drop item");

        System.out.printf("%s drops %s\n", GetName(), item.GetName());
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
        if (!destination.CanStepInto(this)) {
            System.out.printf("%s failed to step into room#%d", GetName(), destination.GetRoomNumber());
            return false;
        }

        if (containingRoom != null)
            containingRoom.RemoveEntity(this);
        destination.AcceptEntity(this);
        containingRoom = destination;
        System.out.printf("%s steps to room#%d", GetName(), containingRoom.GetRoomNumber());
        return true;
    }

    public void DropOutOfGame() {
        System.out.printf("%s dropped out of game\n", GetName());

        containingRoom.RemoveEntity(this);
        game.RemoveEntity(this);
    }

    public boolean MissRounds(int roundCount) {
        System.out.printf("%s misses %d round(s)\n", GetName(), roundCount);
        missedRoundsLeft = roundCount;
        return true;
    }

    public int GetSpaceInInventory() {
        // TODO: implement max inventory size
        return 1;
    }
}
