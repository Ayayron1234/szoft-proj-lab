package main.itemtypes;

import main.*;

public class Mask extends Item {
    private int durationLeft = 3;
    private Protection providedProtection;
    private Entity owner;

    @Override
    public String GetName() {
        return "FFP2-es Maszk";
    }

    @Override
    public void PickedUp(Entity who, Room where) {
        Protection protection = new Protection(ProtectionType.POISON_PROTECTION, durationLeft, this);
        providedProtection = protection;
        owner = who;
        who.AddProtection(protection);
    }

    @Override
    public void Placed(Entity who, Room where) {
        if (providedProtection != null) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
        } else

        owner = null;
    }

    @Override
    public void StartRound(TimerEvent data) {
        if (providedProtection == null)
            return;

        providedProtection.DecreaseDuration();
        durationLeft = providedProtection.GetDuration();
        if (providedProtection.GetDuration() == 0) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
        }
    }

    @Override
    public void Use(Entity who) {
        System.out.printf("%s blocked poison with %s", who.GetName(), GetName());
    }
}
