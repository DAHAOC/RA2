import java.util.Random;

public class Filosof extends Thread {
    private String nom;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta; 
    private int gana; 
    private Random rand = new Random();


    public Filosof(String nom, Forquilla esquerra, Forquilla dreta) {
        this.nom = nom;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
        this.gana = 0;
    }

    
    private void pensar() {
        System.out.println("Filosof: " + nom + " pensant");
        try {
           
            Thread.sleep(rand.nextInt(1000) + 1000); 
        } catch (InterruptedException e) {}
    }

   
    private void menjar() {
        boolean haMenjat = false;
        
        while (!haMenjat) {
        
            if (!forquillaEsquerra.isEnUs()) {
                forquillaEsquerra.setEnUs(true);
                System.out.println("Filosof: " + nom + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());
                
             
                if (!forquillaDreta.isEnUs()) {
                    forquillaDreta.setEnUs(true);
                    System.out.println("Filosof: " + nom + " agafa la forquilla dreta " + forquillaDreta.getNumero());
                    
                    
                    System.out.println("Filosof: " + nom + " menja");
                    try {
                        Thread.sleep(rand.nextInt(1000) + 1000); 
                    } catch (InterruptedException e) {}
                    
                    System.out.println("Filosof: " + nom + " ha acabat de menjar");
                    this.gana = 0; 
                    
                    forquillaDreta.setEnUs(false);
                    forquillaEsquerra.setEnUs(false);
                    haMenjat = true;
                    
                } else {
                    forquillaEsquerra.setEnUs(false);
                    System.out.println("Filosof: " + nom + " deixa l'esquerra (" + forquillaEsquerra.getNumero() + ") i espera (dreta ocupada)");
                    this.gana++;
                    System.out.println("Filosof: " + nom + " gana =" + this.gana);
                    esperar();
                }
            } else {
                this.gana++;
                System.out.println("Filosof: " + nom + " gana =" + this.gana);
                esperar();
            }
        }
    }

    private void esperar() {
        try {
            Thread.sleep(rand.nextInt(500) + 500);
        } catch (InterruptedException e) {}
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
    }
}