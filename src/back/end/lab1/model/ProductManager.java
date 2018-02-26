package back.end.lab1.model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Horacio
 */
public class ProductManager extends OracleConnection {

    private static OracleConnection OC = null;
    private static final String GETALLSTM = "{? = call buscar_productos()}";
    private static final String CREATESTM = "{call insertar_producto(?,?,?,?,?,?)}";
    private static final String UPDATESTM = "{call modificar_producto(?,?,?,?,?,?)}";
    private static final String READSTM = "{? = call buscar_producto(?)}";
    private static final String DELETESTM = "{call eliminar_producto(?)}";
    public static final int CREATE = 0;
    public static final int UPDATE = 1;

    private ProductManager() {
    }

    public static ProductManager getInstance() {
        if (OC == null) {
            OC = new ProductManager();
        }
        return (ProductManager) OC;
    }

    public void createOrUpdate(Product product, int t) throws ClassNotFoundException, SQLException, Exception {
        CallableStatement pstmt;
        connect();
        if (t == CREATE) {
            pstmt = connection.prepareCall(CREATESTM);
        } else if (t == UPDATE) {
            pstmt = connection.prepareCall(UPDATESTM);
        } else {
            throw new Exception("t is not valid option");
        }
        pstmt.setString(1, product.getCode());
        pstmt.setString(2, product.getName());
        pstmt.setInt(3, product.getPrice());
        pstmt.setInt(4, product.getImported());
        pstmt.setInt(5, product.getKind().getId());
        
        if (pstmt.execute()) {
            if (t == CREATE) {
                throw new Exception("failed to insert");
            } else {
                throw new Exception("failed to update");
            }
        }
        pstmt.close();
        disconnect();
    }

    public void delete(Product product) throws ClassNotFoundException, SQLException, Exception {
        CallableStatement pstmt;
        connect();
        pstmt = connection.prepareCall(DELETESTM);
        pstmt.setString(1, product.getCode());
        if (pstmt.execute()) {
            throw new Exception("failed to delete");
        }
        pstmt.close();
        disconnect();
    }

    public Product read(String code) throws ClassNotFoundException, SQLException {
        Product product = null;
        CallableStatement pstmt;
        connect();
        pstmt = connection.prepareCall(READSTM);
        pstmt.registerOutParameter(1, OracleTypes.CURSOR);
        pstmt.setString(2, code);
        pstmt.execute();
        ResultSet rs = (ResultSet) pstmt.getObject(1);
        product = new Product(
                rs.getString("codigo"),
                rs.getString("nombre"),
                rs.getInt("precio"),
                rs.getInt("importado"),
                new Kind(rs.getInt("id"), rs.getString("tipo_nombre"), rs.getInt("porcentaje_impuesto")));
        pstmt.close();
        disconnect();
        return product;
    }

    public ArrayList<Product> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<Product> list = new ArrayList<>();
        CallableStatement pstmt;
        connect();
        pstmt = connection.prepareCall(GETALLSTM);
        pstmt.registerOutParameter(1, OracleTypes.CURSOR);
        pstmt.execute();
        ResultSet rs = (ResultSet) pstmt.getObject(1);
        String as = "";
        while (rs.next()) {
           // as = rs.getString("codigo");
            
            list.add(new Product(
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getInt("precio"),
                    rs.getInt("importado"),
                    new Kind(rs.getInt("id"), rs.getString("nombre"), rs.getInt("porcentaje_impuesto"))
            ));
        }
        pstmt.close();
        disconnect();
        return list;
    }
}
