package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Curso;
import model.Aula;
import model.AulaDAO;

@WebServlet(name = "GerenciarAula", urlPatterns = {"/gerenciarAula"})
public class GerenciarAula extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idAula = request.getParameter("idAula");
        String mensagem = "";

        Aula a = new Aula();

        try {
            AulaDAO adao = new AulaDAO();
            if (acao.equalsIgnoreCase("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    a.setIdAula(Integer.parseInt(idAula));
                    if (adao.deletar(a.getIdAula())) {
                        mensagem = "Aula excluída da base dados!";
                    } else {
                        mensagem = "Falha ao excluir a aula da base de dados!";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            } // fim do if deletar

            if (acao.equalsIgnoreCase("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    a = adao.getCarregarPorId(Integer.parseInt(idAula));
                    if (a.getIdAula() > 0) {
                        RequestDispatcher dispatcher
                                = getServletContext().
                                        getRequestDispatcher("/cadastrarAula.jsp");
                        request.setAttribute("aula", a);
                        dispatcher.forward(request, response);
                    } else {
                        mensagem = "Aula não encontrada na base de dados!";
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
                + "location.href='listarAula.jsp';"
                + "</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idAula = request.getParameter("idAula");
        String materia = request.getParameter("materia");
        String dataInicio = request.getParameter("dataInicio");
        String dataFim = request.getParameter("dataFim");
        String horario = request.getParameter("horario");
        String qtdAula = request.getParameter("qtdAula");
        String idCurso = request.getParameter("idCurso");
        String mensagem = "";

        Aula a = new Aula();

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            
            if (!idAula.isEmpty()) {
                a.setIdAula(Integer.parseInt(idAula));
            }
            if (materia.equals("")
                    || dataInicio.equals("")
                    || dataFim.equals("")
                    || horario.equals("")
                    || qtdAula.equals("")
                    || idCurso.equals("")) {
                mensagem = "Os Campos obrigatórios devem ser preenchidos!";
            } else {
                a.setMateria(materia);
                a.setDataInicio(df.parse(dataInicio));
                a.setDataFim(df.parse(dataFim));
                a.setHorario(horario);
                a.setQtdAula(Integer.parseInt(qtdAula));

                Curso cs = new Curso();
                cs.setIdCurso(Integer.parseInt(idCurso));
                a.setCurso(cs);

                try {
                    AulaDAO adao = new AulaDAO();
                    if (adao.gravar(a)) {
                        mensagem = "Aula gravada com sucesso na base de dados!";

                    } else {
                        mensagem = "Falha ao gravar a aula na base de dados!";
                    }
                } catch (Exception e) {
            mensagem = "Erro: " + e.getMessage();
            out.println(mensagem);
            }

            out.println(
                    "<script type='text/javascript'>"
                    + "alert('" + mensagem + "');"
                    + "location.href='listarTurma.jsp';"
                    + "</script>");
        } 
        } catch (ParseException pe) {
            mensagem = "Erro: " + pe.getMessage();
            pe.printStackTrace();
        }
    }
}
