package test;

import main.Room;
import main.Student;
import main.Teacher;
import main.TimerEvent;
import main.itemtypes.Duster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class DusterTest {
    Student student;
    Teacher teacher;
    Duster duster;
    Room room;

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        Room.ResetUIDs();
        Teacher.ResetUIDs();

        student = new Student("player1");
        teacher = new Teacher();
        room = new Room(5);
        duster = new Duster();

        room.PlaceItem(duster);
        student.Teleport(room);
        teacher.Teleport(room);
        student.PickUpItem(duster);

        System.setOut(new PrintStream(myOut));
    }

    @Test
    public void NormalUsage() {
        assertEquals("Oktató0 misses 1 rounds.\n", myOut.toString());
        myOut.reset();
    }

    @Test
    public void MultipleStudents() {
        Student otherStudent = new Student("player2");
        otherStudent.Teleport(room);
        duster.StartRound(new TimerEvent(1,1, 1));
        assertEquals("Oktató0 misses 1 rounds.\n", myOut.toString());
        myOut.reset();
    }

    @Test
    public void MultipleTeachers() {
        Teacher otherTeacher = new Teacher();
        otherTeacher.Teleport(room);
        duster.StartRound(new TimerEvent(1,1, 1));
        assertEquals("Oktató0 misses 1 rounds.\nOktató1 misses 1 rounds.\n", myOut.toString());
        myOut.reset();

    }

    @Test
    public void MultipleStudentsAndTeachers() {
        Student otherStudent = new Student("player2");
        Teacher otherTeacher = new Teacher();
        otherStudent.Teleport(room);
        otherTeacher.Teleport(room);
        duster.StartRound(new TimerEvent(1,1, 1));
        assertEquals("Oktató0 misses 1 rounds.\nOktató1 misses 1 rounds.\n", myOut.toString());
        myOut.reset();
    }

    @Test
    public void UsesTest() {
        duster.StartRound(new TimerEvent(1,1, 1));
        duster.StartRound(new TimerEvent(1,1, 1));
        duster.StartRound(new TimerEvent(1,1, 1));
        assertEquals("Oktató0 misses 1 rounds.\nOktató0 misses 1 rounds.\n", myOut.toString());
    }
}
