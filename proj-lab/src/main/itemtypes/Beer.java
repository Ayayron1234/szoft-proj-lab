package main.itemtypes;

import com.google.gson.JsonObject;
import main.*;

import java.util.Random;
/**
 * Represents a "Szent Söröspohár" item in the game that provides protection against soul drain
 * attacks when picked up by an entity. This protection is temporary, lasting for a specified duration that decrements
 * each round. The beer can be real or fake, affecting its functionality.
 */
public class Beer extends Item {
    private int durationLeft = 3;
    private Protection providedProtection = null;
    private Entity owner = null;
    /**
     * Returns the name of the item, "Szent Söröspohár."
     *
     * @return the name of the item
     */
    @Override
    public String GetName() {
        return "Szent Söröspohár";
    }

    /**
     * Defines the behavior when the beer is picked up by an entity. If the beer is real, it grants the entity
     * protection against soul drain attacks. This protection is registered with the entity and will last for the remaining
     * duration or until the beer is placed down.
     *
     * @param who the entity that picked up the beer
     * @param where the room where the beer was picked up
     */
    @Override
    public void PickedUp(Entity who, Room where) {
        if(!IsFake()) {
            owner = who;
            Protection protection = new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, durationLeft, this);
            providedProtection = protection;
            who.AddProtection(protection);
        }
    }
    /**
     * Defines what happens when the beer is placed in a room by an entity. If the beer is real and was providing protection,
     * that protection is removed from the owner, and the beer stops having any effect.
     *
     * @param who the entity that placed the beer
     * @param where the room where the beer was placed
     */
    @Override
    public void Placed(Entity who, Room where) {
        if(IsFake()) {
            return;
        }

        if (providedProtection != null) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
        }

        owner = null;
    }

    /**
     * Updates the protection's duration at the start of each round. If the protection expires, it is removed from
     * the owner.
     *
     * @param data the event data associated with the start of the round
     */
    @Override
    public void StartRound(TimerEvent data) {
        if (IsFake())
            return;

        if (providedProtection == null)
            return;

        providedProtection.DecreaseDuration();
        durationLeft = providedProtection.GetDuration();
        if (providedProtection.GetDuration() == 0) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
        }
    }

    /**
     * Defines the beer's use when an entity decides to actively use it. It provides immediate protection against soul
     * drain and forces the entity to drop a random item from their inventory as a cost.
     *
     * @param entity the entity using the beer
     */
    @Override
    public void Use(Entity entity) {
        if(IsFake()) return;
        if (entity.GetItems().isEmpty())
            throw new RuntimeException("Entity's inventory should contain at least a beer. ");

        System.out.printf("%s blocks soul drain with %s\n", entity.GetName(), GetName());

        // Entity drops a random item from their inventory
        int indexOfDroppedItem = new Random().nextInt(0, entity.GetItems().size() - 1);
        entity.DropItem(entity.GetItems().get(indexOfDroppedItem));
    }

    /**
     * Restores the state of this beer from a JSON object. The details of deserialization are specific to the game's
     * data structure and should be implemented to match the game's requirements.
     *
     * @param json the JSON object containing the beer's data
     */
    @Override
    public void Deserialize(JsonObject json) {
       // TODO: Implement the deserialization logic
    }
}
