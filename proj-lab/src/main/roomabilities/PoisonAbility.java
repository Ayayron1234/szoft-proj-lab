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
    @Override
    public void Activate(Room room) {
        Poisoner poisoner = new Poisoner();

        System.out.printf("room#%d poisons it's entities\n", room.GetRoomNumber());

        for (Entity entity : room.GetEntities()) {
            Optional<Item> airFreshenerOptional = entity.GetItems().stream().filter(item -> item.GetName().equals(new AirFreshener().GetName())).findFirst();
            if (airFreshenerOptional.isPresent()) {
                entity.PlaceItem(airFreshenerOptional.get());
                return;
            }
        }

        for (Entity entity : room.GetEntities()) {
            entity.ApplyAction(poisoner);
        }
    }

    @Override
    public String GetTypeString() {
        return "poison_ability";
        }

    /**
     * Retrieves a string representation of the poison type of the room abilities.
     *
     * @return A string representing the type of poison ability.
     */
//    @Override
//    public String GetTypeString() {
//        return "poison_ability";
//    }
}
