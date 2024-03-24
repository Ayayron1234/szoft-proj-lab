package test;

import main.*;
import main.itemtypes.Beer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BeerTest {
    Student student;
    Teacher teacher;
    Room room;
    Timer timer;
    Beer beer;

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Teacher.ResetUIDs();

        student = new Student("player1");
        teacher = new Teacher();
        room = new Room(3);
        timer = new Timer();
        beer = new Beer();

        timer.Subscribe(beer);
        room.PlaceItem(beer);
        student.Teleport(room);
        teacher.Teleport(room);

        System.setOut(new PrintStream(myOut));
    }

    @Test
    void BeerProvidedProtection() {
        student.PickUpItem(beer);
        assertEquals("Hallgató(player1) picked up beer with duration:3.\n", myOut.toString());
        myOut.reset();

        timer.StartRound(new TimerEvent(0, 0, 0));
        assertEquals("Hallgató(player1)'s protection provided by beer now has duration:2.\n", myOut.toString());
        myOut.reset();

        student.PlaceItem(beer);
        assertEquals("Hallgató(player1) placed down beer and lost protection.\n", myOut.toString());
        myOut.reset();

        student.PickUpItem(beer);
        assertEquals("Hallgató(player1) picked up beer with duration:2.\n", myOut.toString());
        myOut.reset();

        timer.StartRound(new TimerEvent(0, 0, 0));
        myOut.reset();
        timer.StartRound(new TimerEvent(0, 0, 0));
        assertEquals("Hallgató(player1) lost protection from beer.\n", myOut.toString());

        timer.StartRound(new TimerEvent(0, 0, 0));

        myOut.reset();
        student.PlaceItem(beer);
        assertEquals("Hallgató(player1) placed down beer.\n", myOut.toString());
    }

    @Test
    void BeerProtectsAgainstSoulDrain() {
        student.PickUpItem(beer);
        assertEquals(true, student.HasProtectionType(ProtectionType.SOULD_DRAIN_PROTECTION));
        myOut.reset();

        teacher.DrainSouls();
        assertEquals("Oktató0 drains the soul of entities.\nHallgató(player1) was protected against soul draining.\n", myOut.toString());
        myOut.reset();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}