package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;

import java.io.*;
import java.util.Scanner;

public class FileParser {
    File file ;
    ClientBLL c = new ClientBLL();
    OrderBLL o = new OrderBLL();
    ProductBLL p = new ProductBLL();
    GeneratePDF g = new GeneratePDF();
    OrderDAO o1 = new OrderDAO();
    ProductDAO p3 = new ProductDAO();
    private int nr =10;

    /**
     * functia o sa parseze fisierul text in functie de ce informatii o sa se gaseasca, insert, delete, order sau raport
     */
    public void parse(String fileName) {
        try {
            file=new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;int pres=0;Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                pres=0;String s = sc.next();
                if(s.compareToIgnoreCase("insert") == 0) {
                    processInsert(s+sc.nextLine());
                    pres=1;
                }
                if(s.compareToIgnoreCase("delete") == 0) {
                    processDelete(s+sc.nextLine());
                    pres=1;
                }
                if(s.compareToIgnoreCase("order:") == 0) {
                    processOrder(s+sc.nextLine());
                    pres=1;
                }
               if(s.compareToIgnoreCase("report")==0){
                   setNr(getNr() + 1);
                   processReport(s+sc.nextLine());
                   pres=1;
               }
                if(pres==0)
                    sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); }
        p.selectProduct();
        o.selectOrder();
    }

    /**
     * @param s este Stringul pe care il primim din fisier si il folosim pentru a extrage datele si a vedea in ce caz suntem
     * */
    public void processReport(String s){
        System.out.println(s);
        String[] result = s.split(" ");
        if(result[1].compareToIgnoreCase("client")==0){
            g.generateClient(getNr());
        }
        if(result[1].compareToIgnoreCase("order") == 0){
            g.generateOrder(getNr());
        }
        if(result[1].compareToIgnoreCase("product") == 0){
            g.generateProduct(getNr());
        }
    }

    /**
     * @param s este Stringul pe care il primim din fisier si il folosim pentru a extrage datele si a vedea in ce caz suntem
     * */
    public void processInsert(String s){
        //System.out.println(s);
        String[] result = s.split(" ");
        if(result[1].compareToIgnoreCase("client:")==0){
            String nume = result[2] + " " + result[3].substring(0,result[3].length() - 1);
            String adresa = result[4];
            Client c1 = new Client(nume,adresa);
            System.out.println("A FOST INTRODUS ID : " + c.insertClient(c1));
        }
        if(result[1].compareToIgnoreCase("product:")==0){
            String nume = result[2].substring(0,result[2].length() - 1);
            int q = Integer.parseInt(result[3].substring(0,result[3].length() - 1));
            float price = Float.parseFloat(result[4]);
            Product p1 = new Product(nume,q,price);
            System.out.println(p1.toString());
            p.insertProduct(p1);

        }
    }

    /**
     * @param s este Stringul pe care il primim din fisier si il folosim pentru a extrage datele si a vedea in ce caz suntem
    * */
    public void processDelete(String s){
        System.out.println(s);
        String[] result = s.split(" ");
        if(result[1].compareToIgnoreCase("client:")==0){
            String nume = result[2] + " " + result[3].substring(0,result[3].length() - 1);
            String adresa = result[4];
            Client c1 = new Client(nume,adresa);
            c.deleteClient(c1);
        }
        if(result[1].compareToIgnoreCase("product:")==0){
            String nume = result[2];
            p.deleteProduct(nume);

        }
    }

    /**
     * @param s este Stringul pe care il primim din fisier si il folosim pentru a extrage datele si a vedea in ce caz suntem
     */
    public void processOrder(String s)
    {
        System.out.println(s);
        String[] result = s.split(" ");
        String nume = result[1] + " " + result[2].substring(0,result[2].length() -1 );
        String numeProd = result[3].substring(0,result[3].length() - 1);
        int q = Integer.parseInt(result[4]);
        System.out.println("TREBE SA INTRODUCEM PE " + nume + "CU ID : " + c.findClientByName(nume) + "SI PRODUSUL " + numeProd + " CU ID : " + p.findProductByName(numeProd) + " CANT " +q);
        if(o.insertOrder(c.findClientByName(nume),p.findProductByName(numeProd),q)!=0){}
        else
        {
            setNr(getNr() + 1);
            g.generateErrorOrder(getNr(),o1.selectOrderError(nume,numeProd,q));

        }

    }


    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }
}
