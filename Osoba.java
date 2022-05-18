public class Osoba{
    public String imie,nazwisko;
    public int wiek;
    public float skutecznosc;

    boolean imie_dluzsze_niz(int min_dlugosc){
        return imie.length() > min_dlugosc;
    }

    boolean nazwisko_zakonczone(String koncowka){
        String nazw=nazwisko;
        int roznica_dlugosci=nazw.length()-koncowka.length();
        for(int i=0;i<koncowka.length();++i){
            if(koncowka.charAt(i)!=nazw.charAt(i+roznica_dlugosci))return false;
        }
        return true;
    }

    boolean ma_najw_skut(float najw_skut){
        return skutecznosc == najw_skut;
    }

    String anonimizuj(String s){
        String nowy = new String();
        nowy+=s.charAt(0);
        for(int i=1;i<s.length()-3;++i){
            nowy+='*';
        }
        for(int i=s.length()-3;i<s.length();++i){
            nowy+=s.charAt(i);
        }
        return nowy;
    }
}
