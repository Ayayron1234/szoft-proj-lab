package main.itemtypes;

import main.*;

public class TVSZ extends Item {
    private int usesLeft = 3;
    // When in an entity's inventory providedProtection is the object which is in the entity's activeProtections list.
    private Protection providedProtection = null;

    @Override
    public String GetName() {
        return "TVSZ Denevérbőrre Nyomtatott Példánya";
    }

    @Override
    public void PickedUp(Entity who, Room where) {
        if (usesLeft == 0) return;

        Protection protection = new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, 3);
        providedProtection = protection;
        // TODO: this
    }

    public void Use() {

    }

}
