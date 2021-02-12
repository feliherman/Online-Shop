package dao;

import com.itextpdf.text.pdf.PdfPTable;
import connection.ConnectionFactory;
import model.Client;

import java.sql.*;

public class OrderDAO {
    private final static String insertQuery = "INSERT INTO mydatabase.order (id_client, id_product, quantity) " + " VALUES (?,?,?) ";
    private final static String selectQuery = "SELECT * from mydatabase.order";
    private final static String selectName = "SELECT name from clients where id = ?";
    ProductDAO pr = new ProductDAO();
    ClientDAO cl = new ClientDAO();

    /**
     *
     * @param idC id ul clientului
     * @param idP id ul produsului
     * @param quant cantitatea produsului pe care vrem sa il inseram
     * @return id ul comenzii inserate sau 0 in caz de esec
     */
    public int insertOrder(int idC, int idP, int quant){
        PreparedStatement insertStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        try {
            insertStatement = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, idC);
            insertStatement.setInt(2, idP);
            insertStatement.setInt(3,quant);
            insertStatement.executeUpdate();
            result =insertStatement.getGeneratedKeys();
            if(result.next())
                return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *
     * @return toate comenzile sumb forma de tabel pentru a genera PDF
     */
    public PdfPTable selectOrder(){
        PreparedStatement selectStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        PdfPTable text = new PdfPTable(4);
        try {
            selectStatement = con.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            selectStatement.execute();
            result =selectStatement.getResultSet();

            while(result.next())
            {
                text.addCell(result.getString("id"));
                text.addCell(cl.findClientById(result.getInt("id_client")));
                text.addCell(pr.findProdById(result.getInt("id_product")));
                text.addCell(result.getString("quantity"));
                //System.out.println("ID : " + result.getString("id")+" id_client : " + result.getString("id_client") + " id_produs : " + result.getString("id_product") + " quantity : " + result.getString("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     *
     * @param nume numele clientului in cazul caruia nu s a putut face comanda
     * @param produs produsul care nu a putut fi inserat
     * @param q cantitatea insuficienta
     * @return tabelul special pentru generarea erorii din pdf
     */
    public PdfPTable selectOrderError(String nume, String produs, int q){
        PreparedStatement selectStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        PdfPTable text = new PdfPTable(4);
        text.addCell(nume);
        text.addCell(produs);
        text.addCell(String.valueOf(q));
        text.addCell("A ESUAT, INSUFICIENTA PRODUSE!");
        //System.out.println("ID : " + result.getString("id")+" id_client : " + result.getString("id_client") + " id_produs : " + result.getString("id_product") + " quantity : " + result.getString("quantity"));
        return text;
    }
}
