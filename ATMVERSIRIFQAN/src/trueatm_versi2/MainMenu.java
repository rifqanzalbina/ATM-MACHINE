package trueatm_versi2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainMenu extends JFrame {
    private static double balance;
    private ATMSimulator atm;

    public static void showMainMenu(double currentBalance, ATMSimulator atmSimulator) {
        balance = currentBalance;

        MainMenu frame = new MainMenu();
        frame.setTitle("ATM Simulator - Main Menu");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLUE);

        JButton depositButton = new JButton("Setor Tunai");
        depositButton.setBounds(80, 235, 185, 25);
        JButton balanceButton = new JButton("Informasi");
        balanceButton.setBounds(80, 280, 185, 25);
        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(80, 325, 185, 25);
        JButton withdrawButton = new JButton("Tarik Tunai");
        withdrawButton.setBounds(520, 235, 200, 25);
        JButton changePINButton = new JButton("Ganti PIN");
        changePINButton.setBounds(520, 280, 200, 25);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(520, 325, 200, 25);
                       
        JLabel textinformasi = new JLabel("<  INFORMASI");
        textinformasi.setBounds(280, 280, 200, 25);
        textinformasi.setForeground(Color.WHITE);
        JLabel textSetorTunai = new JLabel("<  SETOR TUNAI");
        textSetorTunai.setForeground(Color.WHITE);
        textSetorTunai.setBounds(280, 235, 200, 25);
        JLabel textTransfer = new JLabel("<  TRANSFER");
        textTransfer.setForeground(Color.WHITE);
        textTransfer.setBounds(280, 325, 200, 25);
        
        JLabel textTarikTunai = new JLabel("TARIK TUNAI  >");
        textTarikTunai.setBounds(420, 280, 200, 25);
        textTarikTunai.setForeground(Color.WHITE);
        JLabel textGantiPIN = new JLabel("GANTI PIN  >");
        textGantiPIN.setForeground(Color.WHITE);
        textGantiPIN.setBounds(435, 235, 200, 25);
        JLabel textCancel = new JLabel("CANCEL   >");
        textCancel.setForeground(Color.WHITE);
        textCancel.setBounds(442, 325, 200, 25);
        
        JLabel silahkanTransaksi = new JLabel("SILAHKAN MEMILIH TRANSAKSI");
        silahkanTransaksi.setBounds(190, 80, 500, 25);
        silahkanTransaksi.setForeground(Color.WHITE);
        silahkanTransaksi.setFont(new Font("Arial", Font.BOLD, 28));
        
        JLabel teksMembatalkan = new JLabel("UNTUK MEMBATALKAN TRANSAKSI");
        teksMembatalkan.setBounds(165, 120, 500, 25);
        teksMembatalkan.setForeground(Color.WHITE);
        teksMembatalkan.setFont(new Font("Arial",Font.BOLD, 28));
        
        JLabel cancelInfo = new JLabel("TEKAN 'CANCEL'");
        cancelInfo.setBounds(280, 160, 500, 25);
        cancelInfo.setForeground(Color.WHITE);
        cancelInfo.setFont(new Font("Arial",Font.BOLD,28));
                               
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Masukkan jumlah setoran:");
                try {
                    double amount = Double.parseDouble(amountString);
                    deposit(amount);
                    JOptionPane.showMessageDialog(null, "Setoran berhasil dilakukan.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Masukkan jumlah setoran yang valid.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Masukkan jumlah penarikan:");
                try {
                    double amount = Double.parseDouble(amountString);
                    withdraw(amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Masukkan jumlah penarikan yang valid.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        changePINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePIN();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToLogin(atmSimulator);
            }
        });
        
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = JOptionPane.showInputDialog("Masukkan nomor akun tujuan:");
                String amountString = JOptionPane.showInputDialog("Masukkan jumlah transfer:");
                try {
                    double amount = Double.parseDouble(amountString);
                    transfer(accountNumber, amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Masukkan jumlah transfer yang valid.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panel.add(depositButton);
        panel.add(balanceButton);
        panel.add(withdrawButton);
        panel.add(changePINButton);
        panel.add(cancelButton);
        panel.add(transferButton);
        
        panel.add(textinformasi);
        panel.add(textSetorTunai);
        panel.add(textTransfer);
        
        panel.add(textTarikTunai);
        panel.add(textGantiPIN);
        panel.add(textCancel);
        
        panel.add(silahkanTransaksi);
        panel.add(teksMembatalkan);
        panel.add(cancelInfo);
        
        
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void deposit(double amount) {
        balance += amount;
    }

    private static void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Penarikan berhasil dilakukan.");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo tidak mencukupi.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void checkBalance() {
        String formattedBalance = String.format("%,.0f", balance); // Memformat saldo dengan titik setiap tiga digit
        String response = "Saldo Anda saat ini adalah Rp. " + formattedBalance;
        JOptionPane.showMessageDialog(null, response, "SISTEM", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void transfer(String accountNumber, double amount) {
        JOptionPane.showMessageDialog(null, "Transfer berhasil dilakukan.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void returnToLogin(ATMSimulator atmSimulator) {
        JOptionPane.showMessageDialog(null, "Anda telah kembali ke Menu Masukkan PIN.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        atmSimulator.setVisible(true);
    }

    private static void changePIN() {
    String newPIN = JOptionPane.showInputDialog("Masukkan PIN baru:");
    if (newPIN != null && !newPIN.isEmpty()) {
        ATMSimulator atm = new ATMSimulator();
        atm.setPIN(newPIN);
        JOptionPane.showMessageDialog(null, "PIN berhasil diubah.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        returnToLogin(atm);
        
    } else {
        JOptionPane.showMessageDialog(null, "PIN baru tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}


