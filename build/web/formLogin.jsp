<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
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
        <div id="container" class = "container-fluid">
            <div id="header">
                <jsp:include page="template/banner.jsp"/>

            </div><!-- fim da div header -->
            <div id="content">
                <div>
                    <%
                        String mensagem
                                = (String) request.getSession().
                                        getAttribute("mensagem");
                        if (mensagem != null) {
                            request.getSession().removeAttribute("mensagem");
                    %>
                    <div class="alert alert-info" role="alert"><%=mensagem%></div>
                    <%
                        }
                    %>
                    <form action="gerenciarLogin" method="POST">
                        <h2 class="mt-2">Efetuar Login</h2>
                        <div class="mt-5"></div>
                        <div class="form-group col col-sm-4 offset-sm-4">

                            <label for="login" class="btn btn-primary">USUÁRIO</label>
                            <input type="text" class="form-control" 
                                   name="login" id="login" value="" 
                                   required=""/>
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="senha" class="btn btn-primary">SENHA</label>
                            <input type="password" class="form-control" 
                                   name="senha" id="senha" value="" 
                                   required=""/>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                            <button class="btn btn-primary btn-xs mr-4">ACESSAR
                                <i class="fa fa-sign-in-alt"></i>
                            </button>
                        </div>
                    </form>
                </div>
            </div><!-- fim da div content -->
        </div>
    </body>
</html>
