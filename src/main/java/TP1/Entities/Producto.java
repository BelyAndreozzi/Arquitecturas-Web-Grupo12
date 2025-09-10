package TP1.Entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Producto {
    private int idProducto;
    private String nombre;
    private float valor;
}
