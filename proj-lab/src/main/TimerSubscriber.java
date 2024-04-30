package main;

import main.TimerEvent;

public interface TimerSubscriber {
<<<<<<< Updated upstream
    void StartRound(TimerEvent data);
    void EndRound(TimerEvent data);
    void StartTurn(TimerEvent data);
=======
<<<<<<< Updated upstream
    /**
     * Called at the start of a round. Implementing classes can perform necessary actions
     * or updates specific to the beginning of a round.
     *
     * @param data An instance of TimerEvent containing details about the timing event,
     *             such as the current round number and remaining rounds.
=======

    /**
     * Notify every subscriber of a new round starting.
     *
     * @param data  Data containing the current round's number, remaining rounds, and whose turn is it (by indexing)
>>>>>>> Stashed changes
     */
    void StartRound(TimerEvent data);

    /**
<<<<<<< Updated upstream
     * Called at the end of a round. Implementing classes can perform cleanup actions
     * or updates specific to the conclusion of a round.
     *
     * @param data An instance of TimerEvent containing details about the timing event,
     *             such as the current round number and remaining rounds.
=======
     * Notify every subscriber of the end of a round.
     *
     * @param data  Data containing the current round's number, remaining rounds, and whose turn is it (by indexing)
>>>>>>> Stashed changes
     */
    void EndRound(TimerEvent data);

    /**
<<<<<<< Updated upstream
     * Called at the start of a turn within a round. Implementing classes can perform
     * necessary actions or updates specific to the beginning of a turn.
     *
     * @param data An instance of TimerEvent containing details about the timing event,
     *             such as the current turn number within the round.
=======
     * Notify every subscriber of their turn starting.
     *
     * @param data  Data containing the current round's number, remaining rounds, and whose turn is it (by indexing)
>>>>>>> Stashed changes
     */
    void StartTurn(TimerEvent data);

    /**
<<<<<<< Updated upstream
     * Called at the end of a turn within a round. Implementing classes can perform cleanup
     * actions or updates specific to the conclusion of a turn.
     *
     * @param data An instance of TimerEvent containing details about the timing event,
     *             such as the current turn number within the round.
=======
     * Notify every subscriber of their turn ending.
     *
     * @param data  Data containing the current round's number, remaining rounds, and whose turn is it (by indexing)
>>>>>>> Stashed changes
     */
>>>>>>> Stashed changes
    void EndTurn(TimerEvent data);

}

