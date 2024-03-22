package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Entity implements TimerSubscriber {
    Room containingRoom;
    ArrayList<Protection> activeProtections = new ArrayList<>();

    // Returns true if successful otherwise false. Only works on connected rooms, if destination is not connected, use the Teleport method.
    public boolean Step(Room destination) {
        // Check if destination is reachable from the room in which the entity currently is
        ArrayList<Room> neighbours = containingRoom.GetNeighbours();
        if (!neighbours.contains(destination))
            return false;

        return MoveToRoom(destination);
    }

    public abstract String GetName();

    public boolean Teleport(Room destination) {
        return MoveToRoom(destination);
    }

    public ArrayList<Protection> GetActiveProtections() {
        return activeProtections;
    }

    public void ApplyAction(Action action) {
        action.Execute(this);
    }

    private boolean MoveToRoom(Room destination) {
        // Check whether room has enough capacity to accept entity
        if (!destination.CanStepInto(this))
            return false;

        containingRoom.RemoveEntity(this);
        destination.AcceptEntity(this);
        containingRoom = destination;
        return true;
    }
}
