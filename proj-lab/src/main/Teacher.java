package main;

import main.actions.SoulDrainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Teacher extends Entity {
    private static int lastUid = 0;
    private int uid;

    public Teacher() {
        this.uid = lastUid++;
    }

    public Teacher(Game game) {
        super(game);
        this.uid = lastUid++;
    }

    public static void ResetUIDs() {
        lastUid = 0;
    }

    public void DrainSouls() {
        System.out.printf("%s drains the soul of entities.\n", GetName());

        SoulDrainer drainer = new SoulDrainer();

        ArrayList<Entity> entities = containingRoom.GetEntities();
        int entitiesSize = entities.size();
        for (int i = 0; i < entitiesSize; ++i) {
            Entity entity = entities.get(i);
            entity.ApplyAction(drainer);
            if (entitiesSize != entities.size()) {
                --i;
                entitiesSize = entities.size();
            }
        }
    }

    @Override
    public void StartRound(TimerEvent data) {

    }

    @Override
    public void EndRound(TimerEvent data) {

    }

    @Override
    public void StartTurn(TimerEvent data) {

    }

    @Override
    public void EndTurn(TimerEvent data) {

    }

    @Override
    public String GetName() {
        return String.format("Oktató%d", uid);
    }

    @Override
    public boolean DropOutOfGame() {
        return false;
    }

}
