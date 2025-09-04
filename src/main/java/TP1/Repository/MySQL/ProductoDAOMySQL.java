package TP1.Repository.MySQL;

import TP1.DAO.ProductoDAO;
import TP1.Entities.Producto;

import java.util.List;

public class ProductoDAOMySQL implements ProductoDAO {
    @Override
    public Producto productoQueMasRecaudo() throws Exception {
        /* NO HAGAN ESTE ASI LO HACEMOS TODOS JUNTOS <3 */
        return null;
    }

    @Override
    public void insertar(Producto producto) {

    }

    @Override
    public boolean actualizar(Producto producto) {
        return false;
    }

    @Override
    public boolean borrar(Integer id) {
        return false;
    }

    @Override
    public Producto obtener(Integer id) {
        return null;
    }

    @Override
    public List<Producto> obtenerTodos() {
        return List.of();
    }
}
