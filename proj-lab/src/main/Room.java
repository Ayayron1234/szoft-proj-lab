package main;

import main.roomabilities.CursedAbility;
import main.roomabilities.PoisonAbility;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Room implements TimerSubscriber {
//    private int capacity;
//    private static int lastUid = 0;
//    private int uid;

    private ArrayList<Room> neighbours = new ArrayList<>();
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    private ArrayList<RoomAbility> abilities = new ArrayList<>();

    /**
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
     */
    public ArrayList<Item> GetItems() {
        System.out.println("Room.GetItems");
        return new ArrayList<>();
    }

    /**
     * Places item to room, adds it to list of items in room
     * @param item - the item placed
     */
    public void PlaceItem(Item item) {
        System.out.println("Room.PlaceItem");
    }

    /**
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
     */
    public int GetCapacity() {
        System.out.println("Room.GetCapacity");
        return 0;
    }

    /**
     * Sets the value of capacity (how many entities fit into the room)
     * @param capacity - capacity of room
     */
    public void SetCapacity(int capacity) {
        System.out.println("Room.SetCapacity");
    }

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
     */
    public ArrayList<Room> GetNeighbours() {
        System.out.println("Room.GetNeighbours");
        return new ArrayList<>();
    }

    /**
     * Adds a new neighbour of the room's list of neighbours
     * @param neighbour - new neighbour of the room
     */
    public void AddNeighbour(Room neighbour) {
        System.out.println("Room.AddNeighbour");
    }

    /**
     * @param neighbour - the neighbour which we want to remove
     * @return - the boolean value of the success of removal
     */
    public boolean RemoveNeighbour(Room neighbour) {
        System.out.println("Room.RemoveNeighbour");
        return true;
    }

    /**
     * Checks if given entity can step into the room
     * if not throws RuntimeException
     * if it can, adds it to room's list of entities
     * @param entity - the entity who wants to step into the room
     */
    public void AcceptEntity(Entity entity) {
        System.out.println("Room.AcceptEntity");
    }

    /**
     * if we can remove the entity from list of entities, it does
     * if we cannot, it throws a RunTimeException
     * @param entity - the entity which we want to remove
     */
    public void RemoveEntity(Entity entity) {
        System.out.println("Room.RemoveEntity");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Can entity be removed?\n 1-yes 2-no");
        String answer = scanner.nextLine();
        if(answer.equals("2")) {
            System.out.println("main.Room failed to remove entity.");
        }
        entities.remove(entity);

        /*if (!entities.remove(entity))
            throw new RuntimeException("main.Room failed to remove entity.");*/
    }

    /**
     * @return entities - the list of entities currently in the room
     */
    public ArrayList<Entity> GetEntities() {
        System.out.println("Room.GetEntities");
        return entities;
    }

    /**
     *
     * @param who - entity who wants to step into the room
     * @return boolean value of whether we can step into the room or not
     */
    public boolean CanStepInto(Entity who) {
        System.out.println("Room.CanStepInto");
        return true;
    }

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
    }

    @Override
    public void StartRound(TimerEvent data) {
        System.out.println("Room.StartRound");
    }

    @Override
    public void EndRound(TimerEvent data) {
        System.out.println("Room.EndRound");
    }

    @Override
    public void StartTurn(TimerEvent data) {
        System.out.println("Room.StartTurn");
    }

    @Override
    public void EndTurn(TimerEvent data) {
        System.out.println("Room.EndTurn");
    }
}
