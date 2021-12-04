package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VendaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Venda> getLista() throws Exception {
        ArrayList<Venda> lista = new ArrayList<Venda>();
        String sql = "SELECT idVenda, dataVenda, precoTotal, "
                + "idCliente, idUsuario "
                + "FROM venda";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venda v = new Venda();
                v.setIdVenda(rs.getInt("idVenda"));
                v.setDataVenda(rs.getDate("dataVenda"));
                v.setPrecoTotal(rs.getDouble("precoTotal"));

                ClienteDAO ctdao = new ClienteDAO();
                v.setCliente(ctdao.getCarregarPorId(
                        rs.getInt("idCliente")));

                UsuarioDAO udao = new UsuarioDAO();
                v.setUsuario(udao.getCarregarPorId(
                        rs.getInt("idUsuario")));
                lista.add(v);
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println(
                    "Falha ao listar as vendas da base de dados:"
                    + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean registrar(Venda v) {
        try {
            con = ConexaoFactory.conectar();
            String sql = "INSERT INTO venda "
                    + "(dataVenda, precoTotal, idCliente, idUsuario) "
                    + "VALUES (now(), ?, ?, ?) ";
            ps = con.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, v.getPrecoTotal());
            ps.setInt(2, v.getCliente().getIdCliente());
            ps.setInt(3, v.getUsuario().getIdUsuario());
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                v.setIdVenda(rs.getInt(1));
                for (VendaCurso vcs : v.getCarrinho()) {
                    String sql_vcs
                            = "INSERT INTO venda_curso "
                            + "(idVenda, idCurso, qtd, precoVendido) "
                            + "VALUES (?, ?, ?, ?)";
                    PreparedStatement ps_vcs
                            = con.prepareCall(sql_vcs);
                    ps_vcs.setInt(1, v.getIdVenda());
                    ps_vcs.setInt(2, vcs.getCurso().getIdCurso());
                    ps_vcs.setInt(3, vcs.getQtd());
                    ps_vcs.setDouble(4, vcs.getPrecoVendido());
                    ps_vcs.execute();
                }
                ConexaoFactory.close(con);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Falha ao registrar a venda: "
                    + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<Venda> getVendaPorData(
            Date dataInicial, Date dataFinal) throws Exception {
        ArrayList<Venda> lista = new ArrayList<>();

        String sql = "SELECT * from venda WHERE dataVenda >= ? "
                + "AND dataVenda <= ?";

        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);

            ps.setDate(1, new java.sql.Date(dataInicial.getTime()));
            ps.setDate(2, new java.sql.Date(dataFinal.getTime()));

            rs = ps.executeQuery();
            while (rs.next()) {
                Venda v = new Venda();
                v.setIdVenda(rs.getInt("idVenda"));
                
                ClienteDAO ctdao = new ClienteDAO();
                v.setCliente(ctdao.getCarregarPorId(
                        rs.getInt("idCliente")));
                
                UsuarioDAO udao = new UsuarioDAO();
                v.setUsuario(udao.getCarregarPorId(
                        rs.getInt("idUsuario")));
                v.setDataVenda(rs.getDate("dataVenda"));
                v.setPrecoTotal(rs.getDouble("precoTotal"));
                lista.add(v);

            }
        } catch (SQLException e) {
            System.out.println(
                    "Falha ao lista as vendas por data: "
                    + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

}
