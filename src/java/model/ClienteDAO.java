package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Cliente> getLista() throws Exception {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT t.idTurma, t.nome, t.qtdAluno, t.ano, t.semestre, t.turno, t.idBarbeiro, "
                + " t.idCurso, ct.idCliente, "
                + " ct.nome, ct.dataNasc, ct.cpf, ct.email, ct.endereco, ct.telefone, ct.idTurma "
                + "FROM cliente ct "
                + "INNER JOIN turma t "
                + "ON t.idTurma = ct.idTurma ";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente ct = new Cliente();
                ct.setIdCliente(rs.getInt("ct.idCliente"));
                ct.setNome(rs.getString("ct.nome"));
                ct.setDataNasc(rs.getDate("ct.dataNasc"));
                ct.setCpf(rs.getString("ct.cpf"));
                ct.setEmail(rs.getString("ct.email"));
                ct.setEndereco(rs.getString("ct.endereco"));
                ct.setTelefone(rs.getString("ct.telefone"));

                Turma t = new Turma();
                t.setIdTurma(rs.getInt("t.idTurma"));
                t.setNome(rs.getString("t.nome"));
                t.setQtdAluno(rs.getInt("t.qtdAluno"));
                t.setAno(rs.getString("t.ano"));
                t.setSemestre(rs.getString("t.semestre"));
                t.setTurno(rs.getString("t.turno"));
                
                ct.setTurma(t);
                lista.add(ct);

            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println(
                    "Falha ao listar os clientes da base de dados:"
                    + e.getMessage());
        }
        return lista;
    }

    public boolean gravar(Cliente ct) throws Exception {
        String sql;
        try {
            con = ConexaoFactory.conectar();
            if (ct.getIdCliente() == 0) {
                sql = "INSERT INTO cliente (nome, dataNasc, cpf, email, endereco, telefone, idTurma) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE cliente set nome = ?, dataNasc = ?, cpf = ?, email = ?,"
                        + " endereco = ?, telefone = ?, idTurma = ? "
                        + "WHERE idCliente = ?";
            }

            ps = con.prepareStatement(sql);
            ps.setString(1, ct.getNome());
            ps.setDate(2, new Date(ct.getDataNasc().getTime()));
            ps.setString(3, ct.getCpf());
            ps.setString(4, ct.getEmail());
            ps.setString(5, ct.getEndereco());
            ps.setString(6, ct.getTelefone());
            ps.setInt(7, ct.getTurma().getIdTurma());

            if (ct.getIdCliente() > 0) {
                ps.setInt(8, ct.getIdCliente());
            }

            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println("Falha ao gravar o cliente na base de dados:"
                    + e.getMessage());
            return false;
        }

    }

    public boolean deletar(int idCliente) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.executeUpdate();
            ConexaoFactory.close(con);
        } catch (SQLException e) {
            System.out.println("Falha ao excluir o cliente da base de dados"
                    + e.getMessage());
            return false;
        }
        return true;
    }

    public Cliente getCarregarPorId(int idCliente) throws Exception {
        Cliente ct = new Cliente();
        String sql = "SELECT t.idTurma, t.nome, t.qtdAluno, t.ano, t.semestre, t.turno, t.idBarbeiro, "
                + " t.idCurso, ct.idCliente, ct.nome, ct.dataNasc, ct.cpf, ct.email, ct.endereco, ct.telefone,"
                + " ct.idTurma "
                + "FROM cliente ct "
                + "INNER JOIN turma t "
                + "ON t.idTurma = ct.idTurma "
                + "WHERE ct.idCliente = ? ";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                ct.setIdCliente(rs.getInt("ct.idCliente"));
                ct.setNome(rs.getString("ct.nome"));
                ct.setDataNasc(rs.getDate("ct.dataNasc"));
                ct.setCpf(rs.getString("ct.cpf"));
                ct.setEmail(rs.getString("ct.email"));
                ct.setEndereco(rs.getString("ct.endereco"));
                ct.setTelefone(rs.getString("ct.telefone"));

                //Associação entre os Objetos Turma e Cliente 
                Turma t = new Turma();
                t.setIdTurma(rs.getInt("t.idTurma"));
                t.setNome(rs.getString("t.nome"));
                t.setQtdAluno(rs.getInt("t.qtdAluno"));
                t.setAno(rs.getString("t.ano"));
                t.setSemestre(rs.getString("t.semestre"));
                t.setTurno(rs.getString("t.turno"));

                ct.setTurma(t);

            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao listar o cliente: "
                    + e.getMessage());
        }
        return ct;
    }
}
