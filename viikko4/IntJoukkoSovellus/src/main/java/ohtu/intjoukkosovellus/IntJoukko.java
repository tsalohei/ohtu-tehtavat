
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukonLuvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this.joukonLuvut = new int[KAPASITEETTI];
        
        alustaTaulukkoNollilla(joukonLuvut);
        
        this.alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        
        this.joukonLuvut = new int[kapasiteetti];
        
        alustaTaulukkoNollilla(joukonLuvut);
        
        this.alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        this.joukonLuvut = new int[kapasiteetti];
        
        alustaTaulukkoNollilla(joukonLuvut);
        
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }
    
    public void alustaTaulukkoNollilla(int [] taulukko) {
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = 0;
        }
    }

    public boolean lisaaLukuJoukkoon(int luku) {
        if (alkioidenLkm == 0) {
            lisaaLukuTyhjaanJoukkoon(luku);
            return true;
        } 
        if (!kuuluukoLukuJoukkoon(luku)) {
            lisaaLukuJoukkoonJokaEiTyhja(luku);
            return true;
        }
        return false;
    }
    public void lisaaLukuJoukkoonJokaEiTyhja(int luku) {
        joukonLuvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % joukonLuvut.length == 0) {
                int[] taulukkoOld = new int[joukonLuvut.length];
                taulukkoOld = joukonLuvut;
                kopioiTaulukko(joukonLuvut, taulukkoOld);
                joukonLuvut = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, joukonLuvut);
            }
    }

    public void lisaaLukuTyhjaanJoukkoon(int luku) {
        joukonLuvut[0] = luku;
        alkioidenLkm++;
    }
    
    public boolean kuuluukoLukuJoukkoon(int luku) {
        int loytyi = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonLuvut[i]) {
                loytyi++;
            }
        }
        if (loytyi > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean poista(int luku) {
        int kohta = -1;
        
        kohta = poistaLukuJoukkoaKuvaavastaTaulukosta(luku, kohta);
        
        poistaTyhjaKoloJoukkoaKuvaavastaTaulukosta(kohta);
        
        return false;
    }
    
    public boolean poistaTyhjaKoloJoukkoaKuvaavastaTaulukosta(int kohta) {
        int apu;
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = joukonLuvut[j];
                joukonLuvut[j] = joukonLuvut[j + 1];
                joukonLuvut[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }
    
    public int poistaLukuJoukkoaKuvaavastaTaulukosta(int luku, int kohta) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonLuvut[i]) {
                kohta = i; 
                joukonLuvut[kohta] = 0;
                break;
            }
        }
        return kohta;
    }
    

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int alkioidenLukumaara() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + joukonLuvut[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += joukonLuvut[i];
                tuotos += ", ";
            }
            tuotos += joukonLuvut[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukonLuvut[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaaLukuJoukkoon(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaaLukuJoukkoon(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaaLukuJoukkoon(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaaLukuJoukkoon(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
 
        return z;
    }
        
}
