import java.util.List;
import java.util.ArrayList;



public class Esdeveniment {

    private int placesDisponibles;

    private List<Assistent> reserves;
    
    public Esdeveniment(int placesMaxim) {
        this.placesDisponibles = placesMaxim;
        this.reserves = new ArrayList<>();
    }  

    public synchronized void ferReserva(Assistent assistent) throws InterruptedException {

        while(placesDisponibles == 0) {
            wait();
        }


        reserves.add(assistent); 
        placesDisponibles--;
        
        System.out.println(assistent.getName() + " ha fet una reserva. Places disponibles: " + placesDisponibles);
        notifyAll();
        
    }

    
    public synchronized void cancelaReserva(Assistent assistent) {
        if(reserves.remove(assistent)) {
            placesDisponibles++;
            System.out.println(assistent.getName() + " ha cancel·lat una reserva. Places disponibles: " + placesDisponibles );
            notifyAll();
        } else {
            System.out.println(assistent.getName() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + placesDisponibles);
        }
    }
}