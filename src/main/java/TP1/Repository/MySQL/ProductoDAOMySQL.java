package TP1.Repository.MySQL;

import TP1.DAO.ProductoDAO;
import TP1.Entities.Cliente;
import TP1.Entities.Producto;

import java.util.List;

public class ProductoDAOMySQL implements ProductoDAO {
    @Override
    public Producto productoQueMasRecaudo() throws Exception {
        return null;
    }

    @Override
    public void insert(Cliente entity) {

    }

    @Override
    public boolean update(Cliente entity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Cliente get(Integer id) {
        return null;
    }

    @Override
    public List<Cliente> getAll() {
        return List.of();
    }
}
