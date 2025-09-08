package TP1;

import TP1.DAO.*;
import TP1.Entities.*;
import TP1.Factory.*;
import TP1.Repository.MySQL.*;
import TP1.Utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ejecutable {
    public static void main(String[] args) throws Exception {

        // Obtenemos la fábrica para MySQL
        FactoryDAO factory = FactoryDAO.getInstance(DBType.MYSQL);

        // DAOs
        ClienteDAO clienteDAO = factory.createClienteDAO();
        ProductoDAO productoDAO = factory.createProductoDAO();
        FacturaDAO facturaDAO = factory.createFacturaDAO();
        FacturaProductoDAO facturaProductoDAO = factory.createFacturaProductoDAO();

        //Crear tablas
        CrearTablas crearTablas = new CrearTablas(ConnectionManagerMYSQL.getInstance().getConnection());

        //Cargar Datos
        CargarDatos cargarDatos = new CargarDatos();
        cargarDatos.cargarDatosCSV(); // Limpiar y cargar datos

        //Escriba un programa JDBC que retorne el producto que más recaudó. Se define “recaudación” como cantidad de productos vendidos multiplicado por su valor.


        //Escriba un programa JDBC que imprima una lista de clientes, ordenada por a cuál se le facturó más.
        List<Map<String, Object>> clientesOrdenados = clienteDAO.clientesOrdenadosPorFacturacion();
        clientesOrdenados.forEach(System.out::println);

        // Consultar datos
       /* System.out.println("\n=== Clientes ===");
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
*/

    }
}
