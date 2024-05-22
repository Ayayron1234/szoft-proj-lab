package main.graphics;

import main.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ItemPanel extends JPanel {
    public ItemPanel(Item item, ActionListener onClick, int width, int height) {
        // Load image and create button
        ImageIcon icon = MainWindow.LoadImageIcon("/resources/" + item.GetName() + ".png");
        icon = new ImageIcon(MainWindow.ResizeImage(icon.getImage(), width, height));
        JButton button = new JButton(icon);

        // Set button width and height
        button.setPreferredSize(new Dimension(width, height));
        button.setMinimumSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));

        // Add button to JPanel and add provided actionListener to the button's click event
        add(button);
        button.addActionListener(onClick);
    }
}
