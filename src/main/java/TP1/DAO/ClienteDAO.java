package TP1.DAO;

import TP1.Entities.Cliente;
import java.util.List;
import java.util.Map;

public interface ClienteDAO extends CRUD_DAO<Cliente, Integer> {
    List<Map<String, Object>> clientesOrdenadosPorFacturacion() throws Exception;
}