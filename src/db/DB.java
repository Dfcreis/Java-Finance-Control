package db;

import excecao.DbExcecao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection con = null;

    public static Connection conectar() {
        if (con == null) {
            try {
                Properties prop = carregarPropriedades();
                String url = prop.getProperty("db.url");
                con = DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                throw new DbExcecao(e.getMessage());
            }
        }
        return con;
    }

    public static void Desconectar() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DbExcecao(e.getMessage());
            }
        }
    }

    public static void fecharStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbExcecao(e.getMessage());
            }
        }
    }

    public static void fecharResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbExcecao(e.getMessage());
            }
        }
    }


    private static Properties carregarPropriedades() {
        try (FileInputStream fis = new FileInputStream("DB.configuracao")) {
            Properties prop = new Properties();
            prop.load(fis);
            return prop;
        } catch (IOException e) {
            throw new DbExcecao(e.getMessage());
        }
    }
}
