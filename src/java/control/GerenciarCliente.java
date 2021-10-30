package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Turma;
import model.Usuario;
import model.Cliente;
import model.ClienteDAO;

@WebServlet(name = "GerenciarCliente", urlPatterns = {"/gerenciarCliente"})
public class GerenciarCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idCliente = request.getParameter("idCliente");
        String mensagem = "";

        Cliente ct = new Cliente();

        try {
            ClienteDAO ctdao = new ClienteDAO();
            if (acao.equalsIgnoreCase("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    ct.setIdCliente(Integer.parseInt(idCliente));
                    if (ctdao.deletar(ct.getIdCliente())) {
                        mensagem = "Cliente excluído da base dados!";
                    } else {
                        mensagem = "Falha ao excluir o cliente da base de dados!";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            } // fim do if deletar

            if (acao.equalsIgnoreCase("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    ct = ctdao.getCarregarPorId(Integer.parseInt(idCliente));
                    if (ct.getIdCliente() > 0) {
                        RequestDispatcher dispatcher
                                = getServletContext().
                                        getRequestDispatcher("/cadastrarCliente.jsp");
                        request.setAttribute("cliente", ct);
                        dispatcher.forward(request, response);
                    } else {
                        mensagem = "Cliente não encontrado na base de dados!";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            }// fim do if alterar

        } catch (Exception e) {
            mensagem = "Erro: " + e.getMessage();
            out.println(mensagem);
            e.printStackTrace();
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idCliente = request.getParameter("idCliente");
        String nome = request.getParameter("nome");
        String dataNasc = request.getParameter("dataNasc");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String idTurma = request.getParameter("idTurma");
        String idUsuario = request.getParameter("idUsuario");
        String mensagem = "";

        Cliente ct = new Cliente();

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (!idCliente.isEmpty()) {
                ct.setIdCliente(Integer.parseInt(idCliente));
            }
            if (nome.equals("")
                    || dataNasc.equals("")
                    || cpf.equals("")
                    || email.equals("")
                    || endereco.equals("")
                    || telefone.equals("")
                    || idTurma.equals("")
                    || idUsuario.equals("")) {
                mensagem = "Os Campos obrigatórios devem ser preenchidos!";
            } else {
                ct.setNome(nome);
                ct.setDataNasc(df.parse(dataNasc));
                ct.setCpf(cpf);
                ct.setEmail(email);
                ct.setEndereco(endereco);
                ct.setTelefone(telefone);

                Turma t = new Turma();
                t.setIdTurma(Integer.parseInt(idTurma));
                ct.setTurma(t);
                
                Usuario u = new Usuario();
                u.setIdUsuario(Integer.parseInt(idUsuario));
                ct.setUsuario(u);

                try {
                    ClienteDAO ctdao = new ClienteDAO();
                    if (ctdao.gravar(ct)) {
                        mensagem = "Cliente gravado com sucesso na base de dados!";

                    } else {
                        mensagem = "Falha ao gravar o cliente na base de dados!";
                    }
                } catch (Exception e) {
                    mensagem = "Erro: " + e.getMessage();
                    out.println(mensagem);
                    e.printStackTrace();
                }
                out.println(
                        "<script type='text/javascript'>"
                        + "alert('" + mensagem + "');"
                        + "location.href='listarCliente.jsp';"
                        + "</script>");
            }
        } catch (ParseException pe) {
            mensagem = "Erro: " + pe.getMessage();
            out.println(mensagem);
            pe.printStackTrace();
        }
    }
}