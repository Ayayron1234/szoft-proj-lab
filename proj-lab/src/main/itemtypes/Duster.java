package main.itemtypes;

import com.google.gson.JsonObject;
import main.*;
import main.actions.Stunner;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a protective Duster item that can provide protection for a few times (the usesLeft indicates it).
 * Once picked up by an entity, it grants a reusable protection that stuns the Teacher Entity who tries to drain.
 */
public class Duster extends Item {
    private int         usesLeft = 2;
    private Entity      owner;

    /**
     * Returns the name of the duster.
     *
     * @return A string representing the name of the duster.
     */
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

        owner = who;
        System.out.printf("%s picked up \"%s\"\n", who.GetName(), GetName());
        if(IsFake()) {
            System.out.printf("\"%s\" was a fake item\n", GetName());
            return;
        }
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
        if (owner == null && owner.GetContainingRoom() == null)
            return;

        // Apply stunner to each entity in the same room as the duster's owner
        Stunner stunner = new Stunner();
        for (Entity target : owner.GetContainingRoom().GetEntities()) {
            if (target == owner)
                continue;

            System.out.printf("%s stuns %s with \"%s\"\n", owner.GetName(), target.GetName(), GetName());
            target.ApplyAction(stunner);
        }

    }

    @Override
    public void DeserializeSpecificItems(Game game, JsonObject json) {
        if(json.has("durationLeft")) {
            usesLeft = json.get("durationLeft").getAsInt();
        }
    }

    @Override
    public void SetOwnerIfNeeded(Entity owner) {
        this.owner = owner;
    }
}
