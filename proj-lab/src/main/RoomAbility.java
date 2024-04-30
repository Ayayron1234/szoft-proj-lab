package main;

/**
 * This interface defines the Activate method which activates the room's ability.
 */
public interface RoomAbility {
    /**
     * Activates a rooms special ability
     * @param room - the room with special ability
     */
    void Activate(Room room);

    /**
     * Retrieves a string representation of the type of room ability.
     *
     * @return A string representing the type of room ability.
     */
    String GetTypeString();
}
