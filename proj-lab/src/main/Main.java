package main;

import main.actions.Poisoner;
import main.actions.SoulDrainer;
import main.actions.Stunner;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.itemtypes.Beer;
import main.itemtypes.Camembert;
import main.itemtypes.SlideRule;
import main.itemtypes.TVSZ;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        RunTests("./tests/");
    }

    public static void RunTests(String folderPath) {
        File[] files = new File(folderPath).listFiles();

        int passedCount = 0;
        int i = 1;
        for (File file : files) {
            if (file.isDirectory()) {
                if (RunTest(i++, file))
                    ++passedCount;
            }
        }

        System.out.printf("Summary: %d out of %d tests passed.", passedCount, files.length);
    }

    static JsonParser parser = new JsonParser();

    private static boolean RunTest(int index, File testDir) {
        String testPath = testDir.getPath();
        boolean result = true;


        String expectedString = "";
//        //teszt
//        try (FileInputStream fis = new FileInputStream((testPath + "/output.txt"));
//             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
//             BufferedReader reader = new BufferedReader(isr)) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                expectedString = expectedString + line;
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // Load expected output
        //String expectedString = null;
        try {
            expectedString = new String(Files.readAllBytes(Paths.get(testPath + "/output.txt")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Parse initial json
        String initialString = null;
        try {
            initialString = new String(Files.readAllBytes(Paths.get(testPath + "/initial.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonObject initial = parser.parse(initialString).getAsJsonObject();

        // Redirect System.out to output
        ByteArrayOutputStream output = new ByteArrayOutputStream();;
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(output));

//        if (index == 18)
//            System.out.println("");

        // Redirect input
        FileInputStream fis;
        try {
            fis = new FileInputStream(testPath + "/input.txt");

            // Redirect System.in to read from the file
            System.setIn(fis);
        } catch (Exception e) {
            System.out.println("The specified file was not found: " + testPath + "/input.txt");
        }

        // Deserialize initial game state
        Game game = Game.Deserialize(initial);
        game.Start();

        // Reset System.out to console
        System.setOut(originalSystemOut);

        // Compare output against expected
        expectedString = expectedString.replace("\r","").replace("’", "'");
        String outputString = output.toString();

        if (!(expectedString.toString().equals(outputString)))
            result = false;

        // Print data to System.out
        System.out.printf("\t%d. Test: %s - %s\n", index, testDir.getName(), (result) ? "PASSED" : "FAILED");
        System.out.println("******* Expected *******");
        System.out.println(expectedString);
        System.out.println("******** Actual ********");
        System.out.println(output.toString());
        System.out.println();

        return result;
    }

}