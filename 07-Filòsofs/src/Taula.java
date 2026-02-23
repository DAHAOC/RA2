

public class Taula {
    private Filosof[] filosofs;
    private Forquilla[] forquilles;

    public Taula(int numFilosofs)  {
        filosofs = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }


        for (int i = 0; i < numFilosofs; i++) {
            Forquilla esq = forquilles[i];
        
            Forquilla dreta = forquilles[(i + 1) % numFilosofs];
        
            filosofs[i] = new Filosof("fil" + i, esq, dreta);


        }

    }


    // mostrar cada filosof amb la seva forquilla
    public void showTaula() {
        for (int i = 0; i < filosofs.length; i++) {
            int indexDreta = (i + 1) % forquilles.length;
            System.out.println("Comensal:fil" + i + " esq:" + forquilles[i].getNumero() + " dret:" + forquilles[indexDreta].getNumero());
        }
    }

    public void cridarATaula() {
        for(Filosof f : filosofs) {
            f.start();
        }
    }


    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}

