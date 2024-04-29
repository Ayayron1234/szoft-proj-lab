package main.itemtypes;

import main.Entity;
import main.Item;
import main.Room;
import main.TimerEvent;
import main.actions.Poisoner;
/**
 * Represents a "Dobozolt Káposztás Camembert" item in the game, which has the ability
 * to poison rooms temporarily when placed. This item can be picked up and moved around but only activates its
 * effect when placed in a room and not fake.
 */
public class Camembert extends Item {
    private int duration = 3;
    private Room activationPlace = null;

     /**
     * Returns the name of the item, "Dobozolt Káposztás Camembert."
     *
     * @return the name of the item
     */
    @Override
    public String GetName() {
        return "Dobozolt Káposztás Camembert";
    }

     /**
     * Resets the activation place when the item is picked up. This ensures the item doesn't leave effect
     * in the previous room.
     *
     * @param who the entity picking up the item
     * @param where the room from which the item is picked up
     */
    @Override
    public void PickedUp(Entity who, Room where) {
        activationPlace = null;
    }

    /**
     * Checks whether the item can be picked up by an entity. The item can only be picked up if it's not currently
     * active in any room.
     *
     * @param who the entity attempting to pick up the item
     * @return true if the item can be picked up, false otherwise
     */
    @Override
    public boolean CanPickUp(Entity who) {
        return activationPlace == null;
    }

    /**
     * Activates the item's poisonous effect when placed in a room, provided the item is not fake. Sets the room as
     * the activation place where the poisoning will happen.
     *
     * @param who the entity placing the item
     * @param where the room in which the item is placed
     */
    @Override
    public void Placed(Entity who, Room where) {
        if(!IsFake()) {
            activationPlace = where;
        }
    }

     /**
     * Handles what happens when the item is dropped in a room, specifically noting that dropping does not activate
     * the item's effect.
     *
     * @param who the entity that dropped the item
     * @param where the room where the item was dropped
     */
    @Override
    public void Dropped(Entity who, Room where) {
        // When dropped involuntarily does not poison the room
    }

    /**
     * Executes during the start of each round to apply the item's effect if it is active in a room. It poisons all
     * entities in the room and decreases the duration of the effect each round until it expires.
     *
     * @param data the event data for the current round
     */
    @Override
    public void StartRound(TimerEvent data) {
        if(IsFake())
            return;

        // Return if camembert isn't activated
        if (activationPlace == null)
            return;

        System.out.printf("%item#%d poisons room\n", GetUID());
        Poisoner poisoner = new Poisoner();
        for (Entity entity : activationPlace.GetEntities()) {
            entity.ApplyAction(poisoner);
        }

        --duration;

        if (duration == 0) {
            System.out.printf("Room #%d is no longer poisoned by camambert.\n", activationPlace.GetRoomNumber(), duration);
            activationPlace = null;
            // TODO: delete item
        }
        else
            System.out.printf("Room #%d is poisoned by camambert for duration:%d.\n", activationPlace.GetRoomNumber(), duration);
    }
}
