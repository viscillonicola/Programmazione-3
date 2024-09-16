/*
La classe Distributore rappresenta il distributore automatico di bevande.
Gestisce la scorta di bevande, i prezzi, i consumi mensili e fornisce
funzionalità per aggiungere bevande, effettuare pagamenti e generare report.
Questa classe implementa il Singleton Pattern
*/

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Distributore {
    //Istanza unica del distributore (Singleton)
    private static Distributore instance;

    //Modalità corrente del distributore
    private ModalitaDistributore modalitaCorrente;

    //Comando corrente del distributore
    private Comando comandoCorrente;

    //Mappa delle bevande disponibili nella scorta
    public Map<String, Bevanda> scortaBevande = new HashMap<>();

    //Mappa dei prezzi associati alle bevande
    public Map<String, Double> prezziBevande = new HashMap<>();

    //Mappa dei consumi di bevande
    private Map<String, Integer> consumiMensili = new HashMap<>();

    //Nel costruttore vengono aggiunte le bevande preimpostate e successivamente vengono salvate
    //sul file "bevande.txt".
    private Distributore() {
        this.scortaBevande = new HashMap<>();
        aggiungiBevandaPreimpostata("Caffe", 5, 1);
        aggiungiBevandaPreimpostata("The", 5, 2.5);
        aggiungiBevandaPreimpostata("Latte", 5, 1.50);
        aggiungiBevandaPreimpostata("Camomilla", 5, 2.50);
        aggiungiBevandaPreimpostata("Cioccolata calda", 5, 3);
        aggiungiBevandaPreimpostata("Acqua", 5, 0.50);

        salvaSuFile("bevande.txt");

        aggiungiBevandaPreimpostata("Caffe, the", 5, 3.5);
        aggiungiBevandaPreimpostata("Caffe, latte", 5, 2.5);
        aggiungiBevandaPreimpostata("Caffe, camomilla", 5, 3.5);
        aggiungiBevandaPreimpostata("Caffe, cioccolata calda", 5, 4);
        aggiungiBevandaPreimpostata("Caffe, acqua", 5, 1.5);

        aggiungiBevandaPreimpostata("The, caffe", 5, 3.5);
        aggiungiBevandaPreimpostata("The, latte", 5, 2.5);
        aggiungiBevandaPreimpostata("The, camomilla", 5, 5);
        aggiungiBevandaPreimpostata("The, cioccolata calda", 5, 5.5);
        aggiungiBevandaPreimpostata("The, acqua", 5, 3);

        aggiungiBevandaPreimpostata("Latte, caffe", 5, 2.5);
        aggiungiBevandaPreimpostata("Latte, the", 5, 4);
        aggiungiBevandaPreimpostata("Latte, camomilla", 5, 4);
        aggiungiBevandaPreimpostata("Latte, cioccolata calda", 5, 4.5);
        aggiungiBevandaPreimpostata("Latte, acqua", 5, 2);

        aggiungiBevandaPreimpostata("Camomilla, caffe", 5, 3.5);
        aggiungiBevandaPreimpostata("Camomilla, the", 5, 5);
        aggiungiBevandaPreimpostata("Camomilla, latte", 5, 4);
        aggiungiBevandaPreimpostata("Camomilla, cioccolata calda", 5, 5.5);
        aggiungiBevandaPreimpostata("Camomilla, acqua", 5, 3);

        aggiungiBevandaPreimpostata("Cioccolata calda, caffe", 5, 4);
        aggiungiBevandaPreimpostata("Cioccolata calda, the", 5, 5.5);
        aggiungiBevandaPreimpostata("Cioccolata calda, latte", 5, 4.5);
        aggiungiBevandaPreimpostata("Cioccolata calda, camomilla", 5, 5.5);
        aggiungiBevandaPreimpostata("Cioccolata calda, acqua", 5, 3.5);

        aggiungiBevandaPreimpostata("Acqua, caffe", 5, 1.5);
        aggiungiBevandaPreimpostata("Acqua, the", 5, 3);
        aggiungiBevandaPreimpostata("Acqua, latte", 5, 2);
        aggiungiBevandaPreimpostata("Acqua, camomilla", 5, 3);
        aggiungiBevandaPreimpostata("Acqua, cioccolata calda", 5, 3.5);

        salvaSuFile("sottotipologie.txt");

    }

    //Metodo per ottenere l'istanza unica del distributore (Singleton)
    public static Distributore getInstance() {
        if(instance == null) {
            instance = new Distributore();
        }
        return instance;
    }

    //Metodo per impostare la modalità corrente del distributore.
    public void impostaModalita(ModalitaDistributore nuovaModalita) {
        this.modalitaCorrente = nuovaModalita;
    }

    //Metodo per gestire la modalità corrente del distributore.
    public void gestisciModalita() {
        modalitaCorrente.gestisciModalita();
    }

    //Metodo per aggiungere nome e prezzo delle bevande preimpostate nelle loro mappe
    private void aggiungiBevandaPreimpostata(String tipoBevanda, double quantitaIniziale, double prezzo) {
        Bevanda bevanda = new Bevanda(tipoBevanda);
        bevanda.aggiungiQuantita(quantitaIniziale);
        scortaBevande.put(tipoBevanda, bevanda);
        prezziBevande.put(tipoBevanda, prezzo);
    }

    //Metodo che permette di definire nome, quantità e prezzo della bevanda da aggiungere, la quale
    //verra salvata sul file.
    public void aggiungiBevande() {
        try {
            String tipoBevanda = JOptionPane.showInputDialog("Inserisci nome della bevanda:");
            String input = JOptionPane.showInputDialog("Inserisci la quantità:");
            double quantita = Double.parseDouble(input);
            input = JOptionPane.showInputDialog("Inserisci il prezzo:");
            double prezzo = Double.parseDouble(input);

            aggiungiScortaBevanda(tipoBevanda, quantita);
            definisciPrezzo(tipoBevanda, prezzo);
            controlloQuantitaMinima(tipoBevanda);

            salvaSuFile("sottotipologie.txt");

            JOptionPane.showMessageDialog(null, "Bevanda aggiunta con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch(Exception exc) {

            exc.printStackTrace();
        }
    }

    //Metodo per salvare le informazioni sulle bevande sul file "bevande.txt".
    public void salvaSuFile(String nomeFile) {
        try(BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(nomeFile))) {
            for(Map.Entry<String, Bevanda> entry : scortaBevande.entrySet()) {
                //writer.write(entry.getKey() + "," + entry.getValue().getQuantita() + "\n");
                String tipoBevanda = entry.getKey();
                double quantita = entry.getValue().getQuantita();
                double prezzo = prezziBevande.getOrDefault(tipoBevanda, 0.0);

                writer.write("Nome bevanda: " + tipoBevanda + ", quantità: " + quantita + ", prezzo: " + prezzo);
                writer.newLine();

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo per aggiungere la bevanda e la sua quantità alla sua mappa
    public void aggiungiScortaBevanda(String tipoBevanda, double quantita) {
        scortaBevande.put(tipoBevanda, scortaBevande.getOrDefault(tipoBevanda, new Bevanda(tipoBevanda)).aggiungiQuantita(quantita));
    }

    //Metodo per definire il prezzo di una bevanda nella sua mappa
    public void definisciPrezzo(String tipoBevanda, double prezzo) {
        prezziBevande.put(tipoBevanda, prezzo);
    }

    public void aggiornaConsumiMensili(String tipoBevanda, int quantita) {
        consumiMensili.put(tipoBevanda, consumiMensili.getOrDefault(tipoBevanda, 0) + quantita);
    }

    public Map<String, Bevanda> getScortaBevande() {
        return scortaBevande;
    }

    public Map<String, Double> getPrezziBevande() {
        return prezziBevande;
    }

    public Map<String, Integer> getConsumiMensili() {
        return consumiMensili;
    }

    //Metodo che mostra una finestra di dialogo con l'elenco delle bevande disponibili,
    //permettendo all'utente di selezionarne una.
    //Restituisce la bevanda selezionata o null se l'utente annulla l'operazione.
    //Utilizza JOptionPane per la visualizzazione dell'elenco.
    public String mostraBevandeDisponibili() {
        //Crea un modello di lista per contenere i nomi delle bevande
        DefaultListModel<String> listModel = new DefaultListModel<>();

        //Popola il modello con i nomi delle bevande presenti nella scorta
        for(String bevanda : getScortaBevande().keySet()) {
            listModel.addElement(bevanda);
        }

        //Crea una lista JList con il modello appena creato
        JList<String> listBevande = new JList<>(listModel);

        //Imposta la selezione singola per la lista
        listBevande.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listBevande);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        //Mostra una finestra di dialogo con l'elenco delle bevande
        int result = JOptionPane.showOptionDialog(
          null,
          panel,
          "Scegli una bevanda",
          JOptionPane.OK_CANCEL_OPTION,
          JOptionPane.PLAIN_MESSAGE,
          null,
          null,
          null
        );

        //Restituisce il nome della bevanda selezionata se l'utente ha premuto OK,
        //altrimenti restituisce null
        if(result == JOptionPane.OK_OPTION) {
            return listBevande.getSelectedValue();
        } else {
            return null;
        }
    }

    //Metodo che riduce la quantità disponibile di una bevanda specifica nel distributore in
    //base alla quantità erogata. Verifica se la bevanda è disponibile,
    //se la quantità erogata è valida e riduce la quantità disponibile della bevanda.
    //Esegue anche un controllo sulla quantità minima e salva le informazioni su file.
    public void riduciQuantita(String tipoBevanda, double quantitaErogata) {
        //Trova la bevanda nel distributore
        Bevanda bevandaDaRidurre = trovaBevanda(tipoBevanda);

        //Verifica se la bevanda è disponibile
        if(bevandaDaRidurre != null) {
            //Verifica se la quantità erogata è valida
            if(quantitaErogata > 0 && quantitaErogata <= bevandaDaRidurre.getQuantita()) {
                //Riduci la quantità disponibile della bevanda
                double nuovaQuantita = bevandaDaRidurre.getQuantita() - quantitaErogata;
                bevandaDaRidurre.setQuantita(nuovaQuantita);
                System.out.println("Quantità di " + tipoBevanda + " ridotta di " + quantitaErogata + " litri");
                controlloQuantitaMinima(tipoBevanda);
                salvaSuFile("sottotipologie.txt");
            } else {
                System.out.println("Quantità erogata non valida");
            }
        } else {
            System.out.println("Bevanda non disponibile");
        }
    }

    //Metodo che permette di trovare e restituire un'istanza di Bevanda corrispondente al
    //tipo specificato nel distributore. La ricerca avviene in modo case-insensitive per gestire
    //possibili differenze di maiuscole/minuscole.
    public Bevanda trovaBevanda(String tipoBevanda) {
        for(Map.Entry<String, Bevanda> entry : scortaBevande.entrySet()) {
            //Confronta il tipo di bevanda in modo case-insensitive
            if(entry.getKey().equalsIgnoreCase(tipoBevanda)) {
                return entry.getValue();
            }
        }
        return null;
    }

    //Controlla se la quantità di una bevanda specifica nel distributore è inferiore a 1.0.
    //Se la condizione è verificata, aggiunge automaticamente una quantità prefissata di bevanda
    //e salva le informazioni sulle bevande su file.
    public void controlloQuantitaMinima(String tipoBevanda) {
        //Trova la bevanda nel distributore
        Bevanda bevandaControllo = trovaBevanda(tipoBevanda);

        //Verifica se la quantità è minore di 1.0
        if(bevandaControllo != null && bevandaControllo.getQuantita() < 1.0) {
            //Aggiunge una determinata quantità di bevanda
            double quantitaAggiunta = 2.0;
            double nuovaQuantita = bevandaControllo.getQuantita() + quantitaAggiunta;
            bevandaControllo.setQuantita(nuovaQuantita);
            salvaSuFile("sottotipologie.txt");

            System.out.println("Quantità di " + tipoBevanda + " aggiunta automaticamente di " + quantitaAggiunta + " litri");
        }
    }

    //Metodo che genera un report dei consumi mensili delle bevande.
    public void generaReportConsumiMensili() {
        if(consumiMensili.isEmpty()) {
            System.out.println("Non ci sono consumi mensili");
        } else {
            for(Map.Entry<String, Integer> entry : consumiMensili.entrySet()) {
                String tipoBevanda = entry.getKey();
                int quantita = entry.getValue();
            }
        }
    }

    public void impostaComando(Comando comando) {
        this.comandoCorrente = comando;
    }

    public void eseguiComando() {
        if(comandoCorrente != null) {
            comandoCorrente.esegui();
        } else {
            System.out.println("Nessun comando impostato");
        }
    }

    public void salvaReportConsumiMensili(String nomeFile) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            for(Map.Entry<String, Integer> entry : consumiMensili.entrySet()) {
                String tipoBevanda = entry.getKey();
                int quantita = entry.getValue();

                writer.write("Bevanda: " + tipoBevanda + ", Quantità consumata: " + quantita + " unità");
                writer.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void aggiungiNuovaTipologia() {
        try {
            String nuovaTipologia = JOptionPane.showInputDialog("Inserisci il nome della nuova tipologia di bevanda:");

            //Verifica se la nuova tipologia già esiste
            if(getScortaBevande().containsKey(nuovaTipologia)) {
                System.out.println("Questa tipologia di bevanda è già presente");
            } else {
                String input = JOptionPane.showInputDialog("Inserisci la quantità:");
                double quantita = Double.parseDouble(input);
                input = JOptionPane.showInputDialog("Inserisci il prezzo:");
                double prezzo = Double.parseDouble(input);

                aggiungiScortaBevanda(nuovaTipologia, quantita);
                definisciPrezzo(nuovaTipologia, prezzo);
                controlloQuantitaMinima(nuovaTipologia);
                salvaSuFile("sottotipologie.txt");

                JOptionPane.showMessageDialog(null, "Nuova tipologia aggiunta con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}
