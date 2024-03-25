package main.actions;

import main.*;
import main.itemtypes.TVSZ;

import java.util.Scanner;

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
        System.out.println("SoulDrainer.Execute");

        target.GetActiveProtections();

        System.out.println("Does the entity have SOUL_DRAIN_PROTECTION?\n 1-yes 2-no");
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        if (line.equals("2"))
            target.DropOutOfGame();
        else if (line.equals("1"))
            System.out.println("Entity blocked soul drain.");
    }
}
