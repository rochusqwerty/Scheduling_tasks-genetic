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
public class Blok {
    int id;
    String nazwa;
    int readyTime;
    int czasTrwania;
    
    public Blok(String nazwa, int id, int ready, int czasTrwania){
        this.id = id;
        this.nazwa = nazwa;
        this.readyTime = ready;
        this.czasTrwania = czasTrwania;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getReadyTime() {
        return readyTime;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }
    
    
}
