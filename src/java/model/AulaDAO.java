package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;

public class AulaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Aula> getLista() throws Exception {
        ArrayList<Aula> lista = new ArrayList<>();
        String sql = "SELECT cs.idCurso, cs.nome, a.idAula, a.materia, "
                + "a.dataInicio, a.dataFim, a.horario, a.qtdAula, a.idCurso "
                + "FROM curso cs "
                + "INNER JOIN aula a "
                + "ON cs.idCurso = a.idCurso ";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Aula a = new Aula();
                a.setIdAula(rs.getInt("a.idAula"));
                a.setMateria(rs.getString("a.materia"));
                a.setDataInicio(rs.getDate("a.dataInicio"));
                a.setDataFim(rs.getDate("a.dataFim"));
                a.setHorario(rs.getTime("a.horario"));
                a.setQtdAula(rs.getInt("a.qtdAula"));

                Curso cs = new Curso();
                cs.setIdCurso(rs.getInt("cs.idCurso"));
                cs.setNome(rs.getString("cs.nome"));
                

                a.setCurso(cs);
                lista.add(a);

            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println(
                    "Falha ao listar as aulas da base de dados:"
                    + e.getMessage());
        }
        return lista;
    }

    public boolean gravar(Aula a) throws Exception {
        String sql;
        try {
            con = ConexaoFactory.conectar();
            if (a.getIdAula() == 0) {
                sql = "INSERT INTO aula (materia, dataInicio, dataFim, horario, qtdAula, idCurso) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE aula set materia = ?, dataInicio = ?, dataFim = ?, horario = ?, "
                        + "qtdAula = ?, idCurso = ? "
                        + "WHERE idAula = ?";
            }

            ps = con.prepareStatement(sql);
            ps.setString(1, a.getMateria());
            ps.setDate(2, new Date(a.getDataInicio().getTime()));
            ps.setDate(3, new Date(a.getDataFim().getTime()));
            ps.setTime(4, new Time(a.getHorario().getTime()));
            ps.setInt(5, a.getQtdAula());
            ps.setInt(6, a.getCurso().getIdCurso());

            if (a.getIdAula() > 0) {
                ps.setInt(7, a.getIdAula());
            }
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println("Falha ao gravar a aula na base de dados:"
                    + e.getMessage());
            return false;
        }
    }

    public boolean deletar(int idAula) {
        String sql = "DELETE FROM aula WHERE idAula = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idAula);
            ps.executeUpdate();
            ConexaoFactory.close(con);
        } catch (SQLException e) {
            System.out.println("Falha ao excluir a aula da base de dados"
                    + e.getMessage());
            return false;
        }
        return true;
    }

    public Aula getCarregarPorId(int idAula) throws Exception {
        Aula a = new Aula();
        String sql = "SELECT cs.idCurso, cs.nome, a.idAula, a.materia, "
                + "a.dataInicio, a.dataFim, a.horario, a.qtdAula, a.idCurso "
                + "FROM curso cs "
                + "INNER JOIN aula a "
                + "ON cs.idCurso = a.idCurso "
                + "WHERE a.idAula = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idAula);
            rs = ps.executeQuery();
            if (rs.next()) {
                a.setIdAula(rs.getInt("a.idAula"));
                a.setMateria(rs.getString("a.materia"));
                a.setDataInicio(rs.getDate("a.dataInicio"));
                a.setDataFim(rs.getDate("a.dataFim"));
                a.setHorario(rs.getTime("a.horario"));
                a.setQtdAula(rs.getInt("a.qtdAula"));

                Curso cs = new Curso();
                cs.setIdCurso(rs.getInt("cs.idCurso"));
                cs.setNome(rs.getString("cs.nome"));

                a.setCurso(cs);
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao listar a aula: "
                    + e.getMessage());
        }
        return a;
    }
}
