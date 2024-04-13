package main.roomabilities;

import main.Entity;
import main.Room;
import main.RoomAbility;
import main.actions.Poisoner;

public class PoisonAbility implements RoomAbility {

    @Override
    public void Activate(Room room) {
        Poisoner poisoner = new Poisoner();

        for (Entity entity : room.GetEntities()) {
            entity.ApplyAction(poisoner);
        }
    }
}
