package model;

public class VendaCurso {

    private long idVendaCurso;
    private int qtd;
    private double precoVendido;
    private Venda venda;
    private Curso curso;

    public long getIdVendaCurso() {
        return idVendaCurso;
    }

    public void setIdVendaCurso(long idVendaCurso) {
        this.idVendaCurso = idVendaCurso;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getPrecoVendido() {
        return precoVendido;
    }

    public void setPrecoVendido(double precoVendido) {
        this.precoVendido = precoVendido;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public VendaCurso() {
    }

    public VendaCurso(long idVendaCurso, int qtd, double precoVendido, Venda venda, Curso curso) {
        this.idVendaCurso = idVendaCurso;
        this.qtd = qtd;
        this.precoVendido = precoVendido;
        this.venda = venda;
        this.curso = curso;
    }

}
