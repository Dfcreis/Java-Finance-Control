package dao.imp;


import dao.LucrosDaoJdbc;
import db.DB;

public class DaoFactory {
    public static LucroDao createLucrosDao() {
        return new LucrosDaoJdbc(DB.conectar());
    }
}