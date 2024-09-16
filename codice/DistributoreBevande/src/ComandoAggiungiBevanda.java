/*
La classe ComandoAggiungiBevanda implementa l'interfaccia Comando e rappresenta un comando
per aggiungere bevande al distributore.
*/
 
public class ComandoAggiungiBevanda implements Comando {
    //Riferimento al distributore su cui eseguire il comando
    private Distributore distributore;

    public ComandoAggiungiBevanda(Distributore distributore) {
        this.distributore = distributore;
    }

    //Metodo per eseguire il comando di aggiunta bevande sul distributore.
    @Override
    public void esegui() {
        distributore.aggiungiBevande();
    }
}
