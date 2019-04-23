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
public class Problem {
    private int nr;
    private int czasTrwania0;
    private int czasTrwania1;
    private int readyTime;

    public Problem(int nr, int czasTrwania0, int czasTrwania1, int readyTime) {
        this.nr = nr;
        this.czasTrwania0 = czasTrwania0;
        this.czasTrwania1 = czasTrwania1;
        this.readyTime = readyTime;
    }

    public int getNr() {
        return nr;
    }

    public int getCzasTrwania0() {
        return czasTrwania0;
    }

    public int getCzasTrwania1() {
        return czasTrwania1;
    }

    public int getReadyTime() {
        return readyTime;
    }

    
}
