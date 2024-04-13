package main.itemtypes;

import main.Entity;
import main.Item;
import main.Room;
import main.RoomAbility;
import main.roomabilities.PoisonAbility;

public class AirFreshener extends Item {
    @Override
    public String GetName() {
        return "Légfrissítő";
    }

    @Override
    public void PickedUp(Entity who, Room where) {

    }

    @Override
    public void Placed(Entity who, Room where) {
        // Remove poison ability from the room where it was placed
        Class poisonerClass = PoisonAbility.class;
        where.RemoveAblilityType(poisonerClass);
    }

}
