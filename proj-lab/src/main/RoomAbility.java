package main;

/**
 * This interface defines the Activate method which activates the room's ability.
 */
public interface RoomAbility {
    void Activate(Room where);

    String GetTypeString();
}
