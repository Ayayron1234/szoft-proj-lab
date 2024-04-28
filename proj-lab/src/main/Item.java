package main;

public abstract class Item implements TimerSubscriber {
    private int     uid;
    private boolean isSticky;
    private boolean isFake; // If true pickedup and placed methods should not be called.

    public void SetUID(int uid) {
        this.uid = uid;
    }

    public int GetUID() {
        return uid;
    }

    public abstract String GetName();

    public void PickedUp(Entity who, Room where) { }

    public void Placed(Entity who, Room where) { }

    public void Dropped(Entity who, Room where) {
        Placed(who, where);
    }

    public boolean CanPickUp(Entity who) {
        return !IsSticky();
    }

    public boolean IsSticky() {
        return isSticky;
    }

    public void Use(Entity who) {}

    @Override
    public void StartRound(TimerEvent data) { }

    @Override
    public void EndRound(TimerEvent data) { }

    @Override
    public void StartTurn(TimerEvent data) { }

    @Override
    public void EndTurn(TimerEvent data) { }
}
