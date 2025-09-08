package TP1.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTablas {
    private Connection conn;

    public CrearTablas(Connection conn) {
        this.conn = conn;
        crearTablaProducto();
        crearTablaCliente();
        crearTablaFactura();
        crearTablaFacturaProducto();
    }

    private void crearTablaProducto() {
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

    private void crearTablaCliente() {
        final String query = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT PRIMARY KEY, " +
                "nombre VARCHAR(500) NOT NULL, " +
                "email VARCHAR(150) NOT NULL)";

        try(Statement st = conn.createStatement()){
            st.execute(query);

        } catch(SQLException e) {
            throw new RuntimeException("Error al crear la tabla Cliente", e);
        }
    }

    private void crearTablaFactura() {
        final String query =
                "CREATE TABLE IF NOT EXISTS Factura(" +
                        "idFactura INT NOT NULL, " +
                        "idCliente INT NOT NULL, " +
                        "PRIMARY KEY(idFactura))";

        try(Statement st = conn.createStatement()) {
            st.execute(query);

        }catch(SQLException e) {
            throw new RuntimeException("Error al crear la tabla factura", e);
        }
    }

    private void crearTablaFacturaProducto() {
        final String query =
                "CREATE TABLE IF NOT EXISTS FacturaProducto(" +
                        "idFactura INT NOT NULL," +
                        "idProducto INT NOT NULL," +
                        "cantidad INT NOT NULL," +
                        "PRIMARY KEY(idFactura, idProducto))";

        try(Statement st = conn.createStatement()) {
            st.execute(query);

        }catch(SQLException e) {
            throw new RuntimeException("Error al crear la tabla FacturaProducto", e);
        }
    }
}
