package main.itemtypes;

import main.*;
import main.actions.Poisoner;

import java.util.Scanner;

/**
 * Represents a Camembert cheese item that, when placed in a room, poisons that room
 * for a specified number of rounds. The cheese does not have an effect when carried
 * by an entity, but its activation begins once placed down. This item is part of a game's
 * mechanics where items can influence the environment or entities within it.
 */
public class Camembert extends Item {
    /**
     * Returns the name of the item.
     *
     * @return A string representing the name of this item, specifically a "Boxed Cabbage Camembert".
     */
    @Override
    public String GetName() {
        System.out.println("Camembert.GetName");
        return "Dobozolt Káposztás Camembert";
    }

    /**
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
     */
    @Override
    public void StartRound(TimerEvent data) {
        System.out.println("Camembert.StartRound");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Is the Camembert active?\n 1-yes 2-no");

        String answer = scanner.nextLine();
        if(answer.equals("2")){
            return;
        }

        System.out.println("new Poisoner");
        Poisoner poisoner = new Poisoner();

        System.out.println("For entity in room.GetEntities()");

        new Student("").ApplyAction(poisoner);
    }
}