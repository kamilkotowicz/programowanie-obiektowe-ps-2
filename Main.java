//package test;

import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class Main {
    static ArrayList<Osoba> wczytaj_z_pliku(File plik_in){
        ArrayList<Osoba> osoby = new ArrayList<Osoba>();
        try {
            Scanner sc = new Scanner(plik_in);
            sc.useLocale(Locale.ENGLISH);
            while(sc.hasNext()){
                Osoba os = new Osoba();
                os.imie = sc.next();
                os.nazwisko = sc.next();
                os.wiek = sc.nextInt();
                os.skutecznosc = sc.nextFloat();
                osoby.add(os);
            }
            sc.close();
            return osoby;
        } 
        catch (Exception e) {
            System.out.println("Blad przy odczycie pliku");
            return osoby;
        }
    }
    static float znajdz_najw_skut(ArrayList<Osoba> osoby){
        float najw_skut=-1;
        for(int i=0;i<osoby.size();++i){
            Osoba os = osoby.get(i);
            najw_skut=Math.max(najw_skut,os.skutecznosc);
        }
        return najw_skut;
    }
    static ArrayList<Osoba> znajdz_odpowiednie_osoby(ArrayList<Osoba> osoby,float najw_skut){
        ArrayList<Osoba> szukane_osoby = new ArrayList<Osoba>();
        for(int i=0;i<osoby.size();++i){
            Osoba os=osoby.get(i);
            if(os.imie_dluzsze_niz(3) && os.nazwisko_zakonczone("ski") && os.ma_najw_skut(najw_skut)){
                szukane_osoby.add(os);
            }
        }
        return szukane_osoby;
    }
    static void wypisz_do_pliku(File plik_out,ArrayList<Osoba>znalezione_osoby){
        try{
            PrintWriter printer = new PrintWriter(plik_out);
            for(int i=0;i<znalezione_osoby.size();++i){
                Osoba os=znalezione_osoby.get(i);
                printer.println(os.anonimizuj(os.imie) + ' ' + os.anonimizuj(os.nazwisko) + ' ' + os.wiek);
            }
            printer.close();
        } 
        catch (Exception e) {
            System.out.println("Blad przy zapisie do pliku");
        }

    }
    public static void main(String[] args) throws Exception {
        System.out.println("Podaj nazwe pliku\n");
        Scanner sc = new Scanner(System.in);
        String sciezka_dostepu = sc.next();
        sc.close();
        File plik_in = new File(sciezka_dostepu);
        ArrayList<Osoba> osoby=new ArrayList<Osoba>();
        if(plik_in.exists()){
            osoby=wczytaj_z_pliku(plik_in);
            System.out.println("Wczytywanie pliku udane");
        }
        else{
            System.out.println("Problem z otwarciem pliku do odczytu");
        }
        float najw_skut=znajdz_najw_skut(osoby);
        ArrayList<Osoba> szukane_osoby = znajdz_odpowiednie_osoby(osoby,najw_skut);
        File plik_out = new File(sciezka_dostepu + ".max");
        if(plik_out.exists()){
            wypisz_do_pliku(plik_out,szukane_osoby);
            System.out.println("Zapisywanie wyniku do pliku udane");
        }
        else{
            System.out.println("Problem z otwarciem pliku do zapisu");
        }
    }
}