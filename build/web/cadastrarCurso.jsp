<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <div id="container" >
            <div id="menu">

                <jsp:include page="template/menu.jsp"/>

            </div><!-- fim da div menu -->
            <div id="content" >
                <div>
                    <form action="gerenciarCurso"  method="POST"
                          enctype="multipart/form-data">
                        <h3 class="mt-3" style="color: #000;">Cadastro de Curso</h3>
                        <input type="hidden" class="form-control" name="idCurso"
                               id="idCurso" value="${curso.idCurso}"/>

                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="nome" class="btn btn-outline-dark dark-label">
                                NOME</label>
                            <input type="text" class="form-control"
                                   name="nome" id="nome" value="${curso.nome}"
                                   required="Informe o Nome do Curso"/>
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="descricao" class="btn btn-outline-dark dark-label">
                                DESCRIÇÃO</label>
                            <input type="text" class="form-control"
                                   id="descricao" name="descricao"
                                   value= "${curso.descricao}"/>
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="cargaHoraria" class="btn btn-outline-dark dark-label">
                                CARGA HORÁRIA</label>
                            <input type="number" class="form-control"
                                   id="cargaHoraria" name="cargaHoraria"
                                   value= "${curso.cargaHoraria}" required=""/>
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="preco" class="btn btn-outline-dark dark-label">
                                PREÇO</label>
                            <input class="form-control" name="preco" type="text"
                                   value ="<fmt:formatNumber pattern="#,##0.00"
                                   value= "${curso.preco}"/>" required ">
                        </div>

                        <div class="form-group col col-sm-5 offset-sm-4">
                            <label for="imagem" class="btn btn-outline-dark dark-label">
                                IMAGEM</label>
                            <input type="file" multiple="multiple" class="form-control" 
                                   name="file" id="file" 
                                   value="${curso.caminho}"/>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                            <button class="btn btn-info btn-xs mr-4">SALVAR&nbsp;<i class="fas fa-save"></i></button>
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
