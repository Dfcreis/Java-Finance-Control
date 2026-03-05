package entidades;

import java.io.Serializable;
import java.util.Date;

public class Lucros implements Serializable {
    private int id;
    private String descricao;
    private double valor;
    private Date data;
    private String categoria;

    public Lucros(){}
    public Lucros(String categoria, String descricao, double valor, Date data) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Lucros{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                '}';
    }
}
