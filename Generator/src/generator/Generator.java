/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ustawienia.Ustawienia;

/**
 *
 * @author Marcin
 */
public class Generator {
    private static final Random random = new Random();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        for (int id = 0; id < Ustawienia.iloscInstancji; id++ ) {
            
            //**********USTAWIENIA**********
            String folder = "../instancje/instancja1";        
            String sciezka = folder;
            String plik = id + ".mwmr";
            if(Ustawienia.iloscPrzerwan == 0) Ustawienia.iloscPrzerwan = random.nextInt(Ustawienia.maxIloscPrzerwan-Ustawienia.minIloscPrzerwan+1) + Ustawienia.minIloscPrzerwan;
            //******************************
            
            int czasZadanSumaPierwszaMaszyna = 0;
            List<String> listaOperacji = new ArrayList<String>();
            
            int dlugoscOperacji1 = 0;
            int dlugoscOperacji2 = 0;
            int pom = 0;
            boolean file = new File(sciezka).mkdirs();
            //if (file.createNewFile());
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(folder + "/" + plik)));
            out.write("**** " + Integer.toString(id) + " ****\n");
            out.write(Integer.toString(Ustawienia.liczbaZadan) + "\n");
            
            for( int k = 0; k < Ustawienia.liczbaZadan; k++) {
                dlugoscOperacji1 = random.nextInt(Ustawienia.maxCzasTrwaniaOperacji)+Ustawienia.minCzasTrwaniaOperacji;
                dlugoscOperacji2 = random.nextInt(Ustawienia.maxCzasTrwaniaOperacji)+Ustawienia.minCzasTrwaniaOperacji;
                czasZadanSumaPierwszaMaszyna += dlugoscOperacji1;
                listaOperacji.add(dlugoscOperacji1 + "; " + dlugoscOperacji2 + "; 0; 1; ");          
            }
            Ustawienia.maxCzasRozpoczecia = czasZadanSumaPierwszaMaszyna / 2;
            System.out.println(Ustawienia.maxCzasRozpoczecia);
            for( int i = 0; i < Ustawienia.liczbaZadan; i++){
                int czasRozpoczeciaZadania = random.nextInt(Ustawienia.maxCzasRozpoczecia) + 1;
                out.write((String)listaOperacji.get(i) + czasRozpoczeciaZadania + "; \n");    
            }

            pom = 1;
            int licznikPrzerwan = 0;

            // przerwania (tylko pierwsza maszyna)
            int[] t = new int[czasZadanSumaPierwszaMaszyna];
            while(licznikPrzerwan < Ustawienia.iloscPrzerwan) {
                int startPrzerwania = random.nextInt(czasZadanSumaPierwszaMaszyna-Ustawienia.maxCzasPrzerwania) + 1;
                int czasTrwaniaPrzerwy = random.nextInt(Ustawienia.maxCzasPrzerwania) + Ustawienia.minCzasPrzerwania;
                if(t[startPrzerwania] == 0){
                    pom = 1;
                    while(t[startPrzerwania+pom] == 0 && pom < czasTrwaniaPrzerwy)
                        pom++;
                    if(pom == czasTrwaniaPrzerwy){
                        pom = 0;
                        while(pom < czasTrwaniaPrzerwy){
                            t[startPrzerwania+pom] = czasTrwaniaPrzerwy;
                            pom++;
                        }
                        licznikPrzerwan++;
                    }         
                }
            }
            int iter = 0;
            licznikPrzerwan = 0;
            while(iter < czasZadanSumaPierwszaMaszyna) {
                if(t[iter]==0)
                    iter++;
                else{
                    out.write(licznikPrzerwan + "; 0; " + iter + "; " + t[iter] + "; \n");
                    licznikPrzerwan++;
                    iter+=t[iter];
                    }
            }
            
            out.write("*** EOF ***");
            out.close();
            System.out.println("Koniec!!!" + id);
        }
    }
    
}
