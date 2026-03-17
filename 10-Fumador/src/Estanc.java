import java.util.ArrayList;
import java.util.Random;

public class Estanc extends Thread {
    private ArrayList<Tabac> llistaTabac;
    private ArrayList<Paper> llistaPaper;
    private ArrayList<Llumi> llistaLlumi;

    private boolean obert;
    private Random random;
    

    public Estanc() {
        llistaTabac = new ArrayList<>();
        llistaPaper = new ArrayList<>();
        llistaLlumi = new ArrayList<>();
        obert = true;
        random = new Random();
        
    }

    public synchronized void addTabac() {
        llistaTabac.add(new Tabac());
        System.out.println("Afegint tabac");
        notifyAll();
    }


    public synchronized void addPaper() {
       llistaPaper.add(new Paper());
       System.out.println("Afegint Paper");
       notifyAll();
    }

    public synchronized void addLlumi() {
         llistaLlumi.add(new Llumi());
        System.out.println("Afegint Llumi");
        notifyAll();
    }
    public void nouSubministrament() {
        int recurs = random.nextInt(3);
        if(recurs ==0) addTabac();
        else if(recurs == 1) addPaper();
        else addLlumi();
    }

    public synchronized Tabac venTabac(int id) throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Tabac");
        while(llistaTabac.isEmpty()) {
            wait();
        }
        return llistaTabac.remove(0);

    }

    public synchronized Paper venPaper(int id) throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Paper");
        while(llistaPaper.isEmpty()) {
            wait();

        }

        return llistaPaper.remove(0);
    }

    public synchronized Llumi venLlumi(int id) throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Llumi");
        while(llistaLlumi.isEmpty()) {
            wait();

        }

        return llistaLlumi.remove(0);

    }

    public void tancarEstanc() {
        obert = false;
        this.interrupt();
    }

    @Override
    public void run() {
        System.out.println("Estanc obert");
        while (obert) {
            nouSubministrament();
            try {
                Thread.sleep(500 + random.nextInt(1001)); 
            } catch (InterruptedException e) {
                break; 
            }
        }
        System.out.println("Estanc tancat");
    }


}