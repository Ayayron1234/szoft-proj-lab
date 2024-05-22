package main.graphics;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LogPanel extends JPanel {
    private JTextArea systemMessages;

    private static ByteArrayOutputStream    logOutputStream;
    private static PrintStream              originalSystemOut;

    public static void HandleLogs() {
        logOutputStream = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(logOutputStream));
    }

    public static void StopHandlingLogs() {
        if (originalSystemOut != null)
            System.setOut(new PrintStream(originalSystemOut));
    }

    public LogPanel() {
        super();
        systemMessages = new JTextArea();
        systemMessages.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(systemMessages);
        scrollPane.setPreferredSize(new Dimension(660, 100));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void Redraw() {
        systemMessages.setText(logOutputStream.toString());
        systemMessages.setCaretPosition(systemMessages.getDocument().getLength());
    }

    public void SetMessages(String message) {
        systemMessages.setText(message);
    }
}
