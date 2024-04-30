package main.actions;

import main.Action;
import main.Entity;
import main.ProtectionType;
import main.Teacher;

/**
 * Represents an action that stuns a target, preventing it from moving for a round.
 * This is typically executed by another entity or as a result of an item's effect.
 */
public class Stunner implements Action {

    /**
     * Executes the stun action on the specified entity target. Once stunned, the target
     * entity will miss its next turn, simulating being stunned and unable to move.
     *
     * @param target The entity that will be stunned and miss a turn as a result of this action.
     */
    //Teacher gets stunned, and can't move for a round
    @Override
    public void Execute(Entity target) {
        // Check if entity is a Teacher
        if (!target.getClass().equals(Teacher.class))
            return;

        // Teacher misses a round
        System.out.printf("%s is stunned\n", target.GetName());
        target.MissRounds(1);

    }
}
