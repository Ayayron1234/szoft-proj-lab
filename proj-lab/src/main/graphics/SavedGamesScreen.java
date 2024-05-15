package main.graphics;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class SavedGamesScreen extends JPanel {
    private static class GameSave {
        private String name = null;
        private String path = null;

        private GameSave(String name, String path) {
            this.name = name;
            this.path = path;
        }

        public static GameSave Create(String path) {
            // Get save name from save path
            String[] splitPath = path.split("/");
            String name = splitPath[splitPath.length - 1];

            return new GameSave(name, path);
        }

        public String GetName() {
            return name;
        }

        public String GetPath() {
            return path;
        }
    }

    private HashMap<String, GameSave> saves = null;
    private GameSave selectedSave = null;

    static JsonParser parser = new JsonParser();

    public SavedGamesScreen(String savesDirectoryPath) {
        super();

        // Load saved games from the ./saves/ directory
        LoadSaves(savesDirectoryPath);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // List saves
        CreateSavedGamesTable();

        // Create start game button
        JButton startGameBtn = new JButton("Start Game");
        add(startGameBtn);
        startGameBtn.addActionListener(e -> StartGame(savesDirectoryPath));

        // Create exit button
        JButton exitBtn = new JButton("Back");
        add(exitBtn);
        exitBtn.addActionListener(e -> MainWindow.SetScreen("startMenu"));
    }

    private void CreateSavedGamesTable() {
        // Select the first save in the list by default
        selectedSave = saves.entrySet().iterator().next().getValue();

        JPanel panel = new JPanel();
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        JTable table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Enable single selection mode

        saves.forEach((saveName, save) -> {
            model.addRow(new Object[]{ save.GetName() });
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    selectedSave = saves.get((String) table.getValueAt(selectedRow, 0));
                    System.out.printf("Selected saved game: \"%s\"\n", selectedSave.GetName());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
    }

    private void LoadSaves(String savesDirectoryPath) {
        saves = new HashMap<>();

        // Get directories inside save path
        File[] files = new File(savesDirectoryPath).listFiles();

        // Create save object for each subdirectory
        for (File file : files) {
            if (file.isDirectory()) {
                CreateGameSaveObject(file.getName());
            }
        }
    }

    private void CreateGameSaveObject(String path) {
        // Create and insert save object
        GameSave save = GameSave.Create(path);
        saves.put(save.GetName(), save);
    }

    private void StartGame(String savesDirectoryPath) {
        // Read map string
        String mapJsonString = null;
        try {
            mapJsonString = Files.readString(Paths.get(savesDirectoryPath + selectedSave.GetPath() + "/map.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Parse json and deserialize game
        JsonObject mapJson = parser.parse(mapJsonString).getAsJsonObject();
        Game game = Game.Deserialize(mapJson);

        // Switch to game screen
        MainWindow.SetGame(game);
        MainWindow.SetScreen("gameScreen");
    }
}
