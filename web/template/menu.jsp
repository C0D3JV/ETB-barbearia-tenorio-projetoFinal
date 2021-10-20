<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Usuario"%>
<%@page import="control.GerenciarLogin" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>


<%
    Usuario ulogado
            = GerenciarLogin.verificarAcesso(request, response);
    request.setAttribute("ulogado", ulogado);

%>

<div class="d-grid gap-2 d-sm-flex justify-content-sm-between">

    <c:if test="${ulogado != null}">
        <h4>Bem vindo,  ${ulogado.nome}</h4>
        <a href="gerenciarLogin?" 
           class="btn btn-info btn-xs" 
           role="button">Sair
            <i class="fas fa-sign-out-alt"></i>
        </a>
    </c:if>

</div>
<div class="float-right">
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

<link rel="stylesheet" href="../css/menu.css" type="text/css" media="all" />
<link rel="stylesheet" href="../css/styles.css" type="text/css" media="all" />





