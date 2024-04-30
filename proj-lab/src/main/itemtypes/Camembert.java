package main.itemtypes;

<<<<<<< Updated upstream
import main.*;
import main.actions.Poisoner;

public class Camembert extends Item {
<<<<<<< Updated upstream
    private int duration = 3;
    private Room activationPlace = null;

=======
    /**
     * Returns the name of the item.
     *
     * @return A string representing the name of this item, specifically a "Boxed Cabbage Camembert".
=======
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
>>>>>>> Stashed changes
     */
>>>>>>> Stashed changes
    @Override
    public String GetName() {
        return "Dobozolt Káposztás Camembert";
    }

<<<<<<< Updated upstream
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
=======
    /**
<<<<<<< Updated upstream
     * Resets the activation place of the item when it is picked up by an entity.
     * This indicates that the item is no longer activating its effects in any room.
     *
     * @param who The entity picking up the item.
     * @param where The current location of the entity when the item is picked up.
     */
    @Override
    public void PickedUp(Entity who, Room where) {
        System.out.println("Camembert.PickedUp");
    }

    /**
     * Determines whether the item can be picked up by an entity. The Camembert can only
     * be picked up if it is not currently activating its effects in a room.
     *
     * @param who The entity attempting to pick up the item.
     * @return True if the item can be picked up, false otherwise.
=======
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
     * @param who the entity who can pick up an item
     * @return true if the Camembert can be picked up. False otherwise.
>>>>>>> Stashed changes
     */
    @Override
    public boolean CanPickUp(Entity who) {
        System.out.println("Camembert.CanPickUp");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Can the entity pick up the camembert?\n 1-yes 2-no");
        String answer = scanner.nextLine();
        return (answer.equals("1"));
    }

    /**
<<<<<<< Updated upstream
     * Activates the item's effects in a room when placed by an entity. This marks the
     * room as poisoned for a set duration.
     *
     * @param who The entity placing the item.
     * @param where The room in which the item is placed.
     */
    @Override
    public void Placed(Entity who, Room where) {
        System.out.println("Camembert.Placed");
    }

    /**
     * Executes the effect of the item at the start of each round. If the item has been
     * activated in a room, it applies a poison effect to all entities within that room
     * and decreases the duration of its effect. Once the duration expires, the item
     * stops affecting the room.
     *
     * @param data The event data related to the timer event at the start of the round.
=======
     * Place down the camembert on the ground.
     * This causes a poison effect in the room. (applied at the start of the round)
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
>>>>>>> Stashed changes
     */
    @Override
    public void StartRound(TimerEvent data) {
        System.out.println("Camembert.StartRound");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the Camembert active?\n 1-yes 2-no");

<<<<<<< Updated upstream
        String answer = scanner.nextLine();
        if(answer.equals("2")){
>>>>>>> Stashed changes
            return;

<<<<<<< Updated upstream
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
=======
        System.out.println("new Poisoner");
=======
        System.out.printf("item#%d poisons room\n", GetUID());
>>>>>>> Stashed changes
        Poisoner poisoner = new Poisoner();

        System.out.println("For entity in room.GetEntities()");

<<<<<<< Updated upstream
        new Student("").ApplyAction(poisoner);
=======
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
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }
}
