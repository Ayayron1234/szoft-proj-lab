package main.graphics;
import main.Game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    static MainWindow instance = null;

    CardLayout cardLayout = null;
    JPanel cardPanel = null;

    StartMenuScreen startMenu = null;
    SavedGamesScreen savedGamesScreen = null;
    NewGameScreen newGameScreen = null;

    Game game = null;

    public MainWindow(String title) {
        super(title);
    }

    public static ImageIcon LoadImageIcon(String path) {
        java.net.URL imgURL = MainWindow.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return new ImageIcon("");
        }
    }

    public static void Open(String title, int width, int height) {
        instance = new MainWindow(title);
        instance.InitFrame(width, height);
        instance.InitScreens();
    }

    public static void SetScreen(String screenName) {
        instance.cardLayout.show(instance.cardPanel, screenName);
    }

    public static void SetGame(Game game) {
        instance.game = game;
    }

    public static Game GetGame() {
        return instance.game;
    }

    public static void Exit() {
        System.out.println("Exit was requested");
        instance.dispose();
    }

    private void InitFrame(int width, int height) {
        // Set the size of the window
        setSize(width, height);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the window visible
        setVisible(true);

        // Change window icon
        ImageIcon icon = LoadImageIcon("/resources/icon.png");
        setIconImage(icon.getImage());

        // Use system theme
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignore) { }
    }

    private void InitScreens() {
        // Create card layout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create screens
        // Start Menu
        startMenu = new StartMenuScreen();
        cardPanel.add(startMenu, "startMenu");
        // New Game Screen
        newGameScreen = new NewGameScreen();
        cardPanel.add(newGameScreen, "newGame");
        // Saved Games Screen
        savedGamesScreen = new SavedGamesScreen("./saves/");
        cardPanel.add(savedGamesScreen, "savedGames");

        add(cardPanel, BorderLayout.CENTER);

        // Show start menu
        cardLayout.show(cardPanel, "startMenu");

        getContentPane().setLayout(cardLayout);
    }

}
