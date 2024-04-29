package main;

import java.util.ArrayList;
/**
 * The Timer class manages time-based events for subscribers within a game. It manages a list of subscribers,
 * and notifies them at the start and end of rounds and turns.
 */
public class Timer {
    final private ArrayList<TimerSubscriber> subscribers = new ArrayList<TimerSubscriber>();
    /**
     * Subscribes a TimerSubscriber to receive notifications about round and turn events.
     *
     * @param subscriber the TimerSubscriber to be added to the notification list
     */
    public void Subscribe(TimerSubscriber subscriber) {
        subscribers.add(subscriber);
    }
    /**
     * Unsubscribes a TimerSubscriber from receiving notifications about round and turn events.
     *
     * @param subscriber the TimerSubscriber to be removed from the notification list
     */
    public void Unsubscribe(TimerSubscriber subscriber) {
        subscribers.remove(subscriber);
    }
    /**
     * Notifies all subscribers about the start of a round by passing the TimerEvent data.
     *
     * @param data the TimerEvent containing details about the current round
     */
    public void StartRound(TimerEvent data) {
        for (TimerSubscriber subscriber : subscribers)
            subscriber.StartRound(data);
    }
    /**
     * Notifies all subscribers about the end of a round by passing the TimerEvent data.
     *
     * @param data the TimerEvent containing details about the current round
     */
    public void EndRound(TimerEvent data) {
        for (TimerSubscriber subscriber : subscribers)
            subscriber.EndRound(data);
    }
    /**
     * Notifies a specific subscriber (entity) about the start of their turn, if they are a subscriber.
     *
     * @param who the entity (subscriber) to notify
     * @param data the TimerEvent containing details about the current turn
     */
    public void StartTurn(Entity who, TimerEvent data) {
        if (subscribers.contains(who))
            who.StartTurn(data);
    }
    /**
     * Notifies a specific subscriber (entity) about the end of their turn, if they are a subscriber.
     *
     * @param who the entity (subscriber) to notify
     * @param data the TimerEvent containing details about the current turn
     */
    public void EndTurn(Entity who, TimerEvent data) {
        if (subscribers.contains(who))
            who.EndTurn(data);
    }
}
