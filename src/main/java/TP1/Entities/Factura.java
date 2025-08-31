package TP1.Entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor

public class Factura {
    private int idFactura;
    private int idProducto;
    private int cantidad;
}
