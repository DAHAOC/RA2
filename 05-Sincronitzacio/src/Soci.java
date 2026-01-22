import java.util.Random;

public class Soci extends Thread {
    private Compte compte;
    private float aportacio = 10f;
    private int esperaMax = 100;
    private Random random;

    private int maxAnys = 10;

    public Soci() {
        this.compte = Compte.getInstancia();
        this.random = new Random();
    }

    public Compte getCompte() {
        return this.compte;
    }

    @Override
    public void run() {
        for (int any = 0; any < maxAnys; any++) {
            for (int mes = 1; mes <= 12; mes++) {
                if (mes % 2 == 0) {
                    compte.ingresar(aportacio); 
                } else {
                    compte.retirar(aportacio); 
                }
            }

            try {
                Thread.sleep(random.nextInt(esperaMax));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
