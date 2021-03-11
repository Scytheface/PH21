/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1a
 * Teema:
 * Tsüklid, järjendid. Algarvuringide leidmine
 *
 * Autor: FireSerpentZ90
 *
  *
 * Mõningane eeskuju: vt https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 *
 *****************************************************************************/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

class AlgarvuRing{

    public static void main(String[] args){

        koosPaiseJaJalusega("1a", ()->{
            valjastaAlgarvuring(70);
            valjastaAlgarvuring(311);
            valjastaAlgarvuring(1_000_000);
            valjastaAlgarvuring(10_000_000);
        });
    }//main

    public static void algarvuRing(int a){
        AlgarvuRingiLeidja ringiLeidja = new AlgarvuRingiLeidja(a);
        List<Integer> ringid = ringiLeidja.leiaRingid(a, 5);
        ringid.forEach(r -> System.out.println(r));

    }//algarvuRing

    public static void valjastaAlgarvuring(int a) {
        System.out.println("\nAntud lähtekoht: "+a);
        System.out.println("Leitud algarvuringid:");
        algarvuRing(a);

    }
    /**
     * Võimaldab leida algarvu ringe kuni konstruktoris määratud arvuni
     */
    private static class AlgarvuRingiLeidja {

        private BitSet kordarvud;

        /**
         *
         * @param maxAlgarv suurim arv, mida saab anda leiaRingid(int) argumendiks
         */
        public AlgarvuRingiLeidja(int maxAlgarv) {
            this.kordarvud = leiaKordarvud(maxAlgarv);
        }


        /**
         * Leiab n algarvu ringi, mille suurim element on väiksem kui a
         * @param a leitavate algarvuringide suurmate elementide suurim lubatav väärtus
         * @param n mitu algarvuringi leida
         * @return leitud algarvuringide suurimad elemendid
         */
        public List<Integer> leiaRingid(int a, int n) {
            int found = 0;
            List<Integer> sobivadTulemused = new ArrayList<>();
            for (int i = kordarvud.previousClearBit(a-1); i > 9 && found < n; i = kordarvud.previousClearBit(i-1)) {
                if (kasOnAlgarvuRingiSuurimElement(i)) {
                    sobivadTulemused.add(i);
                    found++;
                }
            }
            return sobivadTulemused;
        }


        /**
         * Eristab alg- ja kordarvud kuni arvuni n. Tagastab BitSet-i kus get(i)==true parajasti siis kui
         * i on kordarv, ehk get(i)==false parajasti siis kui i on algarv.
         * <p>
         *  Nö taguripidi on tulemus kuna BitSet on vaikimisi väärtustatud false ning p.set(1, n, true) on suhteliselt aeglane
         * @param n suurim arv, milleni algarve leiuda
         * @return BitSet, mille puhul get(i) tagastab false parajasti siis kui i on algarv
         */
        private BitSet leiaKordarvud(int n) {
            //https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
            BitSet p = new BitSet(n);
            int max = (int)Math.sqrt(n) + 1;
            for (int i = 2; i < max; i++) {
                if (!p.get(i)) {
                    int iruut = i * i;
                    int ni = 0;
                    int j = iruut + ni;
                    while (j < n) {
                        p.set(j, true);
                        ni += i;
                        j = iruut + ni;
                    }
                }
            }
            return p;
        }

        /**
         * Kontrollib kas algarv p on algarvuriungi suurim element
         * @param p algarv, mida kontrollida
         * @return ture kui on
         */
        private boolean kasOnAlgarvuRingiSuurimElement(int p) {
            int maxPooramisteArv = ymberpaigutusteArv(p);
            int current = p;
            for (int i = 1; i<maxPooramisteArv; i++) {
                current = rotate(current);
                if (current > p || kordarvud.get(current))
                    return false;
            }
            return true;
        }


        /**
         * Mitu tüklilist  ümberpaigutust saab teha arvus n
         * @param n
         * @return
         */
        private int ymberpaigutusteArv(int n) {
            int ymberpaigutusteArv = 0;
            int a = 1;
            while (a <= n) {
                a *= 10;
                ymberpaigutusteArv++;
            }
            return ymberpaigutusteArv;
        }


        /**
         * Leiab arvu kus arvu i kümnendkuju viimane number on paigutatud esimeseks (tsükliline ümberpaigutus)
         * @param i arv, mille stükliline ümberpaigutus leida
         * @return arvu i stükliline ümberpaigutus
         */
        private int rotate(int i) {
            int a = i / 10;
            int viimane = i - a * 10;
            int result = 1;
            while (result <= a)
                result *= 10;
            result *= viimane;
            result += a;
            return result;
        }

    }

    /**
     * Väljastab etteantud koodi väljundi  standardpäise ning -jaluse vahel
     * @param kodutooNimi kodutöö nimetus päises
     * @param r väljundit genereeriv kood
     */
    private static void koosPaiseJaJalusega(String kodutooNimi, Runnable r){
        System.out.println("Kodutöö nr " + kodutooNimi + ".                          Programmi väljund");
        System.out.println("=========================================================:");
        r.run();
        System.out.println("\n=========================================================.");
        String dateStr = DateTimeFormatter.ofPattern("yyyy-DD-dd HH:mm:ss.SSS").format(LocalDateTime.now());
        System.out.println("FireSerpentZ90                        " + dateStr);
    }

    private static void runTimed(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        System.out.println("Aeg : "+(System.currentTimeMillis() - start));
    }
}//klass AlgarvuRing
