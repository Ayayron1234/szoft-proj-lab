package main;

public class TimerEvent {
    private int roundsLeft;
    private int roundNumber;
    private int turnNumber;

    public int GetRoundsLeft() {
        return roundsLeft;
    }

    public int GetRoundNumber() {
        return roundNumber;
    }

    public int GetTurnNumber() { return turnNumber; }

    public void IncreseTurnCouner() {
        ++turnNumber;
    }

    public TimerEvent(int roundNumber, int roundsLeft, int turnNumber)

    {
        this.roundNumber = roundNumber;
        this.roundsLeft = roundsLeft;
        this.turnNumber = turnNumber;
    }
}
