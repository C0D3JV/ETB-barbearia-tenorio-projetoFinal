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
import model.Curso;
import model.CursoDAO;
import model.Venda;
import model.VendaCurso;

@WebServlet(name = "GerenciarCarrinho", urlPatterns = {"/gerenciarCarrinho"})
public class GerenciarCarrinho extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            Venda v = (Venda) session.getAttribute("venda");
            ArrayList<VendaCurso> carrinho = v.getCarrinho();
            String acao = request.getParameter("acao");
            System.out.println("Ação: " + acao);
            String idCurso = request.getParameter("idCurso");
            System.out.println("IdCurso: " + idCurso);
            String qtd = request.getParameter("qtd");
            System.out.println("Quantidade: " + qtd);
            CursoDAO csdao = new CursoDAO();
            if(acao.equals("add")){
                Curso cs = new Curso();
                cs = csdao.getCarregarPorId(
                        Integer.parseInt(idCurso));
                VendaCurso vcs = new VendaCurso();
                vcs.setCurso(cs);
                vcs.setQtd(Integer.parseInt(qtd));
                vcs.setPrecoVendido(cs.getPreco());
                carrinho.add(vcs);
                v.setCarrinho(carrinho);
                session.setAttribute("venda", v);
                response.sendRedirect("formVenda.jsp?acao=c");
            
            } else if(acao.equals("del")){
                int index = Integer.parseInt(
                        request.getParameter("index"));
                carrinho.remove(index);
                v.setCarrinho(carrinho);
                session.setAttribute("venda", v);
                response.sendRedirect("formFinalizarVenda.jsp");
            }
            
        } catch (Exception e ) {
            out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
