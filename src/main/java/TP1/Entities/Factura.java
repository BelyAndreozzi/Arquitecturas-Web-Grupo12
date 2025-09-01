package TP1.Entities;

import TP1.DAO.FacturaDAO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor

public class Factura  {
    private int idFactura;
    private int idProducto;
    private int cantidad;
}
