package main.itemtypes;

import main.*;
import main.actions.Stunner;

public class Duster extends Item {
    private int         usesLeft = 2;
    private Entity      owner;

    @Override
    public String GetName() {
        return "Nedves Táblatörlő Rongy";
    }

    /**
     * Function for handling the internal data when the duster is being picked up.
     *
     * @param who Entity object, the entity who is picking up the item.
     * @param where Room object, the room where the item is picked up from.
     */
    @Override
    public void PickedUp(Entity who, Room where) {
        owner = who;
    }

    /**
     * Function for handling the internal data when the duster is being placed down.
     *
     * @param who Entity object, the entity who is placing down the item.
     * @param where Room object, the room where the item is being placed.
     */
    @Override
    public void Placed(Entity who, Room where) {
        owner = null;
    }

    /**
     * Implement the stunning feature of the Duster item.
     * Once picked up, the start of each round, the duster will stun every teacher in the room the owner is in.
     * Decrease the amount of uses it has each time it is used.
     *
     * @param timerEvent TimerEvent object holding roundsLeft and roundCount data points.
     */
    @Override
    public void StartRound(TimerEvent timerEvent) {
        if(IsFake()) return;
        if (owner == null || owner.GetContainingRoom() == null)
            return;

        // Apply stunner to each entity in the same room as the duster's owner
        Stunner stunner = new Stunner();
        for (Entity target : owner.GetContainingRoom().GetEntities())
            target.ApplyAction(stunner);
    }
}
