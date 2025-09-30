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

        // Obtenemos el factory para MySQL
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

        //Escriba un programa JDBC que retorne el producto que más recaudó. Se define “recaudación” como cantidad de productos vendidos multiplicado por su valor.
        System.out.println("\n=== Producto que más recaudó ===");
        Producto productoMasRecaudado = productoDAO.productoQueMasRecaudo();
        System.out.println( productoMasRecaudado);

        /*Usado para checkear el valor del producto que mas recaudó
        List<Map<String, Object>> productosMasRecaudados = productoDAO.productosOrdenadosPorFacturacion();
        productosMasRecaudados.forEach(System.out::println);*/

        //Escriba un programa JDBC que imprima una lista de clientes, ordenada por a cuál se le facturó más.
        System.out.println("\n=== Clientes ordenados por facturación ===");
        List<Map<String, Object>> clientesOrdenados = clienteDAO.clientesOrdenadosPorFacturacion();
        clientesOrdenados.forEach(System.out::println);

    }
}
