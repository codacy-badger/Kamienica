//package kamienica.core;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import kamienica.model.PaymentAbstract;
//import kamienica.model.Tenant;
//
///**
//
// */
//public class PdfGenerator {
//
//	public void drukujPdf(String doDruku, Tenant najemca) {
//
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		Date date = new Date();
//
//		String nazwa = najemca.getFullName() + " " + dateFormat.format(date) + ".pdf";
//		Document document = new Document();
//
//		try {
//			PdfWriter.getInstance(document, new FileOutputStream(nazwa));
//			// singletone - konstrukcja w gdize jest jeden obiekt
//			document.open();
//			document.add(new Paragraph(doDruku));
//			document.close(); // no need to close PDFwriter?
//
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//	}
//
////	public static void generujOplate(WartoscOplaty oplata) {
////		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
////		Date date = new Date();
////
////		String doDruku="Data: " + dateFormat.format(date);
////		String doDruku2= "" +oplata.getNajemca().getFullName()+ " ma do zapłaty: ";
////		String doDruku3= "";
////		if (oplata.getOplataZaEnrgie() !=0 ) {
////			doDruku3 += "\nEnergia: " + oplata.getOplataZaEnrgie();
////		}
////		if (oplata.getOplataZaWode() !=0 ) {
////			doDruku3 += "\nWoda: " +oplata.getOplataZaWode();
////		}
////		if (oplata.getOplataZaGaz() !=0 ) {
////			doDruku3 += "\nGaz: " + oplata.getOplataZaGaz();
////		}
////		String nazwa = oplata.getNajemca().getFullName() + " " + dateFormat.format(date) + ".pdf";
////		Document document = new Document();
////
////		try {
////			PdfWriter.getInstance(document, new FileOutputStream(nazwa));
////			// singletone - konstrukcja w gdize jest jeden obiekt
////			document.open();
////			document.add(new Paragraph(doDruku));
////			document.add(new Paragraph(doDruku2));
////			document.add(new Paragraph(doDruku3));
////			document.close(); // no need to close PDFwriter?
////
////		} catch (DocumentException e) {
////			e.printStackTrace();
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
////		}
////	}
//	
//	public static void generujOplate(PaymentAbstract oplata) {
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		Date date = new Date();
//
//		String doDruku="Data: " + dateFormat.format(oplata.getPaymentDate());
//		String doDruku2= oplata.getTenant().getFullName()+ " ma do zapłaty: ";
//		String doDruku3= String.valueOf(oplata.getPaymentAmount());
//		
//		String nazwa = oplata.getTenant().getFullName() + " " + dateFormat.format(date) + ".pdf";
//		Document document = new Document();
//
//		try {
//			PdfWriter.getInstance(document, new FileOutputStream(nazwa));
//			// singletone - konstrukcja w gdize jest jeden obiekt
//			document.open();
//			document.add(new Paragraph(doDruku));
//			document.add(new Paragraph(doDruku2));
//			document.add(new Paragraph(doDruku3));
//			document.close(); // no need to close PDFwriter?
//
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static Document generujOplate3(PaymentAbstract oplata) {
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		Date date = new Date();
//
//		String doDruku="Data: " + dateFormat.format(oplata.getPaymentDate());
//		String doDruku2= oplata.getTenant().getFullName()+ " ma do zapłaty: ";
//		String doDruku3= String.valueOf(oplata.getPaymentAmount());
//		
//		String nazwa = oplata.getTenant().getFullName() + " " + dateFormat.format(date) + ".pdf";
//		Document document = new Document();
//
//		try {
//			PdfWriter.getInstance(document, new FileOutputStream(nazwa));
//			// singletone - konstrukcja w gdize jest jeden obiekt
//			document.open();
//			document.add(new Paragraph(doDruku));
//			document.add(new Paragraph(doDruku2));
//			document.add(new Paragraph(doDruku3));
//			document.close(); // no need to close PDFwriter?
//
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		return document;
//	}
//}