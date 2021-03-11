/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1
 * Teema:
 * Algarvuringide leidmine
 *
 * Autor: xXHAxoRXx
 *****************************************************************************/
import java.lang.Math;
public class AlgarvuRing {
    public static void main(String[] args) {
        System.out.println("Sisend: 1000");
        algarvuRing(1000);
        System.out.println("xXHAxoRXx "
                + new java.sql.Timestamp(System.currentTimeMillis()));
    }//main

    //Antud: täisarv a, millest väiksemaid algarvuringe otsime
    //Tulemus: prinditud 5 esimest algarvuringi alates sisendist.
    //Rakendamine: peamine meetod, mis kombineerib teisi, et väljastada talle antud täisarvust alates 5 algarvuringi
    public static void algarvuRing(int a) {
        //a - algarvuringi leidmise lähtekoht
        //väljastab 5 lähimat algarvuringi antud lähtekohast a väiksemate arvude hulgas
        //võib juhtuda, et vastavaid ringe on vähem kui 5. Siis väljasta ainult need
        //ühekohaliste arvude seast algarvuringe ei otsi
        //väljastab igale reale täpselt ühe algarvuringi algusarvu, väärtuste kahanedes
        //ekraanile väljastuses ei tohi olla tühikuid ega tühje ridu

        a -=1; int leitud = 0;
        if (a%2==0) a -=1;
        //tsükkel töötab, kuni leitakse 5 arvu või kuni on kindel, et algarvuringe rohkem ei leidu
        while(leitud <5 && a>=11){
            int algne = a;
            //kui arvu kõik kohad on paaritud ja arv ise on algarv, hakkame kontrollima, kas tegu on algarvuringiga
            if (koikpaaritud(a) == true && algarvulisus(a) == true){
                int pikkus = (int) (Math.log10(a) + 1);
                for (int i = 1; i < pikkus; i++) {
                    //kui arvu numbreid liigutades ei saada enam algarvu, väljume tsüklist
                    if (algarvulisus(tostmine(a)) == false || (tostmine(a) >algne) ){
                        break;
                    }
                    //kui ühe tõstmisega saadakse algarv, korratakse sama tsüklit uue arvuga
                    if (algarvulisus(tostmine(a)) == true ){
                        a= tostmine(a);
                    }
                    //siia jõuab meetod ainult, kui tema kõik tsüklilised ümbertõstmised on algarvud - saame ta printida algarvuringina
                    if (i==(pikkus-1)){
                        System.out.println(algne + " on algarvuring");
                        leitud +=1;
                    }
                }
            }
            a = jargmineKandidaat(algne);


        }


    } //algarvuRing
    //See meetod kontrollib, kas talle parameetrina antud täisarv on algarv ja tagastab vastava booleani
    //Antud täisarv a
    //Tulemus boolean
    //Rakendamine kontrollimaks, kas arv on algarv või mitte.
    public static boolean algarvulisus(int a) {
        if (a % 2 == 0 || a % 3 == 0) {
            return false;
        }
        //Leidsin internetist, et kõik algarvud avalduvad kujul 6k +-1,näiteks siit: https://primes.utm.edu/notes/faq/six.html, mistõttu kontrollin ainult sellisel kujul olevate arvudega jaguvust
        for (int k = 1; (6 * k - 1)* (6 * k - 1) <= a; k++) {
            if (a % (6 * k - 1) == 0 || a % (6 * k + 1) == 0 ) {
                return false;
            }
        }
        return true;
    }


    //Antud täisarv n
    //Tulemus boolean e tõeväärtus, mis ütleb kas arvu kõik numbrid on paaritud ja mitte 5
    //Rakendamine kontrollimaks , kas kõik numbrid mingis arvus on paaritud ja mitte 5
    //ainult selline arv saab olla algarvuring, sest kui need on viimased numbrid,
    //mis paratamatult numbreid liigutades juhtub,
    //siis arv pole algarv.
    public static boolean koikpaaritud(int n){
        while (n > 0)
        {
            int jääk = n % 10;
            if ((jääk % 2 == 0)||jääk==5)
                return false;
            else
                n = n / 10;
        }
        return true;
    }

    //Antud: täisarv a, mille numbreid hakatakse ümber vaatama.
    //Tulemus : täisarv, mis on moodustatud a esimese numbri viimaseks paigutades
    //Rakendamine  - see meetod võtab parameetriks täisarvu a ning tõstab esimesel kohal arvu viimaseks
    public static int tostmine(int a) {
        int esimene = 0;
        int suurim = 0;
        for (int i = 1; i < a; i= i*10) {
            esimene = a/i;
            suurim = i;
            if(esimene==0){
                return (2);
            }
        }
        return ((a-esimene*suurim)*10 + esimene);


    }
    //See meetod võtab parameetriks täisarvu ning väljastab järgmise täisarvu, mida on üldse mõtet kontrollida.
    //Antud: täisarv n, millest väiksemat kandidaati hakatakse otsima
    //Tulemus: tagastatakse järgmine täisarv, mille algarvuringsust peaks kontrollima
    //Rakendamine uue kandidaatalgarvu leidmiseks. See meetod väldib tühja tööd.
    public static int jargmineKandidaat(int n){
        int esialgne = n;
        int koht =0;
        int summa = 0;
        while (n > 0) {
            int jääk = n % 10;
            if ((jääk % 2 == 0)){
                //Siin lahutatakse kõik, mis eelneb paarisarvulisele numbrile.
                //Näiteks 8699-st tehakse 8600 ja siis viimane -1 veel edasi 8599ks.
                //Kasutan rekursiooni, et eemaldada võimalikult mitu paarisarvulist kohta.
                //St 8599-st tehakse rekursiooniga kohe 7999.
                return jargmineKandidaat(esialgne - summa-1);
            }
            //Muutuja summa on, et hoida meeles, kui palju paarisarvu leidmisel lahutama peab
            summa += Math.pow(10,koht)*jääk;
            //Muutuja koht on selleks, et teada, mis kümne astmega korrutatult numbri summale liitma peab.
            koht ++;
            n = n/10;
        }
        return esialgne-2;

    }

}//klass AlgarvuRing
