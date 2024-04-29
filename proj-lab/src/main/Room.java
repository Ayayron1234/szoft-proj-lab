package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import main.actions.Poisoner;
import main.roomabilities.CursedAbility;
import main.roomabilities.PoisonAbility;

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

    public String toString(){
        return "room#" + Integer.toString(uid);
    }

    @Override
    public void StartRound(TimerEvent data) {
        for(RoomAbility ra : abilities){
            ra.Activate(this);
        }
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

    public JsonObject Serialize() {
        JsonObject json = new JsonObject();
        json.add("id", new JsonPrimitive(String.format("item#%d", uid)));

        json.add("capacity", new JsonPrimitive(capacity));

        JsonArray neigboursJson = new JsonArray();
        for (Room room : neighbours)
            neigboursJson.add(String.format("room#%d", room.GetRoomNumber()));
        json.add("neighbours", neigboursJson);

        JsonArray entitiesJson = new JsonArray();
        for (Entity entity : entities)
            entitiesJson.add(String.format("entity#%d", entity.GetUID()));
        json.add("containedEntities", entitiesJson);

        JsonArray itemsJson = new JsonArray();
        for (Item item : items)
            itemsJson.add(String.format("item#%d", item.GetUID()));
        json.add("items", itemsJson);

        json.add("isSticky", new JsonPrimitive(isSticky));

        JsonArray abilitiesJson = new JsonArray();
        for (RoomAbility ability : abilities)
            abilitiesJson.add(ability.GetTypeString());
        json.add("abilities", abilitiesJson);

        return json;
    }

    public static Room Deserialize(Game game, JsonObject json) {
        if (!json.has("id") || !json.has("capacity"))
            throw new RuntimeException("");

        int uid = Integer.parseInt(json.get("id").getAsString().split("#")[1]);

        Room room = game.GetOrCreateDeserializedObjectReference(json.get("id").getAsString(),
                () -> game.CreateRoom(json.get("capacity").getAsInt()));
        room.uid = uid;

        if (json.has("neighbours")) {
            JsonArray neighboursJson = json.get("neighbours").getAsJsonArray();
            for (int i = 0; i < neighboursJson.size(); ++i) {
                Room neighbour = game.GetOrCreateDeserializedObjectReference(neighboursJson.get(i).getAsString(),
                        () -> game.CreateRoom(-1));

                room.neighbours.add(neighbour);
            }
        }

        if (json.has("containedEntities")) {
            JsonArray entitiesJson = json.get("containedEntities").getAsJsonArray();
            for (int i = 0; i < entitiesJson.size(); ++i) {
                Entity entity = game.GetDeserializedObjectReference(entitiesJson.get(i).getAsString());
                entity.Teleport(room);
            }
        }

        if (json.has("items")) {
            JsonArray itemsJson = json.get("items").getAsJsonArray();
            for (int i = 0; i < itemsJson.size(); ++i) {
                Item item = game.GetDeserializedObjectReference(itemsJson.get(i).getAsString());

                room.items.add(item);
            }
        }

        if (json.has("isSticky"))
            room.isSticky = json.get("isSticky").getAsBoolean();

        if (json.has("abilities")) {
            JsonArray abilitiesJson = json.get("abilities").getAsJsonArray();
            for (int i = 0; i < abilitiesJson.size(); ++i) {
                String abilityStr = abilitiesJson.get(i).getAsString();
                if (abilityStr.equals(new CursedAbility().GetTypeString()))
                    room.abilities.add(new CursedAbility());
                if (abilityStr.equals(new PoisonAbility().GetTypeString()))
                    room.abilities.add(new PoisonAbility());
            }
        }

        return room;
    }

}
