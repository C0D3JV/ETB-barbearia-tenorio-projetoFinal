<%-- 
    Document   : listarTurma
    Created on : 25 de out de 2021, 11:15:04
    Author     : Acer Aspire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.ArrayList"
        import="model.Turma"
        import="model.TurmaDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Listagem de Turmas</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, 
              maximum-scale=1, user-scalable=no" />
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate">
        <!--<meta http-equiv="expires" content="0"> -->
        <link rel="stylesheet" href="bootstrap/bootstrap.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="css/menu.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/styles.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/all.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/fontawesome.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.css"/>
        <link rel="stylesheet" href="googlefonts/stylesheet.css" type="text/css" media="all"/>
        <script src="js/bootstrap.js"></script>
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
            <div id="header">
                <jsp:include page="template/banner.jsp"/>
            </div><!-- fim da div header -->
            
            <div id="menu">
                <jsp:include page="template/menu.jsp"/>
            </div><!-- fim da div menu -->
            
            <div id="content">
                <div class="bg-background">
                    <div class="h-100 justify-content-center align-items-center">
                        <div clas="col-12">
                            <div>
                                <h2 class="mt-5">Listagem de Turmas</h2> 
                            </div>
                            <div class="col-sm-2 col-12" style="padding-bottom: 10px">
                                <a class="btn btn-primary btn-lg" href="cadastrarTurma.jsp" 
                                   role="button">NOVO CADASTRO&nbsp;<i class="fas fa-user-plus"></i></a>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-hover table-striped 
                                       table-bordered" id="listarTurma">
                                    <thead class="bg-primary">
                                        <tr class="text-white">
                                            <th>Código</th>
                                            <th>Nome</th>
                                            <th>Quantidade de Alunos</th>
                                            <th>Ano</th>
                                            <th>Semestre</th>
                                            <th>Turno</th>
                                            <th>Barbeiro</th>
                                            <th>Curso</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <jsp:useBean class="model.TurmaDAO" id="tdao" />
                                        <c:forEach var="t" items="${tdao.lista}">
                                            <tr>
                                                <td>${t.idTurma}</td>
                                                <td>${t.nome}</td>
                                                <td>${t.qtdAluno}</td>
                                                <td>${t.ano}</td>
                                                <td>${t.semestre}</td>
                                                <td>${t.turno}</td>
                                                <td>${t.barbeiro.nome}</td>
                                                <td>${t.curso.nome}</td>
                                                
                                                <td class='text-center'>
                                                    <a href="gerenciarTurma?acao=alterar&idTurma=${t.idTurma}"
                                                       class="btn btn-primary btn-xs" role="button">
                                                        Alterar&nbsp;<i class="fas fa-edit"></i>
                                                    </a>
                                                    <script type="text/javascript">
                                                        function confirmarExclusao(idTurma, nome) {
                                                            if (confirm('Deseja realmente excluir a turma ' + nome + ' ?'))
                                                            {
                                                                location.href = "gerenciarTurma?acao=deletar&idTurma="
                                                                        + idTurma;
                                                            }
                                                        }
                                                    </script>
                                                    <button class='btn btn-danger btn-xs'
                                                            onclick="confirmarExclusao('${t.idTurma}', '${t.nome}')">
                                                        Excluir&nbsp;<i class="fas fa-trash"></i>
                                                    </button>
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
                                                                    $("#listarTurma").dataTable({
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
