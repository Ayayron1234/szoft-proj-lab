package main.itemtypes;

import main.Entity;
import main.Item;
import main.Room;
import main.roomabilities.PoisonAbility;

public class AirFreshener extends Item {
    @Override
    public String GetName() {
        return "Légfrissítő";
    }

    @Override
    public void Placed(Entity who, Room where) {
        // If item is fake, it has no effect
        if (IsFake())
            return;

        // Remove poison ability from the room where it was placed
        Class poisonerClass = PoisonAbility.class;
        where.RemoveAbilityType(poisonerClass);
        System.out.printf("%s refreshes air\n", GetName());
    }

    @Override
    public void Dropped(Entity who, Room where) {
        // If item is fake, it has no effect
        if (IsFake())
            return;

        // Remove poison ability from the room where it was placed
        Class poisonerClass = PoisonAbility.class;
        where.RemoveAbilityType(poisonerClass);
        System.out.printf("%s refreshes air\n", GetName());
    }

}
