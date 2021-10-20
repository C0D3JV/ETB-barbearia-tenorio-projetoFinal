package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDAO {

    public ArrayList<Menu> getLista() {
        ArrayList<Menu> menus = new ArrayList<>();
        String sql = "SELECT idMenu, nome, link, icone, exibir FROM menu";
        try {

            Connection con = ConexaoFactory.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Menu m = new Menu();
                m.setIdMenu(rs.getInt("idMenu"));
                m.setNome(rs.getString("nome"));
                m.setLink(rs.getString("link"));
                m.setIcone(rs.getString("icone"));
                m.setExibir(rs.getInt("exibir"));
                menus.add(m);
            }

            ConexaoFactory.close(con);

        } catch (SQLException e) {
            System.out.println("Falha ao listar os menus:" + e.getMessage());
        }
        return menus;
    }

    public boolean gravar(Menu m) {
        Connection con = null;
        String sql;
        PreparedStatement ps = null;

        try {
            con = ConexaoFactory.conectar();
            if (m.getIdMenu() == 0) {
                sql = "insert into menu (nome, link, icone, exibir) "
                        + "values (?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, m.getNome());
                ps.setString(2, m.getLink());
                ps.setString(3, m.getIcone());
                ps.setInt(4, m.getExibir());

            } else {
                sql = "UPDATE menu set nome = ?, link = ?, icone = ?, "
                        + "exibir = ? WHERE idMenu = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, m.getNome());
                ps.setString(2, m.getLink());
                ps.setString(3, m.getIcone());
                ps.setInt(4, m.getExibir());
                ps.setInt(5, m.getIdMenu());

            }

            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;
        } catch (SQLException e) {
            System.out.println("Falha ao gravar o menu na base de dados: "
                    + e.getMessage());
            return false;
        }

    }

    public boolean deletar(int idMenu) {
        String sql = "delete from menu where idMenu = ?";

        try {
            Connection con = ConexaoFactory.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMenu);
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;
        } catch (SQLException e) {
            System.out.println("Falha ao excluir o menu da base de dados: "
                    + e.getMessage());
            return false;
        }

    }

    public Menu getCarregarPorId(int idMenu) {
        String sql = "SELECT idMenu,nome, link, icone, exibir "
                + "FROM menu WHERE idMenu = ?";
        Menu menu = new Menu();
        try {
            Connection con = ConexaoFactory.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMenu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                menu.setIdMenu(rs.getInt("idMenu"));
                menu.setNome(rs.getString("nome"));
                menu.setLink(rs.getString("link"));
                menu.setIcone(rs.getString("icone"));
                menu.setExibir(rs.getInt("exibir"));
            }

            ConexaoFactory.close(con);
        } catch (SQLException e) {
            System.out.println("Falha ao listar o menu da base dados: "
                    + e.getMessage());
        }
        return menu;
    }

}
