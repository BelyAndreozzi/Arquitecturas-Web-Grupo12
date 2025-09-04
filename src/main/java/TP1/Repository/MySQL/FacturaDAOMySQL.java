package TP1.Repository.MySQL;

import TP1.DAO.FacturaDAO;
import TP1.Entities.Cliente;
import TP1.Entities.Factura;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAOMySQL implements FacturaDAO {
    private final Connection conn;

    public FacturaDAOMySQL(Connection conn) {
        this.conn = conn;
        crearTablasSiNoExisten();
    }

    private void crearTablasSiNoExisten() {
        final String query = "CREATE TABLE IF NOT EXISTS Factura (" +
                "idFactura INT PRIMARY KEY AUTO_INCREMENT, " +
                "idCliente INT NOT NULL, " +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))";

        try(Statement st = conn.createStatement()){
            st.execute(query);
            conn.commit();

        }catch(SQLException e){
            throw new RuntimeException("Error al crear la tabla Factura", e);
        }
    }

    @Override
    public void insert(Factura factura) {
        String query = "INSERT INTO Factura(idFactura, idCliente) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            ps.executeUpdate();
            conn.commit();
            System.out.println("Factura insertada exitosamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la factura", e);
        }
    }

    @Override
    public boolean update(Factura factura) {
        String query = "UPDATE Factura SET idCliente = ? WHERE idFactura = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdFactura());

            int filasActualizadas = ps.executeUpdate();
            conn.commit();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la factura", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        String query = "DELETE FROM Factura WHERE idFactura = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);

            int filasActualizadas = ps.executeUpdate();
            conn.commit();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la factura", e);
        }
    }

    @Override
    public Factura get(Integer id) {
        String query = "SELECT idFactura, idCliente FROM Factura WHERE idFactura = ?";
        Factura factura = null;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    factura = new Factura(
                            rs.getInt("idFactura"),
                            rs.getInt("idCliente")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la factura", e);
        }
        return null;

    }

    @Override
    public List<Factura> getAll() {
        String query = "SELECT idFactura, idCliente FROM Factura";
        List<Factura> facturas = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("idFactura"),
                        rs.getInt("idCliente")
                );
                facturas.add(factura);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las facturas", e);
        }
        return facturas;
    }

    public List<Factura> getByClienteId(int clienteId) {
        String query = "SELECT idFactura, idCliente FROM Factura WHERE idCliente = ?";
        List<Factura> facturas = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Factura factura = new Factura(
                            rs.getInt("idFactura"),
                            rs.getInt("idCliente")
                    );
                    facturas.add(factura);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las facturas por idCliente", e);
        }
        return facturas;
    }

    @Override
    public List<Factura> getFacturasPorCliente(int idCliente) {
        String query = "SELECT idFactura, idCliente " +
                "FROM factura WHERE idCliente = ? " +
                "ORDER BY idFactura";

        List<Factura> facturas = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Factura factura = new Factura(
                            rs.getInt("idFactura"),
                            rs.getInt("idCliente")
                    );
                    facturas.add(factura);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener facturas por cliente", e);
        }

        return facturas;
    }

    @Override
    public double getTotalFactura(int idFactura) {
        String query = "SELECT SUM(f.cantidad * p.valor) as total " +
                "FROM factura f " +
                "JOIN producto p ON f.idProducto = p.idProducto " +
                "WHERE f.idCliente = ?";

        double total = 0.0;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idFactura);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = rs.getDouble("total");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al calcular el total facturado por cliente", e);
        }

        return total;

    }
    /*
    */
}
