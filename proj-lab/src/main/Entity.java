package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a generic entity in the game, providing a foundation for characters or objects that can
 * interact within the game world. This abstract class includes basic functionalities such as moving between
 * rooms, picking up and dropping items, and managing protections.
 */
public abstract class Entity implements TimerSubscriber {
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
    }

    /**
     * Attempts to move the entity to an adjacent (connected) room.
     * Only succeeds if the destination room is directly reachable from the current room.
     *
     * @param destination The room to move to.
     * @return True if the move is successful, false otherwise.
     */
    public boolean Step(Room destination) {
        System.out.println("Entity.Step");

        Room containingRoom = new Room(2);
        containingRoom.GetNeighbours();

        destination.CanStepInto(this);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Can entity step into room? (1-yes 2-no)");

        if (scanner.nextLine().equals("1"))
            destination.AcceptEntity(this);

        return true;
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
        System.out.println("Entity.Teleport");
        return MoveToRoom(destination);
    }

    /**
     * Retrieves a list of active protections on the entity.
     *
     * @return An ArrayList of {@link Protection} objects.
     */
    public ArrayList<Protection> GetActiveProtections() {
        System.out.println("Entity.GetActiveProtections");
        return new ArrayList<>();
    }

    public Room GetContainingRoom() {
        System.out.println("Entity.GetContainingRoom");
        return new Room(0);
    }

    /**
     * Checks if the entity has a specific type of protection.
     *
     * @param type The type of protection to check for.
     * @return True if the entity has the specified protection, false otherwise.
     */
    public boolean HasProtectionType(ProtectionType type) {
        System.out.println("Entity.HasProtectionType");
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Does entity have %s? \n 1-yes 2-no", type.toString());
        String answer = scanner.nextLine();
        if(answer.equals("2")) return false;
        return true;
    }

    /**
     * Adds a protection effect to the entity.
     *
     * @param protection The protection to add.
     */
    public void AddProtection(Protection protection) {
        System.out.println("Entity.AddProtection");
    }

    /**
     * Removes a protection effect from the entity.
     *
     * @param protection The protection to remove.
     */
    public void RemoveProtection(Protection protection) {
        System.out.println("Entity.RemoveProtection");

        Scanner scanner = new Scanner(System.in);
        System.out.printf("Does entity have %s? \n 1-yes 2-no", protection.GetType().toString());

        String answer = scanner.nextLine();
        if(answer.equals("2")) {
            System.out.println("Trying to remove a protection which the entity does not have.");
        }
    }

    /**
     * Allows the entity to pick up an item from its current room.
     *
     * @param item The item to pick up.
     */
    public void PickUpItem(Item item) {
        System.out.println("Entity.PickUpItem");

        System.out.println("Does the entity have enough space in their inventory?\n 1-yes 2-no");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("2")) {
            System.out.println("The entity couldn't pick up item.");
            return;
        }

        Room room = new Room(0);
        new Room(0).PickUpItem(item);

        item.PickedUp(this, room);
    }

    /**
     * Places an item from the entity's inventory into its current room.
     *
     * @param item The item to place down.
     */
    public void PlaceItem(Item item) {
        System.out.println("Entity.PlaceItem");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the item currently in entity's inventory?\n 1-yes 2-no");

        String answer = scanner.nextLine();
        if(answer.equals("2")) {
            System.out.println("Can't place item");
        }
        else {
            new Room(0).PlaceItem(item);
            item.Placed(this, new Room(0));
        }
    }

    /**
     * Drops an item from the entity's inventory without placing it in a specific room.
     *
     * @param item The item to drop.
     */
    public void DropItem(Item item) {
        System.out.println("Entity.DropItem");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the item currently in entity's inventory?\n 1-yes 2-no");

        String answer = scanner.nextLine();
        if(answer.equals("2")) {
            System.out.println("Can't drop item");
        } else {
            new Room(0).PlaceItem(item);
        }
    }

    /**
     * Retrieves the list of items currently held by the entity.
     *
     * @return An ArrayList of {@link Item} objects.
     */
    public ArrayList<Item> GetItems() {
        return new ArrayList<>();
    }

    /**
     * Drops all items held by the entity.
     */
    public void DropAllItems() {
        System.out.println("Entity.DropAllItems");
    }

    /**
     * Applies an action to the entity.
     *
     * @param action The action to apply.
     */
    public void ApplyAction(Action action) {
        System.out.println("Entity.ApplyAction");
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
        System.out.println("Entity.MoveToRoom");
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
        System.out.println("Entity.DropOutOfGame");
        new Game().RemoveEntity(this);
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
        System.out.println("Entity.MissRounds");
        return true;
    }
}
