

public class Assosiació {
    private int numSocis = 1000;
    private Soci[] socis;
    
    
    public Assosiació() {
        this.socis = new Soci[numSocis];
    }

    public void iniciaCompteTempsSocis() {
        for(int i = 0; i < numSocis; i++) {
            socis[i] = new Soci();
            socis[i].start();
        }
    }

    public void esperaPeriodeSocis() {
        for (int i = 0; i < numSocis; i++) {
            try{
                socis[i].join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mostraBalanceComptes() {
        Compte compte = Compte.getInstancia();
        System.out.println("Saldo final: " + compte.getSaldo());
    }

    public static void main(String[] args) {
        Assosiació assoc = new Assosiació();

        System.out.println("Inici simulació");
        assoc.iniciaCompteTempsSocis();

        System.out.println("Esperant socis");
        assoc.esperaPeriodeSocis();

        System.out.println("Simulacio finalitzada");
        assoc.mostraBalanceComptes();
    }
   
}
