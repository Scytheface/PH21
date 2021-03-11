import java.rmi.MarshalException;
import java.util.stream.IntStream;

public class php1 {

    public static int e2_1(int n) {
        int järk = 1;
        while (n >= Math.pow(10, järk)) {
            järk++;
        }
        return n / 10 + (n % 10) * (int) Math.pow(10, järk - 1);
    }

    public static int e2_2(int n) {
        // KETSERLUS! ÄRA TEE
        String s = Integer.toString(n);
        return Integer.parseInt(s.charAt(s.length() - 1) + s.substring(0, s.length() - 1));
    }

    public static int e2_3(int n) {
        return n / 10 + (n % 10) * (int) Math.pow(10, (int) Math.log10(n));
    }

    public static void e3a(int n) {
        System.out.println("#".repeat(n));
        for (int i = 0; i < n - 2; i++) {
            System.out.println("#" + " ".repeat(n - 2) + "#");
        }
        System.out.println("#".repeat(n));
    }

    public static void e3b(int n) {
        System.out.println("#".repeat(n));
        for (int i = 1; i < n - 1; i++) {
            System.out.println(" ".repeat(i) + "#" + " ".repeat(n - 2) + "#");
        }
        System.out.println(" ".repeat(n - 1) + "#".repeat(n));
    }

    public static void e3c_1(int n) {
        // lisamuutujaga
        int taane = 0;
        for (int i = 1; i <= n; i++) { // pane tähele <=
            System.out.println(" ".repeat(taane) + "#".repeat(i));
            taane += i;
        }
    }

    public static void e3c_2(int n) {
        for (int i = 0; i < n; i++) {
            int tühikuid = ((int) Math.pow(i, 2) + i) / 2;
            System.out.println(" ".repeat(tühikuid) + "#".repeat(i + 1));
        }
    }

    public static void e3d_1(int n) {
        int tühikuid = 0;
        for (int i = n; i > 0; i--) {
            tühikuid += i;
        }
        for (int i = 1; i <= n; i++) {
            tühikuid -= i;
            System.out.println(" ".repeat(tühikuid) + "#".repeat(i));
        }
    }

    public static void e3d_2(int n) {
        int tühikuid = ((int) Math.pow(n, 2) + n) / 2;
        for (int i = 1; i <= n; i++) {
            tühikuid -= i;
            System.out.println(" ".repeat(tühikuid) + "#".repeat(i));
        }
    }

    public static void e3f_1(int n) {
        int tühikuid = n;
        for (int i = 1; i <= n; i++) {
            System.out.println(" ".repeat(tühikuid) + "# ".repeat(i));
            tühikuid -= 1;
        }
    }

    public static void e3f_2(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(" ".repeat(n - i) + "# ".repeat(i));
        }
    }


    public static void e4() {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                for (int z = 0; z < 100; z++) {
                    if (4 * x + 3 * y - 9 * z == 5) {
                        System.out.printf("Lahend: x = %d, y = %d, z = %d%n", x, y, z);
                    }
                }
            }
        }
    }

    public static int e5(int n) {
        int summa = 0;
        for (int i = 1; i < n; i += 2) {
            summa += i * (i + 1);
        }
        return summa;
    }

    public static int e6_1() {
        // esimene variant, muudame muutujaid ja summeerime korrutisi
        int a = 2;
        int b = 102;
        int summa = 0;
        while (a < b) {
            summa += a * b;
            a += 4;
            b -= 4;
        }
        return summa;

    }

    public static int e6_2() {
        // teine variant, muutujad kui pmst konstandid
        final int a = 2;
        final int b = 102;
        int summa = 0;
        for (int c = 0; c <= 48; c += 4) { // alternatiivne jätkutingimus: (a + c) < (b - c)
            summa += (a + c) * (b - c);
        }
        return summa;
    }

    public static int e6_3() {
        // aga muutujaid pole ju vajagi enam
        int summa = 0;
        for (int c = 0; c <= 48; c += 4) {
            summa += (2 + c) * (102 - c);
        }
        return summa;
    }

    public static int e6_4() {
        return IntStream.range(0, 13).reduce(0, (jooksev, uus) -> jooksev + (2 + uus * 4) * (102 - uus * 4));
    }

    public static int e6_5() {
        return 23452;
    }

    public static void e7(int katseid) {
        //double et oleks lihtsam jagada
        double punkteRingis = 0;
        double punkteVäljas = 0;
        for (int i = 0; i < katseid; i++) {
            //genreerime suvalised koordinaadid
            double x = Math.random();
            double y = Math.random();//lihtsuse mõttes välistame praegu Random klassi - miinuseks on see et Math.random annab [0;1), mitte [0;1]
            double ringiX = 0.5; //ringi keskpunkti koordinaadid
            double ringiY = 0.5;
            //leiame punktide vektori pikkuse:
            //kuna vektor algab ringi keskelt, siis kõik pikkused, mis on väiksemad kui 0.5, peavad olema ringi sees
            double punktideKaugus = Math.sqrt(Math.pow(x - ringiX, 2) + Math.pow(y - ringiY, 2));
            if (punktideKaugus < 0.5) punkteRingis++;
            else punkteVäljas++;
        }

        System.out.println("Saadud punktide suhe on: " + punkteRingis / punkteVäljas);
    }

    public static void e7_2(int katseid) {
        //raadius on 1, keskpunkt (0;0)
        //tegelikult vaatleme ainult ringi 1. veerandit
        double punkteRingis = 0;
        double punkteVäljaspool = 0;
        double kaugus;
        for (int i = 0; i < katseid; i++) {
            kaugus = Math.pow(Math.random(), 2) + Math.pow(Math.random(), 2);
            if (kaugus < 1) punkteRingis++;
            else if (kaugus != 1) punkteVäljaspool++;
        }
        System.out.println("Saadud punktide suhe on: " + punkteRingis / punkteVäljaspool);
    }

    //käib lõpmatult ja prindib vaheseisud välja
    public static void e7_jooksvalt() {
        double punkteRingis = 0;
        double punkteVäljas = 0;
        while (true) {
            //et printimisega liiga hulluks ei läheks
            for (int i = 0; i < 1000; i++) {
                double punktideKaugus = Math.sqrt(Math.pow(Math.random() - 0.5, 2) + Math.pow(Math.random() - 0.5, 2));
                if (punktideKaugus < 0.5) punkteRingis++;
                else punkteVäljas++;
            }
            System.out.printf("Saadud punktide suhe on: %s\r", punkteRingis / punkteVäljas);

        }
    }

    public static void main(String[] args) {
        e7_jooksvalt();
    }
}
