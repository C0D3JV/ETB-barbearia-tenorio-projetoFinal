package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Usuario;
import model.Venda;
import model.VendaDAO;

@WebServlet(name = "GerenciarVenda", urlPatterns = {"/gerenciarVenda"})
public class GerenciarVenda extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idVenda = request.getParameter("idVenda");
        String mensagem = "";

        Venda v = new Venda();

        try {
            VendaDAO vdao = new VendaDAO();
            if (acao.equalsIgnoreCase("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    v.setIdVenda(Integer.parseInt(idVenda));
                    if (vdao.deletar(v.getIdVenda())) {
                        mensagem = "Venda excluída da base de dados!";
                    } else {
                        mensagem = "Erro ao excluir a venda da base de dados";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            } // fim do if deletar

            if (acao.equalsIgnoreCase("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    v = vdao.getCarregarPorId(Integer.parseInt(idVenda));
                    if (v.getIdVenda() > 0) {
                        RequestDispatcher dispatcher
                                = getServletContext().
                                        getRequestDispatcher("/cadastrarVenda.jsp");
                        request.setAttribute("venda", v);
                        dispatcher.forward(request, response);
                    } else {
                        mensagem = "Venda não encontrada na base de dados!";
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
                + "location.href='listarVenda.jsp';"
                + "</script>");

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idVenda = request.getParameter("idVenda");
        String dataVenda = request.getParameter("dataVenda");
        String precoTotal = request.getParameter("precoTotal");
        String idCliente = request.getParameter("idCliente");
        String idUsuario = request.getParameter("idUsuario");
        String mensagem = "";

        NumberFormat nf = new DecimalFormat("###,###.##");

        Venda v = new Venda();

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Double preco = nf.parse(precoTotal.toString()).doubleValue();
            if (!idVenda.isEmpty()) {
                v.setIdVenda(Integer.parseInt(idVenda));
            }
            if (dataVenda.equals("")
                    || precoTotal.equals("")
                    || idCliente.equals("")
                    || idUsuario.equals("")) {
                mensagem = "Os Campos obrigatórios devem ser preenchidos!";
            } else {
                v.setDataVenda(df.parse(dataVenda));
                v.setPrecoTotal(preco);

                Cliente ct = new Cliente();
                ct.setIdCliente(Integer.parseInt(idCliente));
                v.setCliente(ct);

                Usuario u = new Usuario();
                u.setIdUsuario(Integer.parseInt(idUsuario));
                v.setUsuario(u);

                if (v.gravar()) {
                    mensagem = "Dados da Venda gravadas na base de dados!";
                } else {
                    mensagem = "Erro ao gravar na base de dados!";
                }
            }
        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            out.println(mensagem);
        } catch (ParseException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listarVenda.jsp';"
                + "</script>");
    }
}
