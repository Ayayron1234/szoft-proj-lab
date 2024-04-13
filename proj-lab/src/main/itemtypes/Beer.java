package main.itemtypes;

import main.*;

import java.util.Random;

public class Beer extends Item {
    private int durationLeft = 3;
    private Protection providedProtection = null;
    private Entity owner = null;

    @Override
    public String GetName() {
        return "Szent Söröspohár";
    }

    @Override
    public void PickedUp(Entity who, Room where) {
        System.out.printf("%s picked up beer with duration:%d.\n", who.GetName(), durationLeft);

        Protection protection = new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, durationLeft, this);
        providedProtection = protection;
        owner = who;
        who.AddProtection(protection);
    }

    @Override
    public void Placed(Entity who, Room where) {
        if (providedProtection != null) {
            owner.RemoveProtection(providedProtection);
            providedProtection = null;
            System.out.printf("%s placed down beer and lost protection.\n", who.GetName());
        } else
            System.out.printf("%s placed down beer.\n", who.GetName());

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
            System.out.printf("%s lost protection from beer.\n", owner.GetName());
        }
        else
            System.out.printf("%s's protection provided by beer now has duration:%d.\n", owner.GetName(), durationLeft);
    }


    @Override
    public void Use(Entity entity) {
        if (entity.GetItems().isEmpty())
            throw new RuntimeException("Entity's inventory should contain at least a beer. ");

        // Entity drops a random item from their inventory
        int indexOfDroppedItem = new Random().nextInt(0, entity.GetItems().size() - 1);
        entity.DropItem(entity.GetItems().get(indexOfDroppedItem));
    }
}
