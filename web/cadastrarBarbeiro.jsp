
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- formata para usar o date e money --%>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de Barbeiros</title>
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
            <div id="content">
                <div>
                    <form action="gerenciarBarbeiro" method="POST">
                        <h2 class="mt-5">Cadastro de Barbeiro</h2>
                        <input type="hidden" class="form-control" name="idBarbeiro"
                               id="idBarbeiro" value="${barbeiro.idBarbeiro}"/>

                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="nome" class="btn btn-primary">NOME DO BARBEIRO</label>
                            <input type="text" class="form-control" 
                                   name="nome" id="nome" value="${barbeiro.nome}" 
                                   required="Informe o Barbeiro"/>
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="dataNasc" class="btn btn-primary">DATA DE NASCIMENTO</label>
                            <input type="date" class="form-control" 
                                   name="dataNasc" id="dataNasc" value="${barbeiro.dataNasc}"
                                   required="">
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="cpf" class="btn btn-primary">CPF</label>
                            <input type="text" class="form-control" 
                                   name="cpf" id="cpf" value="${barbeiro.cpf}" 
                                   required="">
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="email" class="btn btn-primary">E-MAIL</label>
                            <input type="text" class="form-control" 
                                   name="email" id="email" value="${barbeiro.email}" 
                                   required="">
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="endereco" class="btn btn-primary">ENDEREÇO</label>
                            <input type="text" class="form-control" 
                                   name="endereco" id="endereco" value="${barbeiro.endereco}" 
                                   required="">
                        </div>                        
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="telefone" class="btn btn-primary">TELEFONE</label>
                            <input type="text" class="form-control" 
                                   name="telefone" id="telefone" value="${barbeiro.telefone}" 
                                   required="">
                        </div>    

                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="usuario" class="btn btn-primary">USUARIO</label>
                            <select class="form-select form-select-lg m-lg-3"
                                    name="idUsuario" id="usuario" required="">
                                <option value="" selected>Selecione o Usuario</option>
                                <jsp:useBean class="model.UsuarioDAO" id="usuario" />
                                <c:forEach var="u" items="${usuario.lista}">
                                    <option value="${u.idUsuario}"
                                            <c:if test="${u.idUsuario == barbeiro.usuario.idUsuario}">
                                                selected=""
                                            </c:if>
                                            >${u.nome}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                            <button class="btn btn-primary btn-xs mr-4">GRAVAR <i class="fas fa-save"></i></button>
                            <a href="listarBarbeiro.jsp" class="btn btn-secondary btn-xs" role="button" >
                                LISTAR BARBEIRO <i class="fas fa-arrow-circle-left"></i></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>