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
        String sql = "SELECT idCurso, nome, cargaHoraria, preco, nomeArquivo, caminho, descricao FROM curso";
        ArrayList<Curso> lista = new ArrayList<>();
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Curso cs = new Curso();
                
                cs.setIdCurso(rs.getInt("idCurso"));
                cs.setNome(rs.getString("nome"));
                cs.setCargaHoraria(rs.getInt("cargaHoraria"));
                cs.setPreco(rs.getDouble("preco"));
                cs.setNomeArquivo(rs.getString("nomeArquivo"));
                cs.setCaminho(rs.getString("caminho"));
                cs.setDescricao(rs.getString("descricao"));
                
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
                sql = "INSERT INTO curso (nome, cargaHoraria, preco, nomeArquivo, caminho, descricao) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE curso set nome = ?, cargaHoraria = ?, preco = ?, nomeArquivo = ?, caminho = ?, "
                        + "descricao = ? WHERE idCurso = ?";
            }
            ps = con.prepareStatement(sql);
            if (cs.getIdCurso() == 0) {
                ps.setString(1, cs.getNome());
                ps.setInt(2, cs.getCargaHoraria());
                ps.setDouble(3, cs.getPreco());
                ps.setString(4, cs.getNomeArquivo());
                ps.setString(5, cs.getCaminho());
                ps.setString(6, cs.getDescricao());
            } else {
                ps.setString(1, cs.getNome());
                ps.setInt(2, cs.getCargaHoraria());
                ps.setDouble(3, cs.getPreco());
                ps.setString(4, cs.getNomeArquivo());
                ps.setString(5, cs.getCaminho());
                ps.setString(6, cs.getDescricao());

            }
            if (cs.getIdCurso() > 0) {
                ps.setInt(7, cs.getIdCurso());
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
        Curso cs = new Curso();
        String sql = "SELECT idCurso, nome, cargaHoraria, preco, nomeArquivo, caminho, descricao "
                + "FROM curso WHERE idCurso = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCurso);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                cs.setIdCurso(rs.getInt("idCurso"));
                cs.setNome(rs.getString("nome"));
                cs.setCargaHoraria(rs.getInt("cargaHoraria"));
                cs.setPreco(rs.getDouble("preco"));
                cs.setNomeArquivo(rs.getString("nomeArquivo"));
                cs.setCaminho(rs.getString("caminho"));
                cs.setDescricao(rs.getString("descricao"));
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao listar o curso: "
                    + e.getMessage());
        }
        return cs;
    }
}
