public class Administracio {
    private int num_poblacio_activa = 50;
    private Treballador[] poblacio_activa = new Treballador[num_poblacio_activa];

    public Administracio() {
        for (int i = 0; i < num_poblacio_activa; i++) {
            // Nom ciutada sou 25000 Inici 20 fi 65
            poblacio_activa[i] = new Treballador("Ciutadà-" + i, 25000f, 20, 65);
        }
    }

    public void iniciSimulacio() {
        for(Treballador t : poblacio_activa) {
            t.start();
        }

        for(Treballador t : poblacio_activa) {
            try {
                t.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(Treballador t : poblacio_activa) {
            System.out.println(String.format("%s -> edat: %d / total: %.2f", t.getName(), t.getEdat(), t.getCobrat()));
        }   
    }

    public static void main(String[] args) {
        Administracio admin = new Administracio();
        admin.iniciSimulacio();
    }
}
