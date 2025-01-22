package otomasyon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Abone {
    private String ad;
    private String plaka;
    private String abonelikTipi;
    private double odemeMiktari;
    private static final String PLAKA_REGEX = "^(0[1-9]|[1-7][0-9]|8[01])\\s?[A-Z]{1,4}\\s?\\d{1,4}$";
    private static List<Abone> aboneListesi = new ArrayList<>();
    public Abone(String ad, String plaka, String abonelikTipi, double odemeMiktari) {
        this.ad = ad;
        this.plaka = plaka;
        this.abonelikTipi = abonelikTipi;
        this.odemeMiktari = odemeMiktari;
    }
    public static List<Abone> getAboneListesi() {
        return aboneListesi;
    }
    public String getAd() {
        return ad;
    }
    public String getPlaka() {
        return plaka;
    }
    public String getAbonelikTipi() {
        return abonelikTipi;
    }
    public double getOdemeMiktari() {
        return odemeMiktari;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Abone İşlemleri");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("ABONE İŞLEMLERİ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton addButton = new JButton("Abone Kaydı");
        JButton removeButton = new JButton("Abone Sonlandırma");
        JButton listButton = new JButton("Abone Listesi");
        JButton backMenu = new JButton("Ana Menü");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(listButton);
        buttonPanel.add(backMenu);

        frame.add(buttonPanel, BorderLayout.CENTER);
        backMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenuPage.main(args);
                frame.dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aboneKaydi();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aboneSonlandirma();
            }
        });
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aboneListesiGoruntule();
            }
        });

        frame.setVisible(true);
    }
    private static void aboneKaydi() {
        JFrame frame = new JFrame("Abone Kaydı");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5, 2, 10, 10));
        JLabel adLabel = new JLabel("Adınız:");
        JTextField adField = new JTextField();
        JLabel plakaLabel = new JLabel("Aracın Plakası:");
        JTextField plakaField = new JTextField();
        JLabel abonelikLabel = new JLabel("Abonelik Tipi:");
        JComboBox<String> abonelikComboBox = new JComboBox<>(new String[]{"Aylık", "Üç Aylık"});
        JLabel odemeLabel = new JLabel("Ücreti Ödendi mi?");
        JComboBox<String> odemeComboBox = new JComboBox<>(new String[]{"Evet", "Hayır"});
        JButton kaydetButton = new JButton("Kaydet");
        frame.add(adLabel);
        frame.add(adField);
        frame.add(plakaLabel);
        frame.add(plakaField);
        frame.add(abonelikLabel);
        frame.add(abonelikComboBox);
        frame.add(odemeLabel);
        frame.add(odemeComboBox);
        frame.add(new JLabel(""));
        frame.add(kaydetButton);
        kaydetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ad = adField.getText();
                String plaka = plakaField.getText().toUpperCase().trim();
                String abonelikTipi = (String) abonelikComboBox.getSelectedItem();
                String odemeDurumu = (String) odemeComboBox.getSelectedItem();
                double odemeMiktari;
                if (!Pattern.matches("[a-zA-ZğĞıİöÖüÜşŞçÇ\\s]+", ad)) {
                    JOptionPane.showMessageDialog(frame, "Geçersiz isim! Sadece harflerden oluşan bir isim giriniz.");
                    return;
                }
                if (!Pattern.matches(PLAKA_REGEX, plaka)) {
                    JOptionPane.showMessageDialog(frame, "Geçersiz plaka formatı! Lütfen geçerli bir plaka girin.");
                    return;
                }
                if (abonelikTipi.equals("Aylık")) {
                    odemeMiktari = 2500.0;
                } else if (abonelikTipi.equals("Üç Aylık")) {
                    odemeMiktari = 6000.0;
                } else {
                    JOptionPane.showMessageDialog(frame, "Geçersiz abonelik tipi!");
                    return;
                }
                if (odemeDurumu.equals("Evet")) {
                    Abone yeniAbone = new Abone(ad, plaka, abonelikTipi, odemeMiktari);
                    aboneListesi.add(yeniAbone);
                    JOptionPane.showMessageDialog(frame, "Abone kaydınız başarıyla oluşturuldu.");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Ücret ödenmediği için abonelik kaydı tamamlanamadı.");
                }
            }
        });
        frame.setVisible(true);
    }
    private static void aboneSonlandirma() {
        String plaka = JOptionPane.showInputDialog("Plakanızı giriniz:");

        for (Abone abone : aboneListesi) {
            if (abone.getPlaka().equals(plaka)) {
                aboneListesi.remove(abone);
                JOptionPane.showMessageDialog(null, "Abonelik sonlandırıldı.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Belirtilen plakaya sahip bir abone bulunamadı.");
    }
    private static void aboneListesiGoruntule() {
        JFrame frame = new JFrame("Abone Listesi");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        String[] columnNames = {"Ad", "Plaka", "Abonelik Tipi", "Ödeme Miktarı"};
        String[][] data = new String[aboneListesi.size()][4];
        double toplamOdeme = 0.0;
        for (int i = 0; i < aboneListesi.size(); i++) {
            Abone abone = aboneListesi.get(i);
            data[i][0] = abone.getAd();
            data[i][1] = abone.getPlaka();
            data[i][2] = abone.getAbonelikTipi();
            data[i][3] = String.valueOf(abone.getOdemeMiktari());
            toplamOdeme += abone.getOdemeMiktari();
        }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        JLabel toplamLabel = new JLabel("Toplam Ödeme: " + toplamOdeme, SwingConstants.CENTER);
        toplamLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(toplamLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}