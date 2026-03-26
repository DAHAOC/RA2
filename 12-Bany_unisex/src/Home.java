public class Home extends Thread {
    
    private String nom;
    private BanyUnisex lavabo;

    public Home(String nom, BanyUnisex lavabo) {
        this.nom = nom;
        this.lavabo = lavabo;
    }

    @Override
    public void run() {
        System.out.println(nom + " vol entrar al bany"); 
        lavabo.entraHome(); 
        utilitzaLavabo(); 
        lavabo.surtHome(); 
        System.out.println(nom + " ha acabat d'usar el bany");
    }

    private void utilitzaLavabo() {
        try {
            int tempsUs = (int) (Math.random() * 1000) + 1000;
            Thread.sleep(tempsUs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}