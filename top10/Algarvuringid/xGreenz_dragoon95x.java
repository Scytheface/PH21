/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1a
 * Teema:
 *      Algarvuring
 *
 * Autor: xGreenz_dragoon95x
 *
 ****************************************************************************/


class AlgarvuRing{

  public static void main(String[] args){
      System.out.println("Koduöö nr 1a.                                    Programmi väljund");
      System.out.println("==================================================================:");
      algarvuRing(311);
      algarvuRing(1000000);
      /*
      long start = System.currentTimeMillis();
      for (int i=1;i<5000;i++) {
        algarvuRing(1000000);
      }
      long stop = System.currentTimeMillis();
      System.out.println("Aega kulus " + (stop - start)
             + " milliskundit");
       */
      System.out.println("\n==================================================================.");
      System.out.println("xGreenz_dragoon95x                           " + new java.sql.Timestamp(System.currentTimeMillis()));
  }//main


/**
 * Algaarvuringi leidmine
 * <p>
 * Rakendamine algarvuringi leidmiseks: algarvuRing(a).
 * @param a Antud täisarvuline alguspunkt.
 * @return Ei tagasta midagi.
 */
 public static void algarvuRing(int a){

     System.out.println("\nAntud lähtekoht: " + a);
     System.out.println("Leitud algarvuringid:");

     int lugeja = 0;  // loeb leitud algarvu ring

     // Kui vaja, muuta lähtearv paarituks arvuks
     // See vajalik, et saaks edaspidi ainult paarituid arve käidelda.
     // Lisaks tehakse arv väiksemaks kuna tuleb leidalähtekohast väiksemad algarvude ringid.
     if (a%2 == 0){
         a -=3;
     } else {
         a -=2;
     }

     // Käia läbi kõik paaritud arvud
     for (; a > 9 ; a -= 2){

         // Eemaldada arvud mille koosseisus on mõni paarisarvuline number (sh 0) või number 5-te.
         if (sisaldabEbasobivatNumbrit(a)){
             continue;
         }

         // Kontrollida kas on algarv
         if (onAlgarv(a)){
             // Kontrollida kas moodustab algarvuringi
             if (onAlgarvuRing(a)) {
                 System.out.println(a);
                 lugeja++;

                 //Lõpetada tsükkel kui 5 algarvuringi on leitud
                 if (lugeja == 5){
                     break;
                 }
             }
         }
     }
 }//algarvuRing


    /**
     * Arvu algarvulisuse kontroll.
     *   Kontrollib jagamise jääki ainul paaritute arvudega.
     *   Jääki jagamisel 2ga ei kontrolli, kuna sisendiks siin skriptis tulevad ainult paaritud arvud.
     *   Jagamise jääki kontrollitakse kuni arvuga <= ruutjuur a-st, kuna suuremate arvudega jagamisel tuleks
     *   jäägita jagamisel vastuseks juba kontrollitust väiksem arv.
     *   Lahendus ei ole kuskilt kopeeritud vaid ise välja mõeldud.
     * <p>
     * Rakendamine algarvulisuse kontrolliks: onAlgarv(a).
     * @param a Antud täisarvuline arv.
     * @return Tõevaäärus, mis näitab kas sisend täisarv on täisarv või mitte.
     */
    public static boolean onAlgarv(int a) {
        int piir = (int)Math.sqrt(a);
        for(int x=3; x<=piir; x += 2){ // jagamine ainult paaritute arvudega
          if (a%x == 0){
              return false;
          }
        }
        return true;
    }


    /**
     * Algarvu ringilisuse kontroll.
     * <p>
     * Rakendamine algarvulisuse kontrolliks: onAlgarvuRing(x).
     * @param x Antud täisarvuline arv.
     * @return Tõevaäärus, mis näitab kas sisend algarvu kõik permutatsioonid (viimase numbri ette tõstmiega saadud)
     * sisend arvust väiksemad algarvud.
     */
    public static boolean onAlgarvuRing(int x){

        int algne = x; // salvestab algse arvu.

        // käiakse läbi kõik sisend arvu permutatsioonid
        // (viimase numbri ette tõstmisega saadud)
        int piir = (int)(Math.log10(x));
        for(int i=0; i <=piir; i++){
            // Tõsta tagumine number esimeseks
            x = muutja(x);

            // Katkestada kui arv on suurem kui algne.
            // Sellega tagatakse ka, et arvarvu ring üldiselt ei kordu.
            if (algne < x){
                return false;
            }

            // Kontrollida kas on algarv
            if (!(onAlgarv(x))){
                return false;
            }
        }
        return true;
    }


    /**
     * Arvu numbrite ümberpaigutamine nii, et viimane number tõstetakse ette.
     * nt: 12345 -> 51234
     * <p>
     * Rakendamine algarvulisuse kontrolliks: muutja(x).
     * @param x Antud täisarvuline arv.
     * @return Täisarv, kus sisend arvu viimane number on tõstetud ette.
     */
    public static int muutja(int x){
        return Integer.parseInt(String.valueOf(x%10) + String.valueOf(x/10));
        //return  (int) (x / 10 + x % 10 * Math.pow(10, (int) Math.log10(x)));  // aegajalt tundub see olevat
                                                                                // aeglasem kui stringi töötlus.
                                                                                // Ühesõnaga raske öelda kumb kiirem.
    }


    /**
     * Kontrollitakse kas sisend arv sisaldab paarisarvlist numbrit või numbrit 5.
     * Neid numbrit sisaldav arv ei saa moodustada algarvuringi.
     * <p>
     * Rakendamine paarisarvulise numbri sisalduse kontroliks: sisaldabEbasobivatArvu(x).
     * @param x Antud täisarvuline arv.
     * @return Tõeväärtus, mis näitab kas sisend arv sisaldab ebasobivat numbrit.
     */
    public static boolean sisaldabEbasobivatNumbrit(int x){
        x = x / 10; // jagab kümnega sest arvu viimast numbrit pole mõtet kontrollida,
                    // kuna tegevus toimub paaritute numbritega.
        while (x > 0) {
            if (x % 2 == 0) {
                return true;
            } else if ( x % 5 == 0) {
                return true;
            }
            x = x / 10;
        }
        return false;
    }

}//klass AlgarvuRing
