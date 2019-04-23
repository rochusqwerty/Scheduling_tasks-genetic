/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

/**
 *
 * @author Marcin
 */
public class Maintenance {
    private int nr;
    private int dlugosc;
    private int poczatek;

    public Maintenance(int nr, int poczatek, int dlugosc) {
        this.nr = nr;
        this.dlugosc = dlugosc;
        this.poczatek = poczatek;
    }

    public int getNr() {
        return nr;
    }

    public int getDlugosc() {
        return dlugosc;
    }

    public int getPoczatek() {
        return poczatek;
    }
    
}
