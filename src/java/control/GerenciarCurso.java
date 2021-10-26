//Arrumar o preco que não está retornando na hora de alterar

package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Curso;
import model.CursoDAO;

@WebServlet(name = "GerenciarCurso", urlPatterns = {"/gerenciarCurso"})
public class GerenciarCurso extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idCurso = request.getParameter("idCurso");
        String mensagem = "";

        Curso cs = new Curso();

        try {
            CursoDAO csdao = new CursoDAO();
            if (acao.equalsIgnoreCase("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    cs.setIdCurso(Integer.parseInt(idCurso));
                    if (csdao.deletar(cs.getIdCurso())) {
                        mensagem = "Curso excluído da base de dados!";
                    } else {
                        mensagem = "Erro ao excluir o Curso da base de dados";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            } // fim do if deletar

            if (acao.equalsIgnoreCase("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    cs = csdao.getCarregarPorId(Integer.parseInt(idCurso));
                    if (cs.getIdCurso() > 0) {
                        RequestDispatcher dispatcher
                                = getServletContext().
                                        getRequestDispatcher("/cadastrarCurso.jsp");
                        request.setAttribute("curso", cs);
                        dispatcher.forward(request, response);
                    } else {
                        mensagem = "Curso não encontrado na base de dados!";
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
                + "location.href='listarCurso.jsp';"
                + "</script>");

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idCurso = request.getParameter("idCurso");
        String nome = request.getParameter("nome");
        String cargaHoraria = request.getParameter("cargaHoraria");
        String preco = request.getParameter("preco");
        String imagem = request.getParameter("imagem");
        String descricao = request.getParameter("descricao");
        String mensagem = "";

        Curso cs = new Curso();

        try {
            if (!idCurso.isEmpty()) {
                cs.setIdCurso(Integer.parseInt(idCurso));
            }
            
            if (nome.equals("")
                    || cargaHoraria.equals("")
                    || preco.equals("")) {
                mensagem = "Os Campos obrigatórios devem ser preenchidos!";
            }
             else {
                cs.setNome(nome);
                cs.setCargaHoraria(Integer.parseInt(cargaHoraria));
                cs.setPreco(Double.parseDouble(preco));
                cs.setImagem(imagem);
                cs.setDescricao(descricao);
                if (cs.gravar()) {
                    mensagem = "Dados do Curso gravados na base de dados!";
                } else {
                    mensagem = "Erro ao gravar na base de dados!";
                }
            }

        } catch (SQLException | NumberFormatException e) {
            mensagem = "Erro: " + e.getMessage();
            out.println(mensagem);
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarCurso.jsp';"
                + "</script>");
    }
}
