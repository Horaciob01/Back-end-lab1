package back.end.lab1;

import back.end.lab1.model.Kind;
import back.end.lab1.model.KindManager;
import back.end.lab1.model.Product;
import back.end.lab1.model.ProductManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Horacio
 */
public class BackEndLab1 {

    public static void main(String[] args) {
        try {
            Product p = new Product("x5", "insertado", 0, 0, new Kind(1, "", 0));
            Product p1 = new Product("x7", "Marco", 0, 0, new Kind(1, "", 0));
            ProductManager.getInstance().getAll();
            KindManager.getInstance().getAll();
            p.setPrice(400);
            //ProductManager.getInstance().createOrUpdate(p1,ProductManager.CREATE);
            //ProductManager.getInstance().delete(p);
            ProductManager.getInstance().read("x2");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BackEndLab1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BackEndLab1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BackEndLab1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
