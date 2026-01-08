import java.util.Scanner;

public class Coet {

    private Motor[] motors = new Motor[4];

    public Coet() {
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor(i);
        }
    }

    public void arranca() {
        for (Motor m : motors) {
            m.start();
        }
    }

    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Error: potència incorrecta");
            return;
        }

        System.out.println("Passant a potència " + p);
        for (Motor m : motors) {
            m.setPotencia(p);
        }
    }

    public void llegirConsola() {
        Scanner sc = new Scanner(System.in);
        int p;

        System.out.print("Introdueix potència objectiu");

        do {
            p = sc.nextInt();
            passaAPotencia(p);
        } while (p != 0);

        sc.close();
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();
        coet.llegirConsola();
    }
}
