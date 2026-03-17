import java.util.Random;

public class Fumador extends Thread {
    private Estanc estanc;
    private int idFumador;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int fumades;
    private Random random;

    public Fumador(Estanc estanc, int idFumador) {
        this.estanc =estanc;
        this.idFumador = idFumador;
        this.fumades= 0;
        this.random = new Random();

    }


    public void compraTabac() throws InterruptedException {
        tabac = estanc.venTabac(idFumador);

    }

    public void compraPaper() throws InterruptedException{
        paper = estanc.venPaper(idFumador);

    }


    public void compraLLumi() throws InterruptedException {
        llumi = estanc.venLlumi(idFumador);
    }

    public void fuma() throws InterruptedException {
        if(tabac != null && paper != null && llumi != null) {
            System.out.println("Fumador " + idFumador + " fumant");
            tabac =null;
            paper=null;
            llumi = null;

            Thread.sleep(500 + random.nextInt(501));

            fumades++;
            System.out.println("Fumador " + idFumador + " ha fumat " + fumades + " vegades");

        }

    }


    @Override
    public void run() {
        try {
            while(fumades <3) {
                compraTabac();
                compraPaper();
                compraLLumi();
                fuma();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
