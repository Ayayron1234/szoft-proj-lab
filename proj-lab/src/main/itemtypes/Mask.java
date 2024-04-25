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
        System.out.printf("%s picked up mask with duration:%d.\n", who.GetName(), durationLeft);

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
            System.out.printf("%s placed down mask and lost protection.\n", who.GetName());
        } else
            System.out.printf("%s placed down mask.\n", who.GetName());

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
            System.out.printf("%s lost protection from mask.\n", owner.GetName());
        }
        else
            System.out.printf("%s's protection provided by mask now has duration:%d.\n", owner.GetName(), durationLeft);
    }


}
