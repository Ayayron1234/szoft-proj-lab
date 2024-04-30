package main;

import java.util.ArrayList;
/**
 * The Timer class manages time-based events for subscribers within a game. It manages a list of subscribers,
 * and notifies them at the start and end of rounds and turns.
 */
public class Timer {
<<<<<<< Updated upstream
    final private ArrayList<TimerSubscriber> subscribers = new ArrayList<TimerSubscriber>();
=======
>>>>>>> Stashed changes
    /**
     * Subscribes a TimerSubscriber to receive notifications about round and turn events.
     *
     * @param subscriber the TimerSubscriber to be added to the notification list
     */
    public void Subscribe(TimerSubscriber subscriber) {
<<<<<<< Updated upstream
        subscribers.add(subscriber);
=======
        System.out.println("Timer.Subscribe");
>>>>>>> Stashed changes
    }
    /**
     * Unsubscribes a TimerSubscriber from receiving notifications about round and turn events.
     *
     * @param subscriber the TimerSubscriber to be removed from the notification list
     */
    public void Unsubscribe(TimerSubscriber subscriber) {
<<<<<<< Updated upstream
        subscribers.remove(subscriber);
=======
        System.out.println("Timer.Unsubscribe");
>>>>>>> Stashed changes
    }
    /**
     * Notifies all subscribers about the start of a round by passing the TimerEvent data.
     *
     * @param data the TimerEvent containing details about the current round
     */
    public void StartRound(TimerEvent data) {
<<<<<<< Updated upstream
        for (TimerSubscriber subscriber : subscribers)
            subscriber.StartRound(data);
=======
        System.out.println("Timer.StartRound");
        TimerSubscriber subscriber = new Student("");
        Subscribe(subscriber);
        subscriber.StartRound(data);
>>>>>>> Stashed changes
    }
    /**
     * Notifies all subscribers about the end of a round by passing the TimerEvent data.
     *
     * @param data the TimerEvent containing details about the current round
     */
    public void EndRound(TimerEvent data) {
<<<<<<< Updated upstream
        for (TimerSubscriber subscriber : subscribers)
            subscriber.EndRound(data);
=======
        System.out.println("Timer.EndRound");
>>>>>>> Stashed changes
    }
    /**
     * Notifies a specific subscriber (entity) about the start of their turn, if they are a subscriber.
     *
     * @param who the entity (subscriber) to notify
     * @param data the TimerEvent containing details about the current turn
     */
    public void StartTurn(Entity who, TimerEvent data) {
<<<<<<< Updated upstream
        if (subscribers.contains(who))
            who.StartTurn(data);
=======
        System.out.println("Timer.StartTurn");
        TimerSubscriber subscriber = new Student("");
        Subscribe(subscriber);
        subscriber.StartTurn(data);
>>>>>>> Stashed changes
    }
    /**
     * Notifies a specific subscriber (entity) about the end of their turn, if they are a subscriber.
     *
     * @param who the entity (subscriber) to notify
     * @param data the TimerEvent containing details about the current turn
     */
    public void EndTurn(Entity who, TimerEvent data) {
<<<<<<< Updated upstream
        if (subscribers.contains(who))
            who.EndTurn(data);
=======
        System.out.println("Timer.EndTurn");
>>>>>>> Stashed changes
    }
}
