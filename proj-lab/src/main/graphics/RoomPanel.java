package main.graphics;

import main.Room;
import main.Entity;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

public class RoomPanel extends JPanel {

    private final int SMALL_ROOM_SIZE = 200;
    private final int NORMAL_ROOM_SIZE = 225;
    private final int LARGE_ROOM_SIZE = 250;

    private final Color BACKGROUND_COLOR = Color.GRAY;
    private final Color DOOR_COLOR = Color.BLACK;
    private boolean hasNorth;
    private boolean hasSouth;
    private boolean hasEast;
    private boolean hasWest;


    private Room currentRoom;

    private JPanel entityPanel;
    private JPanel northDoorPanel;
    private JPanel southDoorPanel;
    private JPanel eastDoorPanel;
    private JPanel westDoorPanel;

    private JPanel door;
    private JPanel door2;
    private JPanel door3;
    private JPanel door4;
    private JPanel door5;
    private JPanel door6;
    private JPanel door7;


    private ArrayList<JPanel> doorPanels;

    public RoomPanel(Room room, DoorPanel.DoorClickListener clickListener) {
        super();
        currentRoom = room;
        setBackground(BACKGROUND_COLOR);
        SetPanelSize(room);
        //setPreferredSize(new Dimension(250, 250));
        //setMinimumSize(new Dimension(100, 100));
        setLayout(new BorderLayout());

        doorPanels = new ArrayList<>();


        /*Image scaledImage3 = icon.getImage().getScaledInstance(Integer.parseInt(String.valueOf(icon.getIconWidth() * 0.3)), Integer.parseInt(String.valueOf(icon.getIconHeight() * 0.3)), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(scaledImage);
        JLabel entityLabel3 = new JLabel(scaledIcon3);*/


        //initialize door panels
        InitializeDoors(clickListener);

        northDoorPanel = new JPanel(new GridBagLayout());
        northDoorPanel.setPreferredSize(new Dimension(0, 10));
        northDoorPanel.setBackground(Color.blue);
        add(northDoorPanel, BorderLayout.NORTH);



        southDoorPanel = new JPanel(new GridBagLayout());
        southDoorPanel.setPreferredSize(new Dimension(0, 10));
        southDoorPanel.setBackground(Color.GREEN);

        //southDoorPanel.add(door);
        add(southDoorPanel, BorderLayout.SOUTH);

        westDoorPanel = new JPanel(new GridBagLayout());
        westDoorPanel.setPreferredSize(new Dimension(10, 0));
        westDoorPanel.setBackground(Color.pink);
        //westDoorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //westDoorPanel.add(door2);

        add(westDoorPanel, BorderLayout.WEST);

        eastDoorPanel = new JPanel(new GridBagLayout());
        eastDoorPanel.setPreferredSize(new Dimension(10, 0));
        eastDoorPanel.setBackground(Color.RED);

        add(eastDoorPanel, BorderLayout.EAST);

        //initialize entityPanel
        entityPanel = new JPanel();
        add(entityPanel);
        entityPanel.setPreferredSize(new Dimension(25, 25));
        //entityPanel.setMaximumSize(new Dimension(25, 25));
        entityPanel.setBackground(BACKGROUND_COLOR);

        PlaceDoors();
        PlaceEntities();

        //entityPanel.add(entityLabel3);


        setVisible(true);

    }

   /*public RoomPanel(Room room, DoorPanel.DoorClickListener clickListener) {
        super();
        currentRoom = room;
        SetPanelSize(currentRoom);
        //setPreferredSize(new Dimension(250, 250));
        setBackground(BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());
        InitializeDoors(clickListener);
        System.out.println(doorPanels.length);
        InitializePanels();
        PlaceDoors();
        AddPanels();
        PlaceEntities();

        setVisible(true);
    }*/

    public void InitializePanels() {


        //initialize door panels
        northDoorPanel = new JPanel(new GridBagLayout());
        northDoorPanel.setPreferredSize(new Dimension(10, 10));
        northDoorPanel.setBackground(BACKGROUND_COLOR);

        southDoorPanel = new JPanel(new GridBagLayout());
        southDoorPanel.setPreferredSize(new Dimension(10, 10));
        southDoorPanel.setBackground(BACKGROUND_COLOR);

        westDoorPanel = new JPanel(new GridBagLayout());
        westDoorPanel.setPreferredSize(new Dimension(10, 10));
        westDoorPanel.setBackground(Color.GREEN);

        eastDoorPanel = new JPanel(new GridBagLayout());
        eastDoorPanel.setPreferredSize(new Dimension(10, 10));
        eastDoorPanel.setBackground(Color.BLUE);

        entityPanel = new JPanel();
        entityPanel.setBackground(BACKGROUND_COLOR);
        add(entityPanel);
    }

    public void AddPanels() {


        /*northDoorPanel.setPreferredSize(new Dimension(10, 10));
        northDoorPanel.setBackground(BACKGROUND_COLOR);*/
        //add(northDoorPanel, BorderLayout.NORTH);

        /*southDoorPanel.setPreferredSize(new Dimension(10, 10));
        southDoorPanel.setBackground(BACKGROUND_COLOR);*/
        //add(southDoorPanel, BorderLayout.SOUTH);

        /*westDoorPanel.setPreferredSize(new Dimension(10, 10));
        westDoorPanel.setBackground(Color.GREEN);*/
        add(westDoorPanel, BorderLayout.WEST);

        /*eastDoorPanel.setPreferredSize(new Dimension(10, 10));
        eastDoorPanel.setBackground(Color.BLUE);*/
        add(eastDoorPanel, BorderLayout.EAST);
    }

    public void SetRoom(Room room) {
        currentRoom = room;
    }

    /**
     * Sets the size of the JPanel depending on room's capacity
     * @param room - the Room object we are setting the panel size according to
     */
    public void SetPanelSize(Room room) {
        if(room.GetCapacity() <= 2) {
            this.setPreferredSize(new Dimension(SMALL_ROOM_SIZE, SMALL_ROOM_SIZE));
        }
        else if(room.GetCapacity() > 2 && room.GetCapacity() <= 4) {
            this.setPreferredSize(new Dimension(NORMAL_ROOM_SIZE, NORMAL_ROOM_SIZE));
        }
        else if(room.GetCapacity() >= 5) {
            this.setPreferredSize(new Dimension(LARGE_ROOM_SIZE, LARGE_ROOM_SIZE));
        }
    }

    /**
     * Places door panels on the JPanel according
     * to the neighbours of the Room
     */

    public void PlaceDoors() {
        for(JPanel doorPanel : doorPanels) {
            eastDoorPanel.add(doorPanel);
            westDoorPanel.add(doorPanel);
        }
    }

    public void InitializeDoors(DoorPanel.DoorClickListener clickListener) {
        //if(currentRoom.GetNeighbours() == null) return;

        for(int i = 0; i < currentRoom.GetNeighbours().size(); i++) {
            //System.out.println(currentRoom.GetNeighbours().get(i).GetRoomNumber());
            DoorPanel doorPanel = new DoorPanel(DoorPanel.Orientation.HORIZONTAL, currentRoom.GetNeighbours().get(i), clickListener);
            //System.out.println(doorPanel.getHeight());

            JPanel door = new JPanel();
            door.setPreferredSize(new Dimension(10, 40));
            door.setBackground(DOOR_COLOR);
            door.setBorder(new MatteBorder(5,0,5,0, BACKGROUND_COLOR));
            //westDoorPanel.add(doorPanel);*/
            doorPanels.add(doorPanel);
            //westDoorPanel.add(door);

            /*door4 = new JPanel();
            door4.setPreferredSize(new Dimension(10, 40));
            door4.setBackground(Color.BLACK);
            door4.setBorder(new MatteBorder(5,0,5,0, Color.GRAY));*/


            //TODO place the doors based on directions
        }
    }

    public void Step(Room room) {
        //TODO execute stepping function
        //RoomPanel(room);
    }

    public void PlaceEntities() {
        //if(currentRoom.GetEntities() == null) return;
        for (Entity entity : currentRoom.GetEntities()) {
            String entityTypeString = "";
            switch (entity.getClass().getSimpleName()) {
                case "Student" -> entityTypeString = "student";
                case "Teacher" -> entityTypeString = "teacher";
                case "Cleaner" -> entityTypeString = "cleaner";
            }
            int imageNumber = calculateImageNumber(entity.GetName());

            ImageIcon entityIcon = LoadImageIcon("/resources/" + entityTypeString + imageNumber + ".png");
            int newWidth = (int) Math.ceil(entityIcon.getIconWidth() * 0.3);
            int newHeight = (int) Math.ceil(entityIcon.getIconHeight() * 0.3);

            Image scaledImage = entityIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(scaledIcon);
            entityPanel.add(imageLabel);
        }
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

    public int calculateImageNumber(String name) {
        String[] splitted = name.split("#");
        int id = Integer.parseInt(splitted[1]);
        return id % 3 + 1;
    }

    private String GetSimplifiedClassName(Class clazz) {
        String[] name = clazz.getName().split("\\.");
        return name[name.length - 1];
    }
}