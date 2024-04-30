package main.roomabilities;

import main.Entity;
import main.Room;
import main.RoomAbility;
import main.Student;
import main.actions.Poisoner;
import main.Item;
import main.itemtypes.AirFreshener;

import java.util.Optional;

/**
 * Represents a room ability that applies poison effects to entities within the room when activated.
 * This ability, when activated, poisons all entities present in the room by applying a poison effect
 * to each of them.
 */
public class PoisonAbility implements RoomAbility {
<<<<<<< Updated upstream

    @Override
    public void Activate(Room room) {
        Poisoner poisoner = new Poisoner();
        System.out.printf("room#%d poisons it's entities", room.GetRoomNumber());
=======
<<<<<<< Updated upstream
=======

    /**
     * Activates the poison room ability, applying the poison effects towards the entities whose are in this room.
     *
     * @param room The room in which the poison ability is activated.
     */
>>>>>>> Stashed changes
    @Override
    public void Activate(Room room) {
        System.out.println("PoisonAbility.Activate");

        System.out.println("new Poisoner");
        Poisoner poisoner = new Poisoner();
<<<<<<< Updated upstream
=======
        System.out.printf("room#%d poisons it's entities\n", room.GetRoomNumber());
>>>>>>> Stashed changes

       // boolean
        for (Entity entity : room.GetEntities()) {
<<<<<<< Updated upstream
            entity.ApplyAction(poisoner);
        }
    }

    @Override
    public String GetTypeString() {
        return "poison_ability";
=======
            Optional<Item> airFreshenerOptional = entity.GetItems().stream().filter(item -> item.GetName().equals(new AirFreshener().GetName())).findFirst();
            if (airFreshenerOptional.isPresent()) {
                entity.PlaceItem(airFreshenerOptional.get());
                return;
            }
        }
>>>>>>> Stashed changes

        room.GetEntities();
        System.out.println("For entity in room.GetEntities()");

<<<<<<< Updated upstream
        new Student("").ApplyAction(poisoner);
=======
    /**
     * Retrieves a string representation of the poison type of the room abilities.
     *
     * @return A string representing the type of poison ability.
     */
    @Override
    public String GetTypeString() {
        return "poison_ability";
>>>>>>> Stashed changes
>>>>>>> Stashed changes
    }
}
