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
import model.Venda;
import model.VendaCurso;
import model.VendaDAO;

@WebServlet(name = "GerenciarVenda", urlPatterns = {"/gerenciarVenda"})
public class GerenciarVenda extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String mensagem = "";

        try {
            Venda v = (Venda) session.getAttribute("venda");
            VendaDAO vdao = new VendaDAO();
            if (vdao.registrar(v)) {
                mensagem = "Venda realizada com sucesso!";
            } else {
                mensagem = "Falha ao registrar a venda!";
            }

        } catch (Exception e) {
            out.print("Erro: " + e.getMessage());
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarVenda.jsp';"
                + "</script>");
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String acao = request.getParameter("acao");
        System.out.println("Ação: " + acao);
        if (acao.equalsIgnoreCase("alterarQtd")) {
            Venda v = (Venda) session.getAttribute("venda");
            ArrayList<VendaCurso> carrinho = v.getCarrinho();
            String[] qtd = request.getParameterValues("qtd");
            for (int i = 0; i < carrinho.size(); i++) {
                carrinho.get(i).setQtd(Integer.parseInt(qtd[i]));
            }
            session.setAttribute("venda", v);
            response.sendRedirect("formFinalizarVenda.jsp");
        }

    }
}
