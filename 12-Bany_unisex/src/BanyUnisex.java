import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    public static final int BANY_BUIT = 0;
    public static final int BANY_AMB_HOMES = 1;
    public static final int BANY_AMB_DONES = 2;
    public static final int CAPACITAT_MAX = 3; 

    private int estatActual;  
    private int ocupants;    

    private Semaphore capacitat; 
    private ReentrantLock lockEstat;

    public BanyUnisex() {
        this.estatActual = BANY_BUIT;
        this.ocupants = 0;
        this.capacitat = new Semaphore(CAPACITAT_MAX, true);
        this.lockEstat = new ReentrantLock(true);
    }

    public void entraHome() {
        boolean haEntrat = false;
        while (!haEntrat) {
            lockEstat.lock(); 
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    if (capacitat.tryAcquire()) {
                        estatActual = BANY_AMB_HOMES;
                        ocupants++; 
                        haEntrat = true;
                        System.out.println("Home entra al bany. Ocupants: " + ocupants); 
                    }
                }
            } finally {
                lockEstat.unlock(); 
            }
            if (!haEntrat) {
                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        }
    }

    public void surtHome() {
        lockEstat.lock(); 
        try {
            ocupants--; 
            System.out.println("Home surt del bany. Ocupants: " + ocupants); 
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit"); 
            }
            capacitat.release(); 
        } finally {
            lockEstat.unlock();
        }
    }

    public void entraDona() {
        boolean haEntrat = false;
        while (!haEntrat) {
            lockEstat.lock(); 
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                    if (capacitat.tryAcquire()) { 
                        estatActual = BANY_AMB_DONES;
                        ocupants++; 
                        haEntrat = true;
                        System.out.println("Dona entra en el bany. Ocupants: " + ocupants); 
                    }
                }
            } finally {
                lockEstat.unlock(); 
            }
            if (!haEntrat) {
                try { Thread.sleep(50); } catch (InterruptedException e) {}
            }
        }
    }

    public void surtDona() {
        lockEstat.lock();
        try {
            ocupants--; 
            System.out.println("Dona surt del bany. Ocupants: " + ocupants);
            if (ocupants == 0) { 
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit"); 
            }
            capacitat.release(); 
        } finally {
            lockEstat.unlock(); 
        }
    }

    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();
        for (int i = 0; i < 5; i++) {
            new Home("Home-" + i, bany).start();
            new Dona("Dona-" + i, bany).start();
        }
    }
}