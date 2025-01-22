package otomasyon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class LoginPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Yetkili Girişi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 550);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
            
        JPanel imagePanel = new JPanel();
        ImageIcon icon = new ImageIcon("src/OTOARKLOGO.png");
        Image image = icon.getImage(); 
        Image newImage = image.getScaledInstance(400, 200, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage); 
        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);
        frame.add(imagePanel, BorderLayout.NORTH);
        
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel userLabel = new JLabel("Kullanıcı Adı: ");
        JTextField userText = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(userLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(userText, gbc);
        JLabel passwordLabel = new JLabel("Şifre:");
        JPasswordField passwordText = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordText, gbc);
        JButton loginButton = new JButton("Giriş");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);
        JButton cancelButton = new JButton("İptal");
        gbc.gridy = 3;
        loginPanel.add(cancelButton, gbc);
        frame.add(loginPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
            	String kullanici = "Oto";
            	String sifre = "ark1";
            	if (kullanici.contentEquals(username) && sifre.contentEquals(password)) {
            		MainMenuPage.main(args);
            		frame.dispose();
                } else {
                	JOptionPane.showMessageDialog(frame, "Kullanıcı adı veya şifre hatalı...");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    } 
}
