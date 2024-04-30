//package test;
//
//import main.Game;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class Tests {
//
//    Game game;
//    InputStream inputStream;
//
//    final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
//
//    @BeforeEach
//    void Setup(){
//        System.setOut(new PrintStream(myOut));
//    }
//
//    @Test
//    void BlockPoisonTest(){
//        // Get input
//        List<String> commands = new ArrayList<String>();
//        try {
//            commands = Files.readAllLines(Paths.get("../tests/BlockPoison/input.txt"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Get output
//        List<String> output = new ArrayList<String>();
//        try {
//            output = Files.readAllLines(Paths.get("../tests/BlockPoison/output.txt"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        game = new Game(10);
//        game.Deserialize("../tests/BlockPoison/initial.json");    // TODO serialization
//        game.Start();
//        int i = 0;
//        for(String cmd : commands){
//            inputStream = new ByteArrayInputStream(cmd.getBytes());
//            System.setIn(inputStream);
//            assertEquals(myOut.toString(), output.get(i));
//            i++;
//        }
//    }
//
//    @Test
//    void BlockSoulDrainWithBeerTest(){
//
//    }
//
//    @Test
//    void BlockSoulDrainWithTVSZTest(){
//
//    }
//
//    @Test
//    void CleanerSendsPeopleOutTest(){
//
//    }
//
//    @Test
//    void DrainSoulTest(){
//
//    }
//
//    @Test
//    void LoseGameByTimeoutTest(){
//
//    }
//
//    @Test
//    void PickupFakeItemTest(){
//
//    }
//
//    @Test
//    void PickupItemTest(){
//
//    }
//
//    @Test
//    void PickupStickyItemTest(){
//
//    }
//
//    @Test
//    void PlaceAirFreshenerTest(){
//
//    }
//
//    @Test
//    void PlaceItemTest(){
//
//    }
//
//    @Test
//    void PoisonateRoomTest(){
//
//    }
//
//    @Test
//    void PosisonEntitiesTest(){
//
//    }
//
//    @Test
//    void StepTest(){
//
//    }
//
//    @Test
//    void StepFailCapacityFullTest(){
//
//    }
//
//    @Test
//    void StunTeacherTest(){
//
//    }
//
//    @Test
//    void TeleportTest(){
//
//    }
//
//    @Test
//    void WinGameTest(){
//
//    }
//}
