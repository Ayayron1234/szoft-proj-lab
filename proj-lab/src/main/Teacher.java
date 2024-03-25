package main;

import main.actions.SoulDrainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Teacher extends Entity {
    private static int lastUid = 0;
    private int uid;

    /**
     * This method is the constructor of the Teacher class with autoincremented UID
     */
    public Teacher() {
        this.uid = lastUid++;
    }

    /**
     * This method is the constructor of the Teacher class with a Game class as parameter
     * @param game the already initalizated game class from main
     */
    public Teacher(Game game) {
        super(game);
        this.uid = lastUid++;
    }

    /**
     * This method is resetting the autoincremented UID.
     */
    public static void ResetUIDs() {
        lastUid = 0;
    }

    /**
     * This method is where the teacher tries to drain the soul of every students who is in the same room as him/her
     */
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

    /**
     * TODO: This method will have to do things like teacher picking up things from the room or moving to another room but it's an AI thing
     * @param data
     */
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

    /**
     * This method is to get the name of the Teacher in a form of Oktató<UID>
     * @return the formatted string of that
     */
    @Override
    public String GetName() {
        return String.format("Oktató%d", uid);
    }

    /**
     * This method is to make sure a teacher can't drain a soul of another teacher
     * @return we return false everytime because of that
     */
    @Override
    public boolean DropOutOfGame() {
        return false;
    }

}
