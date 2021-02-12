package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GeneratePDF {
    private OrderDAO o = new OrderDAO();
    private ClientDAO c = new ClientDAO();
    private ProductDAO p = new ProductDAO();
    /**
     * @param nr reprezinta numarul al catelea pdf este
     * @param t reprezinta tabelul care trebuie inserat in pdf cu eroare
     *
     */
    public void generateErrorOrder(int nr,PdfPTable t){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Assignment 3." + nr + ".pdf"));
        } catch (
                DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        try {
            document.add(t);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }

    /**
     *
     * @param nr e numarul al catelea PDF o sa fie generat
     */
    public void  generateClient(int nr) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Assignment 3." + nr + ".pdf"));
        } catch (
                DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        try {
            document.add(c.selectClient());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }
    /**
     * @param nr e numarul al catelea PDF o sa fie generat
     */
    public void  generateOrder(int nr) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Assignment 3." + nr + ".pdf"));
        } catch (
                DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        try {
            document.add(o.selectOrder());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }
    /**
     * @param nr e al catelea PDF o sa fie generat
     *
     * */
    public void  generateProduct(int nr) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Assignment 3." + nr + ".pdf"));
        } catch (
                DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        try {
            document.add(p.selectProduct());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }
}
