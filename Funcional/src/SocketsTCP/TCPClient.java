package SocketsTCP;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import TextReader.Camera;

/**
 * This class represents a simple TCP client application with a graphical user interface. It allows the user
 * to interact with a server using a chat-like interface and control a camera.
 *
 * @author José Barquero
 * @author Diego Elizondo
 */
public class TCPClient extends JFrame {
    private JButton btnCamera;
    private JButton btnSend;
    private JButton btnHistory;
    private JScrollPane txtAreaScrollPane;
    private JTextArea txtArea;
    private JTextField txtField;
    private PrintWriter out;
    private BufferedReader in;
    private Camera camera;

    /**
     * Creates an instance of the TCPClient application.
     */
    public TCPClient() {
        // GUI components
        setTitle("TCP Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(667, 400);
        setLayout(null);

        btnCamera = new JButton("Camera");
        btnSend = new JButton("Send");
        btnHistory = new JButton("History");
        txtArea = new JTextArea(5, 5);
        txtArea.setEditable(false);
        txtArea.setWrapStyleWord(true);
        txtArea.setLineWrap(true);
        txtArea.setOpaque(false);
        txtArea.setFocusable(false);
        txtArea.setMargin(new Insets(10, 10, 10, 10));
        txtArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        txtArea.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
        txtArea.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        txtAreaScrollPane = new JScrollPane(txtArea);
        txtField = new JTextField(5);

        // Component bounds
        btnCamera.setBounds(30, 295, 120, 50);
        btnSend.setBounds(270, 295, 120, 50);
        btnHistory.setBounds(510, 295, 120, 50);
        txtAreaScrollPane.setBounds(10, 10, 640, 220);
        txtField.setBounds(235, 250, 195, 25);

        // Add components to the frame
        add(btnCamera);
        add(btnSend);
        add(btnHistory);
        add(txtAreaScrollPane);
        add(txtField);

        // Action listeners
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        btnHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendHistoryRequest();
            }
        });

        btnCamera.addActionListener(new ActionListener() {
            final boolean run=true;
            @Override
            public void actionPerformed(ActionEvent e) {
                    camera = new Camera();
                    camera.runCamera(run);
                    sendTess();
            }
        });

        // Set up the server connection
        try {
            String serverAddress = "localhost"; // Change to the server's IP address if different
            int serverPort = 12345;
            Socket socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(socket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread messageListener = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String message;
                    while (in != null && (message = in.readLine()) != null) {
                        appendToTextArea(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        messageListener.start();
    }

    /**
     * Appends a message to the text area.
     *
     * @param message The message to append.
     */
    private void appendToTextArea(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtArea.append(message + "\n"); // Agregar el mensaje al txtArea
            }
        });
    }

    /**
     * Sends a message to the server.
     */
    private void sendMessage() {
        String message = txtField.getText();
        if (!message.isEmpty()) {
            out.println(message);
            txtField.setText(""); // Clear the input field
        }
    }

    /**
     * Sends a request for operations history to the server.
     */
    private void sendHistoryRequest() {
        out.println("historial");
    }

    /**
     * Sends an operation message to the server.
     */
    private void sendTess(){
        out.println("tess");
    }

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TCPClient().setVisible(true);
            }
        });
    }
}

