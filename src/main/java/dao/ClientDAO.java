package dao;

import com.itextpdf.text.pdf.PdfPTable;
import connection.ConnectionFactory;
import model.Client;

import javax.swing.text.Element;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

public class ClientDAO {
    private final static String selectName = "SELECT name from clients where id = ?";
        private final static String insertQuery = "INSERT INTO clients (name, address) " + " VALUES (?,?)";
        private final static String selectQuery = "SELECT * from clients";
        private final static String deleteQuery = "DELETE FROM  clients where name = ? and address = ?";
    private final static String findQuery = "SELECT id from clients where name = ?";
    private final static String resetIncrement = "ALTER TABLE product auto_increment = 1";

    /**
     *
     * @param c clientul care urmeaza sa fie inserat
     * @return int returneaza id ul la care a fost inserat
     */
        public int insertClient(Client c){
            PreparedStatement insertStatement = null;
            Connection con = ConnectionFactory.getConnection();
            ResultSet result = null;
            try {
                insertStatement = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setString(1, c.getName());
                insertStatement.setString(2, c.getAddress());
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
     * @return lista cu toti clientii sub forma de tabel pentru a putea fi inserat in PDF
     */
    public PdfPTable selectClient(){
        PreparedStatement selectStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;

        PdfPTable text = new PdfPTable(3);
        try {
            selectStatement = con.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);
            selectStatement.execute();
            result =selectStatement.getResultSet();

            while(result.next())
            {
                text.addCell(result.getString("id"));
                text.addCell(result.getString("name"));
                text.addCell(result.getString("address"));
                //System.out.println("ID : " + result.getString("id")+" Nume : " + result.getString("name") + " Address : " + result.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     *
     * @param s numele clientului pe care dorim sa il gasim(id)
     * @return id ul clientului cu numele s
     */
    public int findClientByName(String s){
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
     * @return numarul de clienti din baza de date, folosim la auto increment reset
     */
    public int selectClientNoPrint(){
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
     * @param c clientul care trbeuie sters
     * @return id ul clientului sters sau 0 in caz de esec
     */
    public int deleteClient(Client c){
        PreparedStatement deleteStatement = null;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement incrementResetStatement = null;
        ResultSet result = null;
        int aux=0;
        try {
            deleteStatement = con.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1,c.getName());
            deleteStatement.setString(2,c.getAddress());
            deleteStatement.execute();
            result =deleteStatement.getGeneratedKeys();
            if(result.next())
            {
               aux=result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(selectClientNoPrint() == 0)
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


    /**
     *
     * @param id id ul dupa care o sa cautam clientul
     * @return numele clientului sau NU daca acesta nu a fost gasit
     */
    public String findClientById(int id)
    {
        PreparedStatement findStatement = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet result = null;
        try {
            findStatement = con.prepareStatement(selectName, Statement.RETURN_GENERATED_KEYS);
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

}
