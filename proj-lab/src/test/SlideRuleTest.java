package test;

import main.Game;
import main.Room;
import main.Student;
import main.Teacher;
import main.itemtypes.SlideRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SlideRuleTest {
    Student student;
    SlideRule sliderule;
    Room room;
    Game game;

    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();


    @BeforeEach
    void setUp() {
        Room.ResetUIDs();

        student = new Student("player1");
        game = new Game();
        sliderule = new SlideRule(game);
        room = new Room(3);

        room.PlaceItem(sliderule);

        student.Teleport(room);

        System.setOut(new PrintStream(myOut));
    }

    @Test
    public void WinGameTest() {
        student.PickUpItem(sliderule);
        assertEquals("Hallgató(player1) picked up the sliderule from room #0 so Students won the game.\nGame.End(true)\n", myOut.toString());
        myOut.reset();

        //assertEquals();

    }

}