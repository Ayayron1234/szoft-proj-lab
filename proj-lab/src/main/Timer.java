package main;

import java.util.ArrayList;

public class Timer {
    /**
     * This method implements subscribtion to the Timer
     * @param subscriber - the subscriber to the Timer
     * subscriber is added to the subscribers list
     */
    public void Subscribe(TimerSubscriber subscriber) {
        System.out.println("Timer.Subscribe");
    }

    /**
     * Unsubscribes from the timer
     * removes subscriber from subsribers list
     * @param subscriber - the subscriber to the Timer
     */
    public void Unsubscribe(TimerSubscriber subscriber) {
        System.out.println("Timer.Unsubscribe");
    }

    /**
     * This method starts a new round in the game
     * calls each subscriber's StartRound method
     * @param data - the current state of the game
     */
    public void StartRound(TimerEvent data) {
        System.out.println("Timer.StartRound");
        TimerSubscriber subscriber = new Student("");
        Subscribe(subscriber);
        subscriber.StartRound(data);
    }

    /**
     * Ends a round in the game
     * calls each subscriber's EndRound method
     * @param data - current state of the game
     */
    public void EndRound(TimerEvent data) {
        System.out.println("Timer.EndRound");
    }

    /**
     * Starts an entity's turn (student or teacher)
     * @param who - the entity whose turn it is
     * @param data - current state of the game
     */
    public void StartTurn(Entity who, TimerEvent data) {
        System.out.println("Timer.StartTurn");
        TimerSubscriber subscriber = new Student("");
        Subscribe(subscriber);
        subscriber.StartTurn(data);
    }

    /**
     * Ends an entity's turn (student or teacher)
     * @param who - the entity whose turn is about to end
     * @param data - current state of the game
     */
    public void EndTurn(Entity who, TimerEvent data) {
        System.out.println("Timer.EndTurn");
    }
}
