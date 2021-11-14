<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        import="java.util.ArrayList"
        import="model.Aula"
        import="model.AulaDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Listagem de Aulas</title>
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
                <div>
                    <div class="h-100 justify-content-center align-items-center">
                        <div clas="col-12">
                            <div>
                                <h2 class="mt-3">Listagem de Aulas</h2> 
                            </div>
                            <div class="col-sm-2 col-12" style="padding-bottom: 10px">
                                <a class="btn btn-primary btn-lg" href="cadastrarAula.jsp" 
                                   role="button">NOVO CADASTRO&nbsp;<i class="fas fa-user-plus"></i></a>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-hover table-striped 
                                       table-bordered" id="listarAula">
                                    <thead class="bg-primary">
                                        <tr class="text-white">
                                            <th>Código</th>
                                            <th>Matéria</th>
                                            <th>Data de Início</th>
                                            <th>Data Final</th>
                                            <th>Horário</th>
                                            <th>Quantidade de Aulas</th>
                                            <th>Curso</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <jsp:useBean class="model.AulaDAO" id="adao" />
                                        <c:forEach var="a" items="${adao.lista}">
                                            <tr>
                                                <td>${a.idAula}</td>
                                                <td>${a.materia}</td>
                                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${a.dataInicio}"/></td>
                                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${a.dataFim}"/></td>
                                                <td>${a.horario}</td>
                                                <td>${a.qtdAula}</td>
                                                <td>${a.curso.nome}</td>

                                                <td class='text-center'>
                                                    <a href="gerenciarAula?acao=alterar&idAula=${a.idAula}"
                                                       class="btn btn-primary btn-xs" role="button">
                                                        Alterar&nbsp;<i class="fas fa-edit"></i>
                                                    </a>
                                                    <script type="text/javascript">
                                                        function confirmarExclusao(idAula) {
                                                            if (confirm('Deseja realmente excluir a aula ?'))
                                                            {
                                                                location.href = "gerenciarAula?acao=deletar&idAula="
                                                                        + idAula;
                                                            }
                                                        }
                                                    </script>
                                                    <button class='btn btn-danger btn-xs'
                                                            onclick="confirmarExclusao('${a.idAula}')">
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
                                                                    $("#listarAula").dataTable({
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
            </div><!-- fim da div content -->
        </div><!--fim da div container -->
    </body>
</html>

