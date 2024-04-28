package main.actions;

import main.*;
import main.itemtypes.TVSZ;

public class SoulDrainer implements Action {
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
