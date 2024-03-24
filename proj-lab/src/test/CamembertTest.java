package test;

import main.*;
import main.itemtypes.Beer;
import main.itemtypes.Camembert;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CamembertTest {
    Student student;
    Room room;
    Timer timer;
    Camembert camembert;

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Teacher.ResetUIDs();
        Room.ResetUIDs();

        student = new Student("player1");
        room = new Room(3);
        timer = new Timer();
        camembert = new Camembert();

        timer.Subscribe(camembert);
        student.Teleport(room);

        System.setOut(new PrintStream(myOut));
    }

    @org.junit.jupiter.api.Test
    void PoisonEntities() {
        room.PlaceItem(camembert);
        student.PickUpItem(camembert);
        student.PlaceItem(camembert);
        assertEquals(String.format("%s placed down mask and poisoned room #%d for %d rounds.\n", student.GetName(), room.GetRoomNumber(), 3), myOut.toString());
        myOut.reset();

        timer.StartRound(new TimerEvent(0, 0, 0));
        assertEquals("Hallgató(player1) was poisoned.\nHallgató(player1) misses 1 rounds.\nRoom #0 is poisoned by camambert for duration:2.\n", myOut.toString());
        myOut.reset();

        timer.StartRound(new TimerEvent(0, 0, 0));
        assertEquals("Hallgató(player1) was poisoned.\nHallgató(player1) misses 1 rounds.\nRoom #0 is poisoned by camambert for duration:1.\n", myOut.toString());
        myOut.reset();

        timer.StartRound(new TimerEvent(0, 0, 0));
        assertEquals("Hallgató(player1) was poisoned.\nHallgató(player1) misses 1 rounds.\nRoom #0 is no longer poisoned by camambert.\n", myOut.toString());
        myOut.reset();

        timer.StartRound(new TimerEvent(0, 0, 0));
        assertEquals("", myOut.toString());
        myOut.reset();
    }
}
