package TP1.Entities;

import TP1.DAO.FacturaProductoDAO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FacturaProducto {
    private int idFactura;
    private int idProducto;
    private int cantidad;
}
