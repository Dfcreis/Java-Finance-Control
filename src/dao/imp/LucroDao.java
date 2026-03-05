package dao.imp;

import entidades.Lucros;

import java.util.List;

public interface LucroDao {
    void insert(Lucros obj);

    void update(Lucros obj);

    void deleteById(Integer id);

   Lucros findById(Integer id);

    List<Lucros> findAll();

}
