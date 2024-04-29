package main.itemtypes;

import main.Entity;
import main.Item;
import main.Room;
import main.TimerEvent;
import main.actions.Poisoner;

public class Camembert extends Item {
    private int duration = 3;
    private Room activationPlace = null;

    @Override
    public String GetName() {
        return "Dobozolt Káposztás Camembert";
    }

    @Override
    public void PickedUp(Entity who, Room where) {
        activationPlace = null;
    }

    @Override
    public boolean CanPickUp(Entity who) {
        return activationPlace == null;
    }

    @Override
    public void Placed(Entity who, Room where) {
        if(!IsFake()) {
            activationPlace = where;
        }
    }

    @Override
    public void Dropped(Entity who, Room where) {
        // When dropped involuntarily does not poison the room
    }

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
