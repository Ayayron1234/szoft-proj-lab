package main.graphics;

import main.Game;
import main.Item;
import main.Room;
import main.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameScreen extends Screen {
    private LogPanel        logPanel;
    private MapPanel        mapPanel;

    private JPanel          inventoryContainerPanel;
    private InventoryPanel  inventoryPanel;

    private JPanel          roomItemsContainerPanel;
    private InventoryPanel  roomItemsPanel;

    Game game;

    GameScreen() {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridBagLayout());
        GridBagConstraints gamePanelConstraints = new GridBagConstraints();
        gamePanelConstraints.fill = GridBagConstraints.BOTH;

        AddInventoryPanel(gamePanel, gamePanelConstraints);

        JPanel mapContainerPanel = new JPanel();
        mapContainerPanel.setBackground(Color.blue);
        gamePanelConstraints.weightx = 0.988;
        gamePanel.add(mapContainerPanel, gamePanelConstraints);

        JButton finishTurnButton = new JButton("finish turn");
        mapContainerPanel.add(finishTurnButton);
        finishTurnButton.addActionListener(e -> {
            if (!game.UIActionEnabled())
                return;

            game.UIReady();
        });

        AddRoomItemsPanel(gamePanel, gamePanelConstraints);

        add(gamePanel);

        logPanel = new LogPanel();
        add(logPanel);
    }

    @Override
    public void Present() {
        game = MainWindow.GetGame();
        MainWindow.GameInitialized();
    }

    public void Redraw() {
        // Redraw inventory
        inventoryContainerPanel.removeAll();
        inventoryPanel = new InventoryPanel("Player Inventory", GetActivePlayersInventory(), (Item item) -> {
            if (!game.UIActionEnabled())
                return;

            game.GetActivePlayer().PlaceItem(item);
            game.UIReady();
        });
        inventoryContainerPanel.add(inventoryPanel);

        // Redraw room items
        roomItemsContainerPanel.removeAll();
        roomItemsPanel = new InventoryPanel("Room Inventory", GetRoomItems(), (Item item) -> {
            if (!game.UIActionEnabled())
                return;

            game.GetActivePlayer().PickUpItem(item);
            game.UIReady();
        });
        roomItemsContainerPanel.add(roomItemsPanel);

        // Redraw log panel
        logPanel.Redraw();

        revalidate();
    }

    void AddInventoryPanel(JPanel parent, GridBagConstraints constraints) {
        inventoryContainerPanel = new JPanel();
        constraints.weightx = 0.06;
        parent.add(inventoryContainerPanel, constraints);
    }

    void AddRoomItemsPanel(JPanel parent, GridBagConstraints constraints) {
        roomItemsContainerPanel = new JPanel();
        constraints.weightx = 0.06;
        parent.add(roomItemsContainerPanel, constraints);
    }

    ArrayList<Item> GetActivePlayersInventory() {
        return game.GetActivePlayer().GetItems();
    }

    ArrayList<Item> GetRoomItems() {
        return GetActivePlayersContainingRoom().GetItems();
    }

    Room GetActivePlayersContainingRoom() {
        return game.GetActivePlayer().GetContainingRoom();
    }
}
