package TP1.Utils;

import TP1.Factory.ConnectionManagerMYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCleaner {

    public static void limpiarBaseDeDatos() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionManagerMYSQL.getInstance().getConnection();
            statement = connection.createStatement();

            statement.execute("SET FOREIGN_KEY_CHECKS = 0");

            statement.execute("DELETE FROM FacturaProducto");
            statement.execute("DELETE FROM Factura");
            statement.execute("DELETE FROM Producto");
            statement.execute("DELETE FROM Cliente");

            statement.execute("SET FOREIGN_KEY_CHECKS = 1");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        System.out.println("Base de datos limpiada.");
    }
}
