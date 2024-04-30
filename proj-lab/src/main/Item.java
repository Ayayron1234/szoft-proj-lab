package main;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * The abstract base class for items within the game. Items are things that can be interacted with
 * by players or other entities within the game environment. Each item has a unique identifier (UID), which
 * makes it different from other items. Items may be sticky or fake, can provide you protections from some actions or roomabilites.
 */
 public abstract class Item implements TimerSubscriber, Serializable {
    private int     uid;
    private boolean isSticky;
    private boolean isFake; // If true pickedup and placed methods should not be called.

    /**
     * This method is the setter of the uid.
     *
     * @param uid the unique identifier to set of the item.
     */
    public void SetUID(int uid) {
        this.uid = uid;
    }

    /**
     * This method is the getter of the uid.
     *
     * @return unique identifier of the item.
     */
    public int GetUID() {
        return uid;
    }


    /**
     * Retrieves a string representation of the item with its uid.
     *
     * @return A string in the format of item#uid.
     */
    public String toString() { return "item#" + Integer.toString(uid); }

    /**
     * Provides the name of the item.
     * Must be implemented by subclasses.
     *
     * @return The name of the item.
     */
    public abstract String GetName();

    /**
     * This method just because we can log who picked up an item from where
     *
     * @param who the entity who picked up the item
     * @param where the room where the item was picked up from
     */
    public void PickedUp(Entity who, Room where) {
        System.out.printf("%s picked up \"%s\"", GetName(), GetName());
    }

    /**
     * This method just because we can log who placed up an item and where
     *
     * @param who the entity who placed the item
     * @param where the room where the item was placed
     */
    public void Placed(Entity who, Room where) { }

    /**
     * This method is the same as placed
     *
     * @param who the entity who dropped the item
     * @param where the room where the item was dropped
     */
    public void Dropped(Entity who, Room where) {
        Placed(who, where);
    }

    /**
     * This method is to make sure the entity can pick up an item
     *
     * @param who the entity who can pick up an item
     * @return a boolean, it's true if the item is not sticky, otherwise it's false
     */
    public boolean CanPickUp(Entity who) {
        return !IsSticky();
    }

    /**
     * This method is to get if the item is stick or not
     *
     * @return a boolean, it's true if the item is sticky, otherwise (if not) it's false
     */
    public boolean IsSticky() {
        return isSticky;
    }

    /**
     * This method is to get if the item is fake or not
     *
     * @return a boolean, it's true if the item is fake, otherwise (if not) it's false
     */
    public boolean IsFake() {
        return isFake;
    }

    /**
     * The action to be performed when the item is used by an entity.
     *
     * @param who The entity that using the item.
     */
    public void Use(Entity who) {}

    /**
     * Saves the Items attributes into a JsonObject, then returns it
     *
     * @return json - A JsonObject made from the Item
     */
    public JsonObject Serialize() {
        JsonObject json = new JsonObject();
        json.add("id", new JsonPrimitive(String.format("item#%d", uid)));
        json.add("isSticky", new JsonPrimitive(isSticky));
        json.add("isFake", new JsonPrimitive(isFake));
        return json;
    }

    public static Item Deserialize(Game game, JsonObject json) {
        Item item = game.CreateItem(json.get("type").getAsString());

        if (json.has("id")) {
            String idStr = json.get("id").getAsString();
            item.uid = Integer.parseInt(idStr.split("#")[1]);
        }

        if (json.has("isSticky"))
            item.isSticky = json.get("isSticky").getAsBoolean();

        if (json.has("isFake"))
            item.isFake = json.get("isFake").getAsBoolean();

        item.DeserializeSpecificItems(game, json);

        return item;
    }

    protected void DeserializeSpecificItems(Game game, JsonObject json) { }

    @Override
    public void StartRound(TimerEvent data) { }

    @Override
    public void EndRound(TimerEvent data) { }

    @Override
    public void StartTurn(TimerEvent data) { }

    @Override
    public void EndTurn(TimerEvent data) { }

    public void SetOwnerIfNeeded(Entity owner) { }

    public void SetLocationIfNeeded(Room location) { }
}
