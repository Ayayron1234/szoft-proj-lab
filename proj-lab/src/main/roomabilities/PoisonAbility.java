package main.roomabilities;

import main.Entity;
import main.Room;
import main.RoomAbility;
import main.actions.Poisoner;
/**
 * Represents a poison ability within a room in the game. When activated, this ability applies a poison effect to all
 * entities present in the room.
 */
public class PoisonAbility implements RoomAbility {

    /**
     * Activates the poison ability in a specified room. This method triggers the application of a poison effect to all entities
     * within the room.
     *
     * @param room the room where the ability is activated.
     */
    @Override
    public void Activate(Room room) {
        Poisoner poisoner = new Poisoner();
        System.out.printf("room#%d poisons it's entities", room.GetRoomNumber());

        for (Entity entity : room.GetEntities()) {
            entity.ApplyAction(poisoner);
        }
    }

     /**
     * Returns a string identifier for this type of room ability. 
     *
     * @return the type identifier of this ability, "poison_ability"
     */
    @Override
    public String GetTypeString() {
        return "poison_ability";
    }
}
