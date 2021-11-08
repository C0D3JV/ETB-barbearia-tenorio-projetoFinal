package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TurmaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Turma> getLista() throws Exception {
        ArrayList<Turma> lista = new ArrayList<>();
        String sql = " SELECT b.idBarbeiro, b.nome, b.dataNasc, b.cpf, b.email, b.endereco,"
                + " b.telefone, b.idUsuario, cs.idCurso, cs.nome, cs.cargaHoraria, cs.preco,"
                + " t.idTurma, t.nome, t.qtdAluno, t.ano, t.semestre,"
                + " t.turno, t.idBarbeiro, t.idCurso "
                + "FROM turma t "
                + "INNER JOIN barbeiro b "
                + "ON b.idBarbeiro = t.idBarbeiro "
                + "INNER JOIN curso cs "
                + "ON cs.idCurso = t.idCurso ";

        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Turma t = new Turma();
                t.setIdTurma(rs.getInt("t.idTurma"));
                t.setNome(rs.getString("t.nome"));
                t.setQtdAluno(rs.getInt("t.qtdAluno"));
                t.setAno(rs.getString("t.ano"));
                t.setSemestre(rs.getString("t.semestre"));
                t.setTurno(rs.getString("t.turno"));

                Barbeiro b = new Barbeiro();
                b.setIdBarbeiro(rs.getInt("b.idBarbeiro"));
                b.setNome(rs.getString("b.nome"));
                b.setDataNasc(rs.getDate("b.dataNasc"));
                b.setCpf(rs.getString("b.cpf"));
                b.setEmail(rs.getString("b.email"));
                b.setEndereco(rs.getString("b.endereco"));
                b.setTelefone(rs.getString("b.telefone"));
                t.setBarbeiro(b);

                Curso cs = new Curso();
                cs.setIdCurso(rs.getInt("cs.idCurso"));
                cs.setNome(rs.getString("cs.nome"));
                cs.setCargaHoraria(rs.getInt("cs.cargaHoraria"));
                cs.setPreco(rs.getDouble("cs.preco"));
                t.setCurso(cs);

                lista.add(t);

            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println(
                    "Falha ao listar as turmas da base de dados:"
                    + e.getMessage());
        }
        return lista;
    }

    public boolean gravar(Turma t) throws Exception {
        String sql;
        try {
            con = ConexaoFactory.conectar();
            if (t.getIdTurma() == 0) {
                sql = "INSERT INTO turma (nome, qtdAluno, ano, semestre, turno, idBarbeiro, idCurso) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE turma set nome = ?, qtdAluno= ?, ano = ?, semestre = ?, turno = ?,"
                        + "idBarbeiro = ?, idCurso = ? "
                        + "WHERE idTurma = ?";
            }

            ps = con.prepareStatement(sql);
            ps.setString(1, t.getNome());
            ps.setInt(2, t.getQtdAluno());
            ps.setString(3, t.getAno());
            ps.setString(4, t.getSemestre());
            ps.setString(5, t.getTurno());
            ps.setInt(6, t.getBarbeiro().getIdBarbeiro());
            ps.setInt(7, t.getCurso().getIdCurso());

            if (t.getIdTurma() > 0) {
                ps.setInt(8, t.getIdTurma());
            }

            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println("Falha ao gravar a turma na base de dados:"
                    + e.getMessage());
            return false;
        }

    }

    public boolean deletar(int idTurma) {
        String sql = "DELETE FROM turma WHERE idTurma = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idTurma);
            ps.executeUpdate();
            ConexaoFactory.close(con);
        } catch (SQLException e) {
            System.out.println("Falha ao excluir a turma da base de dados"
                    + e.getMessage());
            return false;
        }
        return true;
    }

    public Turma getCarregarPorId(int idTurma) throws Exception {
        Turma t = new Turma();
        String sql = " SELECT b.idBarbeiro, b.nome, b.dataNasc, b.cpf, b.email, b.endereco,"
                + " b.telefone, b.idUsuario, cs.idCurso, cs.nome, cs.cargaHoraria, cs.preco,"
                + " t.idTurma, t.nome, t.qtdAluno, t.ano, t.semestre,"
                + " t.turno, t.idBarbeiro, t.idCurso "
                + "FROM turma t "
                + "INNER JOIN barbeiro b "
                + "ON b.idBarbeiro = t.idBarbeiro "
                + "INNER JOIN curso cs "
                + "ON cs.idCurso = t.idCurso "
                + "WHERE t.idTurma = ? ";

        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idTurma);
            rs = ps.executeQuery();
            if (rs.next()) {
                t.setIdTurma(rs.getInt("t.idTurma"));
                t.setNome(rs.getString("t.nome"));
                t.setQtdAluno(rs.getInt("t.qtdAluno"));
                t.setAno(rs.getString("t.ano"));
                t.setSemestre(rs.getString("t.semestre"));
                t.setTurno(rs.getString("t.turno"));

                Barbeiro b = new Barbeiro();
                b.setIdBarbeiro(rs.getInt("b.idBarbeiro"));
                b.setNome(rs.getString("b.nome"));
                b.setDataNasc(rs.getDate("b.dataNasc"));
                b.setCpf(rs.getString("b.cpf"));
                b.setEmail(rs.getString("b.email"));
                b.setEndereco(rs.getString("b.endereco"));
                b.setTelefone(rs.getString("b.telefone"));

                t.setBarbeiro(b);

                Curso cs = new Curso();
                cs.setIdCurso(rs.getInt("cs.idCurso"));
                cs.setNome(rs.getString("cs.nome"));
                cs.setCargaHoraria(rs.getInt("cs.cargaHoraria"));
                cs.setPreco(rs.getDouble("cs.preco"));

                t.setCurso(cs);

            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao listar a turma: "
                    + e.getMessage());
        }
        return t;
    }
}
