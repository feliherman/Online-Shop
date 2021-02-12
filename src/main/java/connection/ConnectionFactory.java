package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.*;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "root";
    private static final String PASS = "nicusorstanciu99";
    private static Connection con;
    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     *
     * @return conexiunea
     */
    public static Connection getConnection(){
        try {


            Class.forName(DRIVER);

            con= DriverManager.getConnection(DBURL,USER,PASS);

            //System.out.println("Conectat la baza de date!");
            return con;

        }
        catch(Exception e){

            System.out.println(e);
            return null;
        }
    }
}
