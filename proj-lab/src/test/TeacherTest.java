package test;

import main.*;
import main.actions.SoulDrainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {
    Teacher teacher;
    Student student1;
    Student student2;
    Room room;
    Game game;

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        Room.ResetUIDs();
        Teacher.ResetUIDs();

        game = new Game();
        teacher = game.CreateTeacher();
        student1 = game.CreateStudent("player1");
        student2 = game.CreateStudent("player2");

        room = new Room(2);
        teacher.Teleport(room);

        System.setOut(new PrintStream(myOut));
    }

    @Test
    void drainSouls() {
        assertTrue(student1.Teleport(room));

        myOut.reset();
        teacher.DrainSouls();
        assertEquals(String.format("%s drains the soul of entities.\n%s dropped out of game.\n%s's soul was drained.\n", teacher.GetName(), student1.GetName(), student1.GetName()), myOut.toString());
        myOut.reset();

        assertTrue(student2.Teleport(room));
        student2.AddProtection(new Protection(ProtectionType.SOULD_DRAIN_PROTECTION, 3));
        teacher.DrainSouls();
        assertEquals(String.format("%s drains the soul of entities.\n%s was protected against soul draining.\n", teacher.GetName(), student2.GetName()), myOut.toString());
        myOut.reset();
    }
}