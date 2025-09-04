package TP1.Repository.MySQL;


import TP1.DAO.ClienteDAO;
import TP1.DAO.FacturaDAO;
import TP1.DAO.FacturaProductoDAO;
import TP1.DAO.ProductoDAO;
import TP1.Factory.ConnectionManagerMYSQL;
import TP1.Factory.FactoryDAO;

public class FactoryDAOMySQL extends FactoryDAO {

    public ClienteDAO createClienteDAO() {
        return new ClienteDAOMySQL(ConnectionManagerMYSQL.getInstance().getConnection());
    }
    public  FacturaDAO createFacturaDAO() {
        return new FacturaDAOMySQL(ConnectionManagerMYSQL.getInstance().getConnection());
    }
    public  ProductoDAO createProductoDAO() {
        return new ProductoDAOMySQL(ConnectionManagerMYSQL.getInstance().getConnection());
    }
    public  FacturaProductoDAO createFacturaProductoDAO() {
        return new FacturaProductoDAOMySQL(ConnectionManagerMYSQL.getInstance().getConnection());
    }

}
