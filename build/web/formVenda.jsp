<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@page import="model.Venda"%>
<%@page import="model.Cliente"%>
<%@page import="model.ClienteDAO"%>
<%@page import="control.GerenciarLogin"%>
<%@page import="model.VendaCurso"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Formulário de Vendas</title>
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

                <%@include file="template/banner.jsp" %>

            </div><!-- fim da div header -->
            <div id="menu">

                <%@include file="template/menu.jsp" %>

            </div><!-- fim da div menu -->
            <div id="content" >

                <!-- Scriptlet é um pedaço de código Java embutido em um código JSP 
                  semelhante a um código HTML. -->
                <%  Venda v = new Venda();
                    Cliente ct = new Cliente();
                    ulogado = GerenciarLogin.verificarAcesso(request, response);
                    request.setAttribute("ulogado", ulogado);

                    try {
                        String acao = request.getParameter("acao");
                        ClienteDAO ctdao = new ClienteDAO();
                        if (acao.equals("novo")) {
                            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
                            ct = ctdao.getCarregarPorId(idCliente);
                            v.setCliente(ct);
                            v.setUsuario(ulogado);
                            v.setCarrinho(new ArrayList<VendaCurso>());
                            session.setAttribute("venda", v);
                        } else {
                            v = (Venda) session.getAttribute("venda");
                        }
                    } catch (Exception e) {
                        out.print("Erro:" + e.getMessage());
                        e.printStackTrace();
                    }
                %>
                <div>   
                    <div class="form-group row offset-sm-4"
                         style="padding-top: 10px">
                        <label for="usuario"
                               class="col-sm-2 text-left btn btn-lg
                               btn-secondary">Vendedor(a)</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   name="usuario" id="usuario" readonly
                                   value="<%= v.getUsuario().getNome()%>"/>
                        </div>
                    </div>

                    <div class="form-group row offset-sm-4">
                        <label for="cliente"
                               class="col-sm-2 text-left btn btn-lg
                               btn-secondary">Cliente</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control"
                                   name="cliente" id="cliente" readonly
                                   value="<%= v.getCliente().getNome()%>"/>
                        </div>
                    </div>

                    <h4 align="center">Catálogo: (<%= v.getCarrinho().size()%>)</h4>

                    <jsp:useBean class="model.CursoDAO" id="curso"/>
                    <c:forEach var="cs" items="${curso.lista}">
                        <div id="curs${cs.idCurso}" class="pb-3">
                            <form action="gerenciarCarrinho" method="GET"
                                  class="form-inline">
                                <input type="hidden" name="acao" value="add"/>
                                <input type="hidden" name="idCurso"
                                       value="${cs.idCurso}"/>
                                <!--Expression Language
                                expressão usada para acessar os dados armazenados no JavaBean-->
                                <h5>${cs.nome}</h5>
                                <input type="number" name="qtd" value="1" class="form-control ml-2"
                                       style="width: 5%" />                     <!-- <-- colocar o readonly no input -->
                                <button class="btn btn-success ml-2">
                                    Comprar&nbsp;<i class="fas fa-cart-plus"></i>
                                </button>
                            </form>
                        </div>
                    </c:forEach>

                    <div class="d-sm-flex justify-content-sm-around m-md-3">
                        <a href="listarCliente.jsp"
                           class="btn btn-outline-danger btn-lg"
                           role="button">Cancelar&nbsp;<i class="fas fa-stop-circle"></i>
                        </a>
                        <a href="formFinalizarVenda.jsp"
                           class="btn btn-outline-info btn-lg"
                           role="button">Finalizar Venda&nbsp;<i class="fas fa-cash-register"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
