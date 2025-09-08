package TP1.Repository.MySQL;

import TP1.DAO.ProductoDAO;
import TP1.Entities.Producto;

import java.sql.*;
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
    }/*
    private void crearTablasSiNoExisten() {
         final String query =
            "CREATE TABLE IF NOT EXISTS Producto(" +
            "idProducto INT NOT NULL," +
            "nombre VARCHAR(100) NOT NULL," +
            "valor FLOAT NOT NULL," +
            "PRIMARY KEY(idProducto))";

         try(Statement st = conn.createStatement()) {
             st.execute(query);

         }catch(SQLException e) {
             throw new RuntimeException("Error al crear la tabla producto", e);
         }
    }
    :D */

    @Override
    public Producto productoQueMasRecaudo() throws Exception {
        /* NO HAGAN ESTE ASI LO HACEMOS TODOS JUNTOS <3 UwU */
        return null;
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
