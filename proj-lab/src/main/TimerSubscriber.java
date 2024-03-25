package main;

import main.TimerEvent;
/**
 * The TimerSubscriber interface is used to notify implementing classes about significant timing
 * events that occur within the game or application. Classes that implement this interface can
 * respond to the start and end of rounds and turns, allowing for timed actions or status updates.
 */
public interface TimerSubscriber {
    /**
     * Called at the start of a round. Implementing classes can perform necessary actions
     * or updates specific to the beginning of a round.
     *
     * @param data An instance of TimerEvent containing details about the timing event,
     *             such as the current round number and remaining rounds.
     */
    void StartRound(TimerEvent data);

    /**
     * Called at the end of a round. Implementing classes can perform cleanup actions
     * or updates specific to the conclusion of a round.
     *
     * @param data An instance of TimerEvent containing details about the timing event,
     *             such as the current round number and remaining rounds.
     */
    void EndRound(TimerEvent data);

    /**
     * Called at the start of a turn within a round. Implementing classes can perform
     * necessary actions or updates specific to the beginning of a turn.
     *
     * @param data An instance of TimerEvent containing details about the timing event,
     *             such as the current turn number within the round.
     */
    void StartTurn(TimerEvent data);

    /**
     * Called at the end of a turn within a round. Implementing classes can perform cleanup
     * actions or updates specific to the conclusion of a turn.
     *
     * @param data An instance of TimerEvent containing details about the timing event,
     *             such as the current turn number within the round.
     */
    void EndTurn(TimerEvent data);

}

