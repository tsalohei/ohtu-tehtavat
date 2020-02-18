package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Sovellus {

    private static IO io;
    private static IntJoukko A, B, C;
    
    public Sovellus(IO io) {
        this.io = io;
        this.A = new IntJoukko();
        this.B = new IntJoukko();
        this.C = new IntJoukko();
    }

    private static String syoteKayttajalta() {
        return io.nextLine();
    }

    private static IntJoukko kayttajanAntamaJoukko() {
        String luettu = syoteKayttajalta();
        while (true) {
            if (luettu.equalsIgnoreCase("a")) {
                return A;
            } else if (luettu.equalsIgnoreCase("b")) {
                return B;
            } else if (luettu.equalsIgnoreCase("c")) {
                return C;
            } else {
                System.out.println("Virheellinen joukko! " + luettu);
                System.out.print("Yritä uudelleen!");
                luettu = syoteKayttajalta();
            }
        }
    }

    private static void lisaa() {
        io.print("Mihin joukkoon?");
        IntJoukko joukko = kayttajanAntamaJoukko();
        System.out.println("");
        io.print("Mikä luku lisätään?");
        int lisattavaLuku = io.nextInt();
        joukko.lisaaLukuJoukkoon(lisattavaLuku);
        return;

    }

    private static IntJoukko [] kysytaanKayttajaltaKaksiJoukkoa() {
        IntJoukko [] palautettavatJoukot = new IntJoukko [2];
        io.print("1. joukko? ");
        palautettavatJoukot [0] = kayttajanAntamaJoukko();
        io.print("2. joukko? ");
        palautettavatJoukot [1] = kayttajanAntamaJoukko();;
        return palautettavatJoukot;
    }
    
    private static void yhdiste() {
        IntJoukko [] kaksijoukkoa = kysytaanKayttajaltaKaksiJoukkoa();
        IntJoukko yhdiste = IntJoukko.yhdiste(kaksijoukkoa[0], kaksijoukkoa[1]);
        io.print("A yhdiste B = " + yhdiste.toString());
        return;
    }

    private static void leikkaus() {
        IntJoukko [] kaksijoukkoa = kysytaanKayttajaltaKaksiJoukkoa();
        IntJoukko leikkaus = IntJoukko.leikkaus(kaksijoukkoa[0], kaksijoukkoa[1]);
        io.print("A leikkaus B = " + leikkaus.toString());
        return;
    }

    private static void erotus() {
        IntJoukko [] kaksijoukkoa = kysytaanKayttajaltaKaksiJoukkoa();
        IntJoukko erotus = IntJoukko.erotus(kaksijoukkoa[0], kaksijoukkoa[1]);
        System.out.println("A erotus B = " + erotus.toString());
        return;
    }

    private static void poista() {
        io.print("Mistä joukosta? ");
        IntJoukko joukko = kayttajanAntamaJoukko();
        System.out.print("Mikä luku poistetaan? ");
        int lisLuku = io.nextInt();
        joukko.poista(lisLuku);
        return;
    }

    private static void kuuluukoLukuJoukkoon() {
        io.print("Mihin joukkoon? ");
        IntJoukko joukko = kayttajanAntamaJoukko();
        io.print("Mikä luku? ");
        int kayttajanAntamaLuku = io.nextInt();
        boolean kuuluuko = joukko.kuuluukoLukuJoukkoon(kayttajanAntamaLuku);
        if (kuuluuko) {
            io.print(kayttajanAntamaLuku + " kuuluu joukkoon ");
        } else {
            io.print(kayttajanAntamaLuku + " ei kuulu joukkoon ");
        }
        return;
    }

    public void tulostaKomennot() {
        System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), "
                + "yhdiste(y), erotus(e), leikkaus(le) ja lopetus(quit)(q).");
    }
    
    public void alkutervehdys() {
        io.println("Tervetuloa joukkolaboratorioon!");
        io.println("Käytössäsi ovat joukot A, B ja C.");
        tulostaKomennot();
        io.println("Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");

    }
    
    public void suorita() {
        String luettu;

        alkutervehdys();
        
        while (true) {
            luettu = io.nextLine();
            if (luettu.equals("lisää") || luettu.equals("li")) {
                lisaa();
            } else if (luettu.equalsIgnoreCase("poista") || luettu.equalsIgnoreCase("p")) {
                poista();
            } else if (luettu.equalsIgnoreCase("kuuluu") || luettu.equalsIgnoreCase("k")) {
                kuuluukoLukuJoukkoon();
            } else if (luettu.equalsIgnoreCase("yhdiste") || luettu.equalsIgnoreCase("y")) {
                yhdiste();
            } else if (luettu.equalsIgnoreCase("leikkaus") || luettu.equalsIgnoreCase("le")) {
                leikkaus();
            } else if (luettu.equalsIgnoreCase("erotus") || luettu.equalsIgnoreCase("e")) {
                erotus();
            } else if (luettu.equalsIgnoreCase("A")) {
                System.out.println(A);
            } else if (luettu.equalsIgnoreCase("B")) {
                System.out.println(B);
            } else if (luettu.equalsIgnoreCase("C")) {
                System.out.println(C);
            } else if (luettu.equalsIgnoreCase("lopeta") || luettu.equalsIgnoreCase("quit") || luettu.equalsIgnoreCase("q")) {
                io.println("Lopetetaan, moikka!");
                break;
            } else {
                io.println("Virheellinen komento! " + luettu);
                tulostaKomennot();    
            }
            tulostaKomennot();
        }
    }
}
