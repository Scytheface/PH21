class AlgarvuRing {
    public static void main(String[] args) {
        /*****************************************************************************
         * Programmeerimisharjutused. LTAT.03.007
         * 2020/2021 kevadsemester
         *
         * Kodutöö. Ülesanne nr 1a
         * Teema:
         * Algarvuringid
         *
         * Autor: Z3aLOuSAngeL95
         *****************************************************************************/
 System.out.println("Z3aLOuSAngeL95 " +  new java.sql.Timestamp(System.currentTimeMillis()));

    }

    static void algarvuRing(int n) {
        //a - algarvuringi leidmise lähtekoht
        //väljastab 5 lähimat algarvuringi antud lähtekohast a väiksemate arvude hulgas
        //võib juhtuda, et vastavaid ringe on vähem kui 5. Siis väljasta ainult need
        //ühekohaliste arvude seast algarvuringe ei otsi
        //väljastab igale reale täpselt ühe algarvuringi algusarvu, väärtuste kahanedes
        //ekraanile väljastuses ei tohi olla tühikuid ega tühje ridu
        int a = n;
        n=n-1;
        n = Paaritu(n);
        int[] kontroll = new int[5]; //Kontrollmassiiv, kuhu lähevad väljastatavad arvud
        int g = 0;
        while (g < 5) {
            if (n<10)
                return;
            n = Paarsus(n);
            if (Algarv(n, a)&&Sisaldab(n,kontroll)==false) {
                System.out.println(n);

                kontroll[g]=n;
                g++;
                n = n - 2;



            } else {
                n = n - 2;
            }


        }
    }

    static boolean Paaris(int n) { //Paarsuse kontroll - arvud, mis sisaldavad paarisarvulisi numbreid meid ei huvita
        if (n % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    static int Paaritu(int n) {
        //Antud: kasutaja poolt antud arv
        //Tulemus: Suurim paaritu arv, mis on kasutaja poolt antud arvust väiksem või võrdne
        //Rakendamine paaritu arvu genereerimiseks

        if (n % 2 == 0) {
            n = n - 1;
            return n;
        } else {
            return n;
        }
    }

    static int Paarsus(int n) {
        //Antud: Paaritu arv
        //Tulemus: Paaritu arv, mis ei sisalda endas ühtegi paarisarvulist numbrit (ega numbrit '5')
        //Rakendamine: lahutame arvust nii palju, et saaks lahti kõigist paarisarvulisest numbritest, aga
        //mitte nii palju, et mõni ainult paaritutest numbritest koosnevatest arvudest vahele jääks
        int k = Kohalisus(n);
        int[] massiiv = ArvMassiivi(n, k);
        for (int c = 0; c < k + 1; c++) {
            if (Paaris(massiiv[k - c])|massiiv[k-c]==5) {
                for (int h = k; h > k - c; h--) {
                    n = n - massiiv[h] * (int) (Math.pow(10, k - h));

                }
                return Paarsus(n - 1);
            }
        }
        return n;
    }

    static boolean Algarv(int n, int a) {
        //Antud: aintult paaritutest arvudest koosnev arv
        //Tulemus: tõeväärtus
        //Kontrollime, kas iga tsükliline permutatsioon arvust on algarv, sh. arv ise

        int k = Kohalisus(n);
        int[] massiiv = ArvMassiivi(n, k);
        //Mitu korda peab viimase numbri esimeseks tõstma
        int r = (int) Math.ceil(Math.sqrt((double) n));
        //Leiame, mis arvuni algarvu kontrolli peab teostama
        for (int i = 0; i < k + 1; i++) {
            for (int j = 3; j < r; j = j + 2) {
                if (n % j == 0)
                    return false;
            }
            n = ViimneEsimeseks(n);
            if (n>=a)
                return false;
            r = (int) Math.ceil(Math.sqrt((double) n));
        }
        return true;

    }


    static int Kohalisus(int n) {
        //Antud: täisarv
        //Tulemus: täisarv k, mis annab meile teada, et arv on k+1 kohaline
        //Mitmekohalise arvuga tegemist on?
        double a = Math.log10((double) n);
        int k = (int) Math.floor(a);
        return k;
    }

    static int[] ArvMassiivi(int n, int k) {
        //Antud: täisarv
        //Tulemus: massiiv, kuhu kõik antud arvu numbrid on arvus esinemise järjekorras sisse viidud
        //Rakendame sest, et massiivis on arvus olevate numbrite järjekorraga lihtsam manipuleerida
        int[] massiiv = new int[k + 1];
        for (int c = 0; c < k + 1; c++) {
            double b = (n / (Math.pow(10, c))) % 10;
            massiiv[k - c] = (int) ((int) (Math.floor(b)));
        }
        return massiiv;
    }

    static int MassiivArvuks(int[] massiiv, int k) {
        //Täpselt vastupidine meetod kui ArvMassiiiks
        int o = 0;
        for (int i = 0; i < k + 1; i++) {
            o = (int) (o + massiiv[i] * (Math.pow(10, (k - i))));
        }
        return o;
    }


    static int ViimneEsimeseks(int n) {
        //Antud: täisarv
        //Tulemus: Täisarvu ühekordne tsükliline permutatsioon
        //Rakendame, et leida algarvu, mille iga tsükliline permutatsioon on algarv
        int k = Kohalisus(n);
        int[] massiiv2 = ArvMassiivi(n, k);
        int viimane = massiiv2.length - 1;
        int[] massiiv1 = new int[viimane + 1];
        for (int i = 0; i < viimane + 1; i++) {
            if (i == 0)
                massiiv1[i] = massiiv2[viimane];
            else {
                massiiv1[i] = massiiv2[i - 1];
            }
        }

        return MassiivArvuks(massiiv1, k);


    }

    static boolean Sisaldab(int n, int[] kontroll) {
        //Antud: kontrollitav täisarv ja massiv arvudega, mis on juba väljastatud
        //Tulemus: tõeväärtus, kui on unikaalne siis väljastatakse 'false'
        //Leiame, kas tegemist on juba varem väljastatud algarvuringi esindajaga
        int k = Kohalisus(n);
        int j = 0;
        while (j < k+1) {
            for (int elem : kontroll) {
                if (elem == n){
                    return true;}}
            n=ViimneEsimeseks(n);
            j++;

        }
        return false;


    }
}