package bll;

import dao.ProductDAO;
import model.Product;

public class ProductBLL {

    private ProductDAO p = new ProductDAO();

    /**
     *
     * @param q produsul care trebuie introdus
     */
    public void insertProduct(Product q)
    {

        if(p.findProductByName(q.getName()) != 0)
        {
            //System.out.println("AM INTRAT LA UPDATE!!");
            p.updateProduct(q.getId(),q.getQuantity(),q.getPrice(),q.getName());
        }
        else
        {

            p.insertProduct(q);
        }
    }

    /**
     *
     * @param nume numele produsului care trbeuie sters
     */
    public void deleteProduct(String nume){
        p.deleteProduct(nume);
    }


    public void selectProduct(){
        p.selectProduct();
    }

    /**
     *
     * @param s numele produsului
     * @return id ul produsului
     */
    public int findProductByName(String s)
    {
        return p.findProductByName(s);
    }

}
