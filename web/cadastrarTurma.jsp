<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- formata para usar o date e money --%>

<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de Turma</title>
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
        <link rel="shortcut icon" href="imagens/penteado.png" />
        <style>
            body {
                background-image: url("imagens/fundo-barbearia.png");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
                background-color: #F0F8FF;
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
            <div id="content">
                <div>
                    <form action="gerenciarTurma" method="POST">
                        <h2 class="mt-3" style="color: #000;">Cadastro de Turma</h2>
                        <input type="hidden" class="form-control" name="idTurma"
                               id="idTurma" value="${turma.idTurma}"/>

                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="nome" class="btn btn-outline-dark dark-label">
                                NOME DA TURMA</label>
                            <input type="text" class="form-control" 
                                   name="nome" id="nome" value="${turma.nome}" 
                                   required="Informe a Turma"/>
                        </div>
                        <div class="form-group col col-sm-5 offset-sm-4">
                            <label for="qtdAluno" class="btn btn-outline-dark dark-label">
                                QUANTIDADE DE ALUNOS</label>
                            <input type="number" class="form-control" style="width:144px"
                                   name="qtdAluno" id="qtdAluno" value="${turma.qtdAluno}"
                                   required="">
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="ano" class="btn btn-outline-dark dark-label">
                                ANO</label>
                            <input type="text" class="form-control" 
                                   name="ano" id="ano" value="${turma.ano}" 
                                   required="">
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="semestre" class="btn btn-outline-dark dark-label">
                                SEMESTRE</label>
                            <input type="text" class="form-control" 
                                   name="semestre" id="semestre" value="${turma.semestre}" 
                                   required="">
                        </div>
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="turno" class="btn btn-outline-dark dark-label">
                                TURNO</label>
                            <input type="text" class="form-control" 
                                   name="turno" id="turno" value="${turma.turno}" 
                                   required="">
                        </div>                                    
                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="barbeiro" class="btn btn-outline-dark dark-label">
                                BARBEIRO</label>
                            <select class="form-select form-select-lg m-lg-3"
                                    name="idBarbeiro" id="barbeiro" required="">
                                <option value="" selected>Selecione o Barbeiro</option>
                                <jsp:useBean class="model.BarbeiroDAO" id="barbeiro" />
                                <c:forEach var="b" items="${barbeiro.lista}">
                                    <option value="${b.idBarbeiro}"
                                            <c:if test="${b.idBarbeiro == turma.barbeiro.idBarbeiro}">
                                                selected=""
                                            </c:if>
                                            >${b.nome}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group col col-sm-4 offset-sm-4">
                            <label for="curso" class="btn btn-outline-dark dark-label">
                                CURSO</label>
                            <select class="form-select form-select-lg m-lg-3"
                                    name="idCurso" id="curso" required="">
                                <option value="" selected>Selecione o Curso</option>
                                <jsp:useBean class="model.CursoDAO" id="curso" />
                                <c:forEach var="cs" items="${curso.lista}">
                                    <option value="${cs.idCurso}"
                                            <c:if test="${cs.idCurso == turma.curso.idCurso}">
                                                selected=""
                                            </c:if>
                                            >${cs.nome}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                            <button class="btn btn-info btn-xs mr-4">SALVAR&nbsp;
                                <i class="fas fa-save"></i></button>
                            <a href="listarTurma.jsp" class="btn btn-secondary btn-xs"
                               role="button" >
                                LISTAR TURMAS <i class="fas fa-arrow-circle-left"></i></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>