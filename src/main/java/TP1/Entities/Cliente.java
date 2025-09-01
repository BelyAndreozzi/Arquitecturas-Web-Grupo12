package TP1.Entities;

import TP1.DAO.ClienteDAO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Cliente implements ClienteDAO {
    private int idCliente;
    private String nombre;
    private String email;
}
