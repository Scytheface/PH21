//Kodutöö 1a
//klassinime ja olemasolevate meetodite päiseid ei tohi muuta
//täienda faili koduülesannetele esitatavate vormistuslike nõuetega
//klassi võib täiendada oma meetoditega

import java.sql.Timestamp;

/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 *
 * Kodutöö. Ülesanne nr /1a/
 * Teema:
 * Tsüklid
 *
 * Autor: xMadcHaoz90x
 *
 *
 *
 * Mõningane eeskuju: vt https://https://stackoverflow.com/questions/15743192/check-if-number-is-prime-number
 *
 *****************************************************************************/

class AlgarvuRing {

    public static void main(String[] args) {
        //mainis lihtsalt annan lähtekoha, mida testida ja väljastan vajaliku info
        int n = 1000000;
        System.out.println("Kodutöö nr 1a. Programmi väljund");
        System.out.println("=========================================================:");
        System.out.println("Antud lähtekoht: " + n);
        algarvuRing(n);
        System.out.println("=========================================================:");
        System.out.println("xMadcHaoz90x " + new Timestamp(System.currentTimeMillis()));

    }//main

    public static int ringiarvud(int n) {
        //n - arv,mille ülejäänud ringi arvud leitakse
        //meetod Math.pow kasutades võetud 1. praktikumist

        return n / 10 + (n % 10) * (int) Math.pow(10, (int) Math.log10(n));

    }

    public static boolean onAlgarv(int n) {
        // n - arv, mille algarvulisust kontrollitakse
        // meetod inspireeritud stackoverflowst, kus toodi välja, et kõik algarvud,mis on suuremad
        //kui 2 ja 3, annavad 6ga jagamisel jäägi 1 või 5
        //limiit on arvu ruutjuur
        if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0) return false;

        int limiit = (int) Math.floor(Math.sqrt(n));

        int i = 6;//väiksemad algarvud kontrollitud
        while (i <= limiit) {
            if (n % (i + 1) == 0 || n % (i + 5) == 0)
                return false;
            i += 6;
        }
        return true;


    }

    public static void algarvuRing(int a) {
        //a - algarvuringi leidmise lähtekoht
        //väljastab 5 lähimat algarvuringi antud lähtekohast a väiksemate arvude hulgas
        //võib juhtuda, et vastavaid ringe on vähem kui 5. Siis väljasta ainult need
        //ühekohaliste arvude seast algarvuringe ei otsi
        //väljastab igale reale täpselt ühe algarvuringi algusarvu, väärtuste kahanedes
        //ekraanile väljastuses ei tohi olla tühikuid ega tühje ridu
        //siin meetodis kontrollin tsükli abil arve lähtekohast kuni 5 arvu on leitud või kuni
        //kõik arvud kuni 11ni on kontrollitud (10 on küll kahekohaline, aga see pole algarv)
        // main meetodis väljastan need arvud selle meetodi põhjal
        int loendur = 0;
        if (a % 2 == 1) {
            a--;
        }//kontrollima peab ainult paarituid arve, seega tahan alustada tsüklit igal
        //juhul paaritust arvust ja hakkan sellest 2 lahutama tsüklis
        välimine:
        for (a -= 1; loendur < 5 & a > 9; a -= 2) {
            //järgmiste if lausetega kontrollin, kas 2,4,6 või 8 asub arvus, mida kontrollitakse
            // ja lahutan vastava järgu võrra, et vähendada kontrollitavate arvude hulka
            if ((a / 100000) % 2 == 0 & (a / 100000) % 10 != 0) {
                a -= 100000;
            }
            if ((a / 10000) % 2 == 0 & (a / 10000) % 10 != 0) {
                a -= 10000;
            }
            if ((a / 1000) % 2 == 0 & (a / 1000) % 10 != 0) {
                a -= 1000;
            }
            if ((a / 100) % 2 == 0 & (a / 100) % 10 != 0) {
                a -= 100;
            }
            if ((a / 10) % 2 == 0 & (a / 10) % 10 != 0) {
                a -= 10;
            }
            if (onAlgarv(a)) {
                int uus = ringiarvud(a);
                for (int i = 0; i < 6; i++) {
                    if (i > 0) {
                        uus = ringiarvud(uus);
                    }
                    if (!onAlgarv(uus) || uus > a) {
                        continue välimine;
                    }

                }
                loendur++;
                System.out.println(a);


            }

        }


    }//algarvuRing

}//klass AlgarvuRing


