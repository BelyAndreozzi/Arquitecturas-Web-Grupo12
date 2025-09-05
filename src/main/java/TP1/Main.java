package TP1;

import TP1.DAO.*;
import TP1.Entities.*;
import TP1.Factory.*;
import TP1.Repository.MySQL.*;
import TP1.Utils.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Obtenemos la fábrica para MySQL
        FactoryDAO factory = FactoryDAO.getInstance(DBType.MYSQL);

        // DAOs
        ClienteDAO clienteDAO = factory.createClienteDAO();
        ProductoDAO productoDAO = factory.createProductoDAO();
        FacturaDAO facturaDAO = factory.createFacturaDAO();
        FacturaProductoDAO facturaProductoDAO = factory.createFacturaProductoDAO();

        //Cargar Datos
        CargarDatos cargarDatos = new CargarDatos();

        // 5. Consultar datos
        System.out.println("\n=== Clientes ===");
        List<Cliente> clientes = clienteDAO.obtenerTodos();
        for (Cliente c : clientes) {
            System.out.println(c);
        }

        System.out.println("\n=== Productos ===");
        List<Producto> productos = productoDAO.obtenerTodos();
        for (Producto p : productos) {
            System.out.println(p);
        }

        System.out.println("\n=== Facturas ===");
        List<Factura> facturas = facturaDAO.obtenerTodos();
        for (Factura f : facturas) {
            System.out.println(f);
        }

        System.out.println("\n=== FacturaProductos ===");
        List<FacturaProducto> fps = facturaProductoDAO.obtenerTodos();
        for (FacturaProducto f : fps) {
            System.out.println(f);
        }


    }
}
