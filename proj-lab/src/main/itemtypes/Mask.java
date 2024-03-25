
package main.itemtypes;

import main.*;

/**
 * Represents a protective mask item that can provide protection against specific
 * threats (e.g., poison) for a limited duration. Once picked up by an entity,
 * it grants a protection effect that decreases in duration each round until it expires.
 */
public class Mask extends Item {
    private int durationLeft = 3; // The number of rounds the protection is effective for.
    private Protection providedProtection; // The specific protection provided by the mask.
    private Entity owner; // The entity currently owning the mask.

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
        System.out.printf("%s picked up mask with duration:%d.\n", who.GetName(), durationLeft);

        Protection protection = new Protection(ProtectionType.POISON_PROTECTION, durationLeft);
        providedProtection = protection;
        owner = who;
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
        if (providedProtection != null) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
            System.out.printf("%s placed down mask and lost protection.\n", who.GetName());
        } else {
            System.out.printf("%s placed down mask.\n", who.GetName());
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
        if (providedProtection == null)
            return;

        providedProtection.DecreaseDuration();
        durationLeft = providedProtection.GetDuration();
        if (providedProtection.GetDuration() == 0) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
            System.out.printf("%s lost protection from mask.\n", owner.GetName());
        } else {
            System.out.printf("%s's protection provided by mask now has duration:%d.\n", owner.GetName(), durationLeft);
        }
    }
}