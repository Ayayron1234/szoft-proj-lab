package main.roomabilities;

import main.Entity;
import main.Room;
import main.RoomAbility;
import main.actions.Poisoner;

public class PoisonAbility implements RoomAbility {

    @Override
    public void Activate(Room room) {
        Poisoner poisoner = new Poisoner();
        System.out.printf("room#%d poisons it's entities", room.GetRoomNumber());

        for (Entity entity : room.GetEntities()) {
            entity.ApplyAction(poisoner);
        }
    }

    @Override
    public String GetTypeString() {
        return "poison_ability";
    }
}
