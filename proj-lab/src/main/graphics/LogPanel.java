package main.graphics;

import javax.swing.*;

public class LogPanel extends JPanel {

    private JTextField currentPlayer;
    private JTextField systemMessages;

    public LogPanel() {
        super();

        //Initialize components
        currentPlayer = new JTextField();
        systemMessages = new JTextField();

        //Add components
        this.add(currentPlayer);
        this.add(systemMessages);
    }

    public void SetPlayer(String playerName) {
        currentPlayer.setText(playerName);
    }

    public void SetMessages(String message) {
        systemMessages.setText(message);
    }
}
