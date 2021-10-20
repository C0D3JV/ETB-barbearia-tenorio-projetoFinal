package model;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    // refazer tudo de aluno para cliente :( ¬¬
    public ArrayList<Cliente> getLista(){
        String sql = "SELECT idCliente, nome, dataNasc, cpf, email, endereco, telefone FROM aluno";
        ArrayList<Aluno> alunos = new ArrayList<>();
        try {
           Connection con = ConexaoFactory.conectar();
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               Aluno a = new Aluno();
               a.setIdAluno(rs.getInt("idAluno"));
               a.setNome(rs.getString("nome"));
               a.setDataNasc(rs.getDate("dataNasc"));
               a.setCpf(rs.getString("cpf"));
               a.setEmail(rs.getString("email"));
               a.setEndereco(rs.getString("endereco"));
               a.setTelefone(rs.getString("telefone"));
               
               alunos.add(a);
           }
            ConexaoFactory.close(con);
            
        } catch (SQLException e) {
            System.out.println(
                    "Falha ao listar os alunos da base de dados:" +
                    e.getMessage());
        }
        return alunos;
    }
    
    public boolean gravar(Aluno a){
        Connection con = null;
        String sql;
        PreparedStatement ps = null;
        try {
           con = ConexaoFactory.conectar();
           if(a.getIdAluno() == 0){
               sql = "INSERT INTO aluno (nome, dataNasc, cpf, email, endereco, telefone) VALUES (?, ?, ?, ?, ?, ?)";
               ps = con.prepareStatement(sql);
               ps.setString(1, a.getNome());
               ps.setDate(2, new Date(a.getDataNasc().getTime()));
               ps.setString(3, a.getCpf());
               ps.setString(4, a.getEmail());
               ps.setString(5, a.getEndereco());
               ps.setString(6, a.getTelefone());
                       
           }else{
               sql = "UPDATE aluno set nome = ?, dataNasc = ?, cpf = ?, email = ?, endereco = ?, telefone = ?" +
                     "WHERE idAluno = ?";
               ps = con.prepareStatement(sql);
               ps.setString(1, a.getNome());
               ps.setDate(2, new Date(a.getDataNasc().getTime()));
               ps.setString(3, a.getCpf());
               ps.setString(4, a.getEmail());
               ps.setString(5, a.getEndereco());
               ps.setString(6, a.getTelefone());
               ps.setInt(7, a.getIdAluno());

           }
           ps.executeUpdate();
           ConexaoFactory.close(con);
           return true;
         
        } catch (SQLException e) {
            System.out.println("Falha ao gravar o aluno na base de dados:" 
                    + e.getMessage());
            return false;
        }
        
    }
    
    public boolean deletar(int idAluno){
        String sql= "DELETE FROM aluno WHERE idAluno = ?";
        try {
            Connection con = ConexaoFactory.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAluno);
            ps.executeUpdate();
            ConexaoFactory.close(con);
        } catch (SQLException e) {
            System.out.println("Falha ao excluir o aluno da base de dados" +
                    e.getMessage());
            return false;
        }
        return true;
    }
    
    public Aluno getCarregarPorId(int idAluno)throws SQLException{
        Aluno aluno = new Aluno();
        String sql = "SELECT idAluno, nome, dataNasc, cpf, email, endereco, telefone FROM aluno WHERE idAluno = ?";
        try {
            Connection con = ConexaoFactory.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAluno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                aluno.setIdAluno(rs.getInt("idAluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setDataNasc(rs.getDate("dataNasc"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setEmail(rs.getString("email"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setTelefone(rs.getString("telefone"));
            }
            ConexaoFactory.close(con);
           
        } catch (SQLException e) {
            System.out.println("Falha ao listar o aluno: " +
                    e.getMessage());
        }
        return aluno;
    }
    
}