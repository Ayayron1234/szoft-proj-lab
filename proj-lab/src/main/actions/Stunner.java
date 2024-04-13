package main.actions;

import main.Action;
import main.Entity;
import main.Teacher;

public class Stunner implements Action {

    //Teacher gets stunned, and can't move for a round
    @Override
    public void Execute(Entity target) {
        if (target.getClass().equals(Teacher.class))
            target.MissRounds(1);
    }
}
