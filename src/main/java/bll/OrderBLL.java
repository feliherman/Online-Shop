package bll;

import dao.OrderDAO;
import dao.ProductDAO;

public class OrderBLL {
    private OrderDAO o = new OrderDAO();
    private ProductDAO p1 = new ProductDAO();

    /**
     *
     * @param c id ul clientului
     * @param p id ul produsului
     * @param q cantitatea care trebuie introdusa in comanda
     * @return 1 daca s a facut inserarea, 0 daca nu
     */
    public int insertOrder(int c, int p, int q){
        if(p1.findProductQuantById(p) >= q)
            {
                o.insertOrder(c,p,q);
                //System.out.println("O SA SE FACA UPDATE LA ID  " + p + " CANTITATEA "+ (p1.findProductQuantById(p) - q) + " PRETU: " + p1.findProductPriceById(p) + "NUMELE : " + p1.findProdById(p));
                p1.updateProduct(p,-q,p1.findProductPriceById(p),p1.findProdById(p));

                return 1;
            }
        return 0;
    }
    public void selectOrder(){
        o.selectOrder();
    }
}
