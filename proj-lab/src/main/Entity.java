package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Array;
import java.util.ArrayList;
<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
import java.util.Scanner;
=======
import java.util.Iterator;
>>>>>>> Stashed changes
>>>>>>> Stashed changes

/**
 * Represents a generic entity in the game, providing a foundation for characters or objects that can
 * interact within the game world. This abstract class includes basic functionalities such as moving between
 * rooms, picking up and dropping items, and managing protections.
 */
<<<<<<< Updated upstream
public abstract class Entity implements TimerSubscriber, Serializable {
    protected int uid;

    Room containingRoom;
    ArrayList<Protection> activeProtections = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    Game game = null;
    int missedRoundsLeft = 0;

    /**
     * This is the constructor of the Entity class with 2 parameters.
=======
<<<<<<< Updated upstream
public abstract class Entity implements TimerSubscriber {
    /**
     * Default constructor for creating an entity without specifying a game context.
     */
    public Entity() { }
=======
public abstract class Entity implements TimerSubscriber, Serializable {
    protected int uid;

    Room containingRoom;
    ArrayList<Protection> activeProtections = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    Game game = null;
    int missedRoundsLeft = 0;
    boolean canMissRounds = true;
>>>>>>> Stashed changes

    /**
     * Constructs an entity within a specified game context.
>>>>>>> Stashed changes
     *
     * @param uid is the unique identitifier of the Entity. (It has to be unique of course).
     * @param game is the game that the entity participates in.
     */
<<<<<<< Updated upstream
    public Entity(int uid, Game game) {
        this.uid = uid;
        this.game = game;
=======
    public Entity(Game game) {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        // Check if destination is reachable from the room in which the entity currently is
        ArrayList<Room> neighbours = containingRoom.GetNeighbours();
        if (!neighbours.contains(destination))
            return false;
=======
        System.out.println("Entity.Step");

        Room containingRoom = new Room(2);
        containingRoom.GetNeighbours();

        destination.CanStepInto(this);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Can entity step into room? (1-yes 2-no)");

        if (scanner.nextLine().equals("1"))
            destination.AcceptEntity(this);
>>>>>>> Stashed changes

        return true;
    }

    /**
     * Provides the name of the entity.
     * Must be implemented by subclasses.
     *
     * @return The name of the entity.
     */
<<<<<<< Updated upstream
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

=======
<<<<<<< Updated upstream
    public abstract String GetName();

    /**
     * Teleports the entity to any room, bypassing adjacency checks.
=======
    public String GetName() {
          return String.format("entity#%d", uid);
    }

    /**
     * This method is the getter of the uid.
     *
     * @return unique identifier of the entity.
     */
    public int GetUID() {
        return uid;
    }

    public abstract void HandleTurn();

    /**
     * Callback for operations when the entity's turn starts.
     * If the entity is stunned it can not perform any actions.
     * @param timerEvent Data containing the current round's number, remaining rounds, and whose turn is it (by indexing)
     */
    @Override
    public void StartTurn(TimerEvent timerEvent) {
        canMissRounds = true;

        if (missedRoundsLeft > 0) {
            System.out.printf("%s misses their turn\n", GetName());
            --missedRoundsLeft;

            if (missedRoundsLeft == 0)
                canMissRounds = false;

            return;
        }

        System.out.printf("it's %s's turn\n", GetName());
        HandleTurn();
    }

    /**
     * Callback for operations when the Entity's turn is over.
     * @param timerEvent Data containing the current round's number, remaining rounds, and whose turn is it (by indexing)
     */
    @Override
    public void EndTurn(TimerEvent timerEvent) { }

    /**
     * Callback for operations when a new round starts.
     * @param timerEvent Data containing the current round's number, remaining rounds, and whose turn is it (by indexing)
     */
    @Override
    public void StartRound(TimerEvent timerEvent) { }

    /**
     * Callback for operations when the round ends.
     * @param timerEvent Data containing the current round's number, remaining rounds, and whose turn is it (by indexing)
     */
>>>>>>> Stashed changes
    @Override
    public void EndRound(TimerEvent timerEvent) { }

    /**
     * Teleports the entity to any room, it's basically just a step that's bypassing to check if the rooms are connected.
<<<<<<< Updated upstream
=======
>>>>>>> Stashed changes
>>>>>>> Stashed changes
     *
     * @param destination The room to teleport to.
     * @return True if the teleportation is successful, false otherwise.
     */
    public boolean Teleport(Room destination) {
<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
        System.out.println("Entity.Teleport");
>>>>>>> Stashed changes
        return MoveToRoom(destination);
=======
        //return MoveToRoom(destination);
        // Check whether room has enough capacity to accept entity
        if (!destination.CanStepInto(this)) {
            //System.out.printf("%s failed to step into room#%d\n", GetName(), destination.GetRoomNumber());
            return false;
        }

        if (containingRoom != null)
            containingRoom.RemoveEntity(this);
        destination.AcceptEntity(this);
        containingRoom = destination;
        //System.out.printf("%s steps to room#%d\n", GetName(), containingRoom.GetRoomNumber());
        return true;
>>>>>>> Stashed changes
    }

    /**
     * Retrieves a list of active protections on the entity.
     *
     * @return An ArrayList of the Active Protections.
     */
    public ArrayList<Protection> GetActiveProtections() {
        System.out.println("Entity.GetActiveProtections");
        return new ArrayList<>();
    }

<<<<<<< Updated upstream
=======
    public Room GetContainingRoom() {
        System.out.println("Entity.GetContainingRoom");
        return new Room(0);
    }

>>>>>>> Stashed changes
    /**
     * Checks if the entity has a specific type of protection.
     *
     * @param type The type of protection to check for.
     * @return True if the entity has the specified protection, false otherwise.
     */
    public boolean HasProtectionType(ProtectionType type) {
<<<<<<< Updated upstream
        for (var protection : activeProtections)
            if (protection.GetType().equals(type))
                return true;
        return false;
=======
        System.out.println("Entity.HasProtectionType");
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Does entity have %s? \n 1-yes 2-no", type.toString());
        String answer = scanner.nextLine();
        if(answer.equals("2")) return false;
        return true;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        activeProtections.add(protection);
=======
        System.out.println("Entity.AddProtection");
>>>>>>> Stashed changes
    }

    /**
     * Removes a protection effect from the entity's activeProtections list.
     *
     * @param protection The protection to remove.
     */
    public void RemoveProtection(Protection protection) {
<<<<<<< Updated upstream
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
=======
        System.out.println("Entity.RemoveProtection");

        Scanner scanner = new Scanner(System.in);
        System.out.printf("Does entity have %s? \n 1-yes 2-no", protection.GetType().toString());

        String answer = scanner.nextLine();
        if(answer.equals("2")) {
            System.out.println("Trying to remove a protection which the entity does not have.");
        }
>>>>>>> Stashed changes
    }

    /**
     * Allows the entity to pick up an item from its current room.
     *
     * @param item The item to pick up.
     */
    public void PickUpItem(Item item) {
<<<<<<< Updated upstream
        if (containingRoom == null || !containingRoom.GetItems().contains(item))
            throw new RuntimeException("Can't pick up item");

        System.out.printf("%s picked up \"%s\"", GetName(), item.GetName());
        containingRoom.PickUpItem(item);
        item.PickedUp(this, containingRoom);
        items.add(item);
=======
        System.out.println("Entity.PickUpItem");

<<<<<<< Updated upstream
        System.out.println("Does the entity have enough space in their inventory?\n 1-yes 2-no");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("2")) {
            System.out.println("The entity couldn't pick up item.");
            return;
        }

        Room room = new Room(0);
        new Room(0).PickUpItem(item);

        item.PickedUp(this, room);
=======
        if (containingRoom.PickUpItem(item)) {
            item.PickedUp(this, containingRoom);
            items.add(item);
        }
        else {
            System.out.printf("%s couldn't pick up \"%s\", because its sticky\n", GetName(), item.GetName());
        }

>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }

    /**
     * Places an item from the entity's inventory into its current room.
     *
     * @param item The item to place down.
     */
    public void PlaceItem(Item item) {
<<<<<<< Updated upstream
        if (containingRoom == null || !items.contains(item))
            throw new RuntimeException("Can't place item");

        System.out.printf("%s placed \"%s\"", GetName(), item.GetName());
        containingRoom.PlaceItem(item);
        item.Placed(this, containingRoom);
        items.remove(item);
=======
        System.out.println("Entity.PlaceItem");

<<<<<<< Updated upstream
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
=======
        System.out.printf("%s placed \"%s\"\n", GetName(), item.GetName());
        containingRoom.PlaceItem(item);
        item.Placed(this, containingRoom);
        items.remove(item);
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }

    /**
     * Drops an item from the entity's inventory without placing it in a specific room.
     *
     * @param item The item to drop.
     */
    public void DropItem(Item item) {
<<<<<<< Updated upstream
        if (containingRoom == null || !items.contains(item))
            throw new RuntimeException("Can't drop item");

        System.out.printf("%s drops %s\n", GetName(), item.GetName());
        containingRoom.PlaceItem(item);
        items.remove(item);
=======
        System.out.println("Entity.DropItem");

<<<<<<< Updated upstream
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the item currently in entity's inventory?\n 1-yes 2-no");

        String answer = scanner.nextLine();
        if(answer.equals("2")) {
            System.out.println("Can't drop item");
        } else {
            new Room(0).PlaceItem(item);
        }
=======
        System.out.printf("%s drops \"%s\"\n", GetName(), item.GetName());
        containingRoom.PlaceItem(item);
        items.remove(item);
>>>>>>> Stashed changes
    }

    public void DropItem(Iterator<Item> iter) {
        Item item = iter.next();

        if (containingRoom == null || !items.contains(item))
            throw new RuntimeException("Can't drop item");

        System.out.printf("%s drops \"%s\"\n", GetName(), item.GetName());
        containingRoom.PlaceItem(item);
        iter.remove();
>>>>>>> Stashed changes
    }

    /**
     * Retrieves the list of items currently held by the entity.
     *
     * @return An ArrayList of Item objects.
     */
    public ArrayList<Item> GetItems() {
        return new ArrayList<>();
    }

    /**
     * Drops all items held by the entity.
     */
    public void DropAllItems() {
<<<<<<< Updated upstream
        for (Item item : items)
            DropItem(item);
=======
<<<<<<< Updated upstream
        System.out.println("Entity.DropAllItems");
=======
        var iter = items.iterator();
        while (iter.hasNext())
            DropItem(iter);
>>>>>>> Stashed changes
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        // Check whether room has enough capacity to accept entity
        if (!destination.CanStepInto(this)) {
            System.out.printf("%s failed to step into room#%d", GetName(), destination.GetRoomNumber());
=======
<<<<<<< Updated upstream
        System.out.println("Entity.MoveToRoom");
=======
        // Check whether room has enough capacity to accept entity
        if (!destination.CanStepInto(this)) {
            System.out.printf("%s failed to step into room#%d\n", GetName(), destination.GetRoomNumber());
>>>>>>> Stashed changes
            return false;
        }

        if (containingRoom != null)
            containingRoom.RemoveEntity(this);
        destination.AcceptEntity(this);
        containingRoom = destination;
<<<<<<< Updated upstream
        System.out.printf("%s steps to room#%d", GetName(), containingRoom.GetRoomNumber());
=======
        System.out.printf("%s steps to room#%d\n", GetName(), containingRoom.GetRoomNumber());
>>>>>>> Stashed changes
>>>>>>> Stashed changes
        return true;
    }
    /**
     * Removes the entity from the game entirely. This includes removing the entity from
     * its current room and from the game's list of active entities. A message is printed
     * to indicate that the entity has dropped out of the game.
     */
<<<<<<< Updated upstream
    public void DropOutOfGame() {
        System.out.printf("%s dropped out of game\n", GetName());

        containingRoom.RemoveEntity(this);
        game.RemoveEntity(this);
=======
    public boolean DropOutOfGame() {
        System.out.println("Entity.DropOutOfGame");
        new Game().RemoveEntity(this);
        return true;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
        System.out.println("Entity.MissRounds");
        return true;
    }
=======
        if (!canMissRounds)
            return false;

>>>>>>> Stashed changes
        System.out.printf("%s misses %d round(s)\n", GetName(), roundCount);
        missedRoundsLeft = roundCount;
        return true;
    }

<<<<<<< Updated upstream
=======
    public boolean CanMissRounds() {
        return canMissRounds;
    }

>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        if(json.has("items")) {
            JsonArray itemsJson = json.get("items").getAsJsonArray();
            for(int i = 0; i < itemsJson.size(); i++) {
                Item item = game.GetDeserializedObjectReference(itemsJson.get(i).getAsString());

=======
        if(json.has("inventory")) {
            JsonArray itemsJson = json.get("inventory").getAsJsonArray();
            for(int i = 0; i < itemsJson.size(); i++) {
                Item item = game.GetDeserializedObjectReference(itemsJson.get(i).getAsString());

                item.SetOwnerIfNeeded(this);
>>>>>>> Stashed changes
                items.add(item);
            }
        }

<<<<<<< Updated upstream
        if(json.has("protections")) {
            JsonArray protectionsJson = json.get("protections").getAsJsonArray();
=======
        if(json.has("activeProtections")) {
            JsonArray protectionsJson = json.get("activeProtections").getAsJsonArray();
>>>>>>> Stashed changes
            for(int i = 0; i < protectionsJson.size(); i++) {
                Protection protection = game.GetDeserializedObjectReference(protectionsJson.get(i).getAsString());

                activeProtections.add(protection);
            }
        }

        return this;
    }
<<<<<<< Updated upstream
=======
>>>>>>> Stashed changes
>>>>>>> Stashed changes
}
