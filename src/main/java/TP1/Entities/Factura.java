package TP1.Entities;

import TP1.DAO.FacturaDAO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor

public class Factura implements FacturaDAO {
    private int idFactura;
    private int idProducto;
    private int cantidad;
}
