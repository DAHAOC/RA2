import java.util.Random;

public class Motor extends Thread {

    private int id;
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;
    private boolean actiu = true;
    private Random random = new Random();

    public Motor(int id) {
        this.id = id;
    }

    public synchronized void setPotencia(int p) {
        potenciaObjectiu = p;
        notify();
    }

    @Override
    public void run() {
        while (actiu) {
            synchronized (this) {
                try {
                    if (potenciaActual == potenciaObjectiu) {
                        if (potenciaObjectiu > 0) {
                            System.out.println("Motor " + id + ": FerRes Objectiu: "
                                    + potenciaObjectiu + " Actual: " + potenciaActual);
                        }
                        wait();
                    }

                    if (potenciaActual < potenciaObjectiu) {
                        potenciaActual++;
                        System.out.println("Motor " + id + ": Incre. Objectiu: "
                                + potenciaObjectiu + " Actual: " + potenciaActual);
                    } else if (potenciaActual > potenciaObjectiu) {
                        potenciaActual--;
                        System.out.println("Motor " + id + ": Decre. Objectiu: "
                                + potenciaObjectiu + " Actual: " + potenciaActual);
                    }

                    Thread.sleep((random.nextInt(2) + 1) * 1000);

                    if (potenciaObjectiu == 0 && potenciaActual == 0) {
                        actiu = false;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
