package main.roomabilities;

import main.Entity;
import main.Room;
import main.RoomAbility;
import main.Student;
import main.actions.Poisoner;

public class PoisonAbility implements RoomAbility {
    @Override
    public void Activate(Room room) {
        System.out.println("PoisonAbility.Activate");

        System.out.println("new Poisoner");
        Poisoner poisoner = new Poisoner();

        room.GetEntities();
        System.out.println("For entity in room.GetEntities()");

        new Student("").ApplyAction(poisoner);
    }
}
