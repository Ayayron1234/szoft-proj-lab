package main.actions;

import main.Action;
import main.Entity;
import main.Item;
import main.ProtectionType;
import main.itemtypes.TVSZ;

public class SoulDrainer implements Action {
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
