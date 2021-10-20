<%-- 
    Document   : cadastrarCurso
    Created on : 18 de out de 2021, 17:23:41
    Author     : Acer Aspire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- formata para usar o date e money(number) --%>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de Curso</title>
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
                <div class="bg-background">
                    <form action="gerenciarCurso" method="POST">
                        <h2>Cadastro de Curso</h2>
                        <input type="hidden" class="form-control" name="idCurso"
                               id="idCurso" value="${curso.idCurso}"/>

                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="nome" class="btn btn-primary">NOME</label>
                            <input type="text" class="form-control" name="nome" id="nome" value="${curso.nome}" 
                                   required="Informe o Curso"/>
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="cargaHoraria" class="btn btn-primary">CARGA HORÁRIA</label>
                            <input type="number" class="form-control" id="cargaHoraria" name="cargaHoraria" 
                                   value= "${curso.cargaHoraria}" required=""/>
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="preco" class="btn btn-primary">PREÇO</label>
                            <input type="number" class="form-control" id="preco" name="preco" 
                                   step=".01" value ="<fmt:formatNumber pattern="#,##0.00"
                                                     value= "${curso.preco}"/>" required=""/>
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="imagem" class="btn btn-primary">IMAGEM</label>
                            <input type="text" class="form-control" id="imagem" name="imagem" 
                                   value= "${curso.imagem}"/>
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="descricao" class="btn btn-primary">DESCRIÇÃO</label>
                            <input type="text" class="form-control" id="descricao" name="descricao" 
                                   value= "${curso.descricao}"/>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-left">
                            <button class="btn btn-primary btn-xs mr-4">GRAVAR <i class="fas fa-save"></i></button>
                            <a href="listarCurso.jsp" class="btn btn-secondary btn-xs" role="button" >
                                LISTAR CURSOS <i class="fas fa-arrow-circle-left"></i>
                            </a>
                        </div>
                    </form>
                </div>
            </div> <!-- fim da div content -->
        </div> <!-- fim da div conteiner -->
    </body>
</html>
