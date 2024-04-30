package main.itemtypes;

import com.google.gson.JsonObject;
import main.*;
import main.actions.Poisoner;

/**
 * Represents an aggressive Camembert item that can make rooms become poisoned.
 * Once it's placed down the room will be poisoned for 3 rounds.
 */
public class Camembert extends Item {
    private int duration = 3;
    private Room activationPlace = null;

    /**
     * Returns the name of the camembert.
     *
     * @return A string representing the name of the camembert.
     */
    @Override
    public String GetName() {
        return "Dobozolt Káposztás Camembert";
    }

    @Override
    public boolean CanPickUp(Entity who) {
        return activationPlace == null;
    }

    /*@Override
    public void StartRound(TimerEvent data) {
        if (IsFake())
    }*/

    /**
     * Determines whether the item can be picked up by an entity. The Camembert can only
     * be picked up if it is not currently activating its effects in a room.
     *
     * @param who The entity attempting to pick up the item.
     * @return True if the item can be picked up, false otherwise.

     * Pick up the Camembert item.
     * @param who   the entity who picked up the item
     * @param where the room where the item was picked up from
     */
    @Override
    public void PickedUp(Entity who, Room where) {

        System.out.printf("%s picked up \"%s\"\n", who.GetName(), GetName());
        if(IsFake()) {
            System.out.printf("\"%s\" was a fake item\n", GetName());
            return;
        }
        activationPlace = null;
    }

    /**
     * Place down the camembert on the ground.
     * This causes a poison effect in the room. (applied at the start of the round)
     *
     * @param who   the entity who placed the item
     * @param where the room where the item was placed
     */
    @Override
    public void Placed(Entity who, Room where) {
        if(!IsFake()) {
            activationPlace = where;
        }
        //System.out.printf("%s placed \"%s\"\n", who.GetName(), GetName());
    }

    /**
     * Drop the camembert. This does not cause a poison effect.
     *
     * @param who   the entity who dropped the item
     * @param where the room where the item was dropped
     */
    @Override
    public void Dropped(Entity who, Room where) {
        // When dropped involuntarily does not poison the room
    }

    /**
     * If the camembert is placed, poisonate the room it is in. The room is poisoned for a certain duration.
     * @param data Data containing the current round's number, remaining rounds, and whose turn is it (by indexing)

     */
    @Override
    public void StartRound(TimerEvent data) {
        // Return if camembert isn't activated
        if (activationPlace == null)
            return;

        System.out.printf("item#%d poisons room\n", GetUID());
        Poisoner poisoner = new Poisoner();
        for (Entity entity : activationPlace.GetEntities()) {
            entity.ApplyAction(poisoner);
        }

        --duration;

        if (duration == 0) {
            //System.out.printf("Room #%d is no longer poisoned by camambert.\n", activationPlace.GetRoomNumber(), duration);
            activationPlace = null;
            // TODO: delete item
        }

            //System.out.printf("Room #%d is poisoned by camambert for duration:%d.\n", activationPlace.GetRoomNumber(), duration);

        /*System.out.println("new Poisoner");

        System.out.printf("item#%d poisons room\n", GetUID());

        Poisoner poisoner = new Poisoner();

        System.out.println("For entity in room.GetEntities()");


        new Student("").ApplyAction(poisoner);*/

        if (duration == 0) {
            //System.out.printf("Room #%d is no longer poisoned by camambert.\n", activationPlace.GetRoomNumber(), duration);
            activationPlace = null;
            // TODO: delete item
        }
        //else
            //System.out.printf("Room #%d is poisoned by camambert for duration:%d.\n", activationPlace.GetRoomNumber(), duration);
    }

    protected void DeserializeSpecificItems(Game game, JsonObject json) {
        if (json.has("duration"))
            duration = json.get("duration").getAsInt();

        if (json.has("activationPlace"))
            activationPlace = game.GetDeserializedObjectReference(json.get("activationPlace").getAsString());

    }
}
