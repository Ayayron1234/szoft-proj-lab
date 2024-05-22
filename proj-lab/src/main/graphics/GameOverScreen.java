package main.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends Screen {
    private JLabel resultText;
    private JLabel resultInfoText;
    private JButton exitButton;

    public GameOverScreen() {
        super();

        resultText = new JLabel("result");
        resultText.setFont(new Font(resultText.getFont().getName(), Font.BOLD, 24));
        resultInfoText = new JLabel("info");

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exit();
            }
        });

        this.add(resultText);
        this.add(resultInfoText);
        this.add(exitButton);
    }

    @Override
    public void Present() {
        if (MainWindow.GetGame().IsVictory())
            resultText.setText("Victory!");
        else
            resultText.setText("Teachers Win!");
        // TODO: Set result info

        revalidate();
    }

    public void Exit() {
        MainWindow.Exit();
    }
}
