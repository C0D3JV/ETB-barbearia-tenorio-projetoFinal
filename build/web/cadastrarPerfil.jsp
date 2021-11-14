<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de Perfil</title>
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
                <jsp:include page="template/banner.jsp"/>

            </div><!-- fim da div header -->
            <div id="menu">

                <jsp:include page="template/menu.jsp"/>

            </div><!-- fim da div menu -->
            <div id="content" >
                <div>
                    <form action="gerenciarPerfil" method="POST">
                        <h2 class="mt-3">Cadastro de Perfil</h2>
                        <input type="hidden" class="form-control" name="idPerfil"
                               id="idPerfil" value="${perfil.idPerfil}"/>

                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="nome" class="btn btn-primary">NOME</label>
                            <input type="text" class="form-control" 
                                   name="nome" id="nome" value="${perfil.nome}" 
                                   required="Informe o Perfil"/>
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="dataCadastro" class="btn btn-primary">DATA DE CADASTRO</label>
                            <input type="date" class="form-control" id="dataCadastro" name="dataCadastro" 
                                   value= "${perfil.dataCadastro}"/>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                            <button class="btn btn-primary btn-xs mr-4">GRAVAR <i class="fas fa-save"></i></button>
                            <a href="listarPerfil.jsp" class="btn btn-secondary btn-xs" role="button" >
                                LISTAR PERFIS <i class="fas fa-arrow-circle-left"></i>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
