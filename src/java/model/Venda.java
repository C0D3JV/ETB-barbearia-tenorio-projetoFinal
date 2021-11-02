package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Venda {
    private int idVenda;
    private Date dataVenda;
    private double precoTotal;
    private Cliente cliente;
    private Usuario usuario;
    private ArrayList<Curso> cursos;
    private ArrayList<Curso> naoCursos;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public ArrayList<Curso> getNaoCursos() {
        return naoCursos;
    }

    public void setNaoCursos(ArrayList<Curso> naoCursos) {
        this.naoCursos = naoCursos;
    }

    public Venda() {
    }
    
    public boolean gravar() throws SQLException {
        VendaDAO vdao = new VendaDAO();
        return vdao.gravar(this);
    }
}
