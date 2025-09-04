package TP1.DAO;

import TP1.Entities.Cliente;
import TP1.Entities.Producto;

public interface ProductoDAO  extends CRUD_DAO<Producto, Integer> {
    //Se define “recaudación” como cantidad de productos vendidos multiplicado por su valor.
    Producto productoQueMasRecaudo() throws Exception;
}
