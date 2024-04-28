package main.actions;

import main.Action;
import main.Entity;
import main.Protection;
import main.ProtectionType;

public class Poisoner implements Action {
    @Override
    public void Execute(Entity target) {
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
    }
}
