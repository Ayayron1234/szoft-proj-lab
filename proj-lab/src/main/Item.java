package main;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public abstract class Item implements TimerSubscriber, Serializable {
    private int     uid;
    private boolean isSticky;
    private boolean isFake; // If true pickedup and placed methods should not be called.

    public void SetUID(int uid) {
        this.uid = uid;
    }

    public int GetUID() {
        return uid;
    }

    public String toString() { return "item#" + Integer.toString(uid); }

    public abstract String GetName();

    /**
     * This method just because we can log who picked up an item from where
     *
     * @param who the entity who picked up the item
     * @param where the room where the item was picked up from
     */
    public void PickedUp(Entity who, Room where) { }

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

    public boolean IsFake() {
        return isFake;
    }

    public void Use(Entity who) {}

    public JsonObject Serialize() {
        JsonObject json = new JsonObject();
        json.add("id", new JsonPrimitive(String.format("item#%d", uid)));
        json.add("isSticky", new JsonPrimitive(isSticky));
        json.add("isFake", new JsonPrimitive(isFake));
        return json;
    }

    public void Deserialize(JsonObject json) {
        if (json.has("id")) {
            String idStr = json.get("id").getAsString();
            uid = Integer.parseInt(idStr.split("#")[1]);
        }

        if (json.has("isSticky"))
            isSticky = json.get("isSticky").getAsBoolean();

        if (json.has("isFake"))
            isFake = json.get("isFake").getAsBoolean();
    }


    @Override
    public void StartRound(TimerEvent data) { }

    @Override
    public void EndRound(TimerEvent data) { }

    @Override
    public void StartTurn(TimerEvent data) { }

    @Override
    public void EndTurn(TimerEvent data) { }
}
