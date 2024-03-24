package main.itemtypes;

import main.Entity;
import main.Item;
import main.Room;
import main.TimerEvent;

public class Duster extends Item {
    private int usesLeft = 2;

    @Override
    public String GetName() {
        return "Nedves Táblatörlő Rongy";
    }

    /**
     * @param who
     * @param where
     */
    @Override
    public void PickedUp(Entity who, Room where) {

    }

    /**
     * @param who
     * @param where
     */
    @Override
    public void Placed(Entity who, Room where) {

    }

    @Override
    public void StartRound(TimerEvent data) {

    }

}
