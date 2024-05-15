package main.graphics;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class NewGameScreen extends JPanel {
    private JLabel studentsLabel;
    private JLabel teachersLabel;
    private JLabel cleanersLabel;
    private JLabel title;
    private JComboBox<Integer> studentsCount = new JComboBox<>();
    private JComboBox<Integer> teachersCount = new JComboBox<>();
    private JComboBox<Integer> cleanersCount = new JComboBox<>();
    private JButton startButton;
    private Random random = new Random();

    static JsonParser parser = new JsonParser();

    public NewGameScreen() {
        super();

        //initialize components
        studentsLabel = new JLabel("Number of Students:");
        teachersLabel = new JLabel("Number of Teachers:");
        cleanersLabel = new JLabel("Number of Cleaners:");;
        title = new JLabel("New Game Setup");
        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartNewScreen("./maps/");
            }
        });


        //setup JComboBoxes with numbers
        for(int i = 0; i <= 10; i++) {
            studentsCount.addItem(i);
            teachersCount.addItem(i);
            cleanersCount.addItem(i);
        }

        //add components
        this.add(title);
        this.add(studentsLabel); this.add(studentsCount);
        this.add(teachersLabel); this.add(teachersCount);
        this.add(cleanersLabel); this.add(cleanersCount);
        this.add(startButton);
    }

    public void StartNewScreen(String mapsDirectoryPath) {
        File[] files = new File(mapsDirectoryPath).listFiles();
        assert files != null;

        File selectedMapFile = files[random.nextInt(files.length)];

        String mapJsonString = null;
        try {
            mapJsonString = Files.readString(Paths.get(selectedMapFile.getPath() + "/map.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Parse json and deserialize game
        JsonObject mapJson = parser.parse(mapJsonString).getAsJsonObject();
        Game game = Game.Deserialize(mapJson);

        // Switch to game screen
        MainWindow.SetGame(game);
        MainWindow.SetScreen("gameScreen");



        // Parse json and deserialize game
        // JsonObject mapJson = parser.parse(mapJsonString).getAsJsonObject();
        // Game game = Game.Deserialize(mapJson);

        // Switch to game screen
        //MainWindow.SetGame(game);

    }

}
