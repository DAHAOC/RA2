import java.util.LinkedList;
import java.util.Queue;

public class Barberia {
    private Queue<Client> salaEspera = new LinkedList<>();

    private int maxCadires;

    public static final Object condBarber = new Object();

    public static Barberia instancia;

    public Barberia(int maxCadires) {
        this.maxCadires = maxCadires;
        instancia = this;

    }

    public synchronized Client seguentClient() {
        return salaEspera.poll();

    }

    public void entrarClient(Client client) {
        synchronized (this) {
            if (salaEspera.size() < maxCadires) {
                salaEspera.add(client);
                System.out.println("Client " + client.getNom() + " en espera");

                synchronized (condBarber) {
                    condBarber.notify();
                }

            } else {
                System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
            }
        }
    }

    public void executar() {
        int idClient = 1;
        try {
            for (int i = 0; i < 10; i++) {
                entrarClient(new Client(idClient++));
                Thread.sleep(500);
            }

            Thread.sleep(10000);

            for (int i = 0; i < 10; i++) {
                entrarClient(new Client(idClient++));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("L'execució de la barberia ha estat interrompuda.");
        }
    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);

        Barber barber = new Barber("Pepe");

        barber.start();

        barberia.executar();

    }
}
