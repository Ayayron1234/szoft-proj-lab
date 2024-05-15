package main.graphics;

import main.Game;

import javax.swing.*;

public class GameScreen extends JPanel {
    private LogPanel logPanel;
    private MapPanel mapPanel;
    private InventoryPanel inventoryPanel;

    Game game;

    GameScreen() {
        game = MainWindow.GetGame();
    }




}
