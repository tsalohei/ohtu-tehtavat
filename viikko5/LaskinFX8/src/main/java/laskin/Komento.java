
package laskin;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {

    protected TextField tuloskentta;
    protected TextField syotekentta; 
    protected Button nollaa;
    protected Button undo;
    protected Sovelluslogiikka sovellus;
    protected int edellinenTulos;
    
    
    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;         
    }
    
    public abstract void suorita();
    
    public void peru() {
        sovellus.nollaa();
        sovellus.plus(edellinenTulos);
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }
    
    public void paivitaKentat() {
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }
    
}
