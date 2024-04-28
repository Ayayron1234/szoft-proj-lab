package main;

import main.actions.SoulDrainer;

import java.util.*;

public class Cleaner extends Entity {
    private int             uid;
    private final Random    randomGenerator = new Random();

    public Cleaner(int uid, Game game) {
        super(uid, game);
    }

    public void CleanRoom() {
        if (containingRoom == null)
            return;

        containingRoom.Clean();

        Iterator<Entity> iter = containingRoom.GetEntities().iterator();

        while(iter.hasNext()) {
            Entity entity = iter.next();

            // Cleaner doesn't send themself out
            if (entity.equals(this))
                continue;

            // Filter full rooms
            List<Room> availableRooms = containingRoom.GetNeighbours().stream().filter(room -> room.GetSpaceLeft() > 0).toList();
            if (availableRooms.isEmpty())
                continue;

            // Select room to step into
            int index = randomGenerator.nextInt(availableRooms.size());
            Room roomToSendEntities = availableRooms.get(index);

            entity.Step(roomToSendEntities);
        }
    }

    @Override
    public void DropOutOfGame() {
        // Cleaners can't drop out of the game
    }

    @Override
    public void HandleTurn() {

    }

}

