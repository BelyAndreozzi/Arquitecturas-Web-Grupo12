package TP1.Repository.MySQL;

import TP1.DAO.ProductoDAO;
import TP1.Entities.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOMySQL implements ProductoDAO {
    private final Connection conn;
    private static ProductoDAOMySQL instance;

    private ProductoDAOMySQL(Connection conn) {
        this.conn = conn;

    }
    public static ProductoDAOMySQL getInstance(Connection conn) {
        if (instance == null) {
            instance = new ProductoDAOMySQL(conn);
        }
        return instance;
    }

    //Escriba un programa JDBC que retorne el producto que más recaudó. Se define “recaudación”
    //como cantidad de productos vendidos multiplicado por su valor.
    public Producto productoQueMasRecaudo() throws Exception {
        String query =
                "SELECT p.idProducto, p.nombre, p.valor, " +
                        "       SUM(fp.cantidad * p.valor) AS totalRecaudado " +
                        "FROM Producto p " +
                        "JOIN FacturaProducto fp ON p.idProducto = fp.idProducto " +
                        "GROUP BY p.idProducto, p.nombre, p.valor " +
                        "ORDER BY totalRecaudado DESC " +
                        "LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getFloat("valor")
                );
                return producto;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar el producto que mas recaudo" ,e);
        }


    }

    @Override
    public void insertar(Producto producto) {
        String query = "INSERT INTO Producto(idProducto, nombre, valor) VALUES (?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
            ps.executeUpdate();
            ps.close();

            System.out.println("Producto insertado exitosamente");
        } catch (Exception e) {
            try {
                conn.rollback();//Revierte si algo falla y no cierra la conexion
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Error al insertar el producto", e);
        }
    }


    @Override
    public boolean actualizar(Producto producto) {
        String query = "UPDATE Producto SET nombre = ?, valor = ? WHERE idProducto = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getValor());
            ps.setInt(3, producto.getIdProducto());
            int filas_actualizadas = ps.executeUpdate();

            conn.close();
            return filas_actualizadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el producto", e);
        }
    }

    public boolean borrar(Producto pEntity) {
        String query = "DELETE FROM Producto WHERE idProducto = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, pEntity.getIdProducto());
            int filasActualizadas = ps.executeUpdate();

            conn.close();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el producto", e);
        }
    }

    public Producto obtener(Integer id) {
        String query = "SELECT * FROM Producto WHERE idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getFloat("valor")
                );
                return producto;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Producto> obtenerTodos () {
        String query = "SELECT * FROM Producto";
        List<Producto> productos = new java.util.LinkedList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getFloat("valor")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar los productos", e);
        }
        return productos;
    }
}
