package main.actions;

import main.Action;
import main.Entity;
import main.ProtectionType;
import main.Teacher;

public class Stunner implements Action {

    //Teacher gets stunned, and can't move for a round
    @Override
    public void Execute(Entity target) {
        // Check if entity is a Teacher
        if (!target.getClass().equals(Teacher.class))
            return;

        // Teacher misses a round
        System.out.printf("%s is stunned\n");
        target.MissRounds(1);
    }
}
