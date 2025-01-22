package otomasyon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tarife {
    private static double[] fiyatlar = {65, 90, 120, 160, 220, 300};
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tarife İşlemleri");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("TARİFE BİLGİLERİ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);
        JPanel tarifelerPanel = new JPanel();
        tarifelerPanel.setLayout(new GridLayout(6, 2, 10, 10));
        String[] saatAraliklari = {"0-1 saatlik park ücreti", "1-2 saatlik park ücreti", "2-4 saatlik park ücreti",
                "4-8 saatlik park ücreti", "8-12 saatlik park ücreti", "12-24 saatlik park ücreti"};
        JTextField[] fiyatFields = new JTextField[6];
        for (int i = 0; i < saatAraliklari.length; i++) {
            tarifelerPanel.add(new JLabel(saatAraliklari[i] + ":"));
            fiyatFields[i] = new JTextField(String.valueOf(fiyatlar[i]));
            tarifelerPanel.add(fiyatFields[i]);
        }
        frame.add(tarifelerPanel, BorderLayout.CENTER);
        JButton guncelleButton = new JButton("Fiyatları Güncelle");
        guncelleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < fiyatFields.length; i++) {
                    try {
                        double yeniFiyat = Double.parseDouble(fiyatFields[i].getText());
                        fiyatlar[i] = yeniFiyat;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Geçersiz fiyat girdisi! Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Fiyatlar güncellendi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                MainMenuPage.main(args);
                frame.dispose();
            }
        });
        frame.add(guncelleButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    public double fiyatlandirma(double parksuresi) {
        double ucret = 0;
        if (parksuresi <= 1) {
            ucret = fiyatlar[0];
        } else if (parksuresi <= 2) {
            ucret = fiyatlar[1];
        } else if (parksuresi <= 4) {
            ucret = fiyatlar[2];
        } else if (parksuresi <= 8) {
            ucret = fiyatlar[3];
        } else if (parksuresi <= 12) {
            ucret = fiyatlar[4];
        } else {
            ucret = fiyatlar[5];
        }
        return ucret;
    }
    public void tarifefiyat() {
        StringBuilder tarifeler = new StringBuilder("--- TARİFE BİLGİLERİ ---\n");
        tarifeler.append("0-1 saatlik park ücreti: ").append(fiyatlar[0]).append(" TL\n");
        tarifeler.append("1-2 saatlik park ücreti: ").append(fiyatlar[1]).append(" TL\n");
        tarifeler.append("2-4 saatlik park ücreti: ").append(fiyatlar[2]).append(" TL\n");
        tarifeler.append("4-8 saatlik park ücreti: ").append(fiyatlar[3]).append(" TL\n");
        tarifeler.append("8-12 saatlik park ücreti: ").append(fiyatlar[4]).append(" TL\n");
        tarifeler.append("12-24 saatlik park ücreti: ").append(fiyatlar[5]).append(" TL\n");
        JOptionPane.showMessageDialog(null, tarifeler.toString(), "Tarife Bilgileri", JOptionPane.INFORMATION_MESSAGE);
    }
    public void fiyatGuncelle(String[] args) {
        JFrame frame = new JFrame("Fiyatları Güncelle");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 2, 10, 10));
        String[] saatAraliklari = {"0-1 saatlik park ücreti", "1-2 saatlik park ücreti", "2-4 saatlik park ücreti",
                "4-8 saatlik park ücreti", "8-12 saatlik park ücreti", "12-24 saatlik park ücreti"};
        JTextField[] fiyatFields = new JTextField[6];
        for (int i = 0; i < saatAraliklari.length; i++) {
            frame.add(new JLabel(saatAraliklari[i] + ":"));
            fiyatFields[i] = new JTextField(String.valueOf(fiyatlar[i]));
            frame.add(fiyatFields[i]);
        }
        JButton guncelleButton = new JButton("Güncelle");
        guncelleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < fiyatFields.length; i++) {
                    try {
                        double yeniFiyat = Double.parseDouble(fiyatFields[i].getText());
                        fiyatlar[i] = yeniFiyat;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Geçersiz fiyat girdisi! Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Fiyatlar güncellendi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                MainMenuPage.main(args);
            }
        });
        frame.add(guncelleButton);
        frame.setVisible(true);
    }
}
