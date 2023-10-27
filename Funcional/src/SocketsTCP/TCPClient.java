package SocketsTCP;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class TCPClient extends JFrame {
    private JButton btnCamera;
    private JButton btnSend;
    private JButton btnHistory;
    private JScrollPane txtAreaScrollPane;
    private JTextArea txtArea;
    private JTextField txtField;
    private PrintWriter out;
    private BufferedReader in;

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

        // Set up the server connection
        try {
            String serverAddress = "localhost"; // Change to the server's IP address if different
            int serverPort = 12345;
            Socket socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

    private void appendToTextArea(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtArea.append(message + "\n"); // Agregar el mensaje al txtArea
            }
        });
    }

    private void sendMessage() {
        String message = txtField.getText();
        if (!message.isEmpty()) {
            out.println(message);
            txtField.setText(""); // Clear the input field
        }
    }

    private void sendHistoryRequest() {
        out.println("historial");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TCPClient().setVisible(true);
            }
        });
    }
}