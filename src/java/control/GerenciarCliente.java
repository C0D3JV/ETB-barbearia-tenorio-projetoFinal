package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ClienteDAO;

@WebServlet(name = "GerenciarCliente", urlPatterns = {"/gerenciarCliente"})
public class GerenciarCliente extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
        
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String acao = request.getParameter("acao");
        String idCliente = request.getParameter("idCliente");
        Cliente c = new Cliente();
        ClienteDAO cdao = new ClienteDAO();
        try {
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    c = cdao.getCarregarPorId(Integer.parseInt(idCliente));
                    if(c.getIdCliente() > 0){
                        RequestDispatcher dispatcher =
                            getServletContext().
                                    getRequestDispatcher("/cadastrarCliente.jsp");
                    request.setAttribute("cliente", c);
                    dispatcher.forward(request, response);
                    
                    }else{
                        mensagem = "O Cliente não foi encontrado na base dados!";
                    }
                }else{
                    mensagem = "Acesso Negado!";
                }    
            }//fim do if alterar
            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    c.setIdCliente(Integer.parseInt(idCliente));
                    if(cdao.deletar(c.getIdCliente())){
                        mensagem = "Cliente excluído da base dados!";
                    }else{
                        mensagem = "Falha ao excluir o cliente da base de dados!";
                    }
                
                }else{
                    mensagem = "Acesso Negado!";  
                }
            }// fim do if deletar
            
        } catch (Exception e) {
                mensagem = "Erro: " + e.getMessage();
        }
         out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarCliente.jsp';"
                + "</script>");
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idCliente = request.getParameter("idCliente");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");

        String mensagem = "";
        Cliente c = new Cliente();
        ClienteDAO cdao = new ClienteDAO();
        try {
            if (!idCliente.isEmpty()) {
                c.setIdCliente(Integer.parseInt(idCliente));
            }

            if ((nome.equals("") || nome.isEmpty())
                    || (cpf.equals("") || cpf.isEmpty())
                    || (email.equals("") || email.isEmpty())
                    || (endereco.equals("") || endereco.isEmpty())
                    || (telefone.equals("") || telefone.isEmpty())){
                mensagem = "Campo obrigatório deve ser preenchido!";

            } else {
                c.setNome(nome);
                c.setCpf(cpf);
                c.setEmail(email);
                c.setEndereco(endereco);
                c.setTelefone(telefone);
                if(cdao.gravar(c)){
                    mensagem = "Cliente gravado com sucesso na base de dados!";
                    
                }else{
                    mensagem = "Falha ao gravar o cliente na base de dados!";
                }
            }
        } catch (Exception e) {
             mensagem = "Erro: " + e.getMessage();
            
        }
        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarCliente.jsp';"
                + "</script>");
    }
  
    

}
