package TP1.Utils;

import TP1.DAO.*;
import TP1.Entities.*;

import TP1.Factory.DBType;
import TP1.Factory.FactoryDAO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargarDatos {
    private final ClienteDAO clienteDAO;
    private final FacturaDAO facturaDAO;
    private final ProductoDAO productoDAO;
    private final FacturaProductoDAO facturaProductoDAO;

    public CargarDatos(){
        FactoryDAO f = FactoryDAO.getInstance(DBType.MYSQL);
        this.clienteDAO = f.createClienteDAO();
        this.facturaDAO = f.createFacturaDAO();
        this.productoDAO = f.createProductoDAO();
        this.facturaProductoDAO = f.createFacturaProductoDAO();
        cargarDatosCSV();
    }

    public void cargarDatosCSV(){
        try {
            DatabaseCleaner.limpiarBaseDeDatos();
            leerDatosClientes("src/main/resources/DBData/clientes.csv");
            leerDatosProductos("src/main/resources/DBData/productos.csv");
            leerDatosFacturas("src/main/resources/DBData/facturas.csv");
            leerDatosFacturasProducto("src/main/resources/DBData/facturas-productos.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void leerDatosClientes (String resourcePath) throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(resourcePath));
        List<Cliente> clientes = new ArrayList<>();
        for(CSVRecord row: parser) {
            Cliente nuevoCliente = new Cliente(
                    Integer.parseInt(row.get("idCliente")),
                    row.get("nombre"),
                    row.get("email")
            );
            clientes.add(nuevoCliente);
        }
        clienteDAO.insertarTodos(clientes);
    }

    public void leerDatosProductos (String resourcePath) throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(resourcePath));
        List<Producto> productos = new ArrayList<>();
        for(CSVRecord row: parser) {
            Producto nuevoProducto = new Producto(
                    Integer.parseInt(row.get("idProducto")),
                    row.get("nombre"),
                    Float.parseFloat(row.get("valor"))
            );
            productos.add(nuevoProducto);
        }
        productoDAO.insertarTodos(productos);
    }

    public void leerDatosFacturas (String resourcePath) throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(resourcePath));
        List<Factura> facturas = new ArrayList<>();
        for(CSVRecord row: parser) {
            Factura nuevaFactura = new Factura(
                    Integer.parseInt(row.get("idFactura")),
                    Integer.parseInt(row.get("idCliente"))
            );
            facturas.add(nuevaFactura);
        }
        facturaDAO.insertarTodos(facturas);
    }

    public void leerDatosFacturasProducto(String resourcePath) throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(resourcePath));
        List<FacturaProducto> facturaProductos = new ArrayList<>();

        for (CSVRecord row : parser) {
            FacturaProducto nuevoFP = new FacturaProducto(
                    Integer.parseInt(row.get("idFactura")),
                    Integer.parseInt(row.get("idProducto")),
                    Integer.parseInt(row.get("cantidad"))
            );
            facturaProductos.add(nuevoFP);
        }

        facturaProductoDAO.insertarTodos(facturaProductos);
    }

}
