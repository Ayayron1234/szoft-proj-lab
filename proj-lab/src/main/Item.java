package main;

public abstract class Item implements TimerSubscriber {

    public abstract String GetName();

    /**
     * This method is to
     * @param who the entity who picked up the item
     * @param where the room where the item was picked up from
     */
    public void PickedUp(Entity who, Room where) { }

    /**
     * This method is to
     * @param who the entity who placed the item
     * @param where the room where the entity placed the item
     */
    public void Placed(Entity who, Room where) { }

    /**
     * This method is to make sure the entity can pick up an item
     * @param who the entity who can pick up an item
     * @return true TODO
     */
    public boolean CanPickUp(Entity who) {
        return true;
    }

    @Override
    public void StartRound(TimerEvent data) { }

    @Override
    public void EndRound(TimerEvent data) { }

    @Override
    public void StartTurn(TimerEvent data) { }

    @Override
    public void EndTurn(TimerEvent data) { }
}
