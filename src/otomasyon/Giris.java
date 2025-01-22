package otomasyon;
import java.util.Scanner;
public class Giris {	
	public static boolean gir() {
        boolean giris1=false;
		while (!giris1) {
            System.out.println("OTOARK");
            System.out.println("YETKİLİ GİRİŞİ");
            String kuladi="Enes";
            String sifre="enes123";
            Scanner girs = new Scanner(System.in);
            System.out.print("Kullanıcı Adı: ");
            String kulad = girs.nextLine();
            System.out.print("Şifre: ");
            String sifregir = girs.nextLine();
            if (kulad.contentEquals(kuladi) && sifregir.contentEquals(sifre)) {
                System.out.println("Giriş başarılı. Hoş geldiniz!");
                System.out.println();
               giris1=true; 
            } else {
                System.out.println("Kullanıcı adı veya şifre yanlış! Lütfen tekrar deneyin.");
                System.out.println();
            }
        }
		return giris1;
	}
}