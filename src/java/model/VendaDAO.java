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
                
                //Associação entre objeto venda e vários cursos
                v.setCursos(cursosRelacionadosPorVenda(idVenda));
                //Associação entre objeto venda e os vários objetos não cursos
                v.setNaoCursos(cursosNaoRelacionadosPorVenda(idVenda));
            }
            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao listar o perfil: "
                    + e.getMessage());
        }
        return v;
    }

    /*
        Retorna os cursos vinculados por venda baseados no idVenda
     */

    public ArrayList<Curso> cursosRelacionadosPorVenda(int idVenda)
            throws Exception {

        ArrayList<Curso> lista = new ArrayList<>();
        String sql = "SELECT cs.idCurso, cs.nome, cs.cargaHoraria, cs.preco, cs.imagem,"
                + " cs.descricao "
                + "FROM venda_curso as vdc, curso as cs "
                + "WHERE vdc.idCurso = cs.idCurso "
                + "AND vdc.idVenda = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenda);
            ResultSet rs = ps.executeQuery();

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
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<Curso> cursosNaoRelacionadosPorVenda(int idVenda)
            throws Exception {

        ArrayList<Curso> lista = new ArrayList<>();
        String sql = "SELECT cs.idCurso, cs.nome, cs.cargaHoraria, cs.preco, cs.imagem,"
                + " cs.descricao "
                + "FROM curso as cs "
                + "WHERE cs.idCurso "
                + "NOT IN(SELECT vdc.idCurso FROM venda_curso as vdc WHERE vdc.idVenda = ?); ";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenda);
            ResultSet rs = ps.executeQuery();

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
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }

    public boolean relacionar(int idCurso, int idVenda)
            throws Exception {
        String sql = "INSERT INTO venda_curso (idCurso, idVenda) VALUES (?, ?)";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCurso);
            ps.setInt(2, idVenda);
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println(
                    "Erro ao vincular o curso a venda: "
                    + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    public boolean desrelacionar(int idCurso, int idVenda)
            throws Exception {
        String sql = "DELETE FROM venda_curso "
                + "WHERE idVenda = ? AND idCurso= ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCurso);
            ps.setInt(2, idVenda);
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao desrelacionar o curso: "
                    + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
