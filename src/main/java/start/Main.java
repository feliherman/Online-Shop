package start;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import connection.ConnectionFactory;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.Order;
import model.Product;

import presentation.FileParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {//args[0]
        /*System.out.println("DA");
        ConnectionFactory con = new ConnectionFactory();
        con.getConnection();
        Client c = new Client("Pop Marius", "Rogoz 225");
        Client c1 = new Client("Santa Darius", "Lapus 100");
        ClientBLL client = new ClientBLL();
        System.out.println(client.insertClient(c));
        System.out.println(client.insertClient(c1));
        client.selectClient();
        //client.deleteClient(c);
        client.selectClient();
        //System.out.println("Clientul are id " + client.findClientByName(c1.getName()));
        Product p = new Product("apple",20,1);
        Product p1 = new Product("lemon",10,5);
        ProductBLL prod = new ProductBLL();
        prod.insertProduct(p);
        prod.selectProduct();
        prod.insertProduct(p1);
        prod.selectProduct();
        prod.insertProduct(p);
        prod.selectProduct();
        prod.deleteProduct("apple");
        prod.selectProduct();
        prod.deleteProduct("lemon");
        prod.selectProduct();
        prod.insertProduct(p1);
        prod.selectProduct();
        prod.insertProduct(p);
        prod.selectProduct();
        //System.out.println("Produsul are id " + prod.findProductByName(p1.getName()));

        OrderBLL ord = new OrderBLL();
        System.out.println("CLIENTUL " + c.getName() + " ARE ID : " + client.findClientByName(c.getName()));
        System.out.println("PRODUSUL " + p.getName() + " ARE ID : " + prod.findProductByName(p.getName()));
        ord.insertOrder(client.findClientByName(c.getName()),prod.findProductByName(p.getName()),10);
        ord.selectOrder();
        ord.insertOrder(client.findClientByName(c1.getName()),prod.findProductByName(p1.getName()),3);
        ord.selectOrder();*/
        FileParser fp = new FileParser();
        fp.parse(args[0]);//daca rulezi cu args[0] nu merge, din jar, si daca rulezi cu path ul direct merge, din intellij



    }
}

