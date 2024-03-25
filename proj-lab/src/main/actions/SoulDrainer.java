package main.actions;

import main.Action;
import main.Entity;
import main.Item;
import main.ProtectionType;
import main.itemtypes.TVSZ;
/**
 * Represents an action that attempts to drain the soul of a target entity. If the target does not have
 * protection against soul draining, it is removed from the game. If the target has a specific item ("TVSZ"),
 * it uses that item to protect itself instead.
 */
public class SoulDrainer implements Action {
    /**
     * Executes the soul draining action on the specified target entity. Checks for protection against
     * soul draining either through protection type or a specific item. If unprotected, the target is
     * removed from the game. Protected entities are unaffected, and appropriate messages are printed
     * to indicate the outcome.
     *
     * @param target The entity to be potentially affected by soul draining.
     */
    @Override
    public void Execute(Entity target) {
        // Check if target has protection against souldrain
        if (target.HasProtectionType(ProtectionType.SOULD_DRAIN_PROTECTION)) {
            System.out.printf("%s was protected against soul draining.\n", target.GetName());
            return;
        }

        // Check if entity has a TVSZ in their directory
        for(Item item : target.GetItems()) {
            if (!item.GetName().equals(new TVSZ().GetName()))
                continue;

            ((TVSZ)item).Use();
            System.out.printf("%s was protected against soul draining.\n", target.GetName());
            return;
        }

        if (target.DropOutOfGame())
            System.out.printf("%s's soul was drained.\n", target.GetName());
    }
}
