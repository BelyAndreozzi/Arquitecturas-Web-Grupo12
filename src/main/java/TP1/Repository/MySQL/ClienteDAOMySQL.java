package TP1.Repository.MySQL;

import TP1.DAO.ClienteDAO;
import TP1.Entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ClienteDAOMySQL implements ClienteDAO {
    private final Connection conn;
    private static  ClienteDAOMySQL instance;

    private ClienteDAOMySQL(Connection conn) {
        this.conn = conn;
    }
    public static ClienteDAOMySQL getInstance(Connection conn) {
        if (instance == null) {
            instance = new ClienteDAOMySQL(conn);
        }
        return  instance;
    }
    /*private void crearTablasSiNoExisten() {
        final String query = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT PRIMARY KEY, " +
                "nombre VARCHAR(500) NOT NULL, " +
                "email VARCHAR(150) NOT NULL)";

        try(Statement st = conn.createStatement()){
            st.execute(query);

        } catch(SQLException e) {
            throw new RuntimeException("Error al crear la tabla Cliente", e);
        }
    }*/

    public List<Map<String, Object>> clientesOrdenadosPorFacturacion() throws Exception {
        String query =
                "SELECT c.idCliente, c.nombre, c.email, " +
                        "       SUM(fp.cantidad * p.valor) AS totalFacturado " +
                        "FROM Cliente c " +
                        "JOIN Factura f ON c.idCliente = f.idCliente " +
                        "JOIN FacturaProducto fp ON f.idFactura = fp.idFactura " +
                        "JOIN Producto p ON fp.idProducto = p.idProducto " +
                        "GROUP BY c.idCliente, c.nombre, c.email " +
                        "ORDER BY totalFacturado DESC";

        List<Map<String, Object>> clientesConTotal = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("email")
                );
                clientesConTotal.add(
                        Map.of("cliente", cliente,
                                "totalFacturado", rs.getDouble("totalFacturado")
                        )
                );
            }

        } catch (SQLException e) {
            throw new Exception("Error al mostrar clientes ordenados por facturación", e);
        }

        return clientesConTotal;
    }

    public void insertar(Cliente cliente) {
        String query = "INSERT INTO Cliente(idCliente, nombre, email) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            ps.executeUpdate();
            ps.close();

            System.out.println("Cliente insertado exitosamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el cliente", e);
        }
    }

    public boolean actualizar(Cliente cliente) {
        String query = "UPDATE Cliente SET nombre =  ?, email = ? WHERE idCliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, cliente.getIdCliente());

            int filasActualizadas = ps.executeUpdate();

            conn.close();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el cliente", e);
        }
    }

    public boolean borrar(Cliente cEntity) {
        String query = "DELETE FROM Cliente WHERE idCliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cEntity.getIdCliente());

            int filasActualizadas = ps.executeUpdate();

            conn.close();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el cliente", e);
        }
    }


    public Cliente obtener(Integer id) {
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

    public List<Cliente> obtenerTodos() {
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
