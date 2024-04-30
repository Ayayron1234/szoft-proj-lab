package main.itemtypes;

import com.google.gson.JsonObject;
import main.*;

import java.util.Scanner;


/**
 * Represents a protective TVSZ item that can provide protection against souldraining for a few times (the usesLeft indicates it).
 * Once picked up by an entity, it grants a reusable protection against souldraining.
 * It's only activated when it's needed.
 */
public class TVSZ extends Item {
    private int usesLeft = 3;
    // When in an entity's inventory providedProtection is the object which is in the entity's activeProtections list.
    private Protection providedProtection = null;
    private Entity owner = null;

    /**
     * Returns the name of the tvsz.
     *
     * @return A string representing the name of the tvsz.
    */
    @Override
    public String GetName() {
        return "TVSZ Denevérbőrre Nyomtatott Példánya";
    }

    /**
     * Pick up the TVSZ.
     * The TVSZ grants protection against Soul Drain for unlimited amount of time, but finite uses.
     * @param who   the entity who picked up the item
     * @param where the room where the item was picked up from
     */
    @Override
    public void PickedUp(Entity who, Room where) {
        if (IsFake())
            return;

        System.out.printf("%s picked up \"%s\"\n", who.GetName(), GetName());
        if(IsFake()) {
            System.out.printf("\"%s\" was a fake item\n", GetName());
            return;
        }
        owner = who;
        Protection protection = new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, usesLeft, this);
        providedProtection = protection;
        who.AddProtection(protection);
        // TODO: this
    }

    /**
     * Place down the TVSZ.
     * Remove Soul Drain protection from its owner.
     * @param who   the entity who placed the item
     * @param where the room where the item was placed
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
     * When using the TVSZ it negates a Soul Drain action aimed towards its owner.
     * Decrement its available use count when this happens.
     * @param who The entity that using the item.
     */
    @Override
    public void Use(Entity who) {
        if (IsFake())
            return;

        usesLeft--;
        System.out.printf("%s blocks soul drain with \"%s\"\n", who.GetName(), GetName());
        if (usesLeft > 0) {
            //System.out.printf("%s's protection provided by tvsz now has uses left:%d.\n", owner.GetName(), usesLeft);
        }
        else {
            //System.out.printf("%s broke tvsz with last use and lost protection.\n", owner.GetName());
            owner = null;
        }
    }

    @Override
    public void DeserializeSpecificItems(Game game, JsonObject json) {
        if(json.has("durationLeft")) {
            usesLeft = json.get("durationLeft").getAsInt();
        }

        if(json.has("providedProtection")) {
            Protection protection = game.GetDeserializedObjectReference(json.get("providedProtection").getAsString());
            providedProtection = protection;
            providedProtection.SetProvider(this);
        }
    }

    @Override
    public void SetOwnerIfNeeded(Entity owner) {
        this.owner = owner;
    }

}
