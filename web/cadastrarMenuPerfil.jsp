<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Vinculação Menu Perfil</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, 
              maximum-scale=1, user-scalable=no" />
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate">
        <!--<meta http-equiv="expires" content="0"> -->
        <link rel="stylesheet" href="bootstrap/bootstrap.css" type="text/css" 
              media="all"/>
        <link rel="stylesheet" href="css/menu.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/styles.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/all.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/fontawesome.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.css"/>
        <link rel="stylesheet" href="googlefonts/stylesheet.css" type="text/css" media="all"/>
        <script src="js/bootstrap.js"></script>
        <script src="js/jquery-3.6.0.js"></script>
        <script src="js/sweetalert2.all.min.js"type="text/javascript"></script>
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
            <div id="content" >
                <div class="mb-2">
                    <form action="gerenciarMenuPerfil" method="POST">
                        <h3 class="mt-3" style="color: #000; text-align: center;">
                            Cadastro de Menus e Perfis</h3>
                        <input type="hidden" class="form-control" name="idPerfil"
                               id="idPerfil" value="${perfilv.idPerfil}"/>

                        <div style="margin-left: 38%" class="form-group col col-sm-4">
                            <label for="nome" class="btn btn-outline-dark dark-label">
                                PERFIL
                            </label>
                            <input type="text" class="form-control" 
                                   name="nome" id="nome" readonly value="${perfilv.nome}"/>
                        </div>
                        <div style="margin-left: 38%" class="form-group col col-sm-4">
                            <label for="idMenu"class="btn btn-outline-dark dark-label">
                                MENUS</label>
                            <select name="idMenu" required id="idMenu" 
                                    class="form-select form-select-lg m-lg-3">
                                <option value="#" selected>Selecione o Menu</option>
                                <c:forEach var="m" items="${perfilv.naoMenus}">
                                    <option value="${m.idMenu}">${m.nome}</option>                                    
                                </c:forEach>

                            </select>
                        </div>
                        <div class="d-grid d-md-flex justify-content-center">
                            <button class="btn btn-info btn-md mr-2">VINCULAR
                                <i class="fas fa-save"></i>
                            </button>
                            <a href="listarPerfil.jsp" 
                               class="btn btn-secondary btn-md mr-2" role="button" >
                                LISTAR PERFIS&nbsp;
                                <i class="fas fa-arrow-circle-left"></i>
                            </a>
                        </div>
                    </form>
                        <hr>
                    <div class="h-100 justify-content-center align-items-center"">
                        <h3 style="color: #000;">Menus Vinculados</h3>
                        <div class="table-responsive">
                            <table class="table table-hover table-striped table-bordered" id="listarMenus">
                                <thead class="bg-dark">
                                    <tr style="color: #dda968;">
                                        <th>Código</th>
                                        <th>Menu</th>
                                        <th>Link</th>
                                        <th>Icone</th>
                                        <th>Exibir</th>
                                        <th>Ação</th>
                                    </tr>

                                </thead>
                                <tbody>
                                    <c:forEach var="m" items="${perfilv.menus}">
                                        <tr>
                                            <td>${m.idMenu}</td>
                                            <td>${m.nome}</td>
                                            <td>${m.link}</td>
                                            <td>${m.icone}</td>
                                            <td>
                                                <c:if test="${m.exibir == 1}">
                                                    Sim 
                                                </c:if>
                                                <c:if test="${m.exibir == 2}">
                                                    Não
                                                </c:if>
                                            </td>
                                            <td>
                                                <script type="text/javascript">
                                                    function confirmarExclusao(idMenu, nome, idPerfil) {
                                                        if (confirm('Deseja desvincular o menu ' + nome + '?')) {
                                                            location.href = "gerenciarMenuPerfil?acao=desvincular&idMenu=" + idMenu + "&idPerfil=" + idPerfil;
                                                        }
                                                    }
                                                </script>
                                                <button class='btn btn-danger btn-xs'
                                                        onclick="confirmarExclusao('${m.idMenu}', '${m.nome}',
                                                        ${perfilv.idPerfil})">
                                                    Desvincular&nbsp;<i class="fas fa-trash"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <script src="js/jquery-3.6.0.min.js"></script>
                            <script src="datatables/jquery.dataTables.js"></script>
                            <script src="datatables/dataTables.bootstrap4.js"></script>
                            <script>
                                                            $(document).ready(function () {
                                                                $("#listarMenus").dataTable({
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
        </div>
    </body>
</html>
