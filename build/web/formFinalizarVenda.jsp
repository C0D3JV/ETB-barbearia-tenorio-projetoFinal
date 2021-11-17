<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Venda"%>
<%@page import="model.Cliente"%>
<%@page import="model.ClienteDAO"%>
<%@page import="control.GerenciarLogin"%>
<%@page import="model.VendaCurso"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%--Arrumar o pesquisar e o anterior/próximo --%>

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
        <!-- olho nesse if -->
        <script type="text/javascript">
            function excluir(index, item) {
                if (confirm("Tem certeza que deseja excluir o item " + item + " ?")) {
                    window.open("gerenciarCarrinho?acao=del&index=" + index, +"_self")
                }
            }
        </script>
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
        <div id="container" class = "container-fluid">
            <div id="header">

                <%@include file="template/banner.jsp" %>

            </div><!-- fim da div header -->
            <div id="menu">

                <%@include file="template/menu.jsp" %>

            </div><!-- fim da div menu -->
            <div id="content" >

                <!-- Scriptlet é um pedaço de código Java embutido em um código JSP 
                  semelhante a um código HTML. -->
                <%                    Venda v = new Venda();
                    Cliente ct = new Cliente();
                    try {
                        v = (Venda) session.getAttribute("venda");
                    } catch (Exception e) {
                        out.println("Erro: " + e.getMessage());
                    }
                %>
                <div>   
                    <div class="form-group row offset-sm-4"
                         style="padding-top: 10px">
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
                                <th>Remover</th>
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
                                <td><%= vcs.getQtd()%></td>
                                <td>R$ <fmt:formatNumber pattern="#,##0.00" value="<%= vcs.getPrecoVendido()%>"/></td> <%-- Valor unitário --%>
                                <td>R$ <fmt:formatNumber pattern="#,##0.00" value="<%= vcs.getQtd() * vcs.getPrecoVendido()%>"/></td> <%-- Subtotal --%>
                                <td align="center">
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
                                    total = total + (vcs.getQtd() * vcs.getPrecoVendido());
                                    cont++;
                                    v.setPrecoTotal(total);
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
                                   value="R$ <fmt:formatNumber pattern="#,##0.00" value="<%= v.getPrecoTotal() %>"/>"/>
                        </div>
                    </div>

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
                            Confirmar Venda&nbsp;<i class="fas fa-money-check"></i>
                        </a>
                    </div>
                    <script src="js/jquery-3.6.0.js"></script>
                    <script src="datatables/jquery.dataTables.js"></script>
                    <script src="datatables/dataTables.bootstrap4.js"></script>
                    <script>
                                            $(document).ready(function () {
                                                $("#finalizarVenda").dataTable({
                                                    "bJQueryUI": true,
                                                    "lengthMenu": [[10, 20, 30, 40, -1], [10, 20, 30, 40, "All"]],
                                                    "oLanguage": {
                                                        "sProcessing": "Processando..",
                                                        "sLengthMenu": "Mostrar _MENU_ registros",
                                                        "sZeroRecords": "Não foram encontrados resultados",
                                                        "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                                                        "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                                                        "sInfoFiltered": "",
                                                        "sInfoPostFix": "",
                                                        "sSearch": "Pesquisar",
                                                        "sUrl": "",
                                                        "oPaginate": {
                                                            "sFirst": "Primeiro",
                                                            "sPrevious": "Anterior",
                                                            "sNext": "Próximo",
                                                            "sLast": "Último"
                                                        }
                                                    }
                                                });
                                            });
                    </script> 
                </div>
            </div>
        </div>
    </body>
</html>
