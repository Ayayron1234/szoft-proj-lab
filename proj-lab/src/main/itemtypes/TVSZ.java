package main.itemtypes;

import main.*;

public class TVSZ extends Item {
    private int usesLeft = 3;
    // When in an entity's inventory providedProtection is the object which is in the entity's activeProtections list.
    private Protection providedProtection = null;
    private Entity owner = null;

    @Override
    public String GetName() {
        return "TVSZ Denevérbőrre Nyomtatott Példánya";
    }

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
