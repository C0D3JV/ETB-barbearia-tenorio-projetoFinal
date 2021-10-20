package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BarbeiroDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Barbeiro> getLista() throws Exception {
        ArrayList<Barbeiro> lista = new ArrayList<>();
        String sql = "SELECT u.idUsuario, u.nome , u.login, u.senha, u.status, u.idPerfil,"
                + "b.idBarbeiro, b.nome, b.dataNasc, b.cpf, b.email, b.endereco, b.telefone, b.idUsuario "
                + "FROM usuario u "
                + "INNER JOIN barbeiro b "
                + "ON u.idUsuario = b.idUsuario";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Barbeiro b = new Barbeiro();
                b.setIdBarbeiro(rs.getInt("b.idBarbeiro"));
                b.setNome(rs.getString("b.nome"));
                b.setDataNasc(rs.getDate("b.dataNasc"));
                b.setCpf(rs.getString("b.cpf"));
                b.setEmail(rs.getString("b.email"));
                b.setEndereco(rs.getString("b.endereco"));
                b.setTelefone(rs.getString("b.telefone"));

                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("u.idUsuario"));
                u.setNome(rs.getString("u.nome"));
                u.setLogin(rs.getString("u.login"));
                u.setSenha(rs.getString("u.senha"));
                u.setStatus(rs.getInt("u.status"));

                b.setUsuario(u);
                lista.add(b);

            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println(
                    "Falha ao listar os barbeiros da base de dados:"
                    + e.getMessage());
        }
        return lista;
    }

    public boolean gravar(Barbeiro b) throws Exception {
        // Connection con = null;
        String sql;
        // PreparedStatement ps = null;
        try {
            con = ConexaoFactory.conectar();
            if (b.getIdBarbeiro() == 0) {
                sql = "INSERT INTO barbeiro (nome, dataNasc, cpf, email, endereco, telefone, idUsuario) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE barbeiro set nome = ?, dataNasc = ?, cpf = ?, email = ?, endereco = ?, telefone = ?, idUsuario = ?"
                        + "WHERE idBarbeiro = ?";
            }

            ps = con.prepareStatement(sql);
            ps.setString(1, b.getNome());
            ps.setDate(2, new Date(b.getDataNasc().getTime()));
            ps.setString(3, b.getCpf());
            ps.setString(4, b.getEmail());
            ps.setString(5, b.getEndereco());
            ps.setString(6, b.getTelefone());
            ps.setInt(7, b.getUsuario().getIdUsuario());

            if (b.getIdBarbeiro() > 0) {
                ps.setInt(8, b.getIdBarbeiro());
            }

            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println("Falha ao gravar o barbeiro na base de dados:"
                    + e.getMessage());
            return false;
        }

    }

    public boolean deletar(int idBarbeiro) {
        String sql = "DELETE FROM barbeiro WHERE idBarbeiro = ?";
        try {
            Connection con = ConexaoFactory.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBarbeiro);
            ps.executeUpdate();
            ConexaoFactory.close(con);
        } catch (SQLException e) {
            System.out.println("Falha ao excluir o barbeiro da base de dados"
                    + e.getMessage());
            return false;
        }
        return true;
    }

    public Barbeiro getCarregarPorId(int idBarbeiro) throws Exception {
        Barbeiro b = new Barbeiro();
        String sql = "SELECT u.idUsuario, u.nome , u.login, u.senha, u.status, u.idPerfil, "
                + "b.idBarbeiro, b.nome, b.dataNasc, b.cpf, b.email, b.endereco, b.telefone, b.idUsuario "
                + "FROM barbeiro b "
                + "INNER JOIN usuario u "
                + "ON u.idUsuario = b.idUsuario "
                + "WHERE b.idBarbeiro = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idBarbeiro);
            rs = ps.executeQuery();
            if (rs.next()) {
                b.setIdBarbeiro(rs.getInt("idBarbeiro"));
                b.setNome(rs.getString("nome"));
                b.setDataNasc(rs.getDate("dataNasc"));
                b.setCpf(rs.getString("cpf"));
                b.setEmail(rs.getString("email"));
                b.setEndereco(rs.getString("endereco"));
                b.setTelefone(rs.getString("telefone"));
                
                //Associação entre os Objetos Usuario e Barbeiro
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("u.idUsuario"));
                u.setNome(rs.getString("u.nome"));
                u.setLogin(rs.getString("u.login"));
                u.setSenha(rs.getString("u.senha"));
                u.setStatus(rs.getInt("u.status"));

                b.setUsuario(u);
                
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao listar o barbeiro: "
                    + e.getMessage());
        }
        return b;
    }
}
