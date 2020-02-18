package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class KonsoliIO implements IO {

    private Scanner lukija;
    
    public KonsoliIO() {
        this.lukija = new Scanner(System.in);
    }
    
    @Override
    public void print(String m) {
        System.out.print(m);
    }

    @Override
    public String nextLine() {
        return lukija.nextLine();
    }

    @Override
    public int nextInt() {
        return Integer.parseInt(lukija.nextLine());
    }

    @Override
    public void println(String m) {
        System.out.println(m);
    }
    
}
