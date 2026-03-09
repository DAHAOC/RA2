public class Taula {
    private Filosof[] filosofs;
    private Forquilla[] forquilles;
    private int numFilosofs;

    public Taula(int numFilosofs) {
        this.numFilosofs = numFilosofs;
        this.filosofs = new Filosof[numFilosofs];
        this.forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            Forquilla esq = forquilles[i];
            Forquilla dreta = forquilles[(i + 1) % numFilosofs];
            
            filosofs[i] = new Filosof("Fil" + i, esq, dreta);
        }
    }

    public void showTaula() {
        for (int i = 0; i < numFilosofs; i++) {
            System.out.println("Comensal:Fil" + i + " esq:" + forquilles[i].getNum() + " dret:" + forquilles[(i + 1) % numFilosofs].getNum());
        }
    }

    public void cridarATaula() {
        for (Filosof f : filosofs) {
            f.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}