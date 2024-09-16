/*
La classe CartaCredito implementa l'interfaccia MetodoPagamento e consente di effettuare pagamenti
utilizzando una carta di credito.
Richiede all'utente di inserire il numero della carta, la data di scadenza e il codice di
sicurezza per completare il pagamento.
*/
 
import javax.swing.*;

public class CartaCredito implements MetodoPagamento {
    @Override
    public boolean effettuaPagamento(double importo) {
        String numeroCarta = JOptionPane.showInputDialog("Inserisci il numero della carta:");
        String dataScadenza = JOptionPane.showInputDialog("Inserisci la data di scadenza della carta:");
        String codiceSicurezza = JOptionPane.showInputDialog("Inserisci il cvv della carta:");

        if(verificaCarta(numeroCarta, dataScadenza, codiceSicurezza)) {
            JOptionPane.showMessageDialog(null, "Pagamento effettuato con successo. Importo pagato: " + importo + " euro", "Successo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Errore durante il pagamento. Riprova", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    //Metodo privato utilizzato per verificare la validità della carta di credito.
    //In questa implementazione, la verifica è simulata e restituisce sempre true.
    private boolean verificaCarta(String numeroCarta, String dataScadenza, String codiceSicurezza) {
        return true;
    }
}
