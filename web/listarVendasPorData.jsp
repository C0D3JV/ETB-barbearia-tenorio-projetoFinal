<%@page import="model.Venda"%>
<%@page import="model.VendaDAO"%>
<%@page contentType="text/html; charset=ISO-8859-1" 
        pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList, model.Perfil, model.PerfilDAO"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Listagem de Vendas</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, 
              maximum-scale=1, user-scalable=no" />
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate">
        <link rel="stylesheet" href="bootstrap/bootstrap.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="css/menu.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/styles.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/all.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/fontawesome.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.css"/>
        <link rel="stylesheet" href="googlefonts/stylesheet.css" type="text/css" media="all"/>
        <script src="js/bootstrap.js"></script>
        <link rel="shortcut icon" href="imagens/penteado.png" />
        <style>
            body {
                background-color: #F5F5F5;
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

                <jsp:include page="template/menu.jsp"/>

            </div><!-- fim da div menu -->
            <div id="content">
                <div>
                    <div class="h-100 justify-content-center align-items-center">
                        <div clas="col-12">
                            <div>
                                <h3 class="mt-3" style="color: #000;">
                                    Listagem de Vendas por Data</h3> 
                            </div>
                            <div class="col-sm-2 col-12" style="padding-bottom: 10px">
                                <a class="btn btn-info btn-md" href="formVendaPorData.jsp" 
                                   role="button">Listar Vendas Por Data&nbsp;
                                    <i class="fas fa-list-alt"></i></a>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-hover table-striped 
                                       table-bordered table-active" 
                                       id="listarVenda">
                                    <thead class="bg-dark">
                                        <tr style="color: #dda968;">
                                            <th>Código</th>
                                            <th>Preço Total</th>
                                            <th>Data da Venda</th>
                                            <th>Cliente</th>
                                            <th>Vendedor(a)</th>
                                        </tr>

                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Venda> vendas = new ArrayList<>();
                                            vendas = (ArrayList) request.getAttribute("vendas");
                                            for (Venda v : vendas) {

                                        %>
                                        <tr>
                                            <td><%= v.getIdVenda()%></td>
                                            <td>R$&nbsp;<fmt:formatNumber pattern="#,##0.00" 
                                                value="<%= v.getPrecoTotal()%>"/></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy" 
                                                value="<%= v.getDataVenda()%>"/>
                                            </td>
                                            <td><%= v.getCliente().getNome()%></td>
                                            <td><%= v.getUsuario().getNome()%></td>
                                        </tr>
                                        <%
                                            }

                                        %>
                                    </tbody>
                                </table>
                                <script src="js/jquery-3.6.0.js"></script>
                                <script src="datatables/jquery.dataTables.js"></script>
                                <script src="datatables/dataTables.bootstrap4.js"></script>
                                <script>
                                    $(document).ready(function () {
                                        $("#listarVenda").dataTable({
                                            "bJQueryUI": true,
                                            "lengthMenu": [[5, 10, 20, 25, -1], [5, 10, 20, 25, "All"]],
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
                </div>                  
            </div><!-- fim da div content -->
        </div><!--fim da div container -->
    </body>
</html>
