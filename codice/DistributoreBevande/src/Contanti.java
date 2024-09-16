/*
La classe Contanti implementa l'interfaccia MetodoPagamento e consente di effettuare pagamenti
utilizzando monete di diverso valore.
*/
 
import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Contanti implements MetodoPagamento {
    //Lista dei valori delle monete consentite
    private List<Double> moneteConsentite = Arrays.asList(0.05, 0.10, 0.20, 0.50, 1.0, 2.0);

    //Metodo per effettuare il pagamento utilizzando le monete.
    //Richiede all'utente di inserire monete fino a raggiungere l'importo specificato.
    //Restituisce il resto, se presente.
    @Override
    public boolean effettuaPagamento(double importo) {
        double importoRimanente = importo;
        double importoPagato = 0.0;

        while(importoRimanente > 0) {
            String input = JOptionPane.showInputDialog("Inserire moneta:");
            double monetaInserita;

            try {
                monetaInserita = Double.parseDouble(input);
            } catch(NumberFormatException exc) {
                JOptionPane.showMessageDialog(null, "Inserire un valore numerico valido", "Errore", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            if(moneteConsentite.contains(monetaInserita)) {
                importoPagato += monetaInserita;
                importoRimanente -= monetaInserita;
                if(importoRimanente > 0) {
                    JOptionPane.showMessageDialog(null, "Importo rimanente: " + importoRimanente + " euro", "Importo Rimanente", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Moneta non consentita", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Pagamento effettuato con successo. Importo pagato: " + importo + " euro", "Successo", JOptionPane.INFORMATION_MESSAGE);

        //Restituzione resto
        double resto = importoPagato - importo;
        if(resto > 0) {
            JOptionPane.showMessageDialog(null, "Resto: " + resto + " euro", "Resto", JOptionPane.INFORMATION_MESSAGE);
        }
        return true;
    }
}
