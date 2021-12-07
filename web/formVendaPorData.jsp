<%@page contentType="text/html; charset=ISO-8859-1"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Pesquisar Venda por Data</title>
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
        <div id="container">
            <div id="menu">

                <jsp:include page="template/menu.jsp"/>

            </div><!-- fim da div menu -->
            <div id="content" >
                <div>
                    <form action="gerenciarVenda?acao=listarData" method="POST">
                        <h3 class="mt-3 mb-3" style="color: #000;">
                            Pesquisar Vendas por Data</h3>

                        <div class="form-group col col-sm-3 mr-auto ml-auto">
                            <label for="dataInicial" class="btn btn-outline-dark dark-label">
                                Data Inicial</label>
                            <input type="date" class="form-control" style="width:172px"
                                   name="dataInicial" id="dataInicial"
                                   required/>
                        </div>
                        <div class="form-group col col-sm-3 mr-auto ml-auto">
                            <label for="dataFinal" class="btn btn-outline-dark dark-label">
                                Data Final</label>
                            <input type="date" class="form-control" style="width:172px"
                                   id="dataFinal" name="dataFinal"
                                   required/>
                        </div>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                            <button class="btn btn-success btn-md">Pesquisar&nbsp;
                                <i class="fas fa-search"></i>
                            </button>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
