package kamienica.core;

public abstract class ManagerMedia {

	
//	public  static ArrayList<WartoscZuzycia> wyliczZuzycieDlaElementow(
//			ArrayList<Mieszkanie> mieszkanie, ArrayList<OdczytAbstract> staryOdczyt,
//			ArrayList<OdczytAbstract> nowyOdczyt) {
//		ArrayList<WartoscZuzycia> generoWanaArrayListaWartosciZuzycia = new ArrayList<WartoscZuzycia>();
//		for (Mieszkanie m : mieszkanie) {
//			WartoscZuzycia tmp = new WartoscZuzycia();
//			tmp.setOpisZuzycia("Zuzycie calkowite za: " + m.getOpisMieszkania());
//			tmp.setMieszkanie(m);
//			double sumaPoprzednichOdczytowZaElement = 0;
//			double sumaNowychOdczytowZaElement = 0;
//
//			for (int indexOdczytow = 0; indexOdczytow < staryOdczyt.size(); indexOdczytow++) {
//				if (nowyOdczyt.get(indexOdczytow).getLicznik().getMieszkanie().getNrMiekszania() == m
//						.getNrMiekszania()) {
//					sumaNowychOdczytowZaElement = sumaNowychOdczytowZaElement
//							+ nowyOdczyt.get(indexOdczytow).getWartoscOdczytu();
//				}
//				if (staryOdczyt.get(indexOdczytow).getLicznik().getMieszkanie().getNrMiekszania() == m
//						.getNrMiekszania()) {
//					sumaPoprzednichOdczytowZaElement = sumaPoprzednichOdczytowZaElement
//							+ staryOdczyt.get(indexOdczytow).getWartoscOdczytu();
//				}
//			}
//			double zuzycie = sumaNowychOdczytowZaElement - sumaPoprzednichOdczytowZaElement;
//			tmp.setZuzycie(zuzycie);
//			tmp.setJednostka(nowyOdczyt.get(0).getLicznik().getJednostka());
//			tmp.setLiczbaDniPomiedzyOdczytami(Days.daysBetween(new DateTime(staryOdczyt.get(0).getDataOdczytu()),
//					new DateTime(nowyOdczyt.get(0).getDataOdczytu())).getDays());
//			generoWanaArrayListaWartosciZuzycia.add(tmp);
//		}
//
//		return generoWanaArrayListaWartosciZuzycia;
//
//	}
	
}
