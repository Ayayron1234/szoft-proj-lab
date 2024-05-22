package main.graphics;
import main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class MainWindow extends JFrame {
    static MainWindow instance = null;

    CardLayout cardLayout = null;
    JPanel cardPanel = null;

    StartMenuScreen     startMenu = null;
    SavedGamesScreen    savedGamesScreen = null;
    NewGameScreen       newGameScreen = null;
    GameScreen          gameScreen = null;
    GameOverScreen      gameOverScreen = null;

    Game                game = null;
    CountDownLatch      gameInitializedLatch = null;

    private HashMap<String, ImageIcon> loadedImages = new HashMap<>();

    public MainWindow(String title) {
        super(title);
    }

    public static void WaitForGameInitalization() {
        instance.gameInitializedLatch = new CountDownLatch(1);
        try {
            instance.gameInitializedLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void GameInitialized() {
        instance.gameInitializedLatch.countDown();
    }

    public static void RedrawGame() {
        if (instance.game.IsGameOver())
            SetScreen("gameOver");

        instance.gameScreen.Redraw();
    }

    public static ImageIcon LoadImageIcon(String path) {
        if (instance.loadedImages.containsKey(path))
            return instance.loadedImages.get(path);

        java.net.URL imgURL = MainWindow.class.getResource(path);
        if (imgURL != null) {
            instance.loadedImages.put(path, new ImageIcon(imgURL));
            return instance.loadedImages.get(path);
        } else {
            System.err.println("Couldn't find file: " + path);
            return new ImageIcon("");
        }
    }

    public static Image ResizeImage(Image sourceImage, int w, int h) {
        BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(sourceImage, 0, 0, w, h, null);
        g2.dispose();

        return resizedImage;
    }

    public static void Open(String title, int width, int height) {
        instance = new MainWindow(title);
        instance.InitFrame(width, height);
        instance.InitScreens();
    }

    public static void SetScreen(String screenName) {
        instance.cardLayout.show(instance.cardPanel, screenName);

        for (Component comp : instance.cardPanel.getComponents())
            if (comp instanceof Screen && comp.isVisible())
                ((Screen)comp).Present();
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
        // Game Screen
        gameScreen = new GameScreen();
        cardPanel.add(gameScreen, "game");
        // Game Over Screen
        gameOverScreen = new GameOverScreen();
        cardPanel.add(gameOverScreen, "gameOver");

        add(cardPanel, BorderLayout.CENTER);

        // Show start menu
        cardLayout.show(cardPanel, "startMenu");

        getContentPane().setLayout(cardLayout);
    }

}
