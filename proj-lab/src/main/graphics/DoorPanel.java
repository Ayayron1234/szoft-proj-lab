package main.graphics;

import main.Room;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DoorPanel extends JPanel {
    public enum Orientation { HORIZONTAL, VERTICAL };

    public interface DoorClickListener {
        void OnClick(Room destination);
    }

    public DoorPanel(Orientation orientation, Room neighbour, DoorClickListener listener) {
        super();

        if (orientation.equals(Orientation.HORIZONTAL)) {
            System.out.println("horizontal");
            setPreferredSize(new Dimension(40, 10));
            setBackground(Color.BLACK);
            setBorder(new MatteBorder(0,5,0,5, Color.GRAY));
        } else if (orientation.equals(Orientation.VERTICAL)) {
            System.out.println("vertical");
            setPreferredSize(new Dimension(10, 100));
            setBackground(Color.BLACK);
            setBorder(new MatteBorder(5,0,5,0, Color.GRAY));
        }



        setVisible(true);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.OnClick(neighbour);
            }
        });
    }
}
