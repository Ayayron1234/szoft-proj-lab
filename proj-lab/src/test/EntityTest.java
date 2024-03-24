package test;

import main.Room;
import main.Student;
import main.Teacher;
import main.Timer;
import main.itemtypes.Beer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {
    Room room;
    Student student;

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Teacher.ResetUIDs();

        student = new Student("player1");
        room = new Room(1);

        student.Teleport(room);

        System.setOut(new PrintStream(myOut));
    }

    @Test
    void stepToConnected() {
        Room connected = new Room(1);
        room.AddNeighbour(connected);

        assertTrue(student.Step(connected));
        assertTrue(connected.GetEntities().contains(student));
        assertFalse(room.GetEntities().contains(student));
    }

    @Test
    void stepToUnconnected() {
        Room unconnected = new Room(1);

        assertFalse(student.Step(unconnected));
        assertFalse(unconnected.GetEntities().contains(student));
        assertTrue(room.GetEntities().contains(student));
    }

    @Test
    void stepToFull() {
        Room full = new Room(0);

        assertFalse(student.Step(full));
        assertFalse(full.GetEntities().contains(student));
        assertTrue(room.GetEntities().contains(student));
    }
}
