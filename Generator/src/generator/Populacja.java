/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.ArrayList;
import java.util.List;
import ustawienia.Ustawienia;

/**
 *
 * @author Marcin
 */
public class Populacja {
    PrzydzialPoczatkowy pp;
    public List <RozwiazaniePoczatkowe> populacja;
    
    public Populacja(){
        populacja = new ArrayList<>();
        for(int i = 0; i < Ustawienia.rozmiarPopulacji; i++){
            pp = new PrzydzialPoczatkowy();
            populacja.add(pp.getRp());
        }
        Main.optymalne = populacja.get(0);
    };
    
    public Populacja(int a){
        populacja = new ArrayList<>();
    }
    
    public void addPopulacja ( List<RozwiazaniePoczatkowe> a ){
        this.populacja.addAll(a);
    }
    
    public void addPopulacja ( RozwiazaniePoczatkowe a ){
        this.populacja.add(a);
    }
    
    public void delPopulacja ( RozwiazaniePoczatkowe a ){
        this.populacja.remove(a);
    }
    
    public List<RozwiazaniePoczatkowe> getPopulacja() {
        return populacja;
    }
    
}
