import java.util.Random;

public class Assistent extends Thread{
    
    private Esdeveniment esdeveniment;
    private Random random = new Random();

    public Assistent(String nom, Esdeveniment esdeveniment) {

        super(nom);
        this.esdeveniment = esdeveniment;
    }



    @Override
    public void run() {
        try {
            while(true) {
                
                if(random.nextBoolean()) {
                    esdeveniment.ferReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }

                Thread.sleep(random.nextInt(1001));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
