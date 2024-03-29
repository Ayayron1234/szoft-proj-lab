package main.actions;

import main.Action;
import main.Entity;
import main.ProtectionType;

import java.util.Scanner;

/**
 * Represents an action that attempts to poison a target entity. If the target does not have
 * protection against poison, this action will cause the entity to drop all its items and miss
 * a round, simulating the debilitating effects of being poisoned. Entities with poison protection
 * are unaffected by this action.
 */
public class Poisoner implements Action {
    /**
     * Executes the poisoning action on a specified target entity. This method first checks
     * if the target has protection against poison. If the target is protected, it will not be
     * affected. Otherwise, the target will be considered poisoned, which results in the target
     * dropping all items it carries and missing the next round.
     *
     * @param target The entity that is the target of the poisoning attempt.
     */
    @Override
    public void Execute(Entity target) {
        System.out.println("Poisoner.Execute");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Does the Entity have POISON_PROTECTION?\n 1-yes 2-no");
        String answer = scanner.nextLine();
        if(answer.equals("2")){
            target.DropAllItems();
            target.MissRounds(1);
        } else {
            System.out.println("Entity saved by POISON_PROTECTION!");
        }
    }
}
