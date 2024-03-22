package main;

import java.util.ArrayList;

public class Timer {
    final private ArrayList<TimerSubscriber> subscribers = new ArrayList<TimerSubscriber>();

    public void Subscribe(TimerSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void Unsubscribe(TimerSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void StartRound(TimerEvent data) {
        for (TimerSubscriber subscriber : subscribers)
            subscriber.StartRound(data);
    }

    public void EndRound(TimerEvent data) {
        for (TimerSubscriber subscriber : subscribers)
            subscriber.EndRound(data);
    }

    public void StartTurn(Entity who, TimerEvent data) {
        System.out.printf("Timer.StartTurn(%s%d)\n", who.GetName(), data.GetTurnNumber());
        if (subscribers.contains(who))
            who.StartTurn(data);
    }

    public void EndTurn(Entity who, TimerEvent data) {
        if (subscribers.contains(who))
            who.EndTurn(data);
    }
}
