/*
Questa classe rappresenta l'interfaccia grafica del Distributore Automatico.
Fornisce pulsanti per selezionare la modalità Amministratore o Utente,
oltre a un pulsante per uscire dall'applicazione.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistributoreAutomatico extends JFrame implements MenuPrincipale {
    private JPanel distributoreAutomaticoPanel;
    private JButton amministratoreButton;
    private JButton utenteButton;
    private JButton esciButton;
    private JLabel modalitaText;

    private ModalitaAmministratore modalitaAmministratore;
    private ModalitaUtente modalitaUtente;

    //Istanza del distributore (Singleton)
    private Distributore distributore = Distributore.getInstance();

    Utente utente = new Utente(distributore);

    public DistributoreAutomatico() {
        //Inizializza il pannello e i componenti
        distributoreAutomaticoPanel = new JPanel();
        amministratoreButton = new JButton("Amministratore");
        utenteButton = new JButton("Utente");
        esciButton = new JButton("Esci");
        modalitaText = new JLabel("Seleziona la modalità:");
        modalitaAmministratore = new ModalitaAmministratore(this, distributore);
        modalitaUtente = new ModalitaUtente(this, utente);

        modalitaText.setHorizontalAlignment(SwingConstants.CENTER);

        //Aggiungi i componenti al pannello
        distributoreAutomaticoPanel.setLayout(new GridLayout(4, 1));
        distributoreAutomaticoPanel.add(modalitaText);
        distributoreAutomaticoPanel.add(amministratoreButton);
        distributoreAutomaticoPanel.add(utenteButton);
        distributoreAutomaticoPanel.add(esciButton);

        //Imposta il contenuto del frame
        setContentPane(distributoreAutomaticoPanel);
        setTitle("Distributore Bevande");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        //Aggiungi azioni per i pulsanti
        amministratoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modalitaAmministratore.gestisciModalita();
                setContentPane(modalitaAmministratore);
                revalidate();
                repaint();
            }
        });
        utenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modalitaUtente.gestisciModalita();
                setContentPane(modalitaUtente);
                revalidate();
                repaint();
            }
        });
        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    //Mostra il menu principale dell'interfaccia grafica
    public void mostraMenu() {
        setContentPane(distributoreAutomaticoPanel);
        revalidate();
        repaint();
    }

    //Metodo che avvia l'applicazione del Distributore Automatico.
    public static void main(String[] args) {
        new DistributoreAutomatico();
    }
}
