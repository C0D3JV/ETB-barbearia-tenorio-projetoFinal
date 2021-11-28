package control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Curso;
import model.CursoDAO;

@WebServlet(name = "GerenciarCurso", urlPatterns = {"/gerenciarCurso"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 10 MB
        maxFileSize = 1024 * 1024 * 10, // 1 GB
        maxRequestSize = 1024 * 1024 * 50)   	// 1 GB
public class GerenciarCurso extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String acao = request.getParameter("acao");
        String idCurso = request.getParameter("idCurso");
        String mensagem = "";

        try {
            Curso cs = new Curso();
            CursoDAO csdao = new CursoDAO();

            if (acao.equalsIgnoreCase("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    cs = csdao.getCarregarPorId(Integer.parseInt(idCurso));
                    if (cs.getIdCurso() > 0) {
                        RequestDispatcher dispatcher = getServletContext().
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

        } catch (NumberFormatException nfe) {
            mensagem = "Erro: " + nfe.getMessage();
            out.println(mensagem);
            nfe.printStackTrace();
        } catch (Exception e) {
            mensagem = "Erro: " + e.getMessage();
            out.println(mensagem);
            e.printStackTrace();
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idCurso = request.getParameter("idCurso");
        String nome = request.getParameter("nome");
        String cargaHoraria = request.getParameter("cargaHoraria");
        String preco = request.getParameter("preco");
        String descricao = request.getParameter("descricao");

        Part part = request.getPart("file");
        String fileName = extractFileName(part);

        String savePath
                = "C:\\Users\\Acer Aspire\\Documents\\ETB 4ºA\\"
                + "Desenv. de Projeto\\Barbearia Tenório\\"
                + "ProjetoBarbeariaV2\\web\\imagens\\" + fileName;
        File fileSaveDir = new File(savePath);
        part.write(savePath + File.separator);
        String filePath = savePath + File.separator + fileName;

        String mensagem = "";

        NumberFormat nf = new DecimalFormat("###,###.##");

        Curso cs = new Curso();
        
        try {
            Double valor = nf.parse(preco).doubleValue();
            if (!idCurso.isEmpty()) {
                cs.setIdCurso(Integer.parseInt(idCurso));
            }
            if (nome.equals("")
                    || cargaHoraria.equals("")
                    || preco.equals("")) {
                mensagem = "Os Campos obrigatórios devem ser preenchidos!";
            } else {
                cs.setNome(nome);
                cs.setCargaHoraria(Integer.parseInt(cargaHoraria));
                cs.setPreco(valor);
                cs.setNomeArquivo(fileName);
                cs.setCaminho(savePath);
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
            e.printStackTrace();
            out.println(mensagem);
        } catch (ParseException ex) {
            mensagem = "Erro: " + ex.getMessage();
            out.println(mensagem);
            ex.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarCurso.jsp';"
                + "</script>");
    }

    private String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
