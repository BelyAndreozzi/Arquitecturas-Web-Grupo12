package TP1.Entities;

import TP1.DAO.ProductoDAO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Producto implements ProductoDAO {
    private int idProducto;
    private String nombre;
    private float valor;
}
