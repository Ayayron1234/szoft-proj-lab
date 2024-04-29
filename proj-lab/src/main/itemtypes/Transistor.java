package main.itemtypes;

import main.Entity;
import main.Item;
import main.Room;
/**
 * Represents a "Tranzisztor" item in the game, capable of teleporting entities between two linked locations.
 * When picked up, it automatically creates and places its pair in the same room, allowing for a teleportation loop between the two.
 */
public class Transistor extends Item {
    Transistor pair = null;
    Room location = null;

      /**
     * Returns the name of the item, "Tranzisztor."
     *
     * @return the name of the item
     */
    @Override
    public String GetName() {
        return "Tranzisztor";
    }

    /**
     * Defines the behavior when the transistor is picked up by an entity. If real, it ensures there is a pair placed
     * in the same room. If no pair exists yet, it creates one and places it there, setting up a teleportation link.
     *
     * @param who the entity picking up the transistor
     * @param where the room from which the transistor is picked up
     */
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

    /**
     * Handles the placement of the transistor in a room. If real and a pair exists, it teleports the entity to the
     * location of the paired transistor and automatically picks up the paired transistor.
     *
     * @param who the entity placing the transistor
     * @param where the room in which the transistor is placed
     */
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

     /**
     * When the transistor is dropped (as opposed to being placed), it updates its location to the new room. Dropping does not
     * trigger the teleportation process.
     *
     * @param who the entity that dropped the transistor
     * @param where the room where the transistor is dropped
     */
    @Override
    public void Dropped(Entity who, Room where) {
        if (IsFake())
            return;
        location = where;
    }
}
