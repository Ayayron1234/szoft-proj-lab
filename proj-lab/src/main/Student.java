package main;

import java.util.List;

public class Student extends Entity {

    /**
     * This method is the constructor of the Student class
     * @param uniqueName the name that the student is called in this game (it has to be unique but now it isn't checked so we have to make sure of it)
     */
    public Student(String uniqueName) {
    }

    /**
     * This method is the constructor of the Student class with a Game class as parameter
     * @param uniqueName the name that the student is called in this game (it has to be unique but now it isn't checked so we have to make sure of it)
     * @param game the already initalizated game class from main
     */
    public Student(String uniqueName, Game game) {
        super(game);
        System.out.println("Student.Student");
    }

    @Override
    public void StartRound(TimerEvent data) {
        System.out.println("Student.StartRound");
    }

    @Override
    public void EndRound(TimerEvent data) {
        System.out.println("Student.EndRound");
    }

    @Override
    public void StartTurn(TimerEvent data) {
        System.out.println("Student.StartTurn");
    }

    @Override
    public void EndTurn(TimerEvent data) {
        System.out.println("Student.EndTurn");
    }

    /**
     * This method is to get the name of the Student in a form of Hallgató(<NAME>)
     * @return the formatted string of that
     */
    @Override
    public String GetName() {
        System.out.println("Student.GetName");
        return "";
    }
}
