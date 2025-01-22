package otomasyon;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Rapor {
    private static List<String> plakalar;
    private static List<String> girisSaatleri;
    private static List<String> cikisSaatleri;
    private static List<Double> ucretler;
    public Rapor() {
        plakalar = new ArrayList<>();
        girisSaatleri = new ArrayList<>();
        cikisSaatleri = new ArrayList<>();
        ucretler = new ArrayList<>();
    }
    public static void raporaEkle(Rapor rapor, String plaka, LocalTime girisSaati, LocalTime cikisSaati, double ucret) {
        plakalar.add(plaka);
        girisSaatleri.add(girisSaati.getHour() + ":" + String.format("%02d", girisSaati.getMinute()));
        cikisSaatleri.add(String.format("%02d:%02d", cikisSaati.getHour(), cikisSaati.getMinute()));
        ucretler.add(ucret);
    }
    public static void raporyazdir(Rapor rapor, String args[]) {
        JFrame frame = new JFrame("Otopark Raporu");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 600, 100);

        JLabel titleLabel = new JLabel(" Otopark Raporu                 | ", SwingConstants.CENTER);
     
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.setLayout(new FlowLayout());
        topPanel.add(titleLabel);

        String[] columnNames = {"Plaka", "Giriş Saati", "Çıkış Saati", "Ücret"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        double toplamUcret = 0.0;
        for (int i = 0; i < plakalar.size(); i++) {
            String plaka = plakalar.get(i);
            String girisSaati = girisSaatleri.get(i);
            String cikisSaati = cikisSaatleri.get(i);
            double ucret = ucretler.get(i);
            toplamUcret += ucret;
            model.addRow(new Object[]{plaka, girisSaati, cikisSaati, ucret});
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        JLabel toplamLabel = new JLabel("Toplam: " + String.format("%.2f", toplamUcret) + " TL", SwingConstants.CENTER);
        toplamLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(toplamLabel);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1, 10, 10));
        frame.add(buttonPanel, BorderLayout.SOUTH);
        JButton backMenu = new JButton("Ana Menü");
        buttonPanel.add(backMenu);
        backMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenuPage.main(args);
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }
    public static void main(String[] args) {
    }
}