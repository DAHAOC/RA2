public class Fil extends Thread {
    
    private int delay;
    
    //Constructor per a PrincipalIguals i PrincipalDiferents
    public Fil(String nom) {
        super(nom);
        this.delay = 0;
    }


    //Constructor per a PrincipalEstricte
    public Fil(String nom, int delay) {
        super(nom);
        this.delay = delay;
    }

    @Override
    public void run() {
        //bucle 1 al 9 

        for(int i = 1; i <= 9; i++) {
            System.out.println(getName() + " " + i);

            if(this.delay > 0) {
                try {
                    Thread.sleep(this.delay); //pausa fil
                
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                
                for (long j = 0; j < 2000000000; j++); //bucle per a retardar el fil
            }


        }
        System.out.println("Acaba el fil " + getName());
    }
}