/*
Questa classe rappresenta la modalità Utente del distributore automatico.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Utente extends JPanel {
    private DistributoreAutomatico distributoreAutomatico;
    private Distributore distributore;
    private Chiavetta chiavetta;

    public Utente(Distributore distributore) {
        this.distributore = distributore;
        this.chiavetta = new Chiavetta(20);
    }

    //Metodo che consente all'utente di gestire la modalità Utente
    public void gestisciModalitaUtente() {
        this.distributoreAutomatico = distributoreAutomatico;
    }


    public void scegliPrelevaPagaBevanda(String metodoPagamento) {
        //Ottieni la scelta della bevanda dall'utente
        String bevandaScelta = distributore.mostraBevandeDisponibili();

        //Verifica se la bevanda è disponibile
        if(!distributore.getScortaBevande().containsKey(bevandaScelta)) {
            JOptionPane.showMessageDialog(null, "Questa bevanda non è disponibile", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Richiesta della quantita di zucchero
        String inputZucchero = JOptionPane.showInputDialog("Inserisci la quantità di zucchero (da 0 a 3):");
        int quantitaZucchero = Integer.parseInt(inputZucchero);


        //Ottieni la quantità disponibile
        double quantitaDisponibile = distributore.getScortaBevande().get(bevandaScelta).getQuantita();
        System.out.println("Quantità disponibile di " + bevandaScelta + ": " + quantitaDisponibile);

        String input = JOptionPane.showInputDialog("Quantità disponibile di " + bevandaScelta + ": " + quantitaDisponibile + ".\nInserisci la quantità da prelevare:");
        double quantitaPrelevare = Double.parseDouble(input);

        //Verificare se la quantità da prelevare è valida
        if(quantitaPrelevare > quantitaDisponibile) {
            System.out.println("Quantità non disponibile");
            return;
        }

        //Calcola il costo della bevanda
        double prezzoBevanda = distributore.getPrezziBevande().getOrDefault(bevandaScelta, 0.0) * quantitaPrelevare;
        JOptionPane.showMessageDialog(null, "Prezzo della bevanda: " + prezzoBevanda + " euro", "Prezzo", JOptionPane.INFORMATION_MESSAGE);

        //Gestione del pagamento
        switch(metodoPagamento) {
            case "Contanti":
                MetodoPagamento pagamentoContanti = new Contanti();
                if(pagamentoContanti.effettuaPagamento(prezzoBevanda)) {
                    distributore.riduciQuantita(bevandaScelta, quantitaPrelevare);
                }
                break;
            case "Carta di credito":
                MetodoPagamento pagamentoCarta = new CartaCredito();
                if(pagamentoCarta.effettuaPagamento(prezzoBevanda)) {
                    distributore.riduciQuantita(bevandaScelta, quantitaPrelevare);
                }
                break;
            case "Chiavetta ricaricabile":
                MetodoPagamento pagamentoChiavetta = chiavetta;
                if(pagamentoChiavetta.effettuaPagamento(prezzoBevanda)) {
                    distributore.riduciQuantita(bevandaScelta, quantitaPrelevare);
                }
                break;
            default:
                break;
        }

        //Aggiorna i consumi mensili
        int quantitaPrelevareInt = (int) quantitaPrelevare;
        distributore.aggiornaConsumiMensili(bevandaScelta, quantitaPrelevareInt);
    }
    
    public void ricaricaChiavetta() {
        if(chiavetta == null) {
            JOptionPane.showMessageDialog(null, "Errore", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        String input = JOptionPane.showInputDialog("Inserisci l'importo della ricarica (5, 10, 20, 50 euro):");
        double importoRicarica = Double.parseDouble(input);

        chiavetta.ricaricaChiavetta(importoRicarica);
    }
}
