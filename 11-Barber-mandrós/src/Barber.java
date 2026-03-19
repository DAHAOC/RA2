public class Barber extends Thread {
    private String nom; 

    public Barber(String nom) {
        this.nom = nom;
    }


    @Override
    public void run() {
        while(true) {
            Client clientActual = Barberia.instancia.seguentClient();

            if(clientActual != null) {
                System.out.println("Li toca al client " +  clientActual.getNom());
                System.out.println("Tallant cabell a " + clientActual.getNom());

                try {
                    long tempsTall = 900 + (long)(Math.random() * 100);
                    Thread.sleep(tempsTall);
                } catch (InterruptedException e) {
                    
                    System.out.println("El barber ha estat interromput mentre tallava el cabell.");
                }
            } else {
                System.out.println("Ningú en espera");
                System.out.println("Barber " + this.nom + " dormint");
                
                try {
                    synchronized (Barberia.condBarber) {
                        Barberia.condBarber.wait();
                    }

                } catch (InterruptedException e) {
                    System.out.println("El barber ha estat despertat de la migdiada.");
                }
            }
        }
    }
}