package main.actions;

import main.Action;
import main.Entity;
import main.Protection;
import main.ProtectionType;

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
<<<<<<< Updated upstream
        // Check if target has protection against poison
        if (target.HasProtectionType(ProtectionType.POISON_PROTECTION)) {
            // Invoke the Use method of the item which provided the protection
            Protection protection = target.GetProtectionWithType(ProtectionType.SOULD_DRAIN_PROTECTION);
            protection.GetProvider().Use(target);

            return;
        }

        // Poisoned entity drops their items and misses 1 round
        System.out.printf("%s is poisoned.\n", target.GetName());
        target.DropAllItems();
        target.MissRounds(1);
=======
<<<<<<< Updated upstream
        System.out.println("Poisoner.Execute");
=======
        // Check if target has protection against poison
        if (target.HasProtectionType(ProtectionType.POISON_PROTECTION)) {
            // Invoke the Use method of the item which provided the protection
            Protection protection = target.GetProtectionWithType(ProtectionType.POISON_PROTECTION);
            protection.GetProvider().Use(target);
>>>>>>> Stashed changes

        Scanner scanner = new Scanner(System.in);
        System.out.println("Does the Entity have POISON_PROTECTION?\n 1-yes 2-no");
        String answer = scanner.nextLine();
        if(answer.equals("2")){
            target.DropAllItems();
            target.MissRounds(1);
        } else {
            System.out.println("Entity saved by POISON_PROTECTION!");
        }
<<<<<<< Updated upstream
=======

        // Poisoned entity drops their items and misses 1 round
        if (target.CanMissRounds()) {
            System.out.printf("%s is poisoned\n", target.GetName());
            target.DropAllItems();
            target.MissRounds(1);
        }
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }
}
