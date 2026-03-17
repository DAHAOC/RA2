public class Barri {
    private Estanc estanc;
    private Fumador[] fumadors;
    public Barri() {
        estanc = new Estanc();
        fumadors = new Fumador[3];
        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc,i);

        }

    }


    public void iniciar() {
        for(int i=0; i < 3; i++) {
            fumadors[i].start();
        }

        estanc.start();

        for(int i = 0; i< 3; i++) {
            try {
                fumadors[i].join();
            } catch(InterruptedException e) {
                System.out.println(e);
            
            }
        }

        estanc.tancarEstanc();
    }


    public static void main(String[] args) {
        Barri barri = new Barri();
        barri.iniciar();

    }
}
