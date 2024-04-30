package main;

/**
 * This interface defines the Activate method which activates the room's ability.
 */
public interface RoomAbility {
<<<<<<< Updated upstream
    void Activate(Room where);

    String GetTypeString();
=======
<<<<<<< Updated upstream

    /**
     * Activates a rooms special ability
     * @param room - the room with special ability
     */
    void Activate(Room room);
=======
    /**
     * Activates the room ability, applying its effects within a room.
     *
     * @param where The room in which the ability is activated.
     */
    void Activate(Room where);

    /**
     * Retrieves a string representation of the type of room ability.
     *
     * @return A string representing the type of room ability.
     */
    String GetTypeString();
>>>>>>> Stashed changes
>>>>>>> Stashed changes
}
