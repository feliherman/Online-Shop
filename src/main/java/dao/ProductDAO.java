package dao;
import bll.ProductBLL;
import com.itextpdf.text.pdf.PdfPTable;
import connection.ConnectionFactory;
import model.Client;
import model.Product;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;
public class ProductDAO {
    private final static String insertQuery = "INSERT INTO product (name, quantity, price) " + " VALUES (?,?,?)";
    private final static String resetIncrement = "ALTER TABLE product auto_increment = 1";
    private final static String selectQuery = "SELECT * from product";
    private final static String deleteQuery = "DELETE FROM  product  where name = ? ";
    private final static String findQuery = "SELECT id from product where name = ?";
    private final static String findQueryQuant = "SELECT quantity from product where id = ?";
    private final static String findQueryPrice = "SELECT price from product where id = ?";
    private final static String updateQuery = "UPDATE product set quantity =(quantity + ?) , price = ? where id = ?";
    private final static String selectNameProd = "SELECT name from product where id = ?";

    /**
     *
     * @param id id ul produsului
     * @param quantity cantitatea produsului care trebuie facuta update
     * @param price pretul produslui
     * @param name denumirea produslui
     * @return 1 daca s a facut update 0 daca nu
     */
    public int updateProduct(int id, int quantity, float price,String name)
    {
        PreparedStatement updateStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
            try{
            updateStatement = con.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1,quantity);
            updateStatement.setFloat(2,price);
            int aux = findProductByName(name);
            updateStatement.setInt(3,aux);
            System.out.println(" IN UPDATEEE!!!!O sa se faca update cu : " + quantity  + "    "+ price + "    " +  aux);
            updateStatement.executeUpdate();
            return 1;
        }catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
    }

    /**
     *
     * @param p produsul care trebuie inserat in baza de date
     * @return id ul la care a fost introdus sau 0 daca o dat fail
     */
    public int insertProduct(Product p){
        PreparedStatement insertStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        try {

                insertStatement = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setString(1, p.getName());
                insertStatement.setInt(2, p.getQuantity());
                insertStatement.setFloat(3, p.getPrice());
                insertStatement.executeUpdate();
                result = insertStatement.getGeneratedKeys();
                if (result.next())
                    return result.getInt(1);

            }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    /**
     *
     * @param id id ul produsuului al caruia trebuie sa gasim cantitatea
     * @return cantitatea produsului cu id ul transmis ca parametru
     */
    public int findProductQuantById(int id)
    {
        PreparedStatement findStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        try {
            findStatement = con.prepareStatement(findQueryQuant, Statement.RETURN_GENERATED_KEYS);
            findStatement.setInt(1,id);
            findStatement.execute();
            result =findStatement.getResultSet();
            if(result.next())
                return result.getInt("quantity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *
     * @param id id ul produsului la care dorim sa ii gasim pretul
     * @return pretul produsului cu id ul transis ca param
     */
    public float findProductPriceById(int id)
    {
        PreparedStatement findStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        try {
            findStatement = con.prepareStatement(findQueryPrice, Statement.RETURN_GENERATED_KEYS);
            findStatement.setInt(1,id);
            findStatement.execute();
            result =findStatement.getResultSet();
            if(result.next())
                return result.getFloat("price");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0f;
    }

    /**
     *
     * @param s numele produsului, dorim sa ii gasim id ul
     * @return id ul produsului cu numele din param sau 0
     */
    public int findProductByName(String s){
        PreparedStatement findStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        try {
            findStatement = con.prepareStatement(findQuery, Statement.RETURN_GENERATED_KEYS);
            findStatement.setString(1,s);
            findStatement.execute();
            result =findStatement.getResultSet();
            if(result.next())
                return result.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *
     * @return tabelul care o sa fie inserat in pdf
     */
    public PdfPTable selectProduct(){
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
                text.addCell(result.getString("name"));
                text.addCell(result.getString("quantity"));
                text.addCell(result.getString("price"));
                System.out.println("ID : " + result.getString("id")+" Nume : " + result.getString("name") + " Quantity : " + result.getInt("quantity") + " Price : " + result.getFloat("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return text;
    }

    /**
     *
     * @return numarul de linii din tabelul product, folosit pentru reset increment
     */
    public int selectProductNoPrint(){
        PreparedStatement selectStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        int nr=0;
        try {
            selectStatement = con.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);

            selectStatement.execute();
            result =selectStatement.getResultSet();
            while(result.next())
            {
                nr++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nr;

    }

    /**
     *
     * @param id id ul produsului, dorim sa ii gasim numele
     * @return numele produsului cu id ul de mai sus
     */
    public String findProdById(int id)
    {
        PreparedStatement findStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        try {
            findStatement = con.prepareStatement(selectNameProd, Statement.RETURN_GENERATED_KEYS);
            findStatement.setInt(1,id);
            findStatement.execute();
            result =findStatement.getResultSet();
            if(result.next())
                return result.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "NU";
    }

    /**
     *
     * @param nume numele al carui produs dorim sa il stergem
     * @return id ul care a fost sters sau 0 in caz de esec
     */
    public int deleteProduct(String nume)
    {
        PreparedStatement deleteStatement = null;
        PreparedStatement incrementResetStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        int aux=0;
        try {
            deleteStatement = con.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1,nume);
            deleteStatement.execute();
            result =deleteStatement.getGeneratedKeys();
            if(result.next())
            {
                aux=result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(selectProductNoPrint() == 0)
        {
            try {
                incrementResetStatement = con.prepareStatement(resetIncrement, Statement.RETURN_GENERATED_KEYS);
                incrementResetStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return aux;
    }


}
