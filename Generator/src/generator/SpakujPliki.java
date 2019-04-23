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
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Magdalena
 */
public class SpakujPliki {
    
    public static void main(String[] args) throws IOException {
        //int strojenie = Ustawienia.wielkoscTurnieju;
        int krok = 10;
        int ileWynikow = 6;
        int ileInstancji = 20;
        String folder = "../wyniki/prawdopodobienstwoMutacji/";
        List <String> foldery = new ArrayList<>( asList("0.05","0.1","0.15","0.2","0.25","0.3","0.35","0.4","0.45","0.5","0.55","0.6","0.65","0.7","0.75","0.8","0.85","0.9","0.95"));
        
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(folder + "/zbiorcze.txt")));
        String strojenie;
        for (int i=0; i<ileWynikow; i++) {
            strojenie = foldery.get(i);
            File odczyt = new File(folder + strojenie + "/podsumowanie.txt");
            Scanner in = new Scanner(odczyt);
            String zdanie = "";
            for (int j=0; j<ileInstancji; j++) 
                zdanie += "\n" + in.nextLine();
            zdanie += "\n";
            out.write(zdanie);
            /*
            switch ( strojenie ) {
               case 500:
               krok=100;
               break;
               
               case 2000:
               krok=500;
               break;
            }
            */
            //strojenie += krok;
        }
        out.close();
    }
}
