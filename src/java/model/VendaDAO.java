package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Venda> getLista() {
        String sql = "SELECT ct.idCliente, ct.nome, ct.dataNasc, ct.cpf, ct.email, ct.endereco, ct.telefone,"
                + "ct.idTurma, ct.idUsuario, u.idUsuario, u.nome, u.login, u.senha, u.status, u.idPerfil, "
                + "v.idVenda, v.dataVenda, v.precoTotal, v.idCliente, v.idUsuario "
                + "FROM venda v "
                + "INNER JOIN cliente ct "
                + "ON ct.idCliente = v.idCliente "
                + "INNER JOIN usuario u "
                + "ON u.idUsuario = v.idUsuario ";
        //verificar se funciona no workench;
        ArrayList<Venda> lista = new ArrayList<>();
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venda v = new Venda();
                v.setIdVenda(rs.getInt("v.idVenda"));
                v.setDataVenda(rs.getDate("v.dataVenda"));
                v.setPrecoTotal(rs.getDouble("v.precoTotal"));

                Cliente ct = new Cliente();
                ct.setIdCliente(rs.getInt("ct.idCliente"));
                ct.setNome(rs.getString("ct.nome"));
                ct.setDataNasc(rs.getDate("ct.dataNasc"));
                ct.setCpf(rs.getString("ct.cpf"));
                ct.setEmail(rs.getString("ct.email"));
                ct.setEndereco(rs.getString("ct.endereco"));
                ct.setTelefone(rs.getString("ct.telefone"));
                v.setCliente(ct);
                
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("u.idUsuario"));
                u.setNome(rs.getString("u.nome"));
                u.setLogin(rs.getString("u.login"));
                u.setSenha(rs.getString("u.senha"));
                u.setStatus(rs.getInt("u.status"));
                v.setUsuario(u);
                
                lista.add(v);
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println(
                    "Falha ao listar as vendas da base de dados:"
                    + e.getMessage());
        }
        return lista;
    }

    public boolean gravar(Venda v) {
        con = null;
        String sql;
        ps = null;
        try {
            con = ConexaoFactory.conectar();
            if (v.getIdVenda() == 0) {
                sql = "INSERT INTO venda (dataVenda, precoTotal, idCliente, idUsuario) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setDate(1, new Date(v.getDataVenda().getTime()));
                ps.setDouble(2, v.getPrecoTotal());
                ps.setInt(3, v.getCliente().getIdCliente());
                ps.setInt(4, v.getUsuario().getIdUsuario());
            } else {
                sql = "UPDATE venda set dataVenda = ?, precoTotal = ?, idCliente = ?, idUsuario = ? "
                        + " WHERE idVenda = ?";
                ps = con.prepareStatement(sql);
                ps.setDate(1, new Date(v.getDataVenda().getTime()));
                ps.setDouble(2, v.getPrecoTotal());
                ps.setInt(3, v.getCliente().getIdCliente());
                ps.setInt(4, v.getUsuario().getIdUsuario());
                ps.setInt(5, v.getIdVenda());
            }
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println("Falha ao gravar a venda na base de dados: "
                    + e.getMessage());
            return false;
        }
    }

    public boolean deletar(int idVenda) {
        String sql = "DELETE FROM venda WHERE idVenda = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenda);
            ps.executeUpdate();
            ConexaoFactory.close(con);
        } catch (SQLException e) {
            System.out.println("Falha ao excluir a venda da base de dados"
                    + e.getMessage());
            return false;
        }
        return true;
    }

    public Venda getCarregarPorId(int idVenda) throws Exception {
        Venda v = new Venda();
        String sql ="SELECT ct.idCliente, ct.nome, ct.dataNasc, ct.cpf, ct.email, ct.endereco, ct.telefone,"
                + "ct.idTurma, ct.idUsuario, u.idUsuario, u.nome, u.login, u.senha, u.status, u.idPerfil, "
                + "v.idVenda, v.dataVenda, v.precoTotal, v.idCliente, v.idUsuario "
                + "FROM venda v "
                + "INNER JOIN cliente ct "
                + "ON ct.idCliente = v.idCliente "
                + "INNER JOIN usuario u "
                + "ON u.idUsuario = v.idUsuario "
                + "WHERE v.idVenda = ? ";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenda);
            rs = ps.executeQuery();
            if (rs.next()) {
                v.setIdVenda(rs.getInt("v.idVenda"));
                v.setDataVenda(rs.getDate("v.dataVenda"));
                v.setPrecoTotal(rs.getDouble("v.precoTotal"));

                Cliente ct = new Cliente();
                ct.setIdCliente(rs.getInt("ct.idCliente"));
                ct.setNome(rs.getString("ct.nome"));
                ct.setDataNasc(rs.getDate("ct.dataNasc"));
                ct.setCpf(rs.getString("ct.cpf"));
                ct.setEmail(rs.getString("ct.email"));
                ct.setEndereco(rs.getString("ct.endereco"));
                ct.setTelefone(rs.getString("ct.telefone"));
                v.setCliente(ct);
                
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("u.idUsuario"));
                u.setNome(rs.getString("u.nome"));
                u.setLogin(rs.getString("u.login"));
                u.setSenha(rs.getString("u.senha"));
                u.setStatus(rs.getInt("u.status"));
                v.setUsuario(u);
                
                //PAREI AQUI!
                
                //Associação entre objeto perfil e vários menus
                perfil.setMenus(menusVinculadosPorPerfil(idPerfil));
                //Associação entre objeto perfil e os vários objetos não menus
                perfil.setNaoMenus(menusNaoVinculadosPorPerfil(idPerfil));
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao listar o perfil: "
                    + e.getMessage());
        }
        return perfil;
    }

    /*
        Retorna os menus vinculados por perfil baseados no idPerfil
     */

    public ArrayList<Menu> menusVinculadosPorPerfil(int idPerfil)
            throws Exception {

        ArrayList<Menu> lista = new ArrayList<>();
        String sql = "SELECT m.idMenu, m.nome, m.link, "
                + "m.icone, m.exibir "
                + "FROM menu_perfil as mp, menu as m "
                + "WHERE mp.idMenu = m.idMenu "
                + "AND mp.idPerfil = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPerfil);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Menu m = new Menu();
                m.setIdMenu(rs.getInt("m.idMenu"));
                m.setNome(rs.getString("m.nome"));
                m.setLink(rs.getString("m.link"));
                m.setIcone(rs.getString("m.icone"));
                m.setExibir(rs.getInt("m.exibir"));
                lista.add(m);
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<Menu> menusNaoVinculadosPorPerfil(int idPerfil)
            throws Exception {

        ArrayList<Menu> lista = new ArrayList<>();
        String sql = "SELECT m.idMenu, m.nome, m.link, m.icone, m.exibir "
                + "FROM menu as m "
                + "WHERE m.idMenu "
                + "NOT IN(SELECT mp.idMenu FROM menu_perfil as mp WHERE mp.idPerfil = ?);";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPerfil);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Menu m = new Menu();
                m.setIdMenu(rs.getInt("m.idMenu"));
                m.setNome(rs.getString("m.nome"));
                m.setLink(rs.getString("m.link"));
                m.setIcone(rs.getString("m.icone"));
                m.setExibir(rs.getInt("m.exibir"));
                lista.add(m);
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }

    public boolean vincular(int idMenu, int idPerfil)
            throws Exception {
        String sql = "INSERT INTO menu_perfil (idMenu, idPerfil) VALUES (?, ?)";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMenu);
            ps.setInt(2, idPerfil);
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println(
                    "Erro ao vincular o menu ao perfil: "
                    + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    public boolean desvincular(int idMenu, int idPerfil)
            throws Exception {
        String sql = "DELETE FROM menu_perfil "
                + "WHERE idMenu = ? AND idPerfil = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMenu);
            ps.setInt(2, idPerfil);
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao desvincular o menu: "
                    + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
