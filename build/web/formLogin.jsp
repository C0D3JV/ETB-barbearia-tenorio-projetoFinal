<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="css/all.css" type="text/css" media="all" />
        <link rel="stylesheet" href="bootstrap/bootstrap.css" type="text/css"/>
        <link rel="shortcut icon" href="imagens/penteado.png" />
        <style>
            body {
                background-image: url("imagens/fundo-barbearia.png");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
                background-color: #FFFFE0;
            }
            .bg-image {
                min-height: 100vh;
                background-image: url("imagens/logo-tenorio.jpeg");
                background-color: #F0F8FF;
                background-size: cover;
                background-position: center;
            }

            @media (max-width: 991px) {

                .bg-image {
                    display: none;
                }
            }
            .dark-label{
                background-color: #343a40; 
                color: #dda968;
            }

            h3:hover{
                background-color: black!important
            }
        </style>
    </head>
    <body>
        <section class="form">
            <div class="row no-gutters">
                <!-- Imagem -->
                <div class="col-lg-6 bg-image">

                </div>
                <!-- Login -->
                <div class="col-lg-6 px-5" style="margin-top: 180px" >
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
                    <form action="gerenciarLogin" method="POST" class="" style="">
                        <h3 class="text-center" 
                            style="background-color: #343a40; color: #dda968;
                            border-radius: 6px; padding: 6px;"
                            >Efetuar Login</h3>
                        <div class="mt-4"></div>
                        <div class="form-group col col-sm-4">

                            <label for="login" class="btn btn-outline-dark dark-label">
                                USUÁRIO</label>
                            <input type="text" class="form-control" 
                                   name="login" id="login" placeholder="Nome do Usuário" 
                                   required=""/>
                        </div>
                        <div class="form-group col col-sm-4">
                            <label for="senha" class="btn btn-outline-dark dark-label">
                                SENHA</label>
                            <input type="password" class="form-control" 
                                   name="senha" id="senha" placeholder="Senha"
                                   required=""/>
                        </div>

                        <div class="form-group col col-sm-4 pb-4">
                            <button class="btn btn-info btn-xs mr-4">ACESSAR
                                <i class="fa fa-sign-in-alt"></i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </body>    
</html>
