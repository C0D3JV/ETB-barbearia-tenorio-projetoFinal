package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Usuario> getLista() throws Exception {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT p.idPerfil, p.nome, "
                + "u.idUsuario, u.nome , u.login, "
                + "u.senha, u.status, u.idPerfil "
                + "FROM perfil p "
                + "INNER JOIN usuario u "
                + "ON p.idPerfil = u.idPerfil";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("u.idUsuario"));
                u.setNome(rs.getString("u.nome"));
                u.setLogin(rs.getString("u.login"));
                u.setSenha(rs.getString("u.senha"));
                u.setStatus(rs.getInt("u.status"));

                Perfil p = new Perfil();
                p.setIdPerfil(rs.getInt("p.idPerfil"));
                p.setNome(rs.getString("p.nome"));

                u.setPerfil(p);
                lista.add(u);
            }

            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao listar os usuários: "
                    + e.getMessage());
        }

        return lista;
    }

    public boolean gravar(Usuario u) throws Exception {
        String sql;

        try {
            con = ConexaoFactory.conectar();
            if (u.getIdUsuario() == 0) {
                sql
                        = "INSERT INTO usuario (nome, login, senha, status, idPerfil) "
                        + "VALUES (?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, "
                        + "status = ?, idPerfil = ? WHERE idUsuario = ?";
            }

            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getLogin());
            ps.setString(3, u.getSenha());
            ps.setInt(4, u.getStatus());
            ps.setInt(5, u.getPerfil().getIdPerfil());

            if (u.getIdUsuario() > 0) {
                ps.setInt(6, u.getIdUsuario());
            }

            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println(""
                    + "Falha ao gravar o usuário na base de dados "
                    + e.getMessage());
            return false;
        }
    }

    public Usuario getCarregarPorId(int idUsuario) throws Exception {
        Usuario u = new Usuario();
        String sql = "SELECT p.nome, p.idPerfil, u.idUsuario, "
                + " u.nome, u.login, u.senha, u.status, u.idPerfil "
                + "FROM usuario u "
                + "INNER JOIN perfil p "
                + "ON p.idPerfil = u.idPerfil "
                + "WHERE u.idUsuario = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                u.setIdUsuario(rs.getInt("u.idUsuario"));
                u.setNome(rs.getString("u.nome"));
                u.setLogin(rs.getString("u.login"));
                u.setSenha(rs.getString("u.senha"));
                u.setStatus(rs.getInt("u.status"));
                Perfil p = new Perfil();
                p.setIdPerfil(rs.getInt("p.idPerfil"));
                p.setNome(rs.getString("p.nome"));
                //Associação entre os Objetos Perfil e Usuario
                u.setPerfil(p);
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao carregar o usuário: "
                    + e.getMessage());

        }

        return u;
    }

    public boolean desativar(Usuario u) {
        String sql = "UPDATE usuario set status = 2 "
                + "WHERE idUsuario = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, u.getIdUsuario());
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println("Falha ao desativar o usuário: "
                    + e.getMessage());
            return false;
        }

    }

    public boolean ativar(Usuario u) {
        String sql = "UPDATE usuario set status = 1 "
                + "WHERE idUsuario = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, u.getIdUsuario());
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println("Falha ao habilitar o usuário: "
                    + e.getMessage());
            return false;
        }

    }

    public Usuario getRecuperarUsuario(String login)
            throws Exception {
        Usuario u = new Usuario();
        String sql = "select u.idUsuario, u.nome, u.login, u.senha, "
                + "u.status, u.idPerfil "
                + "from usuario u "
                + "where u.login = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                u.setIdUsuario(rs.getInt("u.idUsuario"));
                u.setNome(rs.getString("u.nome"));
                u.setLogin(rs.getString("u.login"));
                u.setSenha(rs.getString("u.senha"));
                u.setStatus(rs.getInt("u.status"));
                PerfilDAO pdao = new PerfilDAO();
                u.setPerfil(pdao.getCarregarPorId(
                        rs.getInt("u.idPerfil")));
            }

            ConexaoFactory.close(con);
        } catch (SQLException e) {
            System.out.println("Falha ao recuperar o usuario: "
                    + e.getMessage());
            e.printStackTrace();
        }

        return u;
    }
}
