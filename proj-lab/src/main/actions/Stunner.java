package main.actions;

import main.Action;
import main.Entity;
/**
 * Represents an action that stuns a target, preventing it from moving for a round.
 * This is typically executed by another entity or as a result of an item's effect.
 */
public class Stunner implements Action {

    int roundsMissed = 0;
  
    public Stunner(int num){
        roundsMissed = num;
    }

    /**
     * Executes the stun action on the specified entity target. Once stunned, the target
     * entity will miss its next turn, simulating being stunned and unable to move.
     *
     * @param target The entity that will be stunned and miss a turn as a result of this action.
     */
    @Override
    public void Execute(Entity target) {
        //TODO kimarad 1 korbol
        target.MissRounds(roundsMissed);
    }
}
