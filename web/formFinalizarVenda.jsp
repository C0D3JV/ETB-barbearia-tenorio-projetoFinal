<%@page contentType="text/html; charset=ISO-8859-1"
        pageEncoding="UTF-8"%>
<%@page import="model.Venda"%>
<%@page import="model.Cliente"%>
<%@page import="model.ClienteDAO"%>
<%@page import="control.GerenciarLogin"%>
<%@page import="model.VendaCurso"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- Arrumar o pesquisar e o anterior/próximo --%>

<!DOCTYPE html>
<html>
    <head>
        <title>Finalizar Venda</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, 
              maximum-scale=1, user-scalable=no" />
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate">
        <!--<meta http-equiv="expires" content="0"> -->
        <link rel="stylesheet" href="bootstrap/bootstrap.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="css/menu.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/styles.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/all.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/fontawesome.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" href="googlefonts/stylesheet.css" type="text/css" media="all"/>
        <script src="js/jquery-3.6.0.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/sweetalert2.all.min.js"type="text/javascript"></script>
        <script type="text/javascript">
            function excluir(index, item) {
                if (confirm("Tem certeza que deseja excluir o item " + item + " ?")) {
                    window.open("gerenciarCarrinho?acao=del&index=" + index, +"_self")
                }
            }
        </script>
        <link rel="shortcut icon" href="imagens/penteado.png" />
        <style>
            body {
                background-image: url("imagens/fundo-barbearia.png");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
                background-color: #F0F8FF;
            }
        </style>
    </head>
    <body>
        <%
            //HTTP 1.1
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            //HTTP 1.0
            response.setHeader("Pragma", "no-cache");
            //Proxie
            //response.setHeader("Expires", "0");
            if (session.getAttribute("ulogado") == null)
                response.sendRedirect("formLogin.jsp");
        %>
        <div id="container">
            <div id="menu">

                <%@include file="template/menu.jsp" %>

            </div><!-- fim da div menu -->
            <div id="content" >

                <!-- Scriptlet é um pedaço de código Java embutido em um código JSP 
                  semelhante a um código HTML. -->
                <%                    
                    Venda v = new Venda();
                    Cliente ct = new Cliente();
                    try {
                        v = (Venda) session.getAttribute("venda");

                    } catch (Exception e) {
                        out.println("Erro: " + e.getMessage());
                    }
                %>
                <div>   
                    <form action="gerenciarVenda?acao=alterarQtd" method="POST">
                        <div class="form-group row offset-sm-4 pt-4">
                            <label for="usuario"
                                   class="col-sm-2 text-left btn btn-lg
                                   btn-secondary">Vendedor(a)</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control"
                                       name="usuario" id="usuario" readonly
                                       value="<%= v.getUsuario().getNome()%>"/>
                            </div>
                        </div>

                        <div class="form-group row offset-sm-4">
                            <label for="cliente"
                                   class="col-sm-2 text-left btn btn-lg
                                   btn-secondary">Cliente</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control"
                                       name="cliente" id="cliente" readonly
                                       value="<%= v.getCliente().getNome()%>"/>
                            </div>
                        </div>

                        <table class="table table-hover table-striped 
                               table-bordered table-active" 
                               id="finalizarVenda">
                            <thead>
                                <tr>
                                    <th>Item</th>
                                    <th>Produto</th>
                                    <th>Quantidade</th>
                                    <th>Valor Unitário</th>
                                    <th>Subtotal</th>
                                    <th>Ação</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%--No "for" o objeto "vcs" vai percorrer o carrinho
                                e exibir os itens listados no "th"--%>
                                <%
                                    double total = 0;
                                    int cont = 0;
                                    for (VendaCurso vcs : v.getCarrinho()) {
                                %>
                                <tr>
                                    <td align="center"><%= cont + 1%></td>
                                    <td><%= vcs.getCurso().getNome()%></td>
                                    <td><input type="number" name="qtd" value="<%= vcs.getQtd()%>"
                                               class="form-control ml-2" style="width: 20%;" /></td>
                                    
                                    <td>R$ <fmt:formatNumber pattern="#,##0.00" 
                                        value="<%= vcs.getPrecoVendido()%>"/></td> <%-- Valor unitário --%>
                                    
                                    <td>R$ <fmt:formatNumber pattern="#,##0.00" 
                                        value="<%= vcs.getQtd() * vcs.getPrecoVendido()%>"/></td> <%-- Subtotal --%>
                                    
                                    <td align="center">
                                        <button class="btn btn-primary btn-md">
                                            Alterar Quantidade&nbsp;
                                            <i class="fas fa-edit"></i>
                                        </button>
                                        <a 
                                            href="#"
                                            onclick="excluir(<%= cont%>, <%= cont + 1%>)"
                                            class="btn btn-danger btn-md"
                                            role="button">
                                            Excluir&nbsp;<i class="fas fa-trash-alt"></i>
                                        </a>
                                    </td>
                                </tr>
                                <%

                                        if (v.getCarrinho().size() > 0) {
                                            total = total + (vcs.getQtd() * vcs.getPrecoVendido());
                                            cont++;
                                            v.setPrecoTotal(total);

                                        } else {
                                            total = total - (vcs.getQtd() * vcs.getPrecoVendido());
                                            cont++;
                                            v.setPrecoTotal(total);
                                        }
                                    }
                                %>
                            </tbody>
                        </table>

                        <div class="form-group row offset-sm-4">
                            <label for="precoTotal"
                                   class="col-sm-2 text-left btn btn-lg
                                   btn-secondary">Preço Total</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control"
                                       name="precoTotal" id="precoTotal" readonly
                                       value="R$ <fmt:formatNumber pattern="#,##0.00" value="<%= total%>"/>"/>
                            </div>
                        </div>
                    </form>

                    <div class="d-sm-flex justify-content-sm-around m-md-3 pb-4">
                        <a href="listarCliente.jsp"
                           class="btn btn-outline-danger btn-lg"
                           role="button">
                            Cancelar&nbsp;<i class="fas fa-stop-circle"></i>
                        </a>
                        <a href="formVenda.jsp?acao=c"
                           class="btn btn-outline-primary btn-lg"
                           role="button">
                            Continuar Venda&nbsp;<i class="fas fa-cart-plus"></i>
                        </a>
                        <a href="gerenciarVenda"
                           class="btn btn-outline-success btn-lg"
                           role="button">
                            Confirmar Venda&nbsp;<i class="fas fa-money-bill"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
