package TP1.Factory;

import java.sql.Connection;


public final class ConnectionManagerMYSQL {
    private static final String URL = "jdbc:mysql://localhost:3306/tp1?createDataBaseIfNotExist=true";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private static volatile ConnectionManagerMYSQL instance;
    private Connection conn;

    private ConnectionManagerMYSQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = java.sql.DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexion exitosa a la base de datos MySQL");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("No se encontró el driver de MySQL");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al conectar a la base de datos MySQL");
        }
    }

    public static ConnectionManagerMYSQL getInstance() {
        if (instance == null) {
            synchronized (ConnectionManagerMYSQL.class) {
                if (instance == null) {
                    instance = new ConnectionManagerMYSQL();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {return conn;}
}
