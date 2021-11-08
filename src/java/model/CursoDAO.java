package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Curso> getLista() {
        String sql = "SELECT cs.idCurso, cs.nome, cs.cargaHoraria, cs.preco, cs.imagem,"
                + " cs.descricao FROM curso cs";
        ArrayList<Curso> lista = new ArrayList<>();
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Curso cs = new Curso();
                cs.setIdCurso(rs.getInt("cs.idCurso"));
                cs.setNome(rs.getString("cs.nome"));
                cs.setCargaHoraria(rs.getInt("cs.cargaHoraria"));
                cs.setPreco(rs.getDouble("cs.preco"));
                cs.setImagem(rs.getString("cs.imagem"));
                cs.setDescricao(rs.getString("cs.descricao"));
                lista.add(cs);
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println(
                    "Falha ao listar os cursos da base de dados:"
                    + e.getMessage());
        }
        return lista;
    }

    public boolean gravar(Curso cs) {
        con = null;
        String sql;
        ps = null;
        try {
            con = ConexaoFactory.conectar();
            if (cs.getIdCurso() == 0) {
                sql = "INSERT INTO curso (nome, cargaHoraria, preco, imagem, descricao) VALUES (?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, cs.getNome());
                ps.setInt(2, cs.getCargaHoraria());
                ps.setDouble(3, cs.getPreco());
                ps.setString(4, cs.getImagem());
                ps.setString(5, cs.getDescricao());
            } else {
                sql = "UPDATE curso set nome = ?, cargaHoraria = ?, preco = ?, imagem = ?, descricao = ? "
                        + "WHERE idCurso = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, cs.getNome());
                ps.setInt(2, cs.getCargaHoraria());
                ps.setDouble(3, cs.getPreco());
                ps.setString(4, cs.getImagem());
                ps.setString(5, cs.getDescricao());
                ps.setInt(6, cs.getIdCurso());
            }
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println("Falha ao gravar o curso na base de dados:"
                    + e.getMessage());
            return false;
        }
    }

    public boolean deletar(int idCurso) {
        String sql = "DELETE FROM curso WHERE idCurso = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCurso);
            ps.executeUpdate();
            ConexaoFactory.close(con);
        } catch (SQLException e) {
            System.out.println("Falha ao excluir o curso da base de dados"
                    + e.getMessage());
            return false;
        }
        return true;
    }

    public Curso getCarregarPorId(int idCurso) throws Exception {
        Curso curso = new Curso();
        String sql = "SELECT cs.idCurso, cs.nome, cs.cargaHoraria, cs.preco, "
                + "cs.imagem, cs.descricao FROM curso cs WHERE cs.idCurso = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCurso);
            rs = ps.executeQuery();
            if (rs.next()) {
                curso.setIdCurso(rs.getInt("cs.idCurso"));
                curso.setNome(rs.getString("cs.nome"));
                curso.setCargaHoraria(rs.getInt("cs.cargaHoraria"));
                curso.setPreco(rs.getDouble("cs.preco"));
                curso.setImagem(rs.getString("cs.imagem"));
                curso.setDescricao(rs.getString("cs.descricao"));
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao listar o curso: "
                    + e.getMessage());
        }
        return curso;
    }
}
