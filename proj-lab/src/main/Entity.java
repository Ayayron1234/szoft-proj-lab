package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents a generic entity in the game, providing a foundation for characters or objects that can
 * interact within the game world. This abstract class includes basic functionalities such as moving between
 * rooms, picking up and dropping items, and managing protections.
 */
public abstract class Entity implements TimerSubscriber, Serializable {
    protected int uid;

    Room containingRoom;
    ArrayList<Protection> activeProtections = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    Game game = null;
    int missedRoundsLeft = 0;

    /**
     * This is the constructor of the Entity class with 2 parameters.
     *
     * @param uid is the unique identitifier of the Entity. (It has to be unique of course).
     * @param game is the game that the entity participates in.
     */
    public Entity(int uid, Game game) {
        this.uid = uid;
        this.game = game;
    }

    /**
     * Attempts to move the entity to a connected room (that means it has door that opens to the destination room).
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
     * Provides the name of the entity.
     * Must be implemented by subclasses.
     *
     * @return The name of the entity.
     */
    public String GetName() {
          return String.format("entity#%d", uid);
    }

    public int GetUID() {
        return uid;
    }

    public abstract void HandleTurn();

    /**
     *
     *
     * @param timerEvent
     */
    @Override
    public void StartTurn(TimerEvent timerEvent) {
        if (missedRoundsLeft > 0) {
            System.out.printf("%s misses their turn\n");
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
     * Teleports the entity to any room, it's basically just a step that's bypassing to check if the rooms are connected.
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
     * @return An ArrayList of the Active Protections.
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
     * Checks if the entity has a specific type of protection.
     *
     * @param type The type of protection to check for.
     * @return The protection out of the entity's active protections if the entity has the specified ProtectionType, null otherwise.
     */
    // can be null
    public Protection GetProtectionWithType(ProtectionType type) {
        for (var protection : activeProtections)
            if (protection.GetType().equals(type))
                return protection;
        return null;
    }

    /**
     * Adds a protection effect to the entity's activeProtections list.
     *
     * @param protection The protection to add.
     */
    public void AddProtection(Protection protection) {
        activeProtections.add(protection);
    }

    /**
     * Removes a protection effect from the entity's activeProtections list.
     *
     * @param protection The protection to remove.
     */
    public void RemoveProtection(Protection protection) {
        if (!activeProtections.remove(protection))
            throw new RuntimeException("Trying to remove a protection which the entity does not have. ");
    }


    /**
     * A returns the Room object for the room entity is currently standing in.
     *
     * @return containingRoom - the room enitity is currently standing in.
     */
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

    /**
     * Retrieves the list of items currently held by the entity.
     *
     * @return An ArrayList of Item objects.
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
    /**
     * Removes the entity from the game entirely. This includes removing the entity from
     * its current room and from the game's list of active entities. A message is printed
     * to indicate that the entity has dropped out of the game.
     */
    public void DropOutOfGame() {
        System.out.printf("%s dropped out of game\n", GetName());

        containingRoom.RemoveEntity(this);
        game.RemoveEntity(this);
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
        System.out.printf("%s misses %d round(s)\n", GetName(), roundCount);
        missedRoundsLeft = roundCount;
        return true;
    }

    /**
     * This method returns the space left in the entity's Inventory.
     *
     * @return an int value that is the space left in the entity's Inventory.
     */
    public int GetSpaceInInventory() {
        // TODO: implement max inventory size
        return 1;
    }

    public static Entity Deserialize(Game game, JsonObject json) {
        int uid = Integer.parseInt(json.get("id").getAsString().split("#")[1]);

        Entity entity = game.CreateEntity(json.get("type").getAsString(), uid);
        entity.DeserializeImpl(game, json);
        return entity;
    }

    protected Entity DeserializeImpl(Game game, JsonObject json) {
        if (!json.has("id") || !json.has("type"))
            throw new RuntimeException("");

        String idStr = json.get("id").getAsString();
        uid = Integer.parseInt(idStr.split("#")[1]);

        if(json.has("items")) {
            JsonArray itemsJson = json.get("items").getAsJsonArray();
            for(int i = 0; i < itemsJson.size(); i++) {
                Item item = game.GetDeserializedObjectReference(itemsJson.get(i).getAsString());

                items.add(item);
            }
        }

        if(json.has("protections")) {
            JsonArray protectionsJson = json.get("protections").getAsJsonArray();
            for(int i = 0; i < protectionsJson.size(); i++) {
                Protection protection = game.GetDeserializedObjectReference(protectionsJson.get(i).getAsString());

                activeProtections.add(protection);
            }
        }

        return this;
    }
}
