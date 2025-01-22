package otomasyon;
import java.util.Scanner;
public class Ana {
	public static aracgir1 aracgir1 = new aracgir1();
	public static Abone abone = new Abone("", "", "", 0);
	public static Tarife tarife = new Tarife();
    public static Rapor rapor = new Rapor();
	public static Park park=new Park(abone, rapor);
	public static void main(String[] args) {
		LoginPage.main(args);
	}
	public static void raporgir(String[] args) {
		Rapor.raporyazdir(rapor, args);
	}
	public static void aracgir(String[] args) {
		park.setgelplak(args, false);
	}
	public static void aracgiry(String[] args) {
		park.setgelplak(args, true);
	}
	public static void arackalk(String[] args) {
		park.arackalk(args);
	} 
	public static void liste(String[] args) {
		park.liste(args);
	}
}