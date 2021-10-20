package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.PerfilDAO;

@WebServlet(name = "GerenciarMenuPerfil",
        urlPatterns = {"/gerenciarMenuPerfil"})
public class GerenciarMenuPerfil extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String acao = request.getParameter("acao");
        String idPerfil = request.getParameter("idPerfil");
        try {
            PerfilDAO pdao = new PerfilDAO();
            Perfil p = new Perfil();
            if (acao.equals("gerenciar")) {
                p = pdao.getCarregarPorId(Integer.parseInt(idPerfil));
                if (p.getIdPerfil() > 0) {
                    RequestDispatcher dispatcher
                            = getServletContext().
                                    getRequestDispatcher("/cadastrarMenuPerfil.jsp");
                    request.setAttribute("perfilv", p);
                    dispatcher.forward(request, response);
                }
            } else {
                mensagem = "Perfil não encontrado na base de dados";
            }

            if (acao.equals("desvincular")) {
                String idMenu = request.getParameter("idMenu");
                if (idMenu.equals("")) {
                    mensagem = "O campo código do menu deve ser selecionado!";
                } else {
                    if (pdao.desvincular(Integer.parseInt(idMenu),
                            Integer.parseInt(idPerfil))) {
                        mensagem = "Menu foi desvinculado com sucesso!";
                    } else {
                        mensagem = "Erro ao desvincular o menu!";

                    }
                }
            }

        } catch (Exception e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();

        }
        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarMenuPerfil?acao=gerenciar&idPerfil="
                + idPerfil + "';"
                + "</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idPerfil = request.getParameter("idPerfil");
        System.out.println("IdPerfil: " + idPerfil + "\n");
        String idMenu = request.getParameter("idMenu");
        System.out.println("IdMenu: " + idMenu + "\n");
        String mensagem = "";
        try {
            if (idPerfil.equals("") || idMenu.equals("")) {
                mensagem = "Os campos obrigatórios devem ser selecionados";
            } else {
                PerfilDAO pdao = new PerfilDAO();
                if (pdao.vincular(Integer.parseInt(idMenu),
                        Integer.parseInt(idPerfil))) {
                    mensagem = "Menu vinculado com sucesso!";
                } else {
                    mensagem = "Erro ao vincular o menu ao perfil";
                }
            }
        } catch (Exception e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }
        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarMenuPerfil?acao=gerenciar&idPerfil=" + idPerfil + "';"
                + "</script>");
    }

}
