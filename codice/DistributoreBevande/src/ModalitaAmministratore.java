/*
Questa classe rappresenta la modalità amministratore del distributore automatico.
Fornisce funzionalità come l'aggiunta di bevande alla scorta, la definizione dei prezzi
delle bevande e la generazione di report sui consumi mensili.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModalitaAmministratore extends JPanel implements ModalitaDistributore {
    private DistributoreAutomatico distributoreAutomatico;
    private Distributore distributore;

    public ModalitaAmministratore(DistributoreAutomatico distributoreAutomatico, Distributore distributore) {
        this.distributoreAutomatico = distributoreAutomatico;
        this.distributore = distributore;


        JButton aggiungiBevandaButton = new JButton("Aggiungi bevanda alla scorta");
        JButton definisciPrezzoButton = new JButton("Definisci il prezzo per una bevanda");
        JButton generaReportButton = new JButton("Genera un report sui consumi mensili");
        JButton aggiungiTipologiaButton = new JButton("Aggiungi tipologia di bevanda");
        JButton esciButton = new JButton("Torna al menu principale");

        aggiungiBevandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Crea un nuovo comando per aggiungere una bevanda al distributore
                Comando comandoAggiungiBevanda = new ComandoAggiungiBevanda(distributore);

                //Imposta il comando corrente nel distributore
                distributore.impostaComando(comandoAggiungiBevanda);

                //Esegue il comando corrente
                distributore.eseguiComando();
                //JOptionPane.showMessageDialog(null, "Bevanda aggiunta con successo alla scorta!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        definisciPrezzoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeBevanda = JOptionPane.showInputDialog("Inserisci il nome della bevanda:");
                Bevanda bevandaModificare = distributore.trovaBevanda(nomeBevanda);

                if(bevandaModificare != null) {
                    String input = JOptionPane.showInputDialog("Inserisci il prezzo:");
                    try {
                        double prezzo = Double.parseDouble(input);
                        distributore.definisciPrezzo(nomeBevanda, prezzo);
                        distributore.salvaSuFile("bevande.txt");
                        JOptionPane.showMessageDialog(null, "Prezzo della bevanda " + nomeBevanda + " aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    } catch(NumberFormatException exc) {
                        JOptionPane.showMessageDialog(null, "Inserisci un prezzo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bevanda non trovata!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        generaReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                distributore.generaReportConsumiMensili();
                distributore.salvaReportConsumiMensili("consumiMensili.txt");
                JOptionPane.showMessageDialog(null, "Report sui consumi mensili generato con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        aggiungiTipologiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                distributore.aggiungiNuovaTipologia();
            }
        });

        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                distributoreAutomatico.mostraMenu();
            }
        });

        setLayout(new GridLayout(5, 1));
        add(aggiungiBevandaButton);
        add(definisciPrezzoButton);
        add(generaReportButton);
        add(aggiungiTipologiaButton);
        add(esciButton);
    }

    @Override
    public void gestisciModalita() {
        JOptionPane.showMessageDialog(null, "Modalità amministratore selezionata", "Successo", JOptionPane.INFORMATION_MESSAGE);
    }
}
