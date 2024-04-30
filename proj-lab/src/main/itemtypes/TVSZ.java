package main.itemtypes;

import com.google.gson.JsonObject;
import main.*;

<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
import java.util.Scanner;

=======
/**
 * Represents a protective TVSZ item that can provide protection against souldraining for a few times (the usesLeft indicates it).
 * Once picked up by an entity, it grants a reusable protection against souldraining.
 * It's only activated when it's needed.
 */
>>>>>>> Stashed changes
>>>>>>> Stashed changes
public class TVSZ extends Item {
    private int usesLeft = 3;
    // When in an entity's inventory providedProtection is the object which is in the entity's activeProtections list.
    private Protection providedProtection = null;
    private Entity owner = null;

<<<<<<< Updated upstream
=======
    /**
<<<<<<< Updated upstream
     * This method returns the name of the TVSZ
     * @return A string representing the name of this item.
=======
     * Returns the name of the tvsz.
     *
     * @return A string representing the name of the tvsz.
>>>>>>> Stashed changes
     */
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        if (IsFake())
            return;
=======
<<<<<<< Updated upstream
        System.out.println("TVSZ.PickedUp");
        owner = who;
>>>>>>> Stashed changes

        System.out.printf("%s picked up tvsz with duration:%d.\n", who.GetName(), usesLeft);
=======
        System.out.printf("%s picked up \"%s\"\n", who.GetName(), GetName());
        if(IsFake()) {
            System.out.printf("\"%s\" was a fake item\n", GetName());
            return;
        }
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
        System.out.println("TVSZ.Placed");
=======
>>>>>>> Stashed changes
        if (IsFake())
            return;

        if (providedProtection != null) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
<<<<<<< Updated upstream
            System.out.printf("%s placed down tvsz and lost protection.\n", who.GetName());
        } else
            System.out.printf("%s placed down tvsz.\n", who.GetName());
=======
        }

       // System.out.printf("%s placed \"%s\"\n", who.GetName(), GetName());

>>>>>>> Stashed changes
        owner = null;
>>>>>>> Stashed changes

        owner = null;
    }


<<<<<<< Updated upstream
    @Override
    public void Use(Entity who) {
        if (IsFake())
            return;

        usesLeft--;
        System.out.printf("%s blocks soul drain with %s", who.GetName(), GetName());
        if (usesLeft > 0) {
            //System.out.printf("%s's protection provided by tvsz now has uses left:%d.\n", owner.GetName(), usesLeft);
        }
=======
    /**
<<<<<<< Updated upstream
     * The method is to control when the TVSZ is used (a freshly one is usable 3 times and the game remembers how many times it was used)
     */
    public void Use() {
        System.out.println("TVSZ.Use");

        usesLeft--;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Does the TVSZ have any more uses? 1-yes 2-no");
        String answer = scanner.nextLine();
        if(answer.equals("1"))  return;
        owner = null;

        /*
        usesLeft--;
        if (usesLeft > 0)
            System.out.printf("%s's protection provided by tvsz now has uses left:%d.\n", owner.GetName(), usesLeft);
=======
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
>>>>>>> Stashed changes
>>>>>>> Stashed changes
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
