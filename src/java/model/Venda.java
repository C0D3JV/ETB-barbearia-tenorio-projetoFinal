package model;

import java.util.ArrayList;
import java.util.Date;

public class Venda {

    private int idVenda;
    private Date dataVenda;
    private double precoTotal;
    private Cliente cliente;
    private Usuario usuario;
    private ArrayList<VendaCurso> carrinho;

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

    public ArrayList<VendaCurso> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ArrayList<VendaCurso> carrinho) {
        this.carrinho = carrinho;
    }

    public Venda() {
    }

    public Venda(int idVenda, Date dataVenda, double precoTotal,
            Cliente cliente, Usuario usuario, ArrayList<VendaCurso> carrinho) {
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.precoTotal = precoTotal;
        this.cliente = cliente;
        this.usuario = usuario;
        this.carrinho = carrinho;
    }

}
