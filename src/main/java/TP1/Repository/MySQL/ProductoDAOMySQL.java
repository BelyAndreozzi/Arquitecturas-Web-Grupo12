package TP1.Repository.MySQL;

import TP1.DAO.ProductoDAO;
import TP1.Entities.Producto;

import java.util.List;

public class ProductoDAOMySQL implements ProductoDAO {
    @Override
    public Producto productoQueMasRecaudo() throws Exception {
        return null;
    }

    @Override
    public void insert(Producto entity) {

    }

    @Override
    public boolean update(Producto entity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Producto get(Integer id) {
        return null;
    }

    @Override
    public List<Producto> getAll() {
        return List.of();
    }
}
