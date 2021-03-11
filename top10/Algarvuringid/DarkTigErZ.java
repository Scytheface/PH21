/**
 * Programmeerimisharjutused. LTAT.03.007
 * 2020/2021 kevadsemester
 * <p>
 * Kodutöö nr 1a
 * Teema: Algarvuringide leidmine
 * Autor: DarkTigErZ
 */

class AlgarvuRing {
	/**
	 * @param arv Antud algarv.
	 * @param log Eelnevalt väljaarvutatud arv, mis tähistab mitmekohaline arv parameetris arv on.
	 * @return Arv, kus parameetri arv viimane number on tõestetud ta esimeseks numbriks.
	 */
	public static int viimaneEsimeseks(int arv, int log) {
		// teistes kommentaarides tähendab 'arvu keeramine' just selle meetodi läbimist
		return arv / 10 + (arv % 10) * (int) Math.pow(10, log);
	}//viimaneEsimeseks


	/**
	 * @param arv Kontrollitav arv.
	 * @return Tõeväärtus vastavalt sellele, kas tegemist on algarvuga (true) või mitte (false).
	 */
	public static boolean algarvuKontrollija(int arv) {
		// kuna me siia meetodisse paarisarve ei saada, pole vaja 2-ga jagumist kontrollida
		if (arv % 3 == 0) return false;
		for (int i = 6; i < Math.sqrt(arv) + 1; i += 6) {
			if (arv % (i - 1) == 0 || arv % (i + 1) == 0) { // kontrollime arvu jagumist kõigi kolmest suuremate ja arvu enda ruutjuurest väiksemate paaritute kolmega mittejaguvate arvudega
				return false;
			}
		}
		return true;
	}//algarvuKontrollija


	public static void main(String[] args) {
		System.out.println("Kodutöö nr 1a.                          Programmi väljund");
		System.out.println("=========================================================:\n");
		algarvuRing(1000000);
		System.out.println("\n=========================================================.");
		System.out.println("DarkTigErZ                       " + new java.sql.Timestamp(System.currentTimeMillis()));
	}//main


	/**
	 * @param a Antud lähtekoht.
	 */
	public static void algarvuRing(int a) {
		System.out.println("Antud lähtekoht: " + a);
		System.out.println("Leitud algarvuringid:");
		int korda = 0;
		a -= 2; // välistame lähtekoha
		if (a % 2 == 0) a += 1; // teeme kindlaks, et me tegeleme paaritute arvudega
		algus:
		for (int i = a; i > 9; i -= 2) { // käime läbi paaritud arvud a-st 11-ni
			int logaritm = (int) Math.log10(i);
			int esimene = i / (int) Math.pow(10, logaritm);
			for (int j = 1; j < logaritm + 1; j++) { // käime läbi käesoleva arvu kõik numbrid
				int aste = (int) (Math.pow(10, j));
				int ajutine = i / aste;
				if (ajutine % 2 == 0) { // kontrollime, et ega ükski neist paarisarv pole
					i -= (i % aste) - 1; // lahutame arvust i vajaliku osa, et ta ei sisaldaks enam paarisarvulist numbrit
					continue algus;
				} else if (ajutine % 10 > esimene) { // kontrollime ega mõni number pole suurem kui arvu esimene number
					int yhed = 0;
					for (int k = 0; k < j + 1; k++) yhed += Math.pow(10, k);
					i = ajutine / 10 * aste * 10 + esimene * yhed + 2; // vähendame i väärtust nii palju, et ta oleks järgmine suurim võimalik arv, kus ükski number poleks esimesest numbrist suurem (nt 731931st saab 731777+2)
					continue algus;
				}
			}
			// kontrollida et esimene number on suurem võrdne kõigi teiste numbritega
			if (algarvuKontrollija(i)) {
				int keeratud = i;
				for (int j = 0; j < logaritm; j++) { // leiame mitmekohalise arvuga me tegutseme ja keerame arvu vastav arv kordi
					keeratud = viimaneEsimeseks(keeratud, logaritm);
					if (!algarvuKontrollija(keeratud) || keeratud > i) { // keeratud arv peab olema algarv ja ei tohi olla suurem kui algne keeramata arv (i)
						continue algus;
					}
				}
				System.out.println(i); // väljastame leitud arvu millest algab algarvuring
				if (5 == ++korda) break; // kui oleme väljastanud 5 arvu lõpetame tsükli töö
			}
		}
	}//algarvuRing

}//klass AlgarvuRing
