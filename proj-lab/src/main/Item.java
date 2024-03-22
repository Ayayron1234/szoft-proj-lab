package main;

public abstract class Item implements TimerSubscriber {

    public abstract String GetName();

    public void PickedUp(Entity who, Room where) {}

    public void Placed(Entity who, Room where) {}

    public boolean CanPickUp(Entity who) {
        return true;
    }

    @Override
    public void StartRound(TimerEvent data) {

    }

    @Override
    public void EndRound(TimerEvent data) {

    }

    @Override
    public void StartTurn(TimerEvent data) {

    }

    @Override
    public void EndTurn(TimerEvent data) {

    }
}
