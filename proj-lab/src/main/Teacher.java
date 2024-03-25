package main;

import main.actions.SoulDrainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Teacher extends Entity {

    /**
     * This method is the constructor of the Teacher class with autoincremented UID
     */
    public Teacher() {

    }

    /**
     * This method is the constructor of the Teacher class with a Game class as parameter
     * @param game the already initalizated game class from main
     */
    public Teacher(Game game) {
        super(game);
        System.out.println("Teacher.Teacher");
    }

    /**
     * This method is resetting the autoincremented UID.
     */
    public static void ResetUIDs() {
        System.out.println("Teacher.ResetUIDs");
    }

    /**
     * This method is where the teacher tries to drain the soul of every students who is in the same room as him/her
     */
    public void DrainSouls() {
        System.out.println("Teacher.DrainSouls");

        new Room(0).GetEntities();
        Student student = new Student("");

        System.out.println("new SoulDrainer");
        SoulDrainer drainer = new SoulDrainer();

        student.ApplyAction(drainer);
    }

    /**
     * TODO: This method will have to do things like teacher picking up things from the room or moving to another room but it's an AI thing
     * @param data
     */
    @Override
    public void StartRound(TimerEvent data) {
        System.out.println("Teacher.StartRound");
    }

    @Override
    public void EndRound(TimerEvent data) {
        System.out.println("Teacher.EndRound");
    }

    @Override
    public void StartTurn(TimerEvent data) {
        System.out.println("Teacher.StartTurn");
    }

    @Override
    public void EndTurn(TimerEvent data) {
        System.out.println("Teacher.EndTurn");
    }

    /**
     * This method is to get the name of the Teacher in a form of Oktató<UID>
     * @return the formatted string of that
     */
    @Override
    public String GetName() {
        System.out.println("Teacher.GetName");
        return "";
    }

    /**
     * This method is to make sure a teacher can't drain a soul of another teacher
     * @return we return false everytime because of that
     */
    @Override
    public boolean DropOutOfGame() {
        System.out.println("Teacher.DropOutOfGame");
        return false;
    }

}
