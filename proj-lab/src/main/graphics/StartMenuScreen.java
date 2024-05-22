package main.graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuScreen extends JPanel {
    public StartMenuScreen() {
//        super();
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
//
//        // Create new game button
//        JButton newGameBtn = new JButton("New Game");
//        firstRow.add(newGameBtn);
//        newGameBtn.addActionListener(e -> MainWindow.SetScreen("newGame"));
//
//        // Create load game button
//        JButton loadGameBtn = new JButton("Load Game");
//        firstRow.add(loadGameBtn);
//        loadGameBtn.addActionListener(e -> MainWindow.SetScreen("savedGames"));
//
//        add(firstRow);
//
//        // Create exit button
//        JButton exitBtn = new JButton("Exit");
//        add(exitBtn);
//        exitBtn.addActionListener(e -> MainWindow.Exit());
        super();
        setLayout(new BorderLayout());
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,2,20,20));
        buttons.setBorder(new EmptyBorder(100,80,100,80));

        // Create new game button
        JButton newGameBtn = new JButton("New Game");
        buttons.add(newGameBtn);
        newGameBtn.addActionListener(e -> MainWindow.SetScreen("newGame"));

        // Create load game button
        JButton loadGameBtn = new JButton("Load Game");
        buttons.add(loadGameBtn);
        loadGameBtn.addActionListener(e -> MainWindow.SetScreen("savedGames"));

        this.add(buttons);

    }
}
