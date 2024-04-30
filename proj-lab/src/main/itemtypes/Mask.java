package main.itemtypes;

import com.google.gson.JsonObject;
import main.*;

/**
 * Represents a protective Mask item that can provide protection against specific
 * threats (e.g., poison) for a limited duration. Once picked up by an entity,
 * it grants a protection effect that decreases in duration each round until it expires.
 */
public class Mask extends Item {
    private int durationLeft = 3;
    private Protection providedProtection;
    private Entity owner;

    /**
     * Returns the name of the mask.
     *
     * @return A string representing the name of the mask.
     */
    @Override
    public String GetName() {
        return "FFP2-es Maszk";
    }

    /**
     * Defines the action to be taken when the mask is picked up by an entity.
     * It assigns the mask to the entity, applies protection to them, and updates the owner.
     *
     * @param who The entity picking up the mask.
     * @param where The room where the mask was picked up.
     */
    @Override
    public void PickedUp(Entity who, Room where) {
        System.out.printf("%s picked up \"%s\"\n", who.GetName(), GetName());
        if(IsFake()) {
            System.out.printf("\"%s\" was a fake item\n", GetName());
            return;
        }

        owner = who;
        Protection protection = new Protection(ProtectionType.POISON_PROTECTION, durationLeft, this);
        providedProtection = protection;
        who.AddProtection(protection);
    }

    /**
     * Defines the action to be taken when the mask is placed by an entity.
     * It removes the protection from the owner and clears the reference to the owner.
     *
     * @param who The entity placing down the mask.
     * @param where The room where the mask is placed.
     */
    @Override
    public void Placed(Entity who, Room where) {
        if (IsFake())
            return;

        if (providedProtection != null) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
        }

        owner = null;
    }

    /**
     * Handles the event of starting a new round. It decreases the duration of the protection
     * by one. If the protection expires, it removes the protection from the owner.
     *
     * @param data The event data related to the timer event.
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
     * With the use of the mask, poison effect is negated.
     * @param who The entity that using the item.
     */
    @Override
    public void Use(Entity who) {
        System.out.printf("%s blocked poison with \"%s\"\n", who.GetName(), GetName());
//        durationLeft--;

    }

    /**
     * Deserialize a json object into a Mask object
     * @param json
     */
    @Override
    public void DeserializeSpecificItems(Game game, JsonObject json) {
        if(json.has("duration")) {
            durationLeft = json.get("duration").getAsInt();
        }

        if(json.has("providedProtection")) {
            Protection protection = game.GetDeserializedObjectReference(json.get("providedProtection").getAsString());
            providedProtection = protection;
            providedProtection.SetProvider(this);
            providedProtection.SetDuration(durationLeft);
        }
    }

    @Override
    public void SetOwnerIfNeeded(Entity owner) {
        this.owner = owner;
    }

}
