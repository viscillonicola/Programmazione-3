/*
La classe Chiavetta implementa l'interfaccia MetodoPagamento e consente di effettuare pagamenti
utilizzando una chiavetta prepagata con un saldo associato.
È inoltre possibile ricaricare la chiavetta.
*/
 
import javax.swing.*;

public class Chiavetta implements MetodoPagamento {
    private double saldo;       //Saldo attuale sulla chiavetta

    public Chiavetta(double saldoIniziale) {
        this.saldo = saldoIniziale;
    }

    public double getSaldo() {
        return saldo;
    }

    //Metodo per effettuare il pagamento utilizzando la chiavetta.
    //Richiede all'utente di inserire il codice della chiavetta e verifica se il saldo è sufficiente.
    @Override
    public boolean effettuaPagamento(double importo) {
        //Verifica se il saldo è sufficiente per effettuare il pagamento
        if(saldo >= importo) {
            String codiceChiavetta = JOptionPane.showInputDialog("Inserisci il codice della chiavetta");
            if(verificaCodiceChiavetta(codiceChiavetta)) {
                saldo -= importo;
                JOptionPane.showMessageDialog(null, "Pagamento effettuato con successo. Importo pagato: " + importo + " euro.\nSaldo rimanente: " + saldo + " euro", "Successo", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Codice chiavetta non valido", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insufficiente", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    //Metodo privato per verificare la validità del codice della chiavetta.
    //In questa implementazione, la verifica è simulata e restituisce sempre true.
    private boolean verificaCodiceChiavetta(String codiceChiavetta) {
        return true;
    }

    //Metodo per ricaricare la chiavetta con un importo specifico.
    public void ricaricaChiavetta(double importoRicarica) {
        if(importoRicarica == 5.0 || importoRicarica == 10.0 || importoRicarica == 20.0 || importoRicarica == 50.0) {
            saldo += importoRicarica;
            JOptionPane.showMessageDialog(null, "Ricarica effettuata con successo. Nuovo saldo: " + saldo + " euro", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Importo ricarica non valido", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
