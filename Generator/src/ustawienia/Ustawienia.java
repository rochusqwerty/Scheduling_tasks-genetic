/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ustawienia;

/**
 *
 * @author Marcin
 */
public class Ustawienia {
    //***Generator***
    static public int iloscInstancji = 50;
    static public int liczbaZadan = 100;
    static public int minIloscPrzerwan = liczbaZadan / 4; //!!!NIE RUSZAĆ!!! w tresci zadania
    static public int maxIloscPrzerwan = minIloscPrzerwan; //można zmieniać
    static public int maxCzasTrwaniaOperacji = 30;
    static public int minCzasTrwaniaOperacji = 1;
    static public int maxCzasRozpoczecia = 1;     //określony później
    static public int maxCzasPrzerwania = 10;
    static public int minCzasPrzerwania = 5;
    static public int iloscPrzerwan = 0; //0-losowa wartosc z przedzialu
    //Genetyk
        //kiedy koniec
    static public int poIteracji = 2; //przelacznik kiedy koniec: 1-iteracje wpp-czas
    static public int iloscIteracji = 2000;
    static public int czasDzialania = 10 * 1000; //w milisekundach 1/1000 s
        //parametry
    static public int rozmiarPopulacji = 200;
    static public double prawdopodobienstwoMutacji = 0.05;
    static public int przedzialKrzyzowania = liczbaZadan/4;
    static public int wielkoscTurnieju = 2;
    
}
