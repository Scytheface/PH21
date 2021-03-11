/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1a
 * Teema:
 * Järjendi summa, nii iteratiivselt kui ka rekursiivselt
 *
 * Autor: IronMOnk91
 *
 * Mõningane eeskuju: vt https://en.wikipedia.org/wiki/Prime_number
 *
 *****************************************************************************/

import java.util.HashSet;

class AlgarvuRing {

    static HashSet<Integer> mitteSobilikNumber = new HashSet<Integer>();
    static HashSet<Integer> algArvuRingid = new HashSet<Integer>();

    public static void main(String[] args) {
        //...
        // üldine päis
        System.out.println("Kodutöö nr 1a. \t\t\t\t\t\t Programmi väljund");
        System.out.println("=========================================================");

        //long algus = System.currentTimeMillis();
        algarvuRing(1_000_000);
        //System.out.println("aega kulus " + (System.currentTimeMillis() - algus));
        System.out.println("=========================================================");
        System.out.print("IronMOnk91 \t\t\t\t\t");
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
    }//main

    public static void algarvuRing(int a) {
        // prindime nõutud päise
        System.out.println("Antud lähtekoht: " + a);
        System.out.println("Leitud algarvuringid:");


        // tekitame seti mittesobivatest numbritest
        mitteSobilikNumber.add(0);
        mitteSobilikNumber.add(2);
        mitteSobilikNumber.add(4);
        mitteSobilikNumber.add(5);
        mitteSobilikNumber.add(6);
        mitteSobilikNumber.add(8);

        int ringideCounter = 0;     // peame arvet mitu ringi oleme leidnud
        int originaalneA = a;

        int arvuPikkus; // kõikide for loopide tingimus, et saaks ainult korra arvutada

        while (ringideCounter < 5 && a > 10){
            boolean jatkata = true;
            a = jargmineSobivArv(originaalneA);
            originaalneA = a;
            arvuPikkus = (int) Math.log10(a);

            for (int i = 0; i <= arvuPikkus; i++) { // kontrollime kas a = 6k +/- 1 ja ega a pööratuna pole suurem kui a

                if (!saabOllaAlgarv(a)) {
                    // ei saa algarv olla seega jätame vahele selle arvu
                    jatkata = false;
                    break;
                }
                a = poora(a);
                if (a > originaalneA){ // kui pööratud arv suurem kui stardiarv siis pole vaja teda uurida. Teostame kontrolli ainult esimeses tsüklis, sest edasistel juhtudel on juba kontrollitud esimeses tsükklis.
                    jatkata = false;
                    break;
                }
            }

            if (jatkata) { // vaatame kas arv on juba mõnes ringis
                if (onTeisesRingis(a)) {
                    // ei ole korras, juba leitud ringis
                    jatkata = false;
                    break;
                }
            }

            if (jatkata) { // vaatame kas kogu a-st algav ring on algarv
                for (int i = 0; i <= arvuPikkus; i++) {
                    if (!onAlgarv(a)) {
                        // a ei ole algarv
                        jatkata = false;
                        break;
                    }
                    a = poora(a);
                }
            }

            if (jatkata) { // a vastab kõigile tingimustele
                ringideCounter += 1;
                System.out.println(a);
                for (int i = 0; i <= arvuPikkus; i++) {
                    lisaTeatudRingidesse(a);
                }
            }
        }
        algArvuRingid.clear(); // tühjendame seti et uuel katsel oleks tühi

    }//algarvuRing

    /**
     * Kontrollimine kas arv saab olla algarv, ehk kas on kujul 6k +/- 1.
     * @param n Kontrollitav arv.
     * @return Tõeväärtus, kas saab olla algarv.
     */
    public static boolean saabOllaAlgarv(int n) {
        return n % 6 == 1 || n % 6 == 5;
    }//saabOllaAlgarv

    /**
     * Kontroll, kas tegu on algarvuga või mitte.
     * @param n Kontrollitav arv.
     * @return Tõeväärtus, kas on algarv.
     */
    public static boolean onAlgarv(int n) {
        // ühte ja kahte pole vaja kontrollida, sest alla kümne arve ei uurita
        if (n % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= (int) Math.sqrt(n) + 1; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    } //onAlgarv

    /**
     * Tõstab arvu viimase numbri esimeseks.
     * @param n Pööratav arv.
     * @return Pööratud arv.
     */
    public static int poora(int n) {
        // leiame viimase numbri ja korrutame selle 10^(arvu pikkus), ülejäänud arvu teeme 10x väiksemaks.
        return (n % 10) * (int) Math.pow(10, (int) Math.log10(n)) + n / 10;
    } //poora

    /**
     * Tagastab järgmise arvu mis saab olla algarvuringi algus, ehk järgmise numbri, mis ei sisalda paarisarvu ega viit.
     * @param n Testitav arv
     * @return Järgmine arv, mis saaks olla algarvuringi algus.
     */
    public static int jargmineSobivArv(int n) {

        int malu_n = n;
        byte suurus = (byte) Math.log10(n);

        int arvuPikkus;
        for (int i = suurus; i >= 0; i--) {
            // vaatame kas mõnel kohal on ta 0,2,4,5,6,8
            arvuPikkus = (int) Math.pow(10, i);
            if (mitteSobilikNumber.contains(n / arvuPikkus)) {
                // sisaldab seda halba numbrit, seega pole mõtet vaadata ühtegi arvu, mis sisaldab sama numbrit samas kohas nt 12999 kuni 12000 pole mõtet vaadata, sest seal on 2 tuhandeliste kohal
                return malu_n - malu_n % arvuPikkus - 1;
            }
            n -= (n / arvuPikkus) * arvuPikkus; // võtame kõige suurema arvu numbrist ära, ehk 123 -> 23 ja 654232 -> 54232
        }
        return malu_n - 2;
    } //jargmineSobivArv

    /**
     * Kontrollib, kas algarvuring on juba teada.
     * @param n Algarvuringi algus.
     * @return Tõeväärtus, kas on juba tuttav algarvuringi osa.
     */
    public static boolean onTeisesRingis(int n) {
        if (algArvuRingid.contains(n)) {
            return true;
        } else {
            return false; //ei ole teises ringis
        }
    } //onTeisesRingis

    /**
     * Abifunktsioon arvu kõikide pööramiste lisamiseks tuntud algarvuringidesse.
     * @param n Lisatava algarvuringi algus
     */
    public static void lisaTeatudRingidesse(int n) {
        // lisame kõik ringis olevad algarvud teatud ringidesse
        algArvuRingid.add(n);
        int arvuSuurusjark = (int) Math.log10(n);
        for (int i = 0; i < arvuSuurusjark; i++) {
            n = poora(n);
            algArvuRingid.add(n);
        }
    }//lisaTeatudRingidesse

}//klass AlgarvuRing
