package main.itemtypes;

import main.*;
import main.actions.Stunner;

import java.util.ArrayList;


public class Duster extends Item {
    private int usesLeft = 2;
    private Entity owner = null;

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
        System.out.printf("%s picked up duster with duration:%d.\n", who.GetName(), usesLeft);
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
        System.out.printf("%s placed down duster.\n", who.GetName());
        owner = null;
    }

    /**
     * Implement the stunning feature of the Duster item.
     * Once picked up, the start of each round, the duster will stun every teacher in the room the owner is in.
     * Decrease the amount of uses it has each time it is used.
     *
     * @param data TimerEvent object holding roundsLeft and roundCount data points.
     */
    @Override
    public void StartRound(TimerEvent data) {
        if(owner == null) return;
        if(usesLeft == 0) return;

        ArrayList<Entity> others = owner.GetContainingRoom().GetEntities();
        others.remove(owner);
        Stunner stunner = new Stunner(1);
        for(Entity teacher : others){
           if(teacher.getClass() == Teacher.class) {
               stunner.Execute(teacher);
           }
        }
        usesLeft -= 1;
    }
}
