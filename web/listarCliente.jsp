<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        import="java.util.ArrayList"
        import="model.Cliente"
        import="model.ClienteDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Listagem de Clientes</title>
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
                                <h3 class="mt-3" style="color: #000;">Listagem de Clientes</h3> 
                            </div>
                            <div class="col-sm-2 col-12" style="padding-bottom: 10px">
                                <a class="btn btn-info btn-md" href="cadastrarCliente.jsp" 
                                   role="button">NOVO CADASTRO&nbsp;<i class="fas fa-user-plus"></i></a>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-hover table-striped 
                                       table-bordered table-active"
                                       id="listarCliente">
                                    <thead class="bg-dark">
                                        <tr style="color: #dda968;">
                                            <th>Nome</th>
                                            <th>Data Nasc.</th>
                                            <th>CPF</link>
                                            <th>E-mail</th>
                                            <th>Endereço</th>
                                            <th>Telefone</th>
                                            <th>Turma</th>
                                            <th style="width: 170px">Ação</th>
                                        </tr>

                                    </thead>
                                    <tbody>
                                        <jsp:useBean class="model.ClienteDAO" id="ctdao" />
                                        <c:forEach var="ct" items="${ctdao.lista}">
                                            <tr>
                                                <td>${ct.nome}</td>
                                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${ct.dataNasc}"/></td>
                                                <td>${ct.cpf}</td>
                                                <td>${ct.email}</td>
                                                <td>${ct.endereco}</td>
                                                <td>${ct.telefone}</td>
                                                <td>${ct.turma.nome}</td>

                                                <td class="text-center">
                                                    <a href="gerenciarCliente?acao=alterar&idCliente=${ct.idCliente}"
                                                       class="btn btn-primary btn-xs" role="button">
                                                        Alterar&nbsp;<i class="fas fa-edit"></i>
                                                    </a>

                                                    <script type="text/javascript">
                                                        function confirmExclusao(idCliente, nome) {
                                                            if (confirm('Deseja realmente excluir o cliente ' + nome + ' ?')) {
                                                                location.href = "gerenciarCliente?acao=deletar&idCliente="
                                                                        + idCliente;
                                                            }
                                                        }
                                                    </script>
                                                    <button class='btn btn-danger btn-xs'
                                                            onclick="confirmExclusao('${ct.idCliente}', '${ct.nome}')">
                                                        Excluir&nbsp;<i class="fas fa-trash"></i>
                                                    </button>
                                                    <!-- ver o link -->
                                                    <a href="formVenda.jsp?acao=novo&idCliente=${ct.idCliente}"
                                                       class="mt-1 btn btn-success btn-xs" role="button">
                                                        Venda&nbsp;<i class="fas fa-shopping-cart"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <script src="js/jquery-3.6.0.js"></script>
                                <script src="datatables/jquery.dataTables.js"></script>
                                <script src="datatables/dataTables.bootstrap4.js"></script>
                                <script>
                                                                $(document).ready(function () {
                                                                    $("#listarCliente").dataTable({
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
                </div>
            </div><!--fim da div content -->
        </div><!--fim da div container -->
    </body>
</html>