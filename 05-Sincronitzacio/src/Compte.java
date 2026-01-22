

public class Compte {
    private float saldo;
    private static Compte instancia;
    
    

    private Compte() {
        this.saldo = 0;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }


    public static synchronized Compte getInstancia() {
        if(instancia == null) {
            instancia = new Compte(); 
        }
        return instancia;
    }



    public synchronized void ingresar(float cantidad) {
        this.saldo += cantidad;
    }

    public synchronized void retirar(float cantidad) {
        this.saldo -= cantidad;
    }



}
