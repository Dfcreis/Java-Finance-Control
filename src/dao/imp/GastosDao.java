package dao.imp;

import entidades.Gastos;
import entidades.Lucros;

import java.util.List;

public interface GastosDao {
    void insert(Gastos obj);

    void update(Gastos obj);

    void deleteById(Integer id);

    Gastos findById(Integer id);

    List<Gastos> findAll();
}
