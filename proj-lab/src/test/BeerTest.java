package test;

import main.*;
import main.itemtypes.Beer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BeerTest {
    Student student;
    Room room;
    Timer timer;
    Beer beer;

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        student = new Student();
        room = new Room(3);
        timer = new Timer();
        beer = new Beer();

        timer.Subscribe(beer);
        room.PlaceItem(beer);
        student.Teleport(room);

        System.setOut(new PrintStream(myOut));
    }

    @Test
    void BeerProvidedProtection() {
        student.PickUpItem(beer);
        assertEquals("Hallgató picked up beer with duration:3.\n", myOut.toString());
        myOut.reset();

        timer.StartRound(new TimerEvent(0, 0, 0));
        assertEquals("Hallgató's protection provided by beer now has duration:2.\n", myOut.toString());
        myOut.reset();

        student.PlaceItem(beer);
        assertEquals("Hallgató placed down beer and lost protection.\n", myOut.toString());
        myOut.reset();

        student.PickUpItem(beer);
        assertEquals("Hallgató picked up beer with duration:2.\n", myOut.toString());
        myOut.reset();

        timer.StartRound(new TimerEvent(0, 0, 0));
        myOut.reset();
        timer.StartRound(new TimerEvent(0, 0, 0));
        assertEquals("Hallgató lost protection from beer.\n", myOut.toString());

        timer.StartRound(new TimerEvent(0, 0, 0));

        myOut.reset();
        student.PlaceItem(beer);
        assertEquals("Hallgató placed down beer.\n", myOut.toString());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}