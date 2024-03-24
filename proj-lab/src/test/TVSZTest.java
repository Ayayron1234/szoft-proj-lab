package test;

import main.Room;
import main.Student;
import main.Teacher;
import main.itemtypes.TVSZ;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TVSZTest {
    Student student;
    Teacher teacher;
    TVSZ tvsz;
    Room room;

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Room.ResetUIDs();
        Teacher.ResetUIDs();

        student = new Student("player1");
        teacher = new Teacher();
        room = new Room(3);
        tvsz = new TVSZ();

        room.PlaceItem(tvsz);
        student.Teleport(room);
        teacher.Teleport(room);

        System.setOut(new PrintStream(myOut));
    }

    @Test
    void ProtectionProvidedByTVSZ() {
        student.PickUpItem(tvsz);
        assertEquals("Hallgató(player1) picked up tvsz with duration:3.\n", myOut.toString());
        myOut.reset();

        teacher.DrainSouls();
        assertEquals("Oktató0 drains the soul of entities.\nHallgató(player1)'s protection provided by tvsz now has uses left:2.\nHallgató(player1) was protected against soul draining.\n", myOut.toString());
        myOut.reset();

        student.PlaceItem(tvsz);
        assertEquals("Hallgató(player1) placed down tvsz.\n", myOut.toString());
        myOut.reset();

        student.PickUpItem(tvsz);
        assertEquals("Hallgató(player1) picked up tvsz with duration:2.\n", myOut.toString());
        myOut.reset();

        teacher.DrainSouls();
        assertEquals("Oktató0 drains the soul of entities.\nHallgató(player1)'s protection provided by tvsz now has uses left:1.\nHallgató(player1) was protected against soul draining.\n", myOut.toString());
        myOut.reset();

        student.PlaceItem(tvsz);
        assertEquals("Hallgató(player1) placed down tvsz.\n", myOut.toString());
        myOut.reset();

        student.PickUpItem(tvsz);
        assertEquals("Hallgató(player1) picked up tvsz with duration:1.\n", myOut.toString());
        myOut.reset();

        teacher.DrainSouls();
        assertEquals("Oktató0 drains the soul of entities.\nHallgató(player1) broke tvsz with last use and lost protection.\nHallgató(player1) was protected against soul draining.\n", myOut.toString());
        myOut.reset();
    }
}