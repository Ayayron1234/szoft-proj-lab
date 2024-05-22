package main;

import main.actions.Poisoner;
import main.actions.SoulDrainer;
import main.actions.Stunner;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.graphics.LogPanel;
import main.graphics.MainWindow;
import main.itemtypes.Beer;
import main.itemtypes.Camembert;
import main.itemtypes.SlideRule;
import main.itemtypes.TVSZ;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static MainWindow mainWindow = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LogPanel.HandleLogs();
            MainWindow.Open("A Logarléc", 720, 480);
            new Thread(() -> {
                MainWindow.WaitForGameInitalization();
                MainWindow.GetGame().Start();
            }).start();
        });
    }
}