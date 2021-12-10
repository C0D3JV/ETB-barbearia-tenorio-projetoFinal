<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="model.Usuario"%>
<%@page import="control.GerenciarLogin" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>


<%
    Usuario ulogado = GerenciarLogin.verificarAcesso(request, response);
    request.setAttribute("ulogado", ulogado);
%>

<div class="d-sm-flex justify-content-between">

    <c:if test="${ulogado != null}">
        <a href="index.jsp" class="ml-1" style="color: #000; margin-bottom: 0px;">
            <img src="imagens/logo-sem-fundo.png" height="50px;"/>
            <img src="imagens/text-barbearia-tenorio.png"
                 height="28px;" width="300px" alt="fonte-quentin-caps" border="0">
        </a>

        <h5 style="color: #000; margin-top: 8px; margin-bottom: 0px;" 
            class="mr-2">Bem vindo(a):&nbsp;${ulogado.nome} 
            <a href="gerenciarLogin?" 
               class="btn btn-outline-dark btn-xs mb-1 mr-1" 
               role="button">Sair&nbsp;<i class="fas fa-sign-out-alt"></i>
            </a></h5>

    </c:if>

</div>
<div class="nav navbar-nav navbar-nav-first mb-2">
    <ul class="navUL">
        <c:if test="${ulogado != null && ulogado.perfil != null}">
            <c:forEach var="menu" items="${ulogado.perfil.menus}">
                <c:if test="${menu.exibir==1}">
                    <li><a href="${menu.link}">${menu.nome}</a></li>
                    </c:if>
                </c:forEach>
            </c:if>
    </ul>
</div>

<link rel="stylesheet" href="../css/menu.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/styles.css" type="text/css" media="all"/>
