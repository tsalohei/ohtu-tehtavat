package ohtu.intjoukkosovellus;

import ohtu.intjoukkosovellus.Sovellus;

public class Main {
    
    public static void main(String[] args) {
        Sovellus sovellus = new Sovellus(new KonsoliIO());
        sovellus.suorita();
    }
}
