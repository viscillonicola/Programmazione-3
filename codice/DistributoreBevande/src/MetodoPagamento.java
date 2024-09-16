/*
L'interfaccia MetodoPagamento definisce il contratto per implementare diversi metodi di pagamento.
Gli oggetti che implementano questa interfaccia devono fornire un'implementazione del metodo
effettuaPagamento che determina se il pagamento di un importo specifico è avvenuto con successo.
Questa interfaccia è stata implementata seguendo lo Strategy Pattern.
*/

public interface MetodoPagamento {
    boolean effettuaPagamento(double importo);
}
 