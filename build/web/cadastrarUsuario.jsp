<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de Usuário</title>
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
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" href="css/all.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/fontawesome.css" type="text/css" media="all"/>
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
                    <form action="gerenciarUsuario" method="POST">
                        <h3 class="mt-3" style="color: #000;">Cadastro de Usuário</h3>
                        <input type="hidden" class="form-control" name="idUsuario"
                               id="idUsuario" value="${usuario.idUsuario}"/>

                        <div class="form-group col col-sm-3 offset-sm-4 ml-auto mr-auto">
                            <label for="nome" class="btn btn-outline-dark dark-label">
                                NOME</label>
                            <input type="text" class="form-control" 
                                   name="nome" id="nome" value="${usuario.nome}" 
                                   required>
                        </div>

                        <div class="form-group col col-sm-3 offset-sm-4 ml-auto mr-auto">
                            <label for="login" class="btn btn-outline-dark dark-label">
                                LOGIN</label>
                            <input type="text" class="form-control" 
                                   name="login" id="login" value="${usuario.login}" 
                                   required>
                        </div>

                        <div class="form-group col col-sm-3 offset-sm-4 ml-auto mr-auto">
                            <label for="senha" class="btn btn-outline-dark dark-label">
                                SENHA</label>
                            <input type="password" class="form-control" style="width:195px;"
                                   name="senha" id="senha" value="${usuario.senha}" 
                                   required>
                        </div>

                        <div class="form-group col col-sm-3 offset-sm-4 ml-auto mr-auto">
                            <label for="status" class="btn btn-outline-dark dark-label">
                                STATUS</label>
                            <select class="form-select form-select-lg m-lg-3 " 
                                    name="status" id="status" required>
                                <option selected>Escolha uma opção</option>
                                <option value="1" 
                                        <c:if test="${usuario.status == 1}"> 
                                            selected=""
                                        </c:if>>Ativo
                                </option>
                                <option value="2"
                                        <c:if test="${usuario.status == 2}">
                                            selected=""
                                        </c:if>>Inativo
                                </option>
                            </select>
                        </div>

                        <div class="form-group col col-sm-3 offset-sm-4 ml-auto mr-auto">
                            <label for="perfil" class="btn btn-outline-dark dark-label">
                                PERFIL</label>
                            <select class="form-select form-select-lg m-lg-3"
                                    name="idPerfil" id="perfil" required>
                                <option value="" selected>Selecione o Perfil</option>
                                <jsp:useBean class="model.PerfilDAO" id="perfil" />
                                <c:forEach var="p" items="${perfil.lista}">
                                    <option value="${p.idPerfil}"
                                            <c:if test="${p.idPerfil == usuario.perfil.idPerfil}">
                                                selected=""
                                            </c:if>
                                            >${p.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                            <button class="btn btn-info btn-xs mr-4">SALVAR&nbsp;
                                <i class="fas fa-save"></i>
                            </button>
                            <a href="listarUsuario.jsp" 
                               class="btn btn-secondary btn-xs" 
                               role="button" >LISTAR USUÁRIOS
                                <i class="fas fa-arrow-circle-left"></i>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
