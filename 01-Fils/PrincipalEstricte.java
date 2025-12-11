public class PrincipalEstricte {
    public static void main(String [] args) {
        //Instanciar fils amb noms i delays
        Fil juan = new Fil("Juan", 1000);
        Fil pepe = new Fil("Pepe", 1000);

        juan.start();

        try {
            //Pausa per assegurar que juan comença abans que pepe"
            Thread.sleep(500);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pepe.start();

        System.out.println("Acaba thread main");
    }
}
