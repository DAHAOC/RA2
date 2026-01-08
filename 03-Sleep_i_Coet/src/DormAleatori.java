import java.util.Random;


public class DormAleatori extends Thread {
    
    private long creacio;
    private Random random = new Random();

    DormAleatori(String name) {
        super(name);
        this.creacio =  System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int interval = random.nextInt(1000);
            long totalTemps = System.currentTimeMillis() - creacio;

            System.out.printf("%s (%d) a dormir %dms total %dms%n", getName(), i, interval, totalTemps);
            
            try {
                Thread.sleep(interval);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        //Crear instancies

        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        //Inici
        joan.start();
        pep.start();

        // Missatge fi MAIN

        System.out.println("Fi de main");
        
    }
}
