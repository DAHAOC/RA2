import java.util.Random;

public class Treballador extends Thread{
    private float sou_anual_brut;
    private int edat_inici_treball;
    private int edat_fi_treball;
    
    private int edat_actual = 0;
    private float cobrat = 0.0f;
    private Random random = new Random();
    
    public Treballador(String nom, float sou_anual_brut, int edat_inici_treball, int edat_fi_treball) 
    {
        super(nom);
        this.sou_anual_brut = sou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
    }


    public void cobra() {
        this.cobrat += (this.sou_anual_brut / 12.0f);
    }


    public void pagaImpostos() {
        this.cobrat -= (this.sou_anual_brut / 12.0f) * 0.24f;
    }
    
    public float getCobrat() {
        return this.cobrat;
    }

    public int getEdat() {
        return this.edat_actual;
    }

    

    @Override
    public void run() {

        //simulacio cada any

        while(edat_actual < edat_fi_treball) {
            //edat  correcte
            edat_actual++;
            if(edat_actual >= edat_inici_treball && edat_actual < edat_fi_treball) {
                for(int i = 0; i < 12; i++) {
                    cobra();
                    pagaImpostos();
                }
            }
            
        }
    }
}
