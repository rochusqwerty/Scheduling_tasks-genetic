/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import ustawienia.Ustawienia;

/**
 *
 * @author Marcin
 */
class Sortbynr implements Comparator<Integer[]>
{
    @Override
    public int compare(Integer[] a, Integer[] b)
    {
        return a[0] - b[0];
    }
}

public class RozwiazaniePoczatkowe {
    private List <Integer> M0;
    private List <Integer> M1;
    private double wynik;
    private int obliczone;
    private List <Integer[]> przypisanieM0;
    private List <Integer[]> przypisanieM1;
    
    public RozwiazaniePoczatkowe() {
        this.M0 = new ArrayList<>();
        this.M1 = new ArrayList<>();
        this.obliczone = 0;
    }
    
    public RozwiazaniePoczatkowe(List<Integer> M0, List<Integer> M1) {
        this.M0 = M0;
        this.M1 = M1;
        this.obliczone = 0;
    }
    
    public void obliczWynik(){
        if(obliczone == 0){
            obliczone = 1;
            wynik = 0;
            int czas = 0;
            int rozpoczecie;
            int pom = 0;
            Problem pro;
            przypisanieM0 = new ArrayList<>();
            przypisanieM1  = new ArrayList<>();
            
            for(int i = 0; i < Ustawienia.liczbaZadan; i++){
                pro = Main.listaProblemow.get(M0.get(i));
                rozpoczecie  = (pro.getReadyTime() < czas)? czas: pro.getReadyTime();
                
                while(pom < Main.listaMaintenancow.size() && Main.listaMaintenancow.get(pom).getPoczatek() < rozpoczecie + pro.getCzasTrwania0()){
                    rozpoczecie  = (Main.listaMaintenancow.get(pom).getPoczatek() + Main.listaMaintenancow.get(pom).getDlugosc() < rozpoczecie) ? rozpoczecie: Main.listaMaintenancow.get(pom).getPoczatek() + Main.listaMaintenancow.get(pom).getDlugosc();
                    pom++;
                }
                
                czas = rozpoczecie + pro.getCzasTrwania0();
                wynik += czas;
                przypisanieM0.add(new Integer[]{M0.get(i), czas, rozpoczecie});
            }
            
            Collections.sort(przypisanieM0, new Sortbynr());
            czas = 0;

            for(int i = 0; i < Ustawienia.liczbaZadan; i++){
                
                pro = Main.listaProblemow.get(M1.get(i));
                rozpoczecie = (pro.getReadyTime() < czas)? (czas > przypisanieM0.get(M1.get(i))[1])? czas: przypisanieM0.get(M1.get(i))[1]: (pro.getReadyTime() > przypisanieM0.get(M1.get(i))[1])? pro.getReadyTime() : przypisanieM0.get(M1.get(i))[1];

                czas = rozpoczecie + pro.getCzasTrwania1();

                wynik += czas;
                przypisanieM1.add(new Integer[]{M1.get(i), czas, rozpoczecie});
            }            
        }
        
    }
    public double getWynik() {
        return wynik;
    }

    public List<Integer> getM0() {
        return M0;
    }

    public List<Integer> getM1() {
        return M1;
    }

    public List<Integer[]> getPrzypisanieM0() {
        return przypisanieM0;
    }

    public List<Integer[]> getPrzypisanieM1() {
        return przypisanieM1;
    }
    
}
