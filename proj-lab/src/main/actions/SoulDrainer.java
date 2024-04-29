package main.actions;

import main.*;
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
            // Invoke the Use method of the item which provided the protection
            Protection protection = target.GetProtectionWithType(ProtectionType.SOULD_DRAIN_PROTECTION);
            protection.GetProvider().Use(target);

            return;
        }

        // Entity's soul was drained and dropped out of game
        target.DropOutOfGame();
    }
}
