package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents a generic entity in the game, providing a foundation for characters or objects that can
 * interact within the game world. This abstract class includes basic functionalities such as moving between
 * rooms, picking up and dropping items, and managing protections.
 */
public abstract class Entity implements TimerSubscriber {
    Room containingRoom; // The room where the entity currently is.
    ArrayList<Protection> activeProtections = new ArrayList<>(); // List of active protections on the entity.
    ArrayList<Item> items = new ArrayList<>(); // Items currently carried by the entity.
    Game game = null; // The game instance to which the entity belongs.
    int missedRoundsLeft = 0; // Rounds the entity is set to miss.

    /**
     * Default constructor for creating an entity without specifying a game context.
     */
    public Entity() { }

    /**
     * Constructs an entity within a specified game context.
     *
     * @param game The game instance to which the entity will be added.
     */
    public Entity(Game game) {
        this.game = game;
    }

    /**
     * Attempts to move the entity to an adjacent (connected) room.
     * Only succeeds if the destination room is directly reachable from the current room.
     *
     * @param destination The room to move to.
     * @return True if the move is successful, false otherwise.
     */
    public boolean Step(Room destination) {
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
    public abstract String GetName();

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
            throw new RuntimeException("Trying to remove a protection which the entity does not have.");
    }

    /**
     * Allows the entity to pick up an item from its current room.
     *
     * @param item The item to pick up.
     */
    public void PickUpItem(Item item) {
        if (containingRoom == null || !containingRoom.GetItems().contains(item))
            throw new RuntimeException("Can't pick up item");

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

        containingRoom.PlaceItem(item);
        items.remove(item);
    }

    /**
     * Retrieves the list of items currently held by the entity.
     *
     * @return An ArrayList of {@link Item} objects.
     */
    public ArrayList<Item> GetItems() {
        return items;
    }

    /**
     * Drops all items held by the entity.
     */
    public void DropAllItems() {
        for (Item item : items)
            DropItem(item);
    }

    /**
     * Applies an action to the entity.
     *
     * @param action The action to apply.
     */
    public void ApplyAction(Action action) {
        action.Execute(this);
    }

    /**
     * Attempts to move the entity to a specified room. The move will be successful only
     * if the destination room has enough capacity to accept the entity. If the entity is
     * already in a room, it will be removed from that room before moving.
     *
     * @param destination The room to which the entity attempts to move.
     * @return True if the move was successful; false otherwise.
     */
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
    /**
     * Removes the entity from the game entirely. This includes removing the entity from
     * its current room and from the game's list of active entities. A message is printed
     * to indicate that the entity has dropped out of the game.
     *
     * @return Always returns true, indicating the entity has been removed from the game.
     */
    public boolean DropOutOfGame() {
        System.out.printf("%s dropped out of game.\n", GetName());
        containingRoom.RemoveEntity(this);
        if (game != null)
            game.RemoveEntity(this);
        return true;
    }
    /**
     * Causes the entity to miss a specified number of rounds. This can be used to
     * temporarily disable an entity's participation in the game, for example, due
     * to an in-game effect or penalty.
     *
     * @param roundCount The number of rounds the entity will miss.
     * @return Always returns true, indicating the action to miss rounds has been applied.
     */
    public boolean MissRounds(int roundCount) {
        System.out.printf("%s misses %d rounds.\n", GetName(), roundCount);
        missedRoundsLeft = roundCount;
        return true;
    }
}
