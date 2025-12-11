public class PrincipalIguals {
    
    public static void main(String[] args) {
        
        // Instanciar fils amb noms
        Fil juan = new Fil("Juan");
        Fil pepe = new Fil("Pepe");

        //Assignar prioritats iguals
        juan.setPriority(Thread.MIN_PRIORITY);
        pepe.setPriority(Thread.MIN_PRIORITY);
        
        //Iniciar fils
        pepe.start();
        juan.start();

        System.out.println("Acaba thread main");

    }
}
