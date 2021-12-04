package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
        if (acao.equals("listarData")) {
            String dataInicial = request.getParameter("dataInicial");
            System.out.println("Data Inicial: " + dataInicial);
            String dataFinal = request.getParameter("dataFinal");
            System.out.println("Data Final: " + dataFinal);

            try {
                SimpleDateFormat df
                        = new SimpleDateFormat("yyyy-MM-dd");
                VendaDAO vdao = new VendaDAO();
                ArrayList<Venda> vendas = new ArrayList<>();

                vendas = vdao.getVendaPorData(
                        df.parse(dataInicial), df.parse(dataFinal));
                RequestDispatcher dispatcher
                        = getServletContext().
                                getRequestDispatcher("/listarVendasPorData.jsp");
                request.setAttribute("vendas", vendas);
                dispatcher.forward(request, response);
            } catch (ParseException ex) {
                System.out.println("Erro Conversão de Datas: " + ex.getMessage());
                ex.printStackTrace();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
