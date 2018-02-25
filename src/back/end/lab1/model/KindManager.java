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
public class KindManager extends OracleConnection {

    private static OracleConnection OC = null;
    private static final String GETALLSTM = "{? = call buscar_tipos()}";

    private KindManager() {
    }

    public static KindManager getInstance() {
        if (OC == null) {
            OC = new KindManager();
        }
        return (KindManager) OC;
    }

    public ArrayList<Kind> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<Kind> list = new ArrayList<>();
        CallableStatement pstmt;
        connect();
        pstmt = connection.prepareCall(GETALLSTM);
        pstmt.registerOutParameter(1, OracleTypes.CURSOR);
        pstmt.execute();
        ResultSet rs = (ResultSet) pstmt.getObject(1);
        while (rs.next()) {
            list.add(new Kind(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("porcentaje_impuesto")));
        }
        pstmt.close();
        disconnect();
        return list;
    }
}
