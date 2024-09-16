/*
L'interfaccia Comando definisce il contratto per implementare diversi comandi.
Gli oggetti che implementano questa interfaccia devono fornire un'implementazione del metodo
esegui, che rappresenta l'azione associata al comando.
Questa interfaccia è stata implementata seguendo il Command Pattern.
*/
 
public interface Comando {
    void esegui();
}
