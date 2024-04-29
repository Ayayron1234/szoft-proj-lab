package main;
/**
 * Represents a timer event in a game, tracking the number of rounds left, the current round number,
 * and the current turn number within the round.
 */
public class TimerEvent {
    private int roundsLeft;
    private int roundNumber;
    private int turnNumber;
    /**
     * Gives back the number of rounds left in the game.
     *
     * @return the number of rounds left
     */
    public int GetRoundsLeft() {
        return roundsLeft;
    }
    /**
     * Gives back the current round number.
     *
     * @return the current round number
     */
    public int GetRoundNumber() {
        return roundNumber;
    }
    /**
     * Gives back the current turn number.
     *
     * @return the current turn number
     */
    public int GetTurnNumber() { return turnNumber; }
    /**
     * Increments the turn number by one. This should be called to move to the next turn within the current round.
     */
    public void IncreseTurnCouner() {
        ++turnNumber;
    }
    /**
     * Constructs a new TimerEvent with specified initial values for round number, rounds left, and turn number.
     *
     * @param roundNumber the initial round number
     * @param roundsLeft the initial number of rounds left in the game
     * @param turnNumber the initial turn number within the current round
     */
    public TimerEvent(int roundNumber, int roundsLeft, int turnNumber)

    {
        this.roundNumber = roundNumber;
        this.roundsLeft = roundsLeft;
        this.turnNumber = turnNumber;
    }
}
