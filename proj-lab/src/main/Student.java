package main;

public class Student extends Entity {
    private final String uniqueName;

    /**
     * This method is the constructor of the Student class
     * @param uniqueName the name that the student is called in this game (it has to be unique but now it isn't checked so we have to make sure of it)
     */
    public Student(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    /**
     * This method is the constructor of the Student class with a Game class as parameter
     * @param uniqueName the name that the student is called in this game (it has to be unique but now it isn't checked so we have to make sure of it)
     * @param game the already initalizated game class from main
     */
    public Student(String uniqueName, Game game) {
        super(game);
        this.uniqueName = uniqueName;
    }

    @Override
    public void StartRound(TimerEvent data) {
//        System.out.println("Student: StartRound");
    }

    @Override
    public void EndRound(TimerEvent data) {

    }

    @Override
    public void StartTurn(TimerEvent data) {
        if (data.GetTurnNumber() == 1) {
            var neighbours = this.containingRoom.GetNeighbours();

        }
    }

    @Override
    public void EndTurn(TimerEvent data) {

    }

    /**
     * This method is to get the name of the Student in a form of Hallgató(<NAME>)
     * @return the formatted string of that
     */
    @Override
    public String GetName() {
        return String.format("Hallgató(%s)", uniqueName);
    }
}
