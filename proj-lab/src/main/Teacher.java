package main;

import main.actions.SoulDrainer;

import java.util.Iterator;
import java.util.ListIterator;

public class Teacher extends Entity {
    private int uid;

    public Teacher(int uid, Game game) {
        super(uid, game);
    }

    public void DrainSouls() {
        System.out.printf("%s drains soul\n", GetName());

        SoulDrainer drainer = new SoulDrainer();

        Iterator<Entity> iter = containingRoom.GetEntities().iterator();
        while(iter.hasNext()) {
            Entity entity = iter.next();
            entity.ApplyAction(drainer);
        }
    }

    @Override
    public String GetName() {
        return String.format("Oktató%d", uid);
    }

    @Override
    public void DropOutOfGame() {
        // Teachers can't drop out of the game
        return;
    }

    @Override
    public void HandleTurn() {
        //TODO Teacher's turnhandling
    }

}
