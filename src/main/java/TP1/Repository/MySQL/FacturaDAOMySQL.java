package TP1.Repository.MySQL;

import TP1.DAO.FacturaDAO;
import TP1.Entities.Factura;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FacturaDAOMySQL implements FacturaDAO {
    private final Connection conn;
    private static FacturaDAOMySQL instance;

    private FacturaDAOMySQL(Connection conn) {
        this.conn = conn;

    }

    public static FacturaDAOMySQL getInstance(Connection conn) {
        if(instance == null) {
            instance = new FacturaDAOMySQL(conn);
        }
        return instance;
    }

    public void insertar(Factura factura) {
        String query = "INSERT INTO Factura(idFactura, idCliente) VALUES (?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            ps.executeUpdate();

            System.out.println("factura insertada exitosamente");
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Error al insertar la factura", e);
        }
    }

    public void insertarTodos(List<Factura> facturas) {
        String query = "INSERT INTO Factura(idFactura, idCliente) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            for (Factura factura : facturas) {
                ps.setInt(1, factura.getIdFactura());
                ps.setInt(2, factura.getIdCliente());
                ps.addBatch();
            }
            ps.executeBatch();

            System.out.println("Facturas insertadas exitosamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar las facturas", e);
        }
    }

    public boolean actualizar(Factura factura) {
        String query = "UPDATE Factura SET idFactura = ?, idCliente = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());

            int filas_actualizadas = ps.executeUpdate();

            conn.close();
            return filas_actualizadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la factura", e);
        }

    }

    public boolean borrar(Factura fEntity) {
        String query = "DELETE FROM Factura WHERE idFactura = ?";

        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, fEntity.getIdFactura());
            int filasActualizadas = ps.executeUpdate();

            conn.close();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el factura", e);
        }
    }

    public Factura obtener(Integer id) {
        String query = "SELECT * FROM factura WHERE idFactura = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                Factura factura = new Factura(
                        rs.getInt("idFactura"),
                        rs.getInt("idCliente")
                );
                return factura;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar la factura", e);
        }
    }


    @Override
    public List<Factura> obtenerTodos() {
        String query = "SELECT * FROM factura";
        List<Factura> facturas = new LinkedList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
           while(rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("idFactura"),
                        rs.getInt("idCliente")
                );
                facturas.add(factura);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar las facturas", e);
        }
        return facturas;
    }
}
