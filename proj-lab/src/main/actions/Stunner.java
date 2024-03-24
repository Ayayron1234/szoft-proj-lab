package main.actions;

import main.Action;
import main.Entity;

public class Stunner implements Action {
    int roundsMissed = 0;
    public Stunner(int num){
        roundsMissed = num;
    }
    //Teacher gets stunned, and can't move for a round
    @Override
    public void Execute(Entity target) {
        //TODO kimarad 1 korbol
        target.MissRounds(roundsMissed);
    }
}
