package main;

import main.TimerEvent;
/**
 * Defines the interface for any game component that needs to respond to timing events such as the start or end of rounds and turns.
 * Implementing this interface allows a game component to be notified and react to these specific game methods.
 */
public interface TimerSubscriber {
    void StartRound(TimerEvent data);
    void EndRound(TimerEvent data);
    void StartTurn(TimerEvent data);
    void EndTurn(TimerEvent data);

}

