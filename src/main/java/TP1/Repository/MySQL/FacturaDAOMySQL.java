package TP1.Repository.MySQL;

import TP1.DAO.FacturaDAO;
import TP1.Entities.Cliente;

import java.util.List;

public class FacturaDAOMySQL implements FacturaDAO {
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
