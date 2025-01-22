package otomasyon;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
public class Park {
    private Tarife tarife = new Tarife();
    private Abone abone;
    private String[] parkyerleri;
    private boolean[] doluyerler;
    private LocalTime[] girissaatler;
    private double parksuresi;
    private LocalTime girisSaati = LocalTime.now();
    private LocalTime cikisSaati = LocalTime.now();
    Rapor rapor = new Rapor();
    private String plaka;
    private String PLAKA_REGEX = "^(0[1-9]|[1-7][0-9]|8[01])\\s?[A-Z]{1,4}\\s?\\d{1,4}$";
    private static final String[] SLOT_LABELS = {
        "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8",
        "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8",
        "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8"
    };
    public Park(Abone abone, Rapor rapor) {
        this.abone = abone;
        this.rapor = rapor;
        this.parkyerleri = new String[SLOT_LABELS.length];
        this.doluyerler = new boolean[SLOT_LABELS.length];
        this.girissaatler = new LocalTime[SLOT_LABELS.length];
        for (int i = 0; i < doluyerler.length; i++) {
            doluyerler[i] = false;
        }
    }
    public void setgelplak(String[] args, Boolean yabanci) {
        yersec(args, yabanci);
    }
    public boolean ayniplaka(String plaka) {
        for (String plakaKontrol : parkyerleri) {
            if (plakaKontrol != null && plakaKontrol.equals(plaka)) {
                return true;
            }
        }
        return false;
    }
    private double ucret;
    public void yersec(String[] args, Boolean yabanci) {
    	if(yabanci == true) {
    		PLAKA_REGEX = "[A-Za-z0-9]+";
    	} else {
    		PLAKA_REGEX = "^(0[1-9]|[1-7][0-9]|8[01])\\s?[A-Z]{1,4}\\s?\\d{1,4}$";
    	}
        String plaka = JOptionPane.showInputDialog(null, "Lütfen plakanızı giriniz:");
        if(plaka == null) {
        	JOptionPane.showMessageDialog(null, "Geçersiz plaka formatı! Lütfen tekrar deneyin.");
            MainMenuPage.main(args);
        	return;
        }
        if (plaka.toUpperCase() != null && Pattern.matches(PLAKA_REGEX, plaka.toUpperCase())) {
            if (ayniplaka(plaka.toUpperCase())) {
                JOptionPane.showMessageDialog(null, "Bu plakaya sahip araç zaten park yerinde!");
                MainMenuPage.main(args);
                return;
            }

            JFrame frame = new JFrame("Park Yeri Seçimi");
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            JLabel label = new JLabel("GİRİŞ YAPACAK ARACI SEÇİNİZ", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            frame.add(label, BorderLayout.NORTH);
            JPanel gridPanel = new JPanel();
            gridPanel.setLayout(new GridLayout(4, 8, 10, 10));
            for (int i = 0; i < SLOT_LABELS.length; i++) {
                JPanel slotPanel = new JPanel();
                slotPanel.setLayout(new BorderLayout());
                JButton button = new JButton(SLOT_LABELS[i]);
                button.setBackground(doluyerler[i] ? Color.RED : Color.GREEN);
                JLabel plateLabel = new JLabel(doluyerler[i] ? parkyerleri[i] : "", SwingConstants.CENTER);
                int index = i;
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	if (ayniplaka(plaka.toUpperCase())) {
                            JOptionPane.showMessageDialog(null, "Bu plakaya sahip araç zaten park yerinde!");
                            MainMenuPage.main(args);
                            frame.dispose();
                            return;
                        }
                        if (!doluyerler[index]) {
                            parkyerleri[index] = plaka.toUpperCase();
                            doluyerler[index] = true;
                            girissaatler[index] = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
                            plateLabel.setText(plaka.toUpperCase());
                            button.setBackground(Color.RED);
                            JOptionPane.showMessageDialog(frame, plaka.toUpperCase() + " plakalı aracın " + SLOT_LABELS[index] + " park yerine girişi yapıldı. Giriş Saati: " + girissaatler[index]);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Seçilen park yeri dolu! Lütfen başka bir yer seçin.");
                        }
                    }
                });
                slotPanel.add(button, BorderLayout.CENTER);
                slotPanel.add(plateLabel, BorderLayout.SOUTH);
                gridPanel.add(slotPanel);
            }
            frame.add(gridPanel, BorderLayout.CENTER);

            JButton ilerleButton = new JButton("İLERLE");
            ilerleButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MainMenuPage.main(args);
                }
            });
            frame.add(ilerleButton, BorderLayout.SOUTH);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Geçersiz plaka formatı! Lütfen tekrar deneyin.");
            MainMenuPage.main(args);
        }
    }
    public void arackalk(String[] args) {
        JFrame frame = new JFrame("Çıkış Yapacak Araç Seçimi");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JLabel label = new JLabel("ÇIKIŞ YAPACAK ARACI SEÇİNİZ", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(label, BorderLayout.NORTH);
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 8, 10, 10));
        for (int i = 0; i < SLOT_LABELS.length; i++) {
            JPanel slotPanel = new JPanel();
            slotPanel.setLayout(new BorderLayout());
            JButton button = new JButton(SLOT_LABELS[i]);
            button.setBackground(doluyerler[i] ? Color.RED : Color.GREEN);
            JLabel plateLabel = new JLabel(doluyerler[i] ? parkyerleri[i] : "", SwingConstants.CENTER);
            int index = i;
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (doluyerler[index]) {
                        plaka = parkyerleri[index];
                        boolean abonelikVar = false;
                        for (Abone abone : Abone.getAboneListesi()) {
                            if (plaka.equalsIgnoreCase(abone.getPlaka())) {
                                abonelikVar = true;
                                break;
                            }
                        }
                        if (abonelikVar) { 
                            ucret = 0;
                            girissaatler[index] = null;
                            Rapor.raporaEkle(rapor, plaka, girisSaati, cikisSaati, ucret);
                            doluyerler[index] = false;
                            parkyerleri[index] = "";
                            plateLabel.setText("");
                            button.setBackground(Color.GREEN);
                            JOptionPane.showMessageDialog(frame, plaka + " Abonelik işlemi başarılı. Araç park yerinden kaldırılıyor...");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Çıkış işlemi gerçekleştiriliyor...");
                            normalCikisIslemi(index);
                        }
                        frame.dispose();
                        MainMenuPage.main(args);
                    } else {
                        JOptionPane.showMessageDialog(frame, SLOT_LABELS[index] + " numaralı park yeri zaten boş.");
                    }
                }
            });
            slotPanel.add(button, BorderLayout.CENTER);
            slotPanel.add(plateLabel, BorderLayout.SOUTH);
            gridPanel.add(slotPanel);
        }
        frame.add(gridPanel, BorderLayout.CENTER);
        JButton ilerleButton = new JButton("İLERLE");
        ilerleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainMenuPage.main(args);
            }
        });
        frame.add(ilerleButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    private void normalCikisIslemi(int cikis) {
        plaka = parkyerleri[cikis];
        LocalTime cikissaati = LocalTime.now();
        long dakikafarki = ChronoUnit.MINUTES.between(girissaatler[cikis], cikissaati);
        long saat = dakikafarki / 60;
        long dakika = dakikafarki % 60;
        parksuresi = saat + (dakika / 60.0);
        ucret = tarife.fiyatlandirma(parksuresi);
        JOptionPane.showMessageDialog(null, plaka + " Araç park süresi: " + saat + " saat " + dakika + " dakika\nÖdemeniz gereken tutar: " + ucret + "TL");
        int onay = JOptionPane.showConfirmDialog(null, "Ödeme tamamlandı mı?", "Ödeme Onayı", JOptionPane.YES_NO_OPTION);
        if (onay == JOptionPane.YES_OPTION) {
            Rapor.raporaEkle(rapor, plaka, girisSaati, cikissaati, ucret);
            doluyerler[cikis] = false;
            parkyerleri[cikis] = "";
            JOptionPane.showMessageDialog(null, "Ödeme başarılı. " + SLOT_LABELS[cikis] + " numaralı park yerinden araç başarıyla kaldırıldı.");
        } else {
            JOptionPane.showMessageDialog(null, "Ödeme başarısız! Ana menüye dönülüyor.");
        }
    }
    public void liste(String[] args) {
        JFrame frame = new JFrame("Otopark Durumu");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JLabel label = new JLabel("OTOPARK DURUMU", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(label, BorderLayout.NORTH);
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 8, 10, 10));
        for (int i = 0; i < SLOT_LABELS.length; i++) {
            JPanel slotPanel = new JPanel();
            slotPanel.setLayout(new BorderLayout());
            JButton button = new JButton(SLOT_LABELS[i]);
            button.setEnabled(false);
            button.setBackground(doluyerler[i] ? Color.RED : Color.GREEN);
            JLabel plateLabel = new JLabel(doluyerler[i] ? parkyerleri[i] : "", SwingConstants.CENTER);
            if (doluyerler[i]) {
                LocalTime now = LocalTime.now();
                long dakikafarki = ChronoUnit.MINUTES.between(girissaatler[i], now);
                long saat = dakikafarki / 60;
                long dakika = dakikafarki % 60;
                plateLabel.setText(parkyerleri[i] + "(" + saat + "H " + dakika + "M )");
            }
            slotPanel.add(button, BorderLayout.CENTER);
            slotPanel.add(plateLabel, BorderLayout.SOUTH);
            gridPanel.add(slotPanel);
        }
        frame.add(gridPanel, BorderLayout.CENTER);
        JButton closeButton = new JButton("KAPAT");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainMenuPage.main(args);
            }
        });
        frame.add(closeButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}