package TP1.DAO;

import java.util.List;

public interface CRUD_DAO<T,K> {
    void insert(T entity);
    boolean update(T entity);
    boolean delete(K id);
    T get(K id);
    List<T> getAll();
}
