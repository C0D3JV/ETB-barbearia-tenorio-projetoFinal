package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.Usuario;
import model.UsuarioDAO;

@WebServlet(name = "GerenciarLogin", urlPatterns = {"/gerenciarLogin"})
public class GerenciarLogin extends HttpServlet {

    private static HttpServletResponse response;

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessao = request.getSession();
        sessao.removeAttribute("ulogado");
        sessao.invalidate();
        response.sendRedirect("formLogin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        GerenciarLogin.response = response;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        ArrayList<String> erros = new ArrayList<>();
        if ((login == null) || (login.trim().isEmpty())) {
            erros.add("Preencha o login!");
        }
        if ((senha == null) || (senha.trim().isEmpty())) {
            erros.add("Preencha a senha!");
        }

        if (erros.size() > 0) {
            String campos = "";
            for (String erro : erros) {
                campos += "\\n-" + erro;
            }
            exibirMensagem("Preencha o(s) campo(s): " + campos);
        } else {
            Usuario u = new Usuario();
            UsuarioDAO udao = new UsuarioDAO();
            try {

                u = udao.getRecuperarUsuario(login);
                if ((u.getIdUsuario() > 0) && (u.getSenha().equalsIgnoreCase(senha.trim()))) {
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("ulogado", u);
                    response.sendRedirect("index.jsp");

                } else {
                    exibirMensagem("Login ou senha inválidos!");
                }
            } catch (Exception e) {
                System.out.println("Usuário ou perfil não encontrados: "
                        + e.getMessage());
            }
        }
    }

    private static void exibirMensagem(String mensagem) {
        try {
            PrintWriter out = response.getWriter();
            out.println(
                    "<script>alert('" + mensagem + "');"
                    + "history.back();"
                    + "</script>"
            );
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Usuario verificarAcesso(
            HttpServletRequest request, HttpServletResponse response) {
        Usuario u = null;
        GerenciarLogin.response = response;

        try {
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("ulogado") == null) {
                response.sendRedirect("formLogin.jsp");
            } else {
                String uri = request.getRequestURI();
                String queryString = request.getQueryString();
                if (queryString != null) {
                    uri += "?" + queryString;
                }
                u = (Usuario) request.getSession().getAttribute("ulogado");
                if (u == null) {
                    sessao.setAttribute("mensagem",
                            "Usuário não autenticado no sistema!");
                    response.sendRedirect("formLogin.jsp");

                } else {
                    for (Menu m : u.getPerfil().getMenus()) {
                        boolean possuiAcesso = false;
                        if (uri.contains(m.getLink())) {
                            possuiAcesso = true;
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            exibirMensagem("Falha: " + e.getMessage());
        }
        return u;

    }

    public static boolean verificarPermissao(
            HttpServletRequest request, HttpServletResponse response) {
        Usuario u = null;
        GerenciarLogin.response = response;
        boolean possuiAcesso = false;

        try {
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("ulogado") == null) {
                response.sendRedirect("formLogin.jsp");
            } else {
                String uri = request.getRequestURI();
                String queryString = request.getQueryString();
                if (queryString != null) {
                    uri += "?" + queryString;
                }
                u = (Usuario) request.getSession().getAttribute("ulogado");
                if (u == null) {
                    sessao.setAttribute("mensagem",
                            "Usuário não autenticado no sistema!");
                    response.sendRedirect("formLogin.jsp");

                } else {
                    for (Menu m : u.getPerfil().getMenus()) {
                        if (uri.contains(m.getLink())) {
                            possuiAcesso = true;
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            exibirMensagem("Falha: " + e.getMessage());
        }
        return possuiAcesso;

    }

}
