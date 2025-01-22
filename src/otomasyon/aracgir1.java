package otomasyon;
import java.util.Scanner;
public class aracgir1 {
    private String girplaka;
    private static final String PLAKA_REGEX = "^(0[1-9]|[1-7][0-9]|8[01])\\s?[A-Z]{1,4}\\s?\\d{1,4}$";
    Scanner sec = new Scanner(System.in);
    public String yurticiaracgirisi() {
        System.out.println("Yurtiçi Araç Girişi seçildi.");
        boolean plakaValid = false;
        while (!plakaValid) {
            System.out.print("Aracın plakasını giriniz: ");
            String plaka = sec.nextLine().toUpperCase().trim();
            if (plaka.matches(PLAKA_REGEX)) {
                System.out.println("Giriş geçerli: " + plaka);
                girplaka = plaka;
                plakaValid = true;
            } else {
                System.out.println("Geçersiz seçenek! Lütfen doğru bir plaka girin.");
                System.out.println();
            }
        }
        return girplaka;
    }
//------------------------------------------------------------------------------------------------
	public String yurtdisiaracgirisi() {
       String plaka="";  
   	  System.out.println("Yurtdışı Araç Girisi seçildi.");
 		 System.out.print("Plakayı giriniz: ");
 		 plaka=sec.nextLine();
 		 System.out.println("Girilen plaka: "+plaka);
     return plaka;
}
}