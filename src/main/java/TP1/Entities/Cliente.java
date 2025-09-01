package TP1.Entities;

import TP1.DAO.ClienteDAO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Cliente{
    private int idCliente;
    private String nombre;
    private String email;
}
