package Interface;

import java.awt.*;
import javax.swing.*;

/**
 * This class represents a graphical user interface (GUI) for a TCP client application.
 * It provides buttons for camera interaction, sending messages, and requesting the message history.
 * The interface also includes a text area for displaying messages and a text field for user input.
 *
 * @author Diego Elizondo
 */
public class Interface extends JPanel {
    private JButton btnCamera;
    private JButton btnSend;
    private JButton btnHistorial;
    private JScrollPane txtAreaScrollPane;
    private JTextArea txtArea;
    private JTextField txtField;

    /**
     * Creates an instance of the Interface class, constructing and arranging all graphical components.
     */
    public Interface() {
        // Construct components
        btnCamera = new JButton("Camera");
        btnSend = new JButton("Send");
        btnHistorial = new JButton("History");
        txtArea = new JTextArea(5, 5);
        txtArea.setEditable(false);
        txtAreaScrollPane = new JScrollPane(txtArea);
        txtField = new JTextField(5);

        // Adjust size and set layout
        setPreferredSize(new Dimension(667, 366));
        setLayout(null);

        // Add components
        add(btnCamera);
        add(btnSend);
        add(btnHistorial);
        add(txtAreaScrollPane);
        add(txtField);

        // Set component bounds (only needed by Absolute Positioning)
        btnCamera.setBounds(30, 295, 120, 50);
        btnSend.setBounds(270, 295, 120, 50);
        btnHistorial.setBounds(510, 295, 120, 50);
        txtAreaScrollPane.setBounds(10, 10, 640, 220);
        txtField.setBounds(235, 250, 195, 25);
    }

    /**
     * The entry point of the application.
     * Creates and displays a JFrame to host the Interface panel.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("TCPClient");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Interface());
        frame.pack();
        frame.setVisible(true);
    }
}
