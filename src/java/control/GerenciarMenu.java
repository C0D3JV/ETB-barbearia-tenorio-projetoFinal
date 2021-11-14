package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Menu;
import model.MenuDAO;

@WebServlet(name = "GerenciarMenu", urlPatterns = {"/gerenciarMenu"})
public class GerenciarMenu extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String acao = request.getParameter("acao");
        String idMenu = request.getParameter("idMenu");
        Menu m = new Menu();
        MenuDAO mdao = new MenuDAO();
        try {
            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    m = mdao.getCarregarPorId(Integer.parseInt(idMenu));
                    if (m.getIdMenu() > 0) {
                        RequestDispatcher dispatcher
                                = getServletContext().
                                        getRequestDispatcher("/cadastrarMenu.jsp");
                        request.setAttribute("menu", m);
                        dispatcher.forward(request, response);
                    } else {
                        mensagem = "O Menu não foi encontrado na base dados!";
                    }
                } else {
                    mensagem = "Acesso negado!";
                }
            } //Fim do if alterar
            if (acao.equals("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    m.setIdMenu(Integer.parseInt(idMenu));
                    if (mdao.deletar(m.getIdMenu())) {
                        mensagem = "Menu excluído da base dados!";
                    } else {
                        mensagem = "Falha ao excluir o menu da base de dados";
                    }
                } else {
                    mensagem = "Acesso negado!";
                }

            }// fim do if excluir

        } catch (Exception e) {
            mensagem = "Erro: " + e.getMessage();
        }
        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarMenu.jsp';"
                + "</script>");

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idMenu = request.getParameter("idMenu");
        String nome = request.getParameter("nome");
        String link = request.getParameter("link");
        String icone = request.getParameter("icone");
        String exibir = request.getParameter("exibir");

        String mensagem = "";
        Menu m = new Menu();
        MenuDAO mdao = new MenuDAO();
        try {
            if (!idMenu.isEmpty()) {
                m.setIdMenu(Integer.parseInt(idMenu));
            }

            if ((nome.equals("") || nome.isEmpty())
                    || (link.equals("") || link.isEmpty())
                    || (exibir.equals("") || exibir.isEmpty())) {
                mensagem = "Campo obrigatório deve ser preenchido!";

            } else {
                m.setNome(nome);
                m.setLink(link);
                m.setIcone(icone);
                m.setExibir(Integer.parseInt(exibir));
                if (mdao.gravar(m)) {
                    mensagem = "Menu gravado com sucesso na base de dados!";

                } else {
                    mensagem = "Falha ao gravar o menu na base de dados!";
                }
            }
        } catch (Exception e) {
            mensagem = "Erro: " + e.getMessage();

        }
        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarMenu.jsp';"
                + "</script>");
    }
}
