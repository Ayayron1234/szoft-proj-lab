package main.graphics;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Game;

import javax.swing.*;
import java.awt.*;
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
//        super();
//
//        //initialize components
//        studentsLabel = new JLabel("Number of Students:");
//        teachersLabel = new JLabel("Number of Teachers:");
//        cleanersLabel = new JLabel("Number of Cleaners:");;
//        title = new JLabel("New Game Setup");
//        startButton = new JButton("Start Game");
//        startButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                StartNewScreen("./maps/");
//            }
//        });
//
//
//        //setup JComboBoxes with numbers
//        for(int i = 0; i <= 10; i++) {
//            studentsCount.addItem(i);
//            teachersCount.addItem(i);
//            cleanersCount.addItem(i);
//        }
//
//        //add components
//        this.add(title);
//        this.add(studentsLabel); this.add(studentsCount);
//        this.add(teachersLabel); this.add(teachersCount);
//        this.add(cleanersLabel); this.add(cleanersCount);
//        this.add(startButton);
        super();
        // Create the main panel with BoxLayout (vertical)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create the top button and center it
        JLabel topLabel = new JLabel("New game setup");
        topLabel.setPreferredSize(new Dimension(600,100));
        topLabel.setFont(new Font(topLabel.getFont().getName(), Font.BOLD, 24));
        topLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(topLabel);

        // Add some vertical space
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create a panel for the labels and combobox
        JPanel labelsComboBoxPanel = new JPanel();
        labelsComboBoxPanel.setLayout(new GridLayout(3,2));
        labelsComboBoxPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelsComboBoxPanel.setPreferredSize(new Dimension(600,100));

        // Create labels and combobox
        for(int i = 0; i<10; i++)
        {
            studentsCount.addItem(i);
            teachersCount.addItem(i);
            cleanersCount.addItem(i);
        }
        JLabel sLabel = new JLabel("Number of students: ");
        sLabel.setFont(new Font(topLabel.getFont().getName(), Font.PLAIN, 12));
        JLabel tLabel = new JLabel("Number of teachers: ");
        tLabel.setFont(new Font(topLabel.getFont().getName(), Font.PLAIN, 12));
        JLabel cLabel = new JLabel("Number of cleaners: ");
        cLabel.setFont(new Font(topLabel.getFont().getName(), Font.PLAIN, 12));


        labelsComboBoxPanel.add(sLabel);
        labelsComboBoxPanel.add(studentsCount);
        labelsComboBoxPanel.add(tLabel);
        labelsComboBoxPanel.add(teachersCount);
        labelsComboBoxPanel.add(cLabel);
        labelsComboBoxPanel.add(cleanersCount);

        // Add the labelsComboBoxPanel to the main panel
        mainPanel.add(labelsComboBoxPanel);

        // Add some vertical space
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create a panel for the bottom buttons
        JPanel bottomButtonsPanel = new JPanel();
        bottomButtonsPanel.setLayout(new GridLayout(1,3));
        bottomButtonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomButtonsPanel.setPreferredSize(new Dimension(600,60));

        // Create the bottom buttons and add them to the panel
        JButton startButton = new JButton("Start game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartNewScreen("./maps/");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> MainWindow.SetScreen("startMenu"));
        bottomButtonsPanel.add(startButton);
        bottomButtonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));  // Add some space between buttons
        bottomButtonsPanel.add(backButton);

        // Add the bottom buttons panel to the main panel
        mainPanel.add(bottomButtonsPanel);

        // Add the main panel to the frame
        this.add(mainPanel);
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
        MainWindow.SetScreen("game");



        // Parse json and deserialize game
        // JsonObject mapJson = parser.parse(mapJsonString).getAsJsonObject();
        // Game game = Game.Deserialize(mapJson);

        // Switch to game screen
        //MainWindow.SetGame(game);

    }

}
