package main;

public class Student extends Entity {
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

    @Override
    public String GetName() {
        return "Hallgató";
    }
}
