package main.graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JPanel {
    private JTextField winnerText;
    private JTextField infoField;
    private JButton exitButton;

    public GameOverScreen(String info) {
        super();

        winnerText = new JTextField("Use the SetMessage(String message) method to set the value of this label");
        infoField = new JTextField(info);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exit();
            }
        });

        this.add(winnerText);
        this.add(infoField);
        this.add(exitButton);


    }

    public void SetMessage(String message) {
        winnerText.setText(message);
    }

    public void Exit() {
        MainWindow.Exit();
    }

}
