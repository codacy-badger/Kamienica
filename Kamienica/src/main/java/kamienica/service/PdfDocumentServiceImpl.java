//package kamienica.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import kamienica.core.PdfDocument;
//import kamienica.dao.PdfDocumentDao;
//
//@Service
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//public class PdfDocumentServiceImpl implements PdfDocumentService {
//
//	@Autowired
//	PdfDocumentDao pdfDocumentDao;
//
//	@Override
//	public void savePdfDocument(PdfDocument document) {
//		pdfDocumentDao.savePdfDocument(document);
//		
//	}
//
//	@Override
//	public List<PdfDocument> getPdfDocument() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deletePdfDocument(int id) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void updatePdfDocument(PdfDocument document) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public PdfDocument getPdfDocumentById(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
