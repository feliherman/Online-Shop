package bll;

import com.itextpdf.text.pdf.PdfPTable;
import dao.ClientDAO;
import model.Client;

public class ClientBLL {
    private ClientDAO c = new ClientDAO();

    /**
     *
     * @param c1 clientul care trebuie inserat
     * @return id ul la care a fost introdus sau 0
     */
    public int insertClient(Client c1)
    {
       return c.insertClient(c1);
    }

    /**
     *
     * @return clientii sub forma de tabel
     */
    public PdfPTable selectClient(){
        return c.selectClient();
    }

    /**
     *
     * @param c1 clientul care trbeuie sters
     * @return id ul de la care a fost sters sau 0
     */
    public int deleteClient(Client c1)
    {
        return c.deleteClient(c1);
    }

    /**
     *
     * @param nume numele dupa care o sa cautam client
     * @return id ul
     */
    public int findClientByName(String nume){
        return c.findClientByName(nume);
    }
}
