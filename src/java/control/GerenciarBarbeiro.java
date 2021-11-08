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
import model.Usuario;
import model.Barbeiro;
import model.BarbeiroDAO;

@WebServlet(name = "GerenciarBarbeiro", urlPatterns = {"/gerenciarBarbeiro"})
public class GerenciarBarbeiro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idBarbeiro = request.getParameter("idBarbeiro");
        String mensagem = "";

        Barbeiro b = new Barbeiro();

        try {
            BarbeiroDAO bdao = new BarbeiroDAO();
            if (acao.equalsIgnoreCase("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    b.setIdBarbeiro(Integer.parseInt(idBarbeiro));
                    if (bdao.deletar(b.getIdBarbeiro())) {
                        mensagem = "Barbeiro excluído da base dados!";
                    } else {
                        mensagem = "Falha ao excluir o barbeiro da base de dados!";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            } // fim do if deletar

            if (acao.equalsIgnoreCase("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    b = bdao.getCarregarPorId(Integer.parseInt(idBarbeiro));
                    if (b.getIdBarbeiro() > 0) {
                        RequestDispatcher dispatcher
                                = getServletContext().
                                        getRequestDispatcher("/cadastrarBarbeiro.jsp");
                        request.setAttribute("barbeiro", b);
                        dispatcher.forward(request, response);
                    } else {
                        mensagem = "Barbeiro não encontrado na base de dados!";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            }// fim do if alterar

        } catch (Exception e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }
        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarBarbeiro.jsp';"
                + "</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idBarbeiro = request.getParameter("idBarbeiro");
        String nome = request.getParameter("nome");
        String dataNasc = request.getParameter("dataNasc");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String idUsuario = request.getParameter("idUsuario");
        String mensagem = "";

        Barbeiro b = new Barbeiro();

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (!idBarbeiro.isEmpty()) {
                b.setIdBarbeiro(Integer.parseInt(idBarbeiro));
            }
            if (nome.equals("")
                    || dataNasc.equals("")
                    || cpf.equals("")
                    || email.equals("")
                    || endereco.equals("")
                    || telefone.equals("")
                    || idUsuario.equals("")) {
                mensagem = "Os Campos obrigatórios devem ser preenchidos!";
            } else {
                b.setNome(nome);
                b.setDataNasc(df.parse(dataNasc));
                b.setCpf(cpf);
                b.setEmail(email);
                b.setEndereco(endereco);
                b.setTelefone(telefone);

                Usuario u = new Usuario();
                u.setIdUsuario(Integer.parseInt(idUsuario));
                b.setUsuario(u);

                try {
                    BarbeiroDAO bdao = new BarbeiroDAO();
                    if (bdao.gravar(b)) {
                        mensagem = "Barbeiro gravado com sucesso na base de dados!";

                    } else {
                        mensagem = "Falha ao gravar o barbeiro na base de dados!";
                    }
                } catch (Exception e) {
                    mensagem = "Erro: " + e.getMessage();
                    e.printStackTrace();
                }
                out.println(
                        "<script type='text/javascript'>"
                        + "alert('" + mensagem + "');"
                        + "location.href='listarBarbeiro.jsp';"
                        + "</script>");
            }
        } catch (ParseException pe) {
            mensagem = "Erro: " + pe.getMessage();
            pe.printStackTrace();
        }
    }
}
