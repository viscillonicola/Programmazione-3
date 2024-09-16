/*
Questa classe rappresenta la modalità Utente del distributore automatico.
Gli utenti possono scegliere, prelevare e pagare una bevanda, ricaricare la chiavetta, o
tornare al menu principale.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModalitaUtente extends JPanel implements ModalitaDistributore {
    private Utente utente;
    private DistributoreAutomatico distributoreAutomatico;

    //private Distributore distributore;

    public ModalitaUtente(DistributoreAutomatico distributoreAutomatico, Utente utente) {
        this.distributoreAutomatico = distributoreAutomatico;
        this.utente = utente;

        JButton sceltaButton = new JButton("Scegli, preleva e paga una bevanda");
        JButton ricaricaButton = new JButton("Ricarica chiavetta");
        JButton esciButton = new JButton("Torna al menu principale");

        sceltaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Mostra un dialogo per la scelta del metodo di pagamento
                String[] opzioniPagamento = {"Contanti", "Carta di credito", "Chiavetta ricaricabile"};
                String metodoPagamentoScelto = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleziona il metodo di pagamento:",
                        "Metodo di pagamento",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opzioniPagamento,
                        opzioniPagamento[0]);

                if(metodoPagamentoScelto != null) {
                    utente.scegliPrelevaPagaBevanda(metodoPagamentoScelto);
                }
            }
        });

        ricaricaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utente.ricaricaChiavetta();
            }
        });

        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                distributoreAutomatico.mostraMenu();
            }
        });

        setLayout(new GridLayout(3, 1));
        add(sceltaButton);
        add(ricaricaButton);
        add(esciButton);
    }

    //Implementazione del metodo dell'interfaccia ModalitaDistributore.
    //Gestisce la modalità Utente richiamando il metodo corrispondente nella classe Utente.
    @Override
    public void gestisciModalita() {
        JOptionPane.showMessageDialog(null, "Modalità utente selezionata", "Successo", JOptionPane.INFORMATION_MESSAGE);
    }
}
