package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Barbeiro;
import model.Curso;
import model.Turma;
import model.TurmaDAO;

@WebServlet(name = "GerenciarTurma", urlPatterns = {"/gerenciarTurma"})
public class GerenciarTurma extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idTurma = request.getParameter("idTurma");
        String mensagem = "";

        Turma t = new Turma();

        try {
            TurmaDAO tdao = new TurmaDAO();
            if (acao.equalsIgnoreCase("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    t.setIdTurma(Integer.parseInt(idTurma));
                    if (tdao.deletar(t.getIdTurma())) {
                        mensagem = "Turma excluída da base dados!";
                    } else {
                        mensagem = "Falha ao excluir a turma da base de dados!";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            } // fim do if deletar

            if (acao.equalsIgnoreCase("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    t = tdao.getCarregarPorId(Integer.parseInt(idTurma));
                    if (t.getIdTurma() > 0) {
                        RequestDispatcher dispatcher
                                = getServletContext().
                                        getRequestDispatcher("/cadastrarTurma.jsp");
                        request.setAttribute("turma", t);
                        dispatcher.forward(request, response);
                    } else {
                        mensagem = "Turma não encontrada na base de dados!";
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
                + "location.href='listarTurma.jsp';"
                + "</script>");

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idTurma = request.getParameter("idTurma");
        String nome = request.getParameter("nome");
        String qtdAluno = request.getParameter("qtdAluno");
        String ano = request.getParameter("ano");
        String semestre = request.getParameter("semestre");
        String turno = request.getParameter("turno");
        String idBarbeiro = request.getParameter("idBarbeiro");
        String idCurso = request.getParameter("idCurso");
        String mensagem = "";

        Turma t = new Turma();

        try {
            if (!idTurma.isEmpty()) {
                t.setIdTurma(Integer.parseInt(idTurma));
            }
            if (nome.equals("")
                    || qtdAluno.equals("")
                    || ano.equals("")
                    || semestre.equals("")
                    || turno.equals("")
                    || idBarbeiro.equals("")
                    || idCurso.equals("")) {
                mensagem = "Os Campos obrigatórios devem ser preenchidos!";
            } else {
                t.setNome(nome);
                t.setQtdAluno(Integer.parseInt(qtdAluno));
                t.setAno(ano);
                t.setSemestre(semestre);
                t.setTurno(turno);
                
                Curso cs = new Curso();
                cs.setIdCurso(Integer.parseInt(idCurso));
                t.setCurso(cs);

                Barbeiro b = new Barbeiro();
                b.setIdBarbeiro(Integer.parseInt(idBarbeiro));
                t.setBarbeiro(b);
                
                try {
                    TurmaDAO tdao = new TurmaDAO();
                    if (tdao.gravar(t)) {
                        mensagem = "Turma gravada com sucesso na base de dados!";

                    } else {
                        mensagem = "Falha ao gravar a turma na base de dados!";
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
        } catch (NullPointerException npe) {
            mensagem = "Erro: " + npe.getMessage();
            out.println(mensagem);
        }
    }
}
