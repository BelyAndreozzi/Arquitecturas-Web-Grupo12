package TP1.Repository.MySQL;

import TP1.DAO.ClienteDAO;
import TP1.Entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOMySQL implements ClienteDAO {
    private final Connection conn;

    public ClienteDAOMySQL(Connection conn) {
        this.conn = conn;
         crearTablasSiNoExisten();
    }

    private void crearTablasSiNoExisten() {
        final String query = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT PRIMARY KEY AUTO_INCREMENT, " +
                "nombre VARCHAR(500) NOT NULL, " +
                "email VARCHAR(150) NOT NULL)";

        try(Statement st = conn.createStatement()){
            st.execute(query);
            conn.commit();

        }catch(SQLException e){
            throw new RuntimeException("Error al crear la tabla Cliente", e);
        }
    }

    @Override
    public ArrayList<Cliente> clientesOrdenadosPorFacturacion() throws Exception {
        String query =
                "SELECT c.idCliente, c.nombre, c.email, " +
                "       SUM(fp.cantidad * p.valor) AS totalFacturado " +
                "FROM Cliente c " +
                "JOIN Factura f ON c.idCliente = f.idCliente " +
                "JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                "JOIN Producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre, c.email " +
                "ORDER BY totalFacturado DESC";

        ArrayList<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("email")
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new Exception("Error al mostrar clientes", e);
        }

        return clientes;
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
            throw new RuntimeException("Error al insertar el cliente", e);
        }
    }


    @Override
    public boolean update(Cliente cliente) {
        String query = "UPDATE Cliente SET nombre =  ?, email = ? WHERE idCliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, cliente.getIdCliente());

            int filasActualizadas = ps.executeUpdate();
            conn.commit();
            conn.close();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el cliente", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String query = "DELETE FROM Cliente WHERE idCliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);

            int filasActualizadas = ps.executeUpdate();
            conn.commit();
            conn.close();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el cliente", e);
        }
    }

    @Override
    public Cliente get(Integer id) {
        String query = "SELECT idCliente, nombre, email FROM Cliente WHERE idCliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("idCliente"),
                            rs.getString("nombre"),
                            rs.getString("email")
                    );
                    return cliente;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar el cliente", e);
        }
        return null; // si no encuentra nada
    }

    @Override
    public List<Cliente> getAll() {
        String query = "SELECT idCliente, nombre, email FROM Cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar clientes", e);
        }
        return clientes;
    }
}
