package TP1.Factory;
import TP1.DAO.ClienteDAO;
import TP1.DAO.FacturaDAO;
import TP1.DAO.FacturaProductoDAO;
import TP1.DAO.ProductoDAO;
import TP1.Repository.MySQL.FactoryDAOMySQL;

public abstract class FactoryDAO {
    private static volatile FactoryDAO instance;

    public static FactoryDAO getInstance(DBType type) {
        if (instance == null) {
            synchronized (FactoryDAO.class) {
                if (instance == null) {
                    switch (type) {
                        case MYSQL:
                            instance = new FactoryDAOMySQL();
                            break;
                        //case DERBY:
                            //instance = new ConnectionManagerPOSTGRESQL();
                            //break;
                        default:
                            throw new IllegalArgumentException("No se reconoce el tipo de base de datos");
                    }
                }
            }
        }
        return instance;
    }

    /*public static FactoryDAO getInstance(){
        String v = System.getenv("MYSQL");
        DBType type = DBType.valueOf((v.toUpperCase()));
        return getInstance(type);
    }*/

    /*public static FactoryDAO getInstance() {
        String v = System.getenv("DB_TYPE");
        if (v == null) {
            v = "MYSQL"; // valor por defecto
        }
        DBType type = DBType.valueOf(v.toUpperCase());
        return getInstance(type);
    }*/

    //Factory methods

    public abstract ClienteDAO createClienteDAO();
    public abstract FacturaDAO createFacturaDAO();
    public abstract ProductoDAO createProductoDAO();
    public abstract FacturaProductoDAO createFacturaProductoDAO();

}
