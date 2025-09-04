package TP1.Utils;

import TP1.DAO.ClienteDAO;

/*
* Considere los CSV dados y escriba un programa JDBC que cargue los datos a la base de
datos. Considere utilizar la biblioteca Apache Commons CSV, disponible en Maven central,
para leer los archivos.
CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
FileReader("productos.csv"));
for(CSVRecord row: parser) {
System.out.println(row.get("idProducto"));
System.out.println(row.get("nombre"));
System.out.println(row.get("valor"));
}*/
public class CargarDatos {
    private final ClienteDAO clienteDAO;
    private final FacturaDAO facturaDAO;
    private final ProductoDAO productoDAO;
    private final 


}
