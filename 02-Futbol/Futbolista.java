import java.util.Random;

public class Futbolista extends Thread {

    private int ngols;
    private int ntirades;
    public static int NUM_JUGADORS = 11;
    public static int NUM_TIRADES = 20;
    public static float PROBABILITAT = 0.5f;

    public Futbolista(String nom) {
        super(nom);
        ngols = 0;
        ntirades = 0;
    }

    @Override
    public void run() {
        Random azar = new Random(); // Creas el generador

        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (azar.nextFloat() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public static void main(String[] args) {
        String[] nombres = {
            "Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", 
            "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé"
        };

        Futbolista[] jugadores = new Futbolista[NUM_JUGADORS];

        System.out.println("Inici dels xuts ----------");

        for(int i = 0; i < NUM_JUGADORS; i++) {
            jugadores[i] = new Futbolista(nombres[i]);

        }

        for(int i = 0; i < jugadores.length; i++) {
            jugadores[i].start();
        }

        for(int i = 0; i < jugadores.length; i++) {
            
            try {
                jugadores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fi dels xuts ----------");
        System.out.println("----------Estadístiques ----------");

        
        for(int i = 0; i < jugadores.length; i++) {
            System.out.println(jugadores[i].getName() + " -> " + jugadores[i].ngols + " gols");
        }

 
        
        

     
    }

}
