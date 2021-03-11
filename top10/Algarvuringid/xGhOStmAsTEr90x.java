/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1a
 * Teema:
 * Arvu algarvulisuse kontrollimine, algarvuringide leidmine
 *
 * Autor: xGhOStmAsTEr90x
 *
 * Algarvulisuse kontrollimisel kasutatud meetod,
 * kontrollida jaguvust ainult arvudega mis on väiksemad arvu ruutjuurest
 * on juba varasemast tuttav, kuid täpset algallikat ei oska öelda.
 *
 *****************************************************************************/

class AlgarvuRing {
    private static int väljastaKuiOnAlgarvuringiSuurimLiige(int algus, int suurimKordaja) {
        // Antud: Täisarv algus, täisarv suurimKordaja, mis on kümne aste millega korrutades saab liigutada numbri selle arvu kümnendesituses esimesele kohale.
        // Tulemus: Kontrollib kas antud arvust algus algab algarvuringi, mille suurim liige on see arv ise, ning kui see on tõsi, väljastab arvu algus.
        // Tagastab väljastatud arvude arvu, ehk 1 kui arv väljastati ning 0 kui arvu ei väljastatud.
        // Meetod eeldab, et teda rakendatakse ainult vähemalt kahekohalistel arvudel, mille kümnendesitus koosneb ainult numbritest 9, 7, 3 ja 1.

        // Kontrollime kõigepealt, kas arv on arvuringi suurim liige, et välistada võimalikult palju arve ilma algarvulisust kontrollimata.
        // Samuti kontrollime käsitsi jaguvust väiksemate algarvudega. See võimaldab välistada suure hulga arve ilma täielikku algavrulisuse kontrolli teostamata.
        // Jaguvust 2 ja 5-ga pole tarvis kontrollida, kuna meetod eeldab, et alguse kümnendesitus koosneb ainult numbrites 9, 7, 3 ja 1 ning seega ei saa ükski ümberpaigutus 2 ega 5-ga jaguda.
        // Tsüklit 'do while' kasutatud, et kontrollida ka arv algus, mille tsükli jätkutingimus muidu välistaks
        int ümberpaigutus = algus;
        do {
            // Kui ümberpaigutus on suurem kui algus siis pole tegemist suurima liikmega.
            if (ümberpaigutus > algus || ümberpaigutus % 3 == 0 || ümberpaigutus % 7 == 0 || ümberpaigutus != 11 && ümberpaigutus % 11 == 0 || ümberpaigutus != 13 && ümberpaigutus % 13 == 0) {
                return 0;
            }

            // Leiame järgmise ümberpaigutuse arvuringis.
            ümberpaigutus = paigutaÜmber(ümberpaigutus, suurimKordaja);
        } while (ümberpaigutus != algus);

        // Kontrollime kas kõik arvuringis esinevad ümberpaigutused on algarvud.
        do {
            // Kui ümberpaigutus pole algarv siis pole tegemist algarvuringiga.
            if (!onAlgarv(ümberpaigutus)) {
                return 0;
            }

            // Leiame järgmise ümberpaigutuse arvuringis.
            ümberpaigutus = paigutaÜmber(ümberpaigutus, suurimKordaja);
        } while (ümberpaigutus != algus);

        System.out.println(algus);
        return 1;
    }

    private static boolean onAlgarv(int arv) {
        // Antud: Täisarv arv mille algarvulisust kontrollida
        // Tulemus: Kontrollib, kas arv on algarv ning tagastab vastuse tõeväärtusena.
        // Meetod eeldab, et jaguvus 17-st väiksemate algarvudega on juba välistatud.

        // Ruutjuur arvust on suurim arv, millega on mõtekas jaguvust kontrollida,
        // kuna iga arvu ruutjuurest suurema teguri k puhul leidub teine tegur l = arv / k,
        // mis on ilmselgelt väiksem kui ruutjuur arvust.
        // Jaguvust on mõtekas kontrollida ainult algarvudega, kuna kui arv jagub kordarvuga, siis jagub ta ka selle
        // kordarvu algteguritega. Kuna kõik algarvud peale 2-e, millega jaguvus on juba välistatud, on paaritud,
        // siis kontrollime jaguvust ainult paaritute arvudega. Me ei kontrolli jaguvust ainult algarvudega,
        // kuna kõigi algarvude genereerimine oleks liigselt ajakulukas.
        // Alustame kontrollimist 17-st, kuna eeldame, et jaguvus väiksemate algarvudega on juba kontrollitud.
        int maksimum = (int) Math.sqrt(arv);
        for (int i = 17; i <= maksimum; i += 2) {
            // Kontrollime kas arv jagub i-ga
            if (arv % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static int paigutaÜmber(int arv, int suurimKordaja) {
        // Antud: Täisarv arv millest leida järgmine arv arvuringis ning kümne aste suurimKordaja, millega tuleb number korrutada, et saada see arvus esimesele kohale.
        // Tulemus: Leiame järgmise arvu arvuringis, liigutades arvu kümnendesituses viimase numbri esimeseks.

        // Leiame arvu viimase numbri jäägina 10-ga jagamisel. Korrutame viimase numbri arvuga suurimKordaja, et liigutada see kümnendesituses esimese numbri kohale.
        // Eemaldame viimase numbri ning liigutame eelnevaid numbreid ühe võrra paremale jagades arvu 10-ga, kusjuures säilitatakse ainult jagatise täisarvuline osa.
        // Liidame need kaks vahetulemust, et saada arv, kus viimasel kohal olev number on liigutatud esimesele kohale ning tagastame selle.
        return arv % 10 * suurimKordaja + arv / 10;
    }

    public static int kontrolliLäbi(int olemasolev, int kordaja, int suurimKordaja, int maksimum, int väljastatud) {
        // Antud: Täisarv olemasolev, mis säilitab siiani genereeritud osa arvust,
        // 10 aste kordaja, millega korrutatakse järgmisena lisatav number,
        // 10 aste suurimKordaja, millega korrutades liigutatakse number genereeritavas arvus esimesele kohale,
        // täisarv maksimum, mis on suurim lubatud genereeritud arvu väärtus,
        // ning täisarv väljastatud, mis sisaldab varasemalt väljastatud arvude arvu.
        //
        // Tulemus: Meetod genereerib rekursiivselt kahanevas järjestuses kõik täisarvud, mis on väiksemad või võrdsed maksimumiga, ning väljastab
        // nendest arvudest suurimad 5 arvu mis on algarvuringi suurimad liikmed. Meetod tagastab kokku väljastatud arvude arvu (sh varasemalt väljastatud arvud).
        // Arve genereeritakse lisatades kõigepealt olemasolevale arvule number kordaja poolt määratud kohale, seejärel kordaja / 10 poolt määratud kohale jne.
        // Iga meetodi rekursiivne rakendamine lisab olemasolevale ühe numbri, kordaja poolt määratud kohale, kuni numbrite lisamisega jõutakse ühelisteni.
        // Võimalik meetodi rakendus genereerimise käigus: kontrolliLäbi(193000, 100, 100000, 2, 923455)
        //
        // Meetodi esmasel väljakutsel peaks olemasolev ning väljastatud olema vaikeväärtusega 0 ning kordaja ja suurimKordaja peaksid olema suurim kümne aste, mis on
        // väiksem või võrdne maksimumiga, et kõik võimalikud numbrid saaksid genereeritud. Nt meetodi rakendus, et leida ning väljastada 5 suurimat
        // arvust 9345 väiksemat või sellega võrdset algarvuringi suurimat liiget: kontrolliLäbi(0, 1000, 1000, 0, 9345).

        // Kui siiani genereeritud osa arvust on suurem kui maksimum siis lõpetame kohe genereerimise, kuna kõik selle väljakutse käigus genereeritavad arvud oleksid
        if (olemasolev > maksimum) return väljastatud;

        // Kui kordaja on 1 oleme genereerimisega jõudnud ühelisteni, mis lisatakse viimasena.
        if (kordaja == 1) {
            // Kui siiani genereeritud arv on 0 ning oleme jõudnud ühelisteni, siis lõpetame genereerimise, et välistada ühekohalised arvud.
            if (olemasolev == 0) return väljastatud;

            // Genereerime arvud, lisades olemasolevale vastavalt 9, 7, 3 või 1 ühelist ning kui arv vastab tingimustele (ei ole suurem kui maksimum ning on algarvuringi suurim liige) siis väljastame ta.
            // Iga arvu kontrollimise järel uuendame siiani väljastatud arvude arvu, ning kui väljastatud on vähemalt 5 arvu siis lõpetame genereerimise.
            int genereeritud;
            if ((genereeritud = olemasolev + 9) <= maksimum && (väljastatud += väljastaKuiOnAlgarvuringiSuurimLiige(genereeritud, suurimKordaja)) >= 5) return väljastatud;
            if ((genereeritud = olemasolev + 7) <= maksimum && (väljastatud += väljastaKuiOnAlgarvuringiSuurimLiige(genereeritud, suurimKordaja)) >= 5) return väljastatud;
            if ((genereeritud = olemasolev + 3) <= maksimum && (väljastatud += väljastaKuiOnAlgarvuringiSuurimLiige(genereeritud, suurimKordaja)) >= 5) return väljastatud;
            if ((genereeritud = olemasolev + 1) <= maksimum && (väljastatud += väljastaKuiOnAlgarvuringiSuurimLiige(genereeritud, suurimKordaja)) >= 5) return väljastatud;
        } else {
            // Leiame kordaja numbri jaoks mis järgneb järgmisena lisatavale numbrile.
            int järgmineKordaja = kordaja / 10;

            // Genereerime rekursiivselt kõik arvud, kus järgmine number on vastavalt 9, 7, 3 või 1 ning väljastame genereerimise käigus tingimustele vastavad arvud.
            // Kontrollim võimalike järgmisi numbreid kahanevas järjekorras, et kontrollida suuremad arvud enne.
            // Iga rekursiivse meetodi rakendamise järel uuendame siiani väljastatud arvude arvu, ning kui väljastatud on vähemalt 5 arvu siis lõpetame genereerimise.
            if ((väljastatud = kontrolliLäbi(9 * kordaja + olemasolev, järgmineKordaja, suurimKordaja, maksimum, väljastatud)) >= 5) return väljastatud;
            if ((väljastatud = kontrolliLäbi(7 * kordaja + olemasolev, järgmineKordaja, suurimKordaja, maksimum, väljastatud)) >= 5) return väljastatud;
            if ((väljastatud = kontrolliLäbi(3 * kordaja + olemasolev, järgmineKordaja, suurimKordaja, maksimum, väljastatud)) >= 5) return väljastatud;
            if ((väljastatud = kontrolliLäbi(kordaja + olemasolev, järgmineKordaja, suurimKordaja, maksimum, väljastatud)) >= 5) return väljastatud;

            // Kui siiani genereeritud arv on 0 siis oleme järelikult just kontrollinud läbi kõik arvud mille kümnendesitus on kordajaga sama pikk,
            // ning nüüd kontrollime läbi kõik arvud mille kümnendesitus on kordajast ühe võrra lühem.
            if (olemasolev == 0) väljastatud = kontrolliLäbi(olemasolev, järgmineKordaja, suurimKordaja / 10, maksimum, väljastatud);
        }

        return väljastatud;
    }

    public static void algarvuRing(int a) {
        // Antud: Täisarv a, mis on algarvuringi leidmise lähtekoht
        // Tulemus: Väljastab 5 lähimat algarvuringi antud lähtekohast a väiksemate arvude hulgas.
        // Võib juhtuda, et vastavaid ringe on vähem kui 5. Siis väljastatakse ainult need.
        // Ühekohaliste arvude seast algarvuringe ei otsita.
        // Väljastab igale reale täpselt ühe algarvuringi algusarvu, väärtuste kahanedes.
        // Ekraanile väljastuses ei ole tühikuid ega tühje ridu.

        int maksimum = a % 2 == 0 ? a - 1 : a - 2; // Seame maksimumiks suurima paaritu arvu, mis on väiksem kui a, kuna teame, et kahekohalised paarisaarvud ei saa algarvud olla.
        int kordaja = (int) Math.pow(10, (int) Math.log10(maksimum)); // Leiame logaritmi abil 10 astme mis on kümnendesituses sama numbrite arvuga kui maksimum.

        // Täheldame, et kui arvu kümnendesituses esinevad numbrid 0, 2, 4, 5, 6 või 8, siis leidub arvu kümnendesituse tsüklilistes ümberpaigutustes
        // selline arv, mille viimane number on 0, 2, 4, 5, 6 või 8 ning seega arv jagub kas 2 või 5-ga ning seega ei ole algarv. Seega on mõtekas otsida algarvuringe
        // ainult arvudest, mille kümnendesitus sisaldab ainult numbreid 9, 7, 3 ja 1.
        // Täheldame, et peame väljastama algarvuringi liikme ainult siis kui ta on algarvuringi suurim liige, kuna vastasel juhul on algarvuringi suurim liige
        // kas juba varem väljastatud või on algarvuringi suurim liige suurem kui maksimum ning seda ei tule väljastada.
        // Seega rakendame meetodit kontrolliLäbi, et genereerida kõik arvud maksimumist väiksemad või maksimumiga võrdsed arvud,
        // mille kümnendesitus koosneb ainult numbritest 9, 7, 3 ja 1 ning väljastada kahanevas järjestuses 5 suurimat arvu mis on algarvuringi suurimad liikmed.
        kontrolliLäbi(0, kordaja, kordaja, maksimum, 0);
    }

    public static void main(String[] args) {
        System.out.println("Kodutöö nr 1a.                          Programmi väljund");
        System.out.println("=========================================================:");

        // Jooksutame meetodit algarvuRing erinevate parameetri väärtustega, et kontrollida väljundi õigsust
        System.out.println("Antud lähtekoht: 97");
        System.out.println("Leitud algarvuringid:");
        algarvuRing(97);
        System.out.println("Antud lähtekoht: 100");
        System.out.println("Leitud algarvuringid:");
        algarvuRing(100);
        System.out.println("Antud lähtekoht: 1000");
        System.out.println("Leitud algarvuringid:");
        algarvuRing(1000);
        System.out.println("Antud lähtekoht: 1000000");
        System.out.println("Leitud algarvuringid:");
        algarvuRing(1000000);

        System.out.println("=========================================================.");
        System.out.println("xGhOStmAsTEr90x              " + new java.sql.Timestamp(System.currentTimeMillis()));
    }
}
