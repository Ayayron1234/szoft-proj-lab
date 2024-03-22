package main;

import main.TimerEvent;

public interface TimerSubscriber {
    void StartRound(TimerEvent data);
    void EndRound(TimerEvent data);
    void StartTurn(TimerEvent data);
    void EndTurn(TimerEvent data);

}

