package main.graphics;

import main.Item;
import main.itemtypes.TVSZ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InventoryPanel extends JPanel {
    public interface ItemClickListener {
        void onClick(Item item);
    }

    public InventoryPanel(String title, ArrayList<Item> items, ItemClickListener listener) {
        setLayout(new GridLayout(5 + 1, 1));

        JLabel label = new JLabel(title);
        add(label);

        int i = 0;
        for (Item item : items) {
            ItemPanel itemPanel = new ItemPanel(item, e -> listener.onClick(item), 48, 48);
            itemPanel.setPreferredSize(new Dimension(48, 48));
            add(itemPanel);
            ++i;
            if (i == 5)
                return;
        }

        for (; i < 5; ++i) {
            JPanel empty = new JPanel();
            empty.setPreferredSize(new Dimension(48, 48));
            add(empty);
        }
    }
}
