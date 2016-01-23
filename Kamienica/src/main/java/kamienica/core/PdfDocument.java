//package kamienica.core;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.itextpdf.text.Document;
//
//import kamienica.model.PaymentAbstract;
//import kamienica.model.Tenant;
//
//@Entity
//@Table
//public class PdfDocument {
//
//	@Id
//	@GeneratedValue
//	@Column
//	private int id;
//	@Column
//	@Lob
//	private Document document;
//	@ManyToOne
//	private Tenant najemca;
//	@Column(nullable = false)
//	private Date date;
//
//	public PdfDocument() {
//	}
//
//	@Autowired
//	public PdfDocument(PaymentAbstract oplata, Tenant najemca, Date date) {
//		setDocument(oplata);
//		this.najemca = najemca;
//		this.date = date;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Document getDocument() {
//		return document;
//	}
//
//	public void setDocument(PaymentAbstract oplata) {
//		this.document = PdfGenerator.generujOplate3(oplata);
//
//	}
//
//	public Tenant getNajemca() {
//		return najemca;
//	}
//
//	public void setNajemca(Tenant najemca) {
//		this.najemca = najemca;
//	}
//
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//}
