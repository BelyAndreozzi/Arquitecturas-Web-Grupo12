package TP1.Repository.MySQL;


import TP1.DAO.ClienteDAO;
import TP1.DAO.FacturaDAO;
import TP1.DAO.FacturaProductoDAO;
import TP1.DAO.ProductoDAO;
import TP1.Factory.ConnectionManagerMYSQL;
import TP1.Factory.FactoryDAO;

public class FactoryDAOMySQL extends FactoryDAO {

    public ClienteDAO createClienteDAO() {
        return ClienteDAOMySQL.getInstance(ConnectionManagerMYSQL.getInstance().getConnection());
    }
    public  FacturaDAO createFacturaDAO() {
        return FacturaDAOMySQL.getInstance(ConnectionManagerMYSQL.getInstance().getConnection());
    }
    public  ProductoDAO createProductoDAO() {
        return ProductoDAOMySQL.getInstance(ConnectionManagerMYSQL.getInstance().getConnection());
    }
    public  FacturaProductoDAO createFacturaProductoDAO() {
        return FacturaProductoDAOMySQL.getInstance(ConnectionManagerMYSQL.getInstance().getConnection());
    }



}
