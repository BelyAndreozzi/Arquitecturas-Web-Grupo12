package TP1.Repository.MySQL;

import TP1.DAO.FacturaProductoDAO;
import TP1.Entities.FacturaProducto;
import TP1.Entities.FacturaProductoIDs;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaProductoDAOMySQL implements FacturaProductoDAO {
    private static FacturaProductoDAOMySQL instance;
    private final Connection conn;
    
    private  FacturaProductoDAOMySQL(Connection conn) {
        this.conn = conn;
    }

    public static FacturaProductoDAOMySQL getInstance(Connection conn) {
        if (instance == null) {
            instance = new FacturaProductoDAOMySQL(conn);
        }
        return instance;
    }

    public void insertar(FacturaProducto facturaProducto) {
        String query = "INSERT INTO FacturaProducto(idFactura, idProducto, cantidad) VALUES (?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, facturaProducto.getIdFactura());
            ps.setInt(2, facturaProducto.getIdProducto());
            ps.setInt(3, facturaProducto.getCantidad());
            ps.executeUpdate();

            System.out.println("factura-producto insertada exitosamente");
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Error al insertar la factura", e);
        }
    }

    public void insertarTodos(List<FacturaProducto> facturaProductos) {
        String query = "INSERT INTO FacturaProducto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            for (FacturaProducto fp : facturaProductos) {
                ps.setInt(1, fp.getIdFactura());
                ps.setInt(2, fp.getIdProducto());
                ps.setInt(3, fp.getCantidad());
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Facturas Productos insertados exitosamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar todos los FacturaProducto", e);
        }
    }


    public boolean actualizar(FacturaProducto facturaProducto) {
        String query = "UPDATE FacturaProducto SET idFactura = ?, idProducto = ?, cantidad = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, facturaProducto.getIdFactura());
            ps.setInt(2, facturaProducto.getIdProducto());
            ps.setInt(3, facturaProducto.getCantidad());

            int filas_actualizadas = ps.executeUpdate();

            conn.close();
            return filas_actualizadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la factura", e);
        }

    }

    public boolean borrar(FacturaProducto fpEntity) {
        String query = "DELETE FROM Factura_Producto WHERE idFactura = ? AND idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, fpEntity.getIdFactura());
            ps.setInt(2, fpEntity.getIdProducto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al borrar FacturaProducto", e);
        }
    }

    public FacturaProducto obtener(FacturaProductoIDs iDs) {
        String query = "SELECT * FROM FacturaProducto WHERE idFactura = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, iDs.getIdFactura());
            ps.setInt(2, iDs.getIdProducto());
            try (ResultSet rs = ps.executeQuery()) {
                FacturaProducto fp = new FacturaProducto(
                        rs.getInt("idFactura"),
                        rs.getInt("idProducto"),
                        rs.getInt("cantidad")
                );
                return fp;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar la factura", e);
        }
    }

    @Override
    public List<FacturaProducto> obtenerTodos() {
        String query = "SELECT FP.* FROM FacturaProducto FP";
        List<FacturaProducto> lista = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FacturaProducto fp = new FacturaProducto(
                        rs.getInt("idFactura"),
                        rs.getInt("idProducto"),
                        rs.getInt("cantidad")
                );
                lista.add(fp);
            }

        } catch(SQLException e){
            throw new RuntimeException("Error al mostrar factura-producto", e);
        }
        return lista;
    }


}
