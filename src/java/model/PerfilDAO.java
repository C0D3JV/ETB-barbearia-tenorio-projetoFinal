package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PerfilDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<Perfil> getLista(){
        String sql = "SELECT idPerfil, nome, dataCadastro FROM perfil";
        ArrayList<Perfil> perfis = new ArrayList<>();
        try {
           con = ConexaoFactory.conectar();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
               Perfil p = new Perfil();
               p.setIdPerfil(rs.getInt("idPerfil"));
               p.setNome(rs.getString("nome"));
               p.setDataCadastro(rs.getDate("dataCadastro"));
               perfis.add(p);
           }
            ConexaoFactory.close(con);
            
        } catch (SQLException e) {
            System.out.println(
                    "Falha ao listar os perfis da base de dados:" +
                    e.getMessage());
        }
        return perfis;
    }
    
    public boolean gravar(Perfil p){
        con = null;
        String sql;
        ps = null;
        try {
           con = ConexaoFactory.conectar();
           if(p.getIdPerfil() == 0){
               sql = "INSERT INTO perfil (nome, dataCadastro) VALUES (?, ?)";
               ps = con.prepareStatement(sql);
               ps.setString(1, p.getNome());
               ps.setDate(2, new Date(p.getDataCadastro().getTime()));
                       
           }else{
               sql = "UPDATE perfil set nome = ?, dataCadastro = ? WHERE idPerfil = ?";
               ps = con.prepareStatement(sql);
               ps.setString(1, p.getNome());
               ps.setDate(2, new Date(p.getDataCadastro().getTime()));
               ps.setInt(3, p.getIdPerfil());
           }
           ps.executeUpdate();
           ConexaoFactory.close(con);
           return true;
         
        } catch (SQLException e) {
            System.out.println("Falha ao gravar o perfil na base de dados:" 
                    + e.getMessage());
            return false;
        }
    }
    
    public boolean deletar(int idPerfil){
        String sql= "DELETE FROM perfil WHERE idPerfil = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPerfil);
            ps.executeUpdate();
            ConexaoFactory.close(con);
        } catch (SQLException e) {
            System.out.println("Falha ao excluir o perfil da base de dados" +
                    e.getMessage());
            return false;
        }
        return true;
    }
    
    public Perfil getCarregarPorId(int idPerfil)throws Exception{
        Perfil perfil = new Perfil();
        String sql = "SELECT idPerfil, nome, dataCadastro FROM perfil WHERE idPerfil = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPerfil);
            rs = ps.executeQuery();
            if(rs.next()){
                perfil.setIdPerfil(rs.getInt("idPerfil"));
                perfil.setNome(rs.getString("nome"));
                perfil.setDataCadastro(rs.getDate("dataCadastro"));
                //Associação entre objeto perfil e vários menus
                perfil.setMenus(menusVinculadosPorPerfil(idPerfil));
                //Associação entre objeto perfil e os vários objetos não menus
                perfil.setNaoMenus(menusNaoVinculadosPorPerfil(idPerfil));
            }
            ConexaoFactory.close(con);
           
        } catch (SQLException e) {
            System.out.println("Falha ao listar o perfil: " +
                    e.getMessage());
        }
        return perfil;
    }
    /*
        Retorna os menus vinculados por perfil baseados no idPerfil
    */
    
    public ArrayList<Menu> menusVinculadosPorPerfil(int idPerfil)
            throws Exception{
        
        ArrayList<Menu> lista = new ArrayList<>();
        String sql = "SELECT m.idMenu, m.nome, m.link, " + 
                    "m.icone, m.exibir " +
                    "FROM menu_perfil as mp, menu as m " +
                    "WHERE mp.idMenu = m.idMenu " +
                    "AND mp.idPerfil = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPerfil);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
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
            throws Exception{
        
        ArrayList<Menu> lista = new ArrayList<>();
        String sql = "SELECT m.idMenu, m.nome, m.link, m.icone, m.exibir " +
                "FROM menu as m " +
                "WHERE m.idMenu " +
                "NOT IN(SELECT mp.idMenu FROM menu_perfil as mp WHERE mp.idPerfil = ?);";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPerfil);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
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
            throws Exception{
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
        String sql = "DELETE FROM menu_perfil " +
                "WHERE idMenu = ? AND idPerfil = ?";
        try {
            con = ConexaoFactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMenu);
            ps.setInt(2, idPerfil);
            ps.executeUpdate();
            ConexaoFactory.close(con);
            return true;
                 
        } catch (SQLException e) {
            System.out.println("Erro ao desvincular o menu: " +
                    e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
