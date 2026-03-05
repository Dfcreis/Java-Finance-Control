package dao;

import dao.imp.GastosDao;
import db.DB;
import entidades.Gastos;
import entidades.Lucros;
import excecao.DbExcecao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GastosDaoJdbc implements GastosDao {
    private Connection conn;

    public GastosDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Gastos obj) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into gastos(descricao,valor,data,categoria) values(?,?,?,?)");
            ps.setString(1, obj.getDescricao());
            ps.setDouble(2, obj.getValor());
            ps.setDate(3, new java.sql.Date(obj.getData().getTime()));
            ps.setString(4, obj.getCategoria());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.fecharStatement(ps);
        }
    }

    @Override
    public void update(Gastos obj) {
        PreparedStatement ps = null;
        try {
            String sql = "update gastos set valor = valor + ? where id = ? ";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, obj.getValor());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbExcecao(e.getMessage());
        } finally {
            DB.fecharStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            String sql = "delete from gastos where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DbExcecao(e.getMessage());
        } finally {
            DB.fecharStatement(ps);
        }

    }

    @Override
    public Gastos findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from gastos where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return createGastos(rs);

            }
        } catch (SQLException e) {
            throw new DbExcecao(e.getMessage());
        } finally {
            DB.fecharResultSet(rs);
            DB.fecharStatement(ps);
        }
        return null;
    }

    @Override
    public List<Gastos> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM gastos";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Gastos> list = new ArrayList<>();
            while (rs.next()) {
                list.add(createGastos(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new DbExcecao(e.getMessage());
        } finally {
            DB.fecharResultSet(rs);
            DB.fecharStatement(ps);
        }
    }

    private Gastos createGastos(ResultSet rs) throws SQLException {
        Gastos obj = new Gastos();
        obj.setId(rs.getInt("id"));
        obj.setDescricao(rs.getString("descricao"));
        obj.setValor(rs.getDouble("valor"));
        obj.setData(rs.getDate("data"));
        obj.setCategoria(rs.getString("categoria"));
        return obj;
    }


}
