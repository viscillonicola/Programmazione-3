/*
La classe Amministratore rappresenta un utente con privilegi di amministrazione
che può gestire un distributore automatico.
*/
 
public class Amministratore {
    private Distributore distributore;
    private DistributoreAutomatico distributoreAutomatico;
    private ModalitaDistributore modalitaCorrente;

    public Amministratore(Distributore distributore) {
        this.distributore = distributore;
        this.modalitaCorrente = new ModalitaAmministratore(distributoreAutomatico, distributore);
    }

    //Metodo che consente all'amministratore di gestire la modalità Amminstratore
    public void gestisciModalitaAmministratore() {
        modalitaCorrente.gestisciModalita();
    }
}
