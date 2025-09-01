package TP1.Repository.MySQL;

import TP1.DAO.ClienteDAO;
import TP1.Entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOMySQL implements ClienteDAO {
    private Connection conn;

    public ClienteDAOMySQL (Connection conn) {
        this.conn = conn;
        //crearTablasSiNoExisten(); -> hay que hacerlo :D
    }

    @Override
    public ArrayList<Cliente> clientesOrdenadosPorFacturacion() throws Exception {
        return null;
    }

    @Override
    public void insert(Cliente cliente) {
        String query = "INSERT INTO Cliente(idCliente, nombre, email) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            System.out.println("Persona insertada exitosamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
