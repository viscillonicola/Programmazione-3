/*
La classe Bevanda rappresenta un tipo di bevanda all'interno del distributore automatico.
Ogni istanza di questa classe contiene informazioni sul tipo di bevanda e la quantità disponibile.
*/
 
public class Bevanda {
    private String tipo;
    private double quantitaDisponibile;

    public Bevanda(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getQuantita() {
        return quantitaDisponibile;
    }

    public void setQuantita(double quantita) {
        this.quantitaDisponibile = quantita;
    }

    public Bevanda aggiungiQuantita(double quantita) {
        this.quantitaDisponibile += quantita;
        return this;
    }
}