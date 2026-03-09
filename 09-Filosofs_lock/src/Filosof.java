import java.util.Random;

public class Filosof extends Thread {
    private String nombre;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    
    private long iniciGana;
    private long fiGana;
    private long gana;
    
    private Random random = new Random();

    public Filosof(String nombre, Forquilla esq, Forquilla dreta) {
        this.nombre = nombre;
        this.forquillaEsquerra = esq;
        this.forquillaDreta = dreta;
        resetGana(); 
    }

    @Override
    public void run() {
        while (true) {
            menjar();
            pensar();
        }
    }

    private void pensar() {
        try {
            System.out.println(nombre + " pensant");
            resetGana();
            Thread.sleep(random.nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void menjar() {
        agafarForquilles();
        
        fiGana = System.currentTimeMillis();
        gana = (fiGana - iniciGana) / 1000;
        
        System.out.println(nombre + " menja amb gana " + gana);

        try {
            Thread.sleep(random.nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(nombre + " ha acabat de menjar");
        
        resetGana();
        deixarForquilles();
    }

    private void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
        System.out.println(nombre + " té forquilles esq(" + forquillaEsquerra.getNum() + ") dreta (" + forquillaDreta.getNum() + ")");
    }

    private void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
    }

    private void agafarForquillaDreta() {
        forquillaDreta.agafar();
    }

    private void deixarForquilles() {
        forquillaDreta.dejar();
        forquillaEsquerra.dejar();
        System.out.println(nombre + " deixa les forquilles");
    }

    private void resetGana() {
        iniciGana = System.currentTimeMillis();
        gana = 0;
    }
}