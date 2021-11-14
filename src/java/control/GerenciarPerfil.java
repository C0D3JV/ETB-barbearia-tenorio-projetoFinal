package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.PerfilDAO;

@WebServlet(name = "GerenciarPerfil", urlPatterns = {"/gerenciarPerfil"})
public class GerenciarPerfil extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";

        Perfil p = new Perfil();

        try {
            PerfilDAO pdao = new PerfilDAO();
            if (acao.equalsIgnoreCase("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    p.setIdPerfil(Integer.parseInt(idPerfil));
                    if (pdao.deletar(p.getIdPerfil())) {
                        mensagem = "Perfil excluído da base de dados!";
                    } else {
                        mensagem = "Erro ao excluir o perfil da base de dados";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            } // fim do if deletar

            if (acao.equalsIgnoreCase("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    p = pdao.getCarregarPorId(Integer.parseInt(idPerfil));
                    if (p.getIdPerfil() > 0) {
                        RequestDispatcher dispatcher
                                = getServletContext().
                                        getRequestDispatcher("/cadastrarPerfil.jsp");
                        request.setAttribute("perfil", p);
                        dispatcher.forward(request, response);
                    } else {
                        mensagem = "Perfil não encontrado na base de dados!";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }

            }// fim do if alterar

        } catch (Exception e) {
            mensagem = "Erro: " + e.getMessage();
            out.println(mensagem);
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarPerfil.jsp';"
                + "</script>");

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String nome = request.getParameter("nome");
        String idPerfil = request.getParameter("idPerfil");
        String dataCadastro = request.getParameter("dataCadastro");
        String mensagem = "";

        Perfil p = new Perfil();

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (!idPerfil.isEmpty()) {
                p.setIdPerfil(Integer.parseInt(idPerfil));
            }
            if (nome.equals("") || nome.isEmpty()) {
                mensagem = "O campo nome é obrigatório!";
            } else {
                p.setNome(nome);
                p.setDataCadastro(df.parse(dataCadastro));
                if (p.gravar()) {
                    mensagem = "Dados do Perfil gravados na base de dados!";
                } else {
                    mensagem = "Erro ao gravar na base de dados!";
                }
            }

        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            out.println(mensagem);

        } catch (ParseException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarPerfil.jsp';"
                + "</script>");

    }

}
