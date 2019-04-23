/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import ustawienia.Ustawienia;

/**
 *
 * @author Marcin
 */
public class Genetyczny {
    Random random = new Random();
    Populacja po1;
    Populacja po2;
    double avg = 0;
    RozwiazaniePoczatkowe rp1;
    RozwiazaniePoczatkowe rp2;
    RozwiazaniePoczatkowe rp3;
    RozwiazaniePoczatkowe rp4;
    
    public Genetyczny(){
        po1 = new Populacja(1);
        po2 = new Populacja();
        
    }
    
    public void selekcja(int a){
        po2.addPopulacja(po1.getPopulacja());
        po1 = new Populacja(1);
        RozwiazaniePoczatkowe roz1 = po2.getPopulacja().get(0);
        
        for(int i = 0; i < po2.getPopulacja().size(); i++){
            po2.getPopulacja().get(i).obliczWynik();
            avg += po2.getPopulacja().get(i).getWynik();
            if(po2.getPopulacja().get(i).getWynik() < Main.optymalne.getWynik()) {
                Main.optymalne = po2.getPopulacja().get(i);
            }
        }
        Main.srednieRozwiazanie = avg / Ustawienia.rozmiarPopulacji;
    }
    
    public void selekcja(){
        po2.addPopulacja(po1.getPopulacja());
        po1 = new Populacja(1);
        for (int i = 0; i < po2.getPopulacja().size(); i++) {
            po2.getPopulacja().get(i).obliczWynik();
            if(po2.getPopulacja().get(i).getWynik() < Main.optymalne.getWynik()) {
                Main.optymalne = po2.getPopulacja().get(i);
            }
        }
        po1.addPopulacja(Main.optymalne);
        po2.delPopulacja(Main.optymalne);
        
        while(po1.getPopulacja().size() < Ustawienia.rozmiarPopulacji && po2.getPopulacja().size() > Ustawienia.wielkoscTurnieju){
            rp1 = po2.getPopulacja().get(random.nextInt(po2.getPopulacja().size()));
            po2.delPopulacja(rp1);
            for(int i = 0; i < Ustawienia.wielkoscTurnieju; i++) {
                rp2 = po2.getPopulacja().get(random.nextInt(po2.getPopulacja().size()));
                po2.delPopulacja(rp2);
                rp1.obliczWynik();
                rp2.obliczWynik();
                if(rp1.getWynik()>rp2.getWynik())
                    rp1 = rp2;
            }
            po1.addPopulacja(rp1);
        }
        po2 = new Populacja(1);
        po2.addPopulacja(po1.getPopulacja());
        po1 = new Populacja(1);
        
    }
    
    public void krzyz(){
        while(po1.getPopulacja().size() < Ustawienia.rozmiarPopulacji){
            rp1 = po2.getPopulacja().get(random.nextInt(po2.getPopulacja().size()));
            rp2 = po2.getPopulacja().get(random.nextInt(po2.getPopulacja().size()));
            rp3 = new RozwiazaniePoczatkowe();
            rp4 = new RozwiazaniePoczatkowe();
            List <Integer> maszyna = new ArrayList<>();
            for(int i = 0; i < Ustawienia.przedzialKrzyzowania; i++) {
                maszyna = rp1.getM0();
                rp3.getM0().add(maszyna.get(i));
                maszyna = rp1.getM1();
                rp3.getM1().add(maszyna.get(i));
                maszyna = rp2.getM0();
                rp4.getM0().add(maszyna.get(i));
                maszyna = rp2.getM1();
                rp4.getM1().add(maszyna.get(i));
            }
            int zawiera;
            for(int i = 0; i < Ustawienia.liczbaZadan; i++) {
                zawiera = 0;
                for(int j = 0; j<rp3.getM0().size(); j++){
                    if(rp2.getM0().get(i) == rp3.getM0().get(j)){
                        zawiera = 1;
                    }
                }
                if(zawiera == 0)
                    rp3.getM0().add(rp2.getM0().get(i));
                zawiera = 0;
                for(int j = 0; j<rp3.getM1().size(); j++){
                    if(rp2.getM1().get(i) == rp3.getM1().get(j)){
                        zawiera = 1;
                    }
                }
                if(zawiera == 0)
                    rp3.getM1().add(rp2.getM1().get(i));
                zawiera = 0;
                for(int j = 0; j<rp4.getM0().size(); j++){
                    if(rp1.getM0().get(i) == rp4.getM0().get(j)){
                        zawiera = 1;
                    }
                }
                if(zawiera == 0)
                    rp4.getM0().add(rp1.getM0().get(i));
                zawiera = 0;
                for(int j = 0; j<rp4.getM1().size(); j++){
                    if(rp1.getM1().get(i) == rp4.getM1().get(j)){
                        zawiera = 1;
                    }
                }
                if(zawiera == 0)
                    rp4.getM1().add(rp1.getM1().get(i));
            }
            po1.addPopulacja(rp3);
            po1.addPopulacja(rp4);
            mutacja(rp3);
            mutacja(rp4);
        }
        
    }
    public void mutacja(RozwiazaniePoczatkowe rozPo) {
        double prawdopodobienstwo = random.nextInt(100)/100;
        if(prawdopodobienstwo < Ustawienia.prawdopodobienstwoMutacji){        
            if(prawdopodobienstwo <  Ustawienia.prawdopodobienstwoMutacji/2) {
                Collections.swap(rozPo.getM0(), random.nextInt(Ustawienia.liczbaZadan), random.nextInt(Ustawienia.liczbaZadan));  
            }
            else {
                Collections.swap(rozPo.getM1(), random.nextInt(Ustawienia.liczbaZadan), random.nextInt(Ustawienia.liczbaZadan));
            }
            po1.addPopulacja(rozPo);
        }
    }
}
