package model;

import java.sql.SQLException;
import java.util.Date;

public class Barbeiro {

    private int idBarbeiro;
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String telefone;
    private Date dataNasc;
    private Usuario usuario;

    public int getIdBarbeiro() {
        return idBarbeiro;
    }

    public void setIdBarbeiro(int idBarbeiro) {
        this.idBarbeiro = idBarbeiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Barbeiro() {

    }
//    public boolean gravar() throws SQLException {
  //      BarbeiroDAO bdao = new BarbeiroDAO();
    //    return bdao.gravar(this);
    //}
}
