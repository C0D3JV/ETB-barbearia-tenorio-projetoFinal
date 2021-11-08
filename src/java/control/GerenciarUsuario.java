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
import model.Usuario;
import model.UsuarioDAO;

@WebServlet(name = "GerenciarUsuario", urlPatterns = {"/gerenciarUsuario"})
public class GerenciarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GerenciarUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GerenciarUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idUsuario = request.getParameter("idUsuario");
        String mensagem = "";
        try {
            Usuario u = new Usuario();
            UsuarioDAO udao = new UsuarioDAO();
            if (acao.equals("alterar")) {
                u = udao.getCarregarPorId(
                        Integer.parseInt(idUsuario));
                if (u.getIdUsuario() > 0) {
                    RequestDispatcher dispatcher
                            = getServletContext().
                                    getRequestDispatcher("/cadastrarUsuario.jsp");
                    request.setAttribute("usuario", u);
                    dispatcher.forward(request, response);

                } else {
                    mensagem = "Usuário não encontrado na base de dados!";
                }

            }
            if (acao.equals("desativar")) {
                u.setIdUsuario(Integer.parseInt(idUsuario));
                if (udao.desativar(u)) {
                    mensagem = "Usuário desativado com sucesso!";
                } else {
                    mensagem = "Falha ao desativar o usuario";
                }
            }
            if (acao.equals("ativar")) {
                u.setIdUsuario(Integer.parseInt(idUsuario));
                if (udao.ativar(u)) {
                    mensagem = "Usuário ativado com sucesso!";
                } else {
                    mensagem = "Falha ao ativar o usuario";
                }
            }

        } catch (Exception e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarUsuario.jsp';"
                + "</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idUsuario = request.getParameter("idUsuario");
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");
        String idPerfil = request.getParameter("idPerfil");

        String mensagem = "";

        Usuario u = new Usuario();

        if (!(idUsuario.isEmpty())) {
            u.setIdUsuario(Integer.parseInt(idUsuario));
        }

        if (nome.equals("") || login.equals("") || senha.equals("")
                || status.equals("") || idPerfil.equals("")) {
            mensagem = "Os campos obrigatórios devem ser preenchidos!";
        } else {
            u.setNome(nome);
            u.setLogin(login);
            u.setSenha(senha);
            u.setStatus(Integer.parseInt(status));

            Perfil p = new Perfil();
            p.setIdPerfil(Integer.parseInt(idPerfil));

            u.setPerfil(p);
            try {
                UsuarioDAO udao = new UsuarioDAO();
                if (udao.gravar(u)) {
                    mensagem = "Usuário gravado com sucesso na base de dados!";
                } else {
                    mensagem = "Falha ao gravar o usuário na base de dados!";
                }
            } catch (Exception e) {
                mensagem = "Erro: " + e.getMessage();
                e.printStackTrace();
            }
            out.println(
                    "<script type='text/javascript'>"
                    + "alert('" + mensagem + "');"
                    + "location.href='listarUsuario.jsp';"
                    + "</script>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
