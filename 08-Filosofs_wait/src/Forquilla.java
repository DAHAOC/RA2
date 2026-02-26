

public class Forquilla {
    private int numero;
    private int propietari;
    public static final int lliure = -1;
    

    public Forquilla(int numero) {
        this.numero = numero;
        this.propietari = lliure;        
    }

    public int getNumero() {
        return numero;
    }

    public int getPropietari() {
        return propietari;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }


}