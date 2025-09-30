package TP1.DAO;

import TP1.Entities.Producto;
import java.util.List;
import java.util.Map;

public interface ProductoDAO  extends CRUD_DAO<Producto, Integer> {
    //Se define “recaudación” como cantidad de productos vendidos multiplicado por su valor.
    Producto productoQueMasRecaudo() throws Exception;
    List<Map<String, Object>> productosOrdenadosPorFacturacion() throws Exception;
}
