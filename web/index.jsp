<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- formata para usar o date e money --%>

<!DOCTYPE html>
<html>
    <head>
        <title>Index</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, 
              maximum-scale=1, user-scalable=no" />
        <script src="js/jquery-3.6.0.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/sweetalert2.all.min.js"type="text/javascript"></script> 
        <link rel="stylesheet" href="css/all.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="css/fontawesome.css" type="text/css" media="all"/>
        <link rel="shortcut icon" href="imagens/penteado.png" />
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">

        <!-- MAIN CSS -->
        <link rel="stylesheet" href="css/templatemo-style.css">
    </head>
    <body id="top" data-spy="scroll" data-target=".navbar-collapse" data-offset="50">
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

        <!-- MENU -->
        <section class="navbar custom-navbar navbar-fixed-top" role="navigation">
            <div class="container">

                <div class="navbar-header">
                    <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon icon-bar"></span>
                        <span class="icon icon-bar"></span>
                        <span class="icon icon-bar"></span>
                    </button>

                    <!-- TEXTO DA LOGO AQUI -->
                    <a href="index.jsp">
                        <img src="imagens/logo.png" width="290px" height="60px" style="padding-top: 10px;"/>
                    </a>
                </div> 

                <!-- MENU LINKS -->
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-nav-first">
                        <li><a href="#top" class="smoothScroll">Inicio</a></li>
                        <li><a href="#about" class="smoothScroll">Sobre</a></li>
                        <li><a href="#team" class="smoothScroll">Equipe</a></li>
                        <li><a href="#courses" class="smoothScroll">Cursos</a></li>
                        <li><a href="#testimonial" class="smoothScroll">Depoimentos</a></li>
                        <li><a href="#contact" class="smoothScroll">Localização</a></li>
                        <div class="dropdown">
                            <button onclick="myFunction()" class="dropbtn">
                                Mais <i class="fa fa-caret-down"></i></button>
                            <div id="myDropdown" class="dropdown-content">
                                <c:if test="${ulogado != null && ulogado.perfil != null}">
                                    <c:forEach var="menu" items="${ulogado.perfil.menus}">
                                        <c:if test="${menu.exibir==1}">
                                            <a href="${menu.link}">${menu.nome}</a>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <c:if test="${ulogado != null}">
                                    <a href="gerenciarLogin?">Sair&nbsp;<i class="fas fa-sign-out-alt"></i>
                                    </a>
                                </c:if>

                            </li>
                        </ul>
                    </ul>
                </div>
            </div>
        </section>

        <script>
            // Quando o usuario clica no botao, alterne entre ocultar e mostrar o conteudo suspenso
            function myFunction() {
                document.getElementById("myDropdown").classList.toggle("show");
            }

            // Feche o menu suspenso se o usu?rio clicar fora dele
            window.onclick = function (event) {
                if (!event.target.matches('.dropbtn')) {
                    var dropdowns = document.getElementsByClassName("dropdown-content");
                    var i;
                    for (i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.classList.contains('show')) {
                            openDropdown.classList.remove('show');
                        }
                    }
                }
            };
        </script>

        <!-- Inicio -->
        <section id="home">
            <div class="row">

                <div class="owl-carousel owl-theme home-slider">
                    <div class="item item-first">
                        <div class="caption">
                            <div class="container">
                                <div class="col-md-6 col-sm-12">
                                    <h1>Centro de Formação de Barbeiros</h1>
                                    <h3>Nossos cursos presenciais são projetados para te dar mais habilidades, 
                                        oferecendo o que há de mais novo no mercado de barbearia. </h3>
                                    <a href="#feature" class="section-btn btn btn-default smoothScroll">Descubra Mais</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="item item-second">
                        <div class="caption">
                            <div class="container">
                                <div class="col-md-6 col-sm-12">
                                    <h1>Inicie sua jornada com cursos práticos</h1>
                                    <h3>Nossos cursos presenciais são desenvolvidos por profissionais qualificados 
                                        e projetados para atender às demandas do setor.</h3>
                                    <a href="#courses" class="section-btn btn btn-default smoothScroll">Escolha um Curso</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="item item-third">
                        <div class="caption">
                            <div class="container">
                                <div class="col-md-6 col-sm-12">
                                    <h1>Métodos de Aprendizagem Eficientes</h1>
                                    <h3> Se você quer ser um barbeiro melhor e viver dessa profissão,você veio ao lugar certo, 
                                        no curso vou te mostrar o que você precisa para isso acontecer.</h3>
                                    <a href="#contact" class="section-btn btn btn-default smoothScroll">Localização</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <!-- FEATURE -->
        <section id="feature">
            <div class="container">
                <div class="row">

                    <div class="col-4 col-4">
                        <div class="feature-thumb">
                            <h2>Sobre nós</h2>
                            <p style="text-align: justify">&emsp;&emsp;A Barberia Tenorio foi fundada em julho de 2020 pelo barbeiro fundador que leva seu sobrenome Daniel Tenorio, mesmo em meio a uma pandemia mundial nosso modelo de negocio buscou se adaptar com a realidade do momento vivido e inaugurou com todos os procedimentos de segurança com a utilização de mascaras, álcool em gel e distanciamento seguro entre os clientes.<br>  
                                &emsp;&emsp;Além dos serviços de barbearia estamos com um diferencial no mercado que á a formação de novos profissionais com o curso “Barber Trying” aonde compartilhamos nossos conhecimentos a fim de somar positividade com a comunidade e arredores.<br>
                                &emsp;&emsp;No momento somos 3 Barbeiros Profissionais (Daniel, Marcos e Wesley) prontos para atende-los com qualidade e profissionalismo. Agende seu horário no AppBarber e venha nos conhecer!
                            <p>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <!-- SOBRE -->
        <section id="about">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-sm-12">
                        <div class="about-info">
                            <h2>Faça um agendamento e conheça nossos serviços:</h2>

                            <figure>
                                <span><a href="https://play.google.com/store/apps/details?id=com.startapp.appbarber" target="_blank">
                                        <i class="fab fa-google-play"></i></a></span>
                                <figcaption>
                                    <h3>O nosso app de agendamento pro sistema Android</h3>
                                    <p>Baixe o AppBarber na Google Play Store</p>
                                </figcaption>
                            </figure>

                            <figure>
                                <span><a href="https://apps.apple.com/br/app/appbarber/id1079972583" target="_blank">
                                        <i class="fab fa-app-store"></i></a></span>
                                <figcaption>
                                    <h3>O nosso app de agendamento pro sistema IOS</h3>
                                    <p>Baixe o AppBarber  na App Store</p>
                                </figcaption>
                            </figure>
                        </div>
                    </div>

                    <div class="col-md-offset-1 col-md-4 col-sm-12">
                        <img style="border-radius: 5%" src="images/tabela-servico.jpg" width="490" height="460">
                    </div>
                </div>
            </div>
        </section>


        <!-- EQUIPE -->
        <section id="team">
            <div class="container">
                <div class="row">

                    <div class="col-md-12 col-sm-9">
                        <div class="section-title">
                            <h2>Nossa Equipe<small>Conheça um pouco mais sobre nosso time de barbeiros</small></h2>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="team-thumb">
                            <div class="team-image">
                                <img src="images/author-image1.jpg" class="img-responsive" alt="">
                            </div>
                            <div class="team-info">
                                <h3>Daniel Tenorio</h3>
                                <span>Fundador e CEO da Barbearia</span>
                            </div>
                            <ul class="social-icon">
                                <li><a href="https://www.facebook.com/daniel.dersohngottes" 
                                       target="_blank" class="fa fa-facebook-square" attr="facebook icon"></a></li>
                                <li><a href="https://www.instagram.com/danieltenoriods/" 
                                       target="_blank" class="fa fa-instagram"></a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="team-thumb">
                            <div class="team-image">
                                <img src="images/author-image2.jpg" class="img-responsive" alt="">
                            </div>
                            <div class="team-info">
                                <h3>Wesley Vitoriano</h3>
                                <span>Barbeiro é a chave!</span>
                            </div>
                            <ul class="social-icon">
                                <li><a href="https://www.instagram.com/barbeiro_vitoriano/" 
                                       target="_blank" class="fa fa-instagram"></a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="team-thumb">
                            <div class="team-image">
                                <img src="images/author-image3.jpg" class="img-responsive" alt="">
                            </div>
                            <div class="team-info">
                                <h3>Marcos Lima</h3>
                                <span>💈 Barbeiro 💈</span>
                            </div>
                            <ul class="social-icon">
                                <li><a href="https://www.instagram.com/marcos_limabarber/" class="fa fa-instagram"
                                       target="_blank"></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <!-- CURSOS -->
        <section id="courses">
            <div class="container">
                <div class="row">

                    <div class="col-md-12 col-sm-12">
                        <div class="section-title">
                            <h2>Novidades chegando em breve!!<small>Acrescente mais habilidades ao seu curriculo com nossos cursos</small></h2>
                        </div>

                        <div class="owl-carousel owl-theme owl-courses">
                            <div class="col-md-4 col-sm-4">
                                <div class="item">
                                    <div class="courses-thumb">
                                        <div class="courses-top">
                                            <div class="courses-image">
                                                <img src="images/courses-image1.jpg" class="img-responsive"
                                                     alt="">
                                            </div>
                                            <div class="courses-date">
                                                <span><i class="fa fa-calendar"></i> 15 / 07 / 2022</span>
                                                <span><i class="fa fa-clock-o"></i> 2200 Horas</span>
                                            </div>
                                        </div>

                                        <div class="courses-detail">
                                            <h3><a href="#">Curso Avançado Barber Trying</a></h3>
                                            <p>Ministrado pelo Barbeiro e Proprietário Daniel, com duração de 4 meses.</p>
                                        </div>

                                        <div class="courses-info">
                                            <div class="courses-author">
                                                <img src="images/author-image1.jpg" class="img-responsive"
                                                     alt="">
                                                <span>Daniel Tenorio</span>
                                            </div>
                                            <div class="courses-price">
                                                <a href="listarCurso.jsp"><span>R$ 1400</span></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4 col-sm-4">
                                <div class="item">
                                    <div class="courses-thumb">
                                        <div class="courses-top">
                                            <div class="courses-image">
                                                <img src="images/courses-image2.jpg" class="img-responsive"
                                                     alt="">
                                            </div>
                                            <div class="courses-date">
                                                <span><i class="fa fa-calendar"></i> --/--/----</span>
                                                <span><i class="fa fa-clock-o"></i> --:--</span>
                                            </div>
                                        </div>

                                        <div class="courses-detail">
                                            <h3><a href="#">Em Breve Mais Novidades</a></h3>
                                            <p>Fique de olho que logo poderam abrir mais cursos e tumas.</p>
                                        </div>

                                        <div class="courses-info">
                                            <div class="courses-author">
                                                <img src="images/author-image2.jpg" class="img-responsive"
                                                     alt="">
                                                <span>Wesley Vitoriano</span>
                                            </div>
                                            <div class="courses-price free">
                                                <a href="#"><span>breve</span></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>   
        </section>


        <!-- DEPOIMENTOS -->
        <section id="testimonial">
            <div class="container">
                <div class="row">

                    <div class="col-md-12 col-sm-12">
                        <div class="section-title">
                            <h2>Review dos Clientes<small>Vejam o que estão falando sobre nós</small></h2>
                        </div>

                        <div class="owl-carousel owl-theme owl-client">
                            <div class="col-md-4 col-sm-4">
                                <div class="item">
                                    <div class="tst-image">
                                        <img src="images/tst-image1.jpg" class="img-responsive" alt="">
                                    </div>
                                    <div class="tst-author">
                                        <h4>Marcos</h4>
                                        <span>Desenvolvedor de Compras</span>
                                    </div>
                                    <p>Você realmente ajuda jovens mentes criativas a obter qualidade 
                                        e assistência profissional na procura de emprego. Eu recomendo a todos!</p>
                                    <div class="tst-rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4 col-sm-4">
                                <div class="item">
                                    <div class="tst-image">
                                        <img src="images/tst-image2.jpg" class="img-responsive" alt="">
                                    </div>
                                    <div class="tst-author">
                                        <h4>Júlia</h4>
                                        <span>Gerente de Marketing</span>
                                    </div>
                                    <p>Tentar algo novo é emocionante! Obrigado pelo incrível curso e 
                                        pelo excelente professor que conseguiu torná-lo interessante.</p>
                                    <div class="tst-rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4 col-sm-4">
                                <div class="item">
                                    <div class="tst-image">
                                        <img src="images/tst-image3.jpg" class="img-responsive" alt="">
                                    </div>
                                    <div class="tst-author">
                                        <h4>Beatriz</h4>
                                        <span>Diretora de Arte</span>
                                    </div>
                                    <p>Você precisa partir de algum lugar, e a Barberia Tenorio te da essa base, 
                                        para você conseguir se aprofundar na carreira de Barbeiro.</p>
                                    <div class="tst-rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>                                     
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4 col-sm-4">
                                <div class="item">
                                    <div class="tst-image">
                                        <img src="images/tst-image4.jpg" class="img-responsive" alt="">
                                    </div>
                                    <div class="tst-author">
                                        <h4>André</h4>
                                        <span>Desenvolvedor Web</span>
                                    </div>
                                    <p>Invistir em novas habilidades é sempre bom, porque o conhecimento é algo que ninguém pode tirar de nós.</p>
                                    <div class="tst-rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </section>


        <!-- LOCALIZAÇÃO -->
        <section id="contact">
            <div class="container">
                <div class="row">
                    <h2>Como Chegar na Barbearia Tenorio</h2>
                    <p>QN, Av. Central Riacho Fundo II, 3 - 9a CJ 01 Lote, Brasília - DF, 71881-511.</p>
                    <div class="gm-conteiner">   
                        <iframe class="responsive-iframe" 
                                src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d3837.162889759285!2d-48.052456!3d-15.900535!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb020ef6d961bdedf!2sBarbearia%20Tenorio!5e0!3m2!1spt-BR!2sbr!4v1637099492615!5m2!1spt-BR!2sbr" 
                                width="800" height="600" style="border:0;" 
                                allowfullscreen="" loading="lazy"></iframe>   
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="template/rodape.jsp"/> <!-- fim do rodapé -->

        <!-- SCRIPTS -->
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/smoothscroll.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>