package laskin;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        edellinenTulos = sovellus.tulos();       
        int lisays = Integer.parseInt(syotekentta.getText());       
        sovellus.plus(lisays);
        
        paivitaKentat();    
    }
 
}
