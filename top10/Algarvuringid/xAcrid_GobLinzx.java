
/******************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1a
 * Teema:
 *      5 suurima algarvuringi leidmine
 *
 * Autor: xAcrid_GobLinzx
 *
 ******************************************************************/

import java.util.concurrent.TimeUnit;

class AlgarvuRing{

    public static void main(String[] args){
        // Väljastab päise
        System.out.println("Kodutöö nr 1a.                   " + "Programmi väljund");
        System.out.println("==================================================:");
        System.out.println("");

        // Meetodi algusaeg
        //long algusAeg = System.nanoTime();
        int a = 1000000;
        System.out.println("Antud lähtekoht: " + a);
        System.out.println("Leitud algarvuringid:");
        algarvuRing(a);

        // Meetodi lõppaeg
        //long l6ppAeg = System.nanoTime();
        // Arvuta ja väljasta kulunud aeg
        //long aegaKulunud = l6ppAeg - algusAeg;
        //long aegaKulunudms = TimeUnit.NANOSECONDS.toMillis(aegaKulunud);
        //System.out.println(aegaKulunudms + "ms");

        // Väljastab ajaviida ja ülejäänud jaluse.
        System.out.println("");
        System.out.println("==================================================.");
        System.out.println("xAcrid_GobLinzx             " + new java.sql.Timestamp(System.currentTimeMillis()));
    }

    /**
     * Väljastab 5 lähimat algarvuringi antud lähtekohast a väiksemate arvude hulgast.
     * Kui ringe on vähem kui 5, siis väljastab ainult need.
     * Ühekohaliste arvude seast algaevuringe ei otsita.
     * Väljastab igale reale väärtuste kahanedes ühe algarvuringi algusarvu
     *
     * @param a Algarvuringide leidmise lähtekoht
     */
    public static void algarvuRing(int a){

        int v2iksedAlgarvudSuurus = 175;
        int[] v2iksedAlgarvud = v2iksedAlgarvud(v2iksedAlgarvudSuurus);


        int[] algarvuRingid = new int[5];
        for (int i = 0; i < 5; i++) {
            int viimaneRing = (i == 0 ? a : algarvuRingid[i-1]);
            for (int j = (viimaneRing % 2 == 0 ? viimaneRing - 1 : viimaneRing - 2); j > 9; j -= 2) {
                if (!onHalbNumber(j)) {
                    if (onAlgarv(j, v2iksedAlgarvud)) {

                        if (onAlgarvuRing(j, v2iksedAlgarvud, algarvuRingid)) {
                            algarvuRingid[i] = j;
                            System.out.println(j);
                            break;
                        }
                    }
                }
            }
        }

    }

    /**
     * Väljastab kas antud arv on algarvuring või mitte.
     *
     *
     * @param a sisendarv
     * @param v2iksedAlgarvud ette genereeritud algarvude massiiv
     * @param algarvuRingid eelnevalt leitud algarvuringide massiiv
     * @return kas arv a on algarvuring?
     */
    public static boolean onAlgarvuRing(int a, int[] v2iksedAlgarvud, int[] algarvuRingid) {
        int j2rk = arvuJ2rk(a);

        int viimaneNumber = a;

        for (int i = 0; i < j2rk; i++) {
            viimaneNumber = viiEtte(viimaneNumber, j2rk);
            if (viimaneNumber > a) {
                return false;
            }

            for (int olemasolevAlgarv: algarvuRingid) {
                if (viimaneNumber == olemasolevAlgarv) {
                    return false;
                }
            }

            if (!onAlgarv(viimaneNumber, v2iksedAlgarvud)) {
                return false;
            }

        }

        return true;
    }

    /**
     *
     * @param a sisendarv
     * @return mitu korda saab arvu a jagada arvuga 10.
     */
    public static int arvuJ2rk(int a) {
        int arv = a;
        int j2rk = 0;

        if (arv >= 10000) {
            j2rk += 4;
            arv /= 10000;
        }
        if (arv >= 100) {
            j2rk += 2;
            arv /= 100;
        }
        if (arv >= 10) {
            j2rk += 1;
        }

        return j2rk;
    }


    /**
     *
     * @param a sisendarv
     * @return vastab kas arvust saab üldse algarvuringi teha.
     */
    public static boolean onHalbNumber(int a) {

        while (a > 0) {
            int kontrollNr = a % 10;
            if (kontrollNr % 2 == 0 || kontrollNr % 5 == 0) {
                return true;
            }
            a /= 10;
        }

        return false;
    }


    /**
     * Viib sisendarvu a, järguga j2rk, viimase numbri esimeseks ja viib ülejäänud numbrid ühe koha võrra paremale.
     * @param a sisendarv
     * @param j2rk arvu a järk
     * @return arv a, kus viimane arv on esimeseks viidud
     */
    public static int viiEtte(int a, int j2rk) {
        int arv = a / 10;
        int viimane = a % 10;

        return arv + viimane * intPow(10, j2rk);
    }

    /**
     * Taasloodud funktsioon asendamaks double tüüpi astendajat.
     * On mõneti kiirem, kui tavaline java astaendaja.
     *
     * @param alus astme alus
     * @param aste astendaja
     * @return alus ^ aste
     */
    public static int intPow(int alus, int aste) {
        int ret = 1;

        for (int i = 0; i < aste; i++) {
            ret *= alus;
        }

        return ret;
    }

    /**
     *
     * @param a sisendarv
     * @param v2iksedAlgarvud ette genereeritud algarvude massiiv.
     * @return kas sisendarv a on algarv.
     */
    public static boolean onAlgarv(int a, int[] v2iksedAlgarvud){
        //double sqrtA = Math.sqrt(a);

        for (int arv : v2iksedAlgarvud) {
            if (a % arv == 0) {
                return false;
            }

            /*
            if (arv > sqrtA) {
                return true;
            }
            */
            if (arv * arv > a) {
                return true;
            }
        }
        /*
        for (int i = v2iksedAlgarvud[v2iksedAlgarvud.length - 1]; i < sqrtA; i += 2) {
            if (a % i == 0) {
                return false;
            }
        }*/
        for (int i = v2iksedAlgarvud[v2iksedAlgarvud.length - 1]; i*i < a; i += 2) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;

    }

    /**
     * Väljastab n suursuse massiivi n esimese algarvuga.
     *
     * @param n mitu algarvu massiivi genereerida
     * @return n esimese algarvuga massiiv
     */
    public static int[] v2iksedAlgarvud(int n) {
        int[] v2iksedAlgarvud = new int[n];
        v2iksedAlgarvud[0] = 2;
        for (int i = 1; i < n; i++) {

            boolean algarvLeitud = false;
            int j = v2iksedAlgarvud[i-1] + 1;

            // Suurendab j nii kaua kuni leiab algarvu
            while (!algarvLeitud) {
                for (int k = 0; k < i; k++) {
                    if (j % v2iksedAlgarvud[k] == 0) {
                        j++;
                        break;
                    } else if (k == i-1) {
                        v2iksedAlgarvud[i] = j;
                        algarvLeitud = true;
                    }
                }
            }
        }

        return v2iksedAlgarvud;
    }
}//klass AlgarvuRing
