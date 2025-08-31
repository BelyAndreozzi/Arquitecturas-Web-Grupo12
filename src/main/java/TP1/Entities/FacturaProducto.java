package TP1.Entities;

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
