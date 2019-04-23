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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import ustawienia.Ustawienia;

/**
 *
 * @author Marcin
 */
class Sortbytime implements Comparator<Maintenance>
{
    @Override
    public int compare(Maintenance a, Maintenance b)
    {
        return a.getPoczatek() - b.getPoczatek();
    }
}
class Sortbytime1 implements Comparator<Blok>
{
    @Override
    public int compare(Blok a, Blok b)
    {
            return a.readyTime - b.readyTime;
    }
}

public class Main {
    //public static List <Zad> daneZ;
    //public static List <Maintenance> daneP;
    /**
     * @param args the command line arguments
     */
    static List <Problem> listaProblemow = new ArrayList<>();
    static List <Maintenance> listaMaintenancow = new ArrayList<>();
    static RozwiazaniePoczatkowe optymalne;
    static double srednieRozwiazanie;
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        for (int stroj=0; stroj<19;stroj++) {       
        
            
        String folderZapis = "../wyniki/prawdopodobienstwoMutacji/" + Ustawienia.prawdopodobienstwoMutacji;        
        String sciezka = folderZapis;
        String plikPodsumowanie = "podsumowanie.txt";
        boolean file = new File(sciezka).mkdirs();
        PrintWriter sum = new PrintWriter(new BufferedWriter(new FileWriter(folderZapis + "/" + plikPodsumowanie)));
        
        
        for(int j = 0; j < Ustawienia.iloscInstancji; j++){
            listaProblemow = new ArrayList<>();
            listaMaintenancow = new ArrayList<>();
            
        String folderOdczyt = "../instancje";
        String plikOdczyt = j + ".txt";
        File odczyt = new File(folderOdczyt + "/" + plikOdczyt);
        Scanner in = new Scanner(odczyt);
        
        String zdanie = in.nextLine();
            System.out.println(zdanie);
        zdanie = in.nextLine();
        int ileZad = Integer.parseInt(zdanie);
        for(int i = 0; i < ileZad; i++){
            zdanie = in.nextLine();
            String[] items= zdanie.split(";");
            Problem p = new Problem(
                i,
                Integer.parseInt(items[0].trim()),
                Integer.parseInt(items[1].trim()),
                Integer.parseInt(items[4].trim()) );
            listaProblemow.add(p);
        }
        zdanie = in.nextLine();
        while( !zdanie.equals("*** EOF ***")){
            String[] items= zdanie.split(";");
            Maintenance p = new Maintenance(
                Integer.parseInt(items[0].trim()),
                Integer.parseInt(items[2].trim()),
                Integer.parseInt(items[3].trim()) );
            listaMaintenancow.add(p);
            zdanie = in.nextLine();
        }
        Collections.sort(listaMaintenancow, new Sortbytime());
        long aktualny = 0;
        long poczatek = System.currentTimeMillis();
        Genetyczny g = new Genetyczny();
        g.selekcja(1);  
        g.krzyz();
        if(Ustawienia.poIteracji == 1){
            for(int i = 1; i < Ustawienia.iloscIteracji; i++){
                g.selekcja();  
                g.krzyz();
            }
        }
        else{
            while(aktualny<Ustawienia.czasDzialania){
                g.selekcja();  
                g.krzyz();
                aktualny = System.currentTimeMillis() - poczatek;
            }
        }

        
        System.out.println(listaProblemow.size());
        System.out.println(listaMaintenancow.size());
        //optymalne.obliczWynik();
        System.out.println(optymalne.getWynik());
        System.out.println(srednieRozwiazanie);
        
        
        
        String plikZapis = "wyniki" + j + ".txt";
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(folderZapis + "/" + plikZapis)));
        
        
        int pommaint = 0;
        
        out.write("**** " + Integer.toString(1) + " ****\n");
        out.write(optymalne.getWynik() + "; " + srednieRozwiazanie + "\n");
        sum.write(optymalne.getWynik() + "; " + srednieRozwiazanie + "\n");
        List <Blok> rozwiazanieM0 = new ArrayList<>();
        List <Blok> rozwiazanieM1 = new ArrayList<>();
        int sumaMa = 0;
        for (Maintenance m : listaMaintenancow) {
            rozwiazanieM0.add(new Blok("maint" + m.getNr() + "_M1", 3, m.getPoczatek(), m.getDlugosc()));
            sumaMa += m.getDlugosc();
            
            pommaint++;
        }
        
        for(int i = 0; i < Ustawienia.liczbaZadan; i++){
                optymalne.getPrzypisanieM0();
                rozwiazanieM0.add(new Blok("o1_" + optymalne.getPrzypisanieM0().get(i)[0] + "_M1", 2,optymalne.getPrzypisanieM0().get(i)[2], optymalne.getPrzypisanieM0().get(i)[1]-optymalne.getPrzypisanieM0().get(i)[2]));
        }
        
        for(int i = 0; i < Ustawienia.liczbaZadan; i++){
                optymalne.getPrzypisanieM1();
                rozwiazanieM1.add(new Blok("o1_" + optymalne.getPrzypisanieM1().get(i)[0] + "_M2", 2,optymalne.getPrzypisanieM1().get(i)[2], optymalne.getPrzypisanieM1().get(i)[1]-optymalne.getPrzypisanieM1().get(i)[2]));
        }
        System.out.println("Rozmiar: " + rozwiazanieM0.size());
        Collections.sort(rozwiazanieM0, new Sortbytime1());
        Collections.sort(rozwiazanieM1, new Sortbytime1());
        System.out.println("Rozmiar: " + rozwiazanieM0.size());
        int k = 0;
        int idle = 0;
        int licz = 0;
        int sumaIdle1 = 0;
        int sumaIdle2 = 0;
        out.write("M1: ");
        while(rozwiazanieM0.size()>licz){
            if(k < rozwiazanieM0.get(licz).readyTime){
                out.write("idle" + idle + "_M1, " + k + ", " + (rozwiazanieM0.get(licz).readyTime-k) + "; ");
                idle++;
                sumaIdle1 += rozwiazanieM0.get(licz).readyTime - k;
            }
            k = rozwiazanieM0.get(licz).readyTime+rozwiazanieM0.get(licz).czasTrwania;
            out.write(rozwiazanieM0.get(licz).getNazwa() + ", " + rozwiazanieM0.get(licz).readyTime + ", " + rozwiazanieM0.get(licz).czasTrwania + "; ");
            licz++;
        }
        k=0;
        idle = 0;
        licz = 0;
        out.write("\n");
        out.write("M2: ");
        while(rozwiazanieM1.size()>licz){
            if(k < rozwiazanieM1.get(licz).readyTime){
                out.write("idle" + idle + "_M2, " + k + ", " + (rozwiazanieM1.get(licz).readyTime-k) + "; ");
                idle++;
                sumaIdle2 += rozwiazanieM1.get(licz).readyTime - k;
            }
            k = rozwiazanieM1.get(licz).readyTime+rozwiazanieM1.get(licz).czasTrwania;
            out.write(rozwiazanieM1.get(licz).getNazwa() + ", " + rozwiazanieM1.get(licz).readyTime + ", " + rozwiazanieM1.get(licz).czasTrwania + "; ");
            licz++;
        }
        out.write("\n" + sumaMa + "\n" + 0 + "\n" + sumaIdle1 + "\n" + sumaIdle2);
        out.close();
    }
        sum.close();
        Ustawienia.prawdopodobienstwoMutacji+= 0.05;
    }
    }
}
