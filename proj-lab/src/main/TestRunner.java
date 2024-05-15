package main;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestRunner {

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

        // Load expected output
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

        // Redirect input
        FileInputStream fis;
        try {
            fis = new FileInputStream(testPath + "/input.txt");

            // Redirect System.in to read from the file
            System.setIn(fis);
        } catch (Exception e) {
            System.out.println("The specified file was not found: " + testPath + "/input.txt");
        }

        if (index == 7)
            System.out.print("");

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
