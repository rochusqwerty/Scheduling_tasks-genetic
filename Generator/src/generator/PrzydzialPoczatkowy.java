/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ustawienia.Ustawienia;

/**
 *
 * @author Marcin
 */
public class PrzydzialPoczatkowy {
    Random random = new Random();
    //kolejnosc
    List<Integer> M0;
    List<Integer> M1;
    
    RozwiazaniePoczatkowe rp;
    
    public PrzydzialPoczatkowy(){
        this.M0 =  new ArrayList<>();
        this.M1 =  new ArrayList<>();
        int[] tab = new int[Ustawienia.liczbaZadan];
        
        //zerujemy tablice
        for( int i = 0; i < Ustawienia.liczbaZadan; i++) {
            tab[i] = 0;
        }
        
        //M0
        for( int i = 0; i < Ustawienia.liczbaZadan; i++) {
            int zadanie = random.nextInt(Ustawienia.liczbaZadan);
            if( tab[zadanie] == 0){
                M0.add(zadanie);
                tab[zadanie] = 1;
            }
        }
        for( int i = 0; i < Ustawienia.liczbaZadan; i++) {
            if( tab[i] == 0){
                M0.add(i);
                tab[i] = 1;
            }
        }
        
        //M1
        for( int i = 0; i < Ustawienia.liczbaZadan; i++) {
            int zadanie = random.nextInt(Ustawienia.liczbaZadan);
            if( tab[zadanie] == 1){
                M1.add(zadanie);
                tab[zadanie] = 2;
            }
        }
        for( int i = 0; i < Ustawienia.liczbaZadan; i++) {
            if( tab[i] == 1){
                M1.add(i);
                tab[i] = 2;
            }
        }
        rp = new RozwiazaniePoczatkowe(M0,M1);
    }

    public RozwiazaniePoczatkowe getRp() {
        return rp;
    }
}
