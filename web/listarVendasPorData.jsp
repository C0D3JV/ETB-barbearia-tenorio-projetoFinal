<%-- 
    Document   : listarVendasPorData
    Created on : 27 de nov de 2021, 18:48:52
    Author     : Acer Aspire
--%>

<%@page import="model.Venda"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            ArrayList<Venda> vendas = new ArrayList<>();
            request.getAttribute("vendas");
            
        %>
    </body>
</html>
