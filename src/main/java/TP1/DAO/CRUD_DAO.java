package TP1.DAO;

import java.util.List;

public interface CRUD_DAO<T,K> {
    void insertar(T entity);
    boolean actualizar(T entity);
    boolean borrar(T entity);
    T obtener(K id);
    List<T> obtenerTodos();
}