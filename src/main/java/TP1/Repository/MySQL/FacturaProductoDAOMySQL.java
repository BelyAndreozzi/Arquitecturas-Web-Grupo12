package TP1.Repository.MySQL;

import TP1.DAO.FacturaProductoDAO;
import TP1.Entities.FacturaProducto;

import java.util.List;

public class FacturaProductoDAOMySQL implements FacturaProductoDAO {
    @Override
    public void insertar(FacturaProducto facturaProducto) {   }

    @Override
    public boolean actualizar(FacturaProducto facturaProducto) {
        return false;
    }

    @Override
    public boolean borrar(Integer id) {
        return false;
    }

    @Override
    public FacturaProducto obtener(Integer id) {
        return null;
    }

    @Override
    public List<FacturaProducto> obtenerTodos() {
        return List.of();
    }
}
