package TP1.DAO;

import TP1.Entities.Cliente;

import java.util.ArrayList;

public interface ClienteDAO extends CRUD_DAO<Cliente, Integer>{
    ArrayList<Cliente> clientesOrdenadosPorFacturacion() throws Exception;
}
