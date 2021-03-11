/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1a
 * Teema:
 * Algarvuringide leidmine
 *
 * Autor: H4xmaStErZ
 *
 * Mõningane eeskuju: vt https://www.w3schools.com/
 *
 *****************************************************************************/
import java.util.Arrays;
import java.util.Scanner;

class AlgarvuRing {
    public static void main(String[] args) {
        Scanner otsiAlgarv = new Scanner(System.in);  // Inputi saamiseks https://www.w3schools.com/java/java_user_input.asp
        System.out.println("Sisesta arv: ");
        int algarv = Integer.parseInt(otsiAlgarv.nextLine());
        System.out.println("Antud lähtekoht: " + algarv);
        System.out.println("Leitud algarvuringid:");
        long aeg = System.currentTimeMillis(); //paneb aja jooksma
        algarvuRing(algarv);
        long pärisAeg = System.currentTimeMillis() - aeg; // paneb aja kinni
        System.out.print("Aega kulus: " + pärisAeg);
    }

    public static void algarvuRing(int a) {
        a -=1;
        int mitmesAlgarv = 0; //loendame mitu ringi oleme leidnud
        if (a % 2 == 0) { //eemaldame juba kõik 2ga jaguvad arvud
            a -= 1;
        }
        for (; a > 0; a -= 2) {
            if (mitmesAlgarv == 5 || a <= 10) { //kui 5 algarvuringi leitud või jõuame ühekohaliste numbriteni katkestab töö
                return;
            }
            int arv = a;
            int pikkus = 0;
            int paaritud = 1;
            while (arv > 0) {
                if (arv % 10 == 1 || arv % 10 == 3 || arv % 10 == 7 || arv % 10 == 9) { //ükski algarv ei lõppe numbriga 0,2,4,5,6,8
                    pikkus += 1; //leiame arvu pikkuse
                } else {
                    paaritud = 0; //ei saa olla algarv jäerlikult muudame inti ja katkestame ahela
                    break;
                }
                arv = arv / 10;
            }
            if (paaritud == 1) { //ainult sobivad arvud saavad edasi
                int kas = kasAlgarv(a);
                int uus = a; //loome teiste ringis olevate arvude jaoks muutuja uus
                if (kas == 1) {
                    int alg = 1;
                    for (int x = 0; x < pikkus - 1; x++) {
                        int jääk = uus % 10; //viimane arv mille toome esimeseks (jääk)
                        int liikuv = (uus - jääk) / 10; //ülejäänud osa
                        int abi = 1;
                        for (; uus > 10; uus = uus / 10) { // leiame millise 10 astmega peame jääki korrutama
                            abi = abi * 10;
                        }
                        uus = jääk * abi + liikuv; // arvu tagasi kokku panemine
                        if (uus > a) { //eemaldab arvud millel on endast suurem arv algarvuringis
                            alg = 0;
                            break;
                        }
                        if (kasAlgarv(uus) == 0) { //eemaldab arvud mis pole algarvud
                            alg = 0;
                            break;
                        }
                    }
                    if (alg == 1) {
                        System.out.println(a); //prindib leitud algarvuringi suurima arvu
                        mitmesAlgarv += 1;
                    }
                }
            }
        }
    }
    public static int kasAlgarv(int n) {
        for (int i = 2; i * i < n; i++) {
            if (n % i == 0) {
                return 0;
            }
        }
        return 1;
    }
}
