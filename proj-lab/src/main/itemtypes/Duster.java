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

    @Override
    public void PickedUp(Entity who, Room where) {
        owner = who;
    }

    @Override
    public void Placed(Entity who, Room where) {
        owner = null;
    }

    @Override
    public void StartRound(TimerEvent timerEvent) {
        if (owner == null || owner.GetContainingRoom() == null)
            return;

        // Apply stunner to each entity in the same room as the duster's owner
        Stunner stunner = new Stunner();
        for (Entity target : owner.GetContainingRoom().GetEntities())
            target.ApplyAction(stunner);
    }
}
