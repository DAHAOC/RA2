public class PrincipalDiferents {
    
    public static void main(String[] args) {
        Fil juan = new Fil("Juan");
        Fil pepe = new Fil("Pepe");

        //Assignar prioritats diferents
        juan.setPriority(Thread.MAX_PRIORITY);
        pepe.setPriority(Thread.MIN_PRIORITY);

        //iniciar
        pepe.start();
        juan.start();

        System.out.println("Acaba thread main");
        
    }
}
