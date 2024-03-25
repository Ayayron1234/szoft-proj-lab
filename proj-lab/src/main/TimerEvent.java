package main;

public class TimerEvent {
    private int roundsLeft;
    private int roundNumber;
    private int turnNumber;

    /**
     * This method is the getter of rounds left
     * @return number of rounds left
     */
    public int GetRoundsLeft() {
        System.out.println("TimerEvent.GetRoundsLeft");
        return roundsLeft;
    }

    /**
     * This method is the getter of the round's number
     * @return number of this round
     */
    public int GetRoundNumber() {
        System.out.println("TimerEvent.GetRoundNumber");
        return roundNumber;
    }

    /**
     * This method is the getter of the turn's number
     * @return number of this turn
     */
    public int GetTurnNumber() {
        System.out.println("TimerEvent.GetTurnNumber");
        return turnNumber;
    }

    /**
     * Increases the turn's number by 1
     */
    public void IncreaseTurnCounter() {
        System.out.println("TimerEvent.IncreaseTurnCounter");
        ++turnNumber;
    }

    /**
     * This method is the constructor of the TimerEvent class with 3 parameters.
     * @param roundNumber the number of this round
     * @param roundsLeft the number of rounds left
     * @param turnNumber the number of this turn
     */
    public TimerEvent(int roundNumber, int roundsLeft, int turnNumber)

    {
        this.roundNumber = roundNumber;
        this.roundsLeft = roundsLeft;
        this.turnNumber = turnNumber;
    }
}
