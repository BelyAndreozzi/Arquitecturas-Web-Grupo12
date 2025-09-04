package TP1.DAO;

import TP1.Entities.Cliente;
import TP1.Entities.Factura;

import java.util.List;

public interface FacturaDAO extends CRUD_DAO<Factura, Integer> {
    //Aca se implementarían funciones especificas
    List<Factura> getFacturasPorCliente(int idCliente);
    double getTotalFactura(int idFactura);



}
