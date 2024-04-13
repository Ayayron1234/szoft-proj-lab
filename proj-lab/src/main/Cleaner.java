package main;

import main.actions.SoulDrainer;

import java.util.Iterator;
import java.util.ListIterator;

public class Cleaner extends Entity {
    private static int lastUid = 0;
    private int uid;

    public Cleaner() {
        this.uid = lastUid++;
    }

    public Cleaner(Game game) {
        super(game);
        this.uid = lastUid++;
    }

    public static void ResetUIDs() {
        lastUid = 0;
    }

    public void CleanRoom() {
        if (containingRoom == null)
            return;

        containingRoom.Clean();

        Iterator<Entity> iter = containingRoom.GetEntities().iterator();

        // Select room to send entities to
        // TODO: select room randomly
        Room roomToSendEntities = containingRoom.GetNeighbours().get(0);

        while(iter.hasNext()) {
            Entity entity = iter.next();

            // Cleaner doesn't send themself out
            if (entity.equals(this))
                continue;

            entity.Step(roomToSendEntities);
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
        return String.format("Takarító%d", uid);
    }

    @Override
    public boolean DropOutOfGame() {
        return false;
    }

}

