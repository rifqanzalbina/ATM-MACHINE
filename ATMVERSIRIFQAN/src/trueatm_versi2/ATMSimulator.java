package trueatm_versi2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ATMSimulator extends JFrame {
    private JTextField pinField;
    private double balance;
    private String pin = "1234";

    public ATMSimulator() {                                
        setTitle("ATM Simulator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLUE);

        JLabel pinLabel = new JLabel("Masukkan PIN:");
        pinLabel.setBounds(450, 250, 500, 25);
        pinLabel.setForeground(Color.WHITE);
        pinField = new JTextField(4);
        pinField.setBounds(550, 250, 100, 25);

        
        JLabel loginMenu = new JLabel("SILAKAN MASUKKAN PIN ANDA");
        loginMenu.setBounds(450, 200, 500, 25);
        loginMenu.setFont(new Font("Arial", Font.BOLD, 20));
        loginMenu.setForeground(Color.white);
        
        String imagePath = "C:\\Users\\User\\OneDrive\\Documents\\NetBeansProjects\\ATMVERSIRIFQAN\\PINGAMBARATM2.png";
        ImageIcon logoIcon = new ImageIcon(imagePath);
        Image image = logoIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(100, 175, 250, 250);
        
        
        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(550, 280, 100, 25);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pin = pinField.getText();
                authenticate(pin);
            }
        });

        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(enterButton);
        panel.add(loginMenu);
        panel.add(imageLabel);
        add(panel);
        setVisible(true);
    }

    public void authenticate(String pin) {
        if (this.pin != null && this.pin.equals(pin)) {
            MainMenu.showMainMenu(balance, this);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid PIN", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setPIN(String newPIN) {
        pin = newPIN;
    }   

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
