package main.actions;

import main.Action;
import main.Entity;
import main.ProtectionType;

public class Poisoner implements Action {
    @Override
    public void Execute(Entity target) {
        // Check if target has protection against poison
        if (target.HasProtectionType(ProtectionType.POISON_PROTECTION)) {
            System.out.printf("%s was protected against poisoning.\n", target.GetName());
            return;
        }

        System.out.printf("%s was poisoned.\n", target.GetName());
        target.DropAllItems();
        // TODO: kimarad 1 korbol

    }
}
