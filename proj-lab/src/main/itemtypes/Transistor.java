package main.itemtypes;

import main.Entity;
import main.Item;
import main.Room;

public class Transistor extends Item {
    Transistor pair = null;
    Room location = null;

    @Override
    public String GetName() {
        return "Tranzisztor";
    }

    @Override
    public void PickedUp(Entity who, Room where) {
        if (IsFake())
            return;
        // Create pair
        if (pair == null) {
            Transistor pairedTransistor = new Transistor();
            pairedTransistor.pair = this;
            pairedTransistor.location = where;
            pair = pairedTransistor;
        }

        // Place paired transistor into origin room
        where.PlaceItem(pair);
    }

    @Override
    public void Placed(Entity who, Room where) {
        if (IsFake())
            return;
        location = where;

        if (pair == null)
            return;

        // Teleport entity to the paired transistor's location and pick up paired transistor
        who.Teleport(pair.location);
        System.out.printf("%s is teleported to room#%d", who.GetName(), pair.location.GetRoomNumber());
        who.PickUpItem(pair);
    }

    @Override
    public void Dropped(Entity who, Room where) {
        if (IsFake())
            return;
        location = where;
    }
}
