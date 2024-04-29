package main.itemtypes;

import main.*;
/**
 * Represents a special edition item, "TVSZ Denevérbőrre Nyomtatott Példánya," used in the game to provide protection against soul drain attacks.
 * This item has a limited number of uses and is tied to the entity holding it. Once picked up, it grants protection which decreases with use.
 */
public class TVSZ extends Item {
    private int usesLeft = 3;
    // When in an entity's inventory providedProtection is the object which is in the entity's activeProtections list.
    private Protection providedProtection = null;
    private Entity owner = null;

     /**
     * Returns the name of the item, "TVSZ Denevérbőrre Nyomtatott Példánya."
     *
     * @return the name of the item
     */
    @Override
    public String GetName() {
        return "TVSZ Denevérbőrre Nyomtatott Példánya";
    }

     /**
     * Handles the behavior when the item is picked up by an entity. If real, it activates the protection against soul drain
     * and logs the pickup with the duration of protection left.
     *
     * @param who the entity picking up the TVSZ
     * @param where the room in which the TVSZ was picked up
     */
    @Override
    public void PickedUp(Entity who, Room where) {
        if (IsFake())
            return;

        System.out.printf("%s picked up tvsz with duration:%d.\n", who.GetName(), usesLeft);
        owner = who;
        Protection protection = new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, usesLeft, this);
        providedProtection = protection;
        who.AddProtection(protection);
        // TODO: this
    }

    /**
     * Defines what happens when the TVSZ is placed down by its owner. Removes the protection and logs the action.
     *
     * @param who the entity placing down the TVSZ
     * @param where the room where the TVSZ is placed
     */
    @Override
    public void Placed(Entity who, Room where) {
        if (IsFake())
            return;

        if (providedProtection != null) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
            System.out.printf("%s placed down tvsz and lost protection.\n", who.GetName());
        } else
            System.out.printf("%s placed down tvsz.\n", who.GetName());

        owner = null;
    }


     /**
     * Manages the use of the TVSZ when an entity decides to actively use it for protection. Decreases the uses left and
     * logs the action. If it's the last use, the protection is removed and the item is effectively "used up."
     *
     * @param who the entity using the TVSZ
     */
    @Override
    public void Use(Entity who) {
        if (IsFake())
            return;

        usesLeft--;
        System.out.printf("%s blocks soul drain with %s", who.GetName(), GetName());
        if (usesLeft > 0) {
            //System.out.printf("%s's protection provided by tvsz now has uses left:%d.\n", owner.GetName(), usesLeft);
        }
        else {
            //System.out.printf("%s broke tvsz with last use and lost protection.\n", owner.GetName());
            owner = null;
        }
    }

}
