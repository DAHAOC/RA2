import java.util.Random;

public class Filosof extends Thread {
    private int numeroComensal;
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private int gana;
    private Random rand = new Random();

    public Filosof(int numeroComensal, Forquilla esquerra, Forquilla dreta) {
        this.numeroComensal = numeroComensal;
        this.forquillaDreta = dreta;
        this.forquillaEsquerra = esquerra;
        this.gana = 0;

    }

    private void menjar() {
        try {
            agafarForquilles();
            System.out.println("Filosof: fil" + numeroComensal + " menja");
            Thread.sleep(rand.nextInt(1000) + 1000); 
            this.gana = 0;
            deixarForquilles();
        } catch (InterruptedException e) {
        }
    }

    public void agafarForquilles() throws InterruptedException{
        boolean teLesDues = false;

        while(!teLesDues) {
            agafarForquillaEsquerra();

            synchronized(forquillaDreta) {
                if(forquillaDreta.getPropietari() == Forquilla.lliure) {
                    agafarForquillaDreta();
                    teLesDues = true;
                } else {
                    synchronized (forquillaEsquerra) {
                        forquillaEsquerra.setPropietari(Forquilla.lliure);
                        forquillaEsquerra.notifyAll();
                        System.out.println("Filosof: fil" + numeroComensal + " deixa l'esquerra (" + forquillaEsquerra.getNumero() +") i espera (dreta ocupada)");
                    }
                    this.gana++;

                    System.out.println("Filosof: fil" + numeroComensal + " gana=" + this.gana);

                    Thread.sleep(rand.nextInt(500) + 500);
                }
            }
        }
    }

    private void pensar() {
        try {
            System.out.println("Filosof: fil" + numeroComensal + " pensant");
            Thread.sleep(rand.nextInt(1000) + 1000);
        } catch (InterruptedException e) {
        }
    }

    private void agafarForquillaEsquerra() throws InterruptedException {
        synchronized(forquillaEsquerra) {
            while(forquillaEsquerra.getPropietari() != Forquilla.lliure) {
                forquillaEsquerra.wait();
            }


            forquillaEsquerra.setPropietari(this.numeroComensal);
            System.out.println("Filosof: fil" + numeroComensal + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());
        }
    }

    private void agafarForquillaDreta() {
        forquillaDreta.setPropietari(this.numeroComensal);
        System.out.println("Filosof: fil" + numeroComensal + " agafa la forquilla dreta " + forquillaDreta.getNumero());

    }


    private void deixarForquilles() {
        synchronized(forquillaEsquerra) {
            forquillaEsquerra.setPropietari(Forquilla.lliure);
            forquillaEsquerra.notifyAll();
        }

        synchronized(forquillaDreta) {
            forquillaDreta.setPropietari(Forquilla.lliure);
            forquillaDreta.notifyAll();
        }
    }


    @Override
    public void run() {
        while(true) {
            pensar();
            menjar();
        }
    }
}
