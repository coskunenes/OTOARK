package otomasyon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainMenuPage {
    public static void main(String[] args) {
        
    	JFrame frame = new JFrame("Ana Menü");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 1000);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        JPanel imagePanel = new JPanel();
        ImageIcon icon = new ImageIcon("src/OTOARKLOGO.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(400, 200, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(imagePanel, BorderLayout.CENTER);
        JLabel titleLabel = new JLabel("ANA MENÜ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.SOUTH);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1, 10, 10));
        frame.add(topPanel, BorderLayout.NORTH);
        JPanel panel1 = new JPanel(new BorderLayout());
        JButton button1 = new JButton("Yurt İçi Araç Giriş");
        JLabel label1 = new JLabel("Yurt içi (Türkiye) plakalı araçların girişinin yapılması.", SwingConstants.CENTER);
        panel1.add(button1, BorderLayout.CENTER);
        panel1.add(label1, BorderLayout.SOUTH);
        
        JPanel panel2 = new JPanel(new BorderLayout());
        JButton button2 = new JButton("Yurt Dışı Araç Giriş");
        JLabel label2 = new JLabel("Yurt dışı plakalı araçların girişinin yapılması.", SwingConstants.CENTER);
        panel2.add(button2, BorderLayout.CENTER);
        panel2.add(label2, BorderLayout.SOUTH);
       
        JPanel panel3 = new JPanel(new BorderLayout());
        JButton button3 = new JButton("Araç Çıkış");
        JLabel label3 = new JLabel("Park halindeki araçların çıkışı.", SwingConstants.CENTER);
        panel3.add(button3, BorderLayout.CENTER);
        panel3.add(label3, BorderLayout.SOUTH);
        
        JPanel panel4 = new JPanel(new BorderLayout());
        JButton button4 = new JButton("Otopark Durum");
        JLabel label4 = new JLabel("Otoparkın anlık durumu.", SwingConstants.CENTER);
        panel4.add(button4, BorderLayout.CENTER);
        panel4.add(label4, BorderLayout.SOUTH);
        
        JPanel panel5 = new JPanel(new BorderLayout());
        JButton button5 = new JButton("Abone İşlem");
        JLabel label5 = new JLabel("Abonelik ile ilgili tüm işlemlerin yönetimi.", SwingConstants.CENTER);
        panel5.add(button5, BorderLayout.CENTER);
        panel5.add(label5, BorderLayout.SOUTH);
        
        JPanel panel6 = new JPanel(new BorderLayout());
        JButton button6 = new JButton("Tarifeler");
        JLabel label6 = new JLabel("Saatlik tarife ücreti bilgisi ve yönetimi.", SwingConstants.CENTER);
        panel6.add(button6, BorderLayout.CENTER);
        panel6.add(label6, BorderLayout.SOUTH);
        
        JPanel panel7 = new JPanel(new BorderLayout());
        JButton button7 = new JButton("Günlük Rapor");
        JLabel label7 = new JLabel("Günlük araç giriş-çıkış ve ücret raporu.", SwingConstants.CENTER);
        panel7.add(button7, BorderLayout.CENTER);
        panel7.add(label7, BorderLayout.SOUTH);
        
        JPanel panel8 = new JPanel(new BorderLayout());
        JButton button0 = new JButton("ÇIKIŞ");
        JLabel label0 = new JLabel("OTOPARK programından çıkış.", SwingConstants.CENTER);
        panel8.add(button0, BorderLayout.CENTER);
        panel8.add(label0, BorderLayout.SOUTH);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);
        mainPanel.add(panel6);
        mainPanel.add(panel7);
        mainPanel.add(panel8);
        
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        button0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ana.aracgir(args);
                frame.dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ana.aracgiry(args);
                frame.dispose();
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ana.arackalk(args);
                frame.dispose();
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ana.liste(args);
                frame.dispose();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Abone.main(args);
                frame.dispose();
            }
        });
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Tarife.main(args);
                frame.dispose();
            }
        });
        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ana.raporgir(args);
                frame.dispose();
            }
        });
    }
}