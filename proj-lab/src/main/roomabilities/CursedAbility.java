package main.roomabilities;

import main.Room;
import main.RoomAbility;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CursedAbility implements RoomAbility {
    public ArrayList<Room> hiddenNeighbours = new ArrayList<>();
    @Override
    public void Activate(Room room) {
        System.out.println("CursedAbility.Activate");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Did the ablility select a room to hide?\n 1-yes 2-no");
        if (scanner.nextLine().equals("1"))
            room.RemoveNeighbour(new Room(0));

        System.out.println("Did the ablility select a room to reveal?\n 1-yes 2-no");
        if (scanner.nextLine().equals("1"))
            room.AddNeighbour(new Room(0));
    }
}
