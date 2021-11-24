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
        <!-- <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="expires" content="0">
        <link rel="stylesheet" href="bootstrap/bootstrap.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="css/menu.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/styles.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/all.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/fontawesome.css"/>
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.min.css"/>
        <link rel="stylesheet" href="googlefonts/stylesheet.css" type="text/css" media="all"/>
        <script src="js/jquery-3.6.0.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/sweetalert2.all.min.js"type="text/javascript"></script> -->
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
        <!-- PRE LOADER -->
        <section class="preloader">
            <div class="spinner">

                <span class="spinner-rotate"></span> 
            </div>  
        </section>

        <!-- MENU -->
        <section class="navbar custom-navbar navbar-fixed-top" role="navigation">
            <div class="container">

                <div class="navbar-header">
                    <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon icon-bar"></span>
                        <span class="icon icon-bar"></span>
                        <span class="icon icon-bar"></span>
                    </button>

                    <!-- lOGO TEXT HERE -->
                    <a href="index.jsp" class="navbar-brand">Barbearia Tenorio</a>
                </div>

                <!-- MENU LINKS -->
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-nav-first">
                        <li><a href="#top" class="smoothScroll">Inicio</a></li>
                        <li><a href="#about" class="smoothScroll">Sobre</a></li>
                        <li><a href="#team" class="smoothScroll">Equipe</a></li>
                        <li><a href="#courses" class="smoothScroll">Cursos</a></li>
                        <li><a href="#testimonial" class="smoothScroll">Depoimentos</a></li>
                        <li><a href="#contact" class="smoothScroll">Como Chegar</a></li>
                        <div class="dropdown">
                            <button onclick="myFunction()" class="dropbtn">
                                Mais <i class="fa fa-caret-down"></i></button>
                            <div id="myDropdown" class="dropdown-content">
                                <a href="listarPerfil.jsp">Perfil</a>
                                <a href="listarMenu.jsp">Menu</a>
                                <a href="listarUsuario.jsp">Usuario</a>
                                <a href="listarCliente.jsp">Cliente</a>
                                <a href="listarBarbeiro.jsp">Barbeiro</a>
                                <a href="listarCurso.jsp">Curso</a>
                                <a href="listarTurma.jsp">Turma</a>
                                <a href="listarVenda.jsp">Venda</a>
                                <a href="listarAula.jsp">Aula</a>
                                
                            </div>  
                        </div>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#"><i class="fa fa-whatsapp"></i>+55 61 99259-5360</a></li>
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
                                    <h1>Distance Learning Education Center</h1>
                                    <h3>Our online courses are designed to fit in your industry supporting all-round
                                        with latest technologies.</h3>
                                    <a href="#feature" class="section-btn btn btn-default smoothScroll">Discover
                                        more</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="item item-second">
                        <div class="caption">
                            <div class="container">
                                <div class="col-md-6 col-sm-12">
                                    <h1>Start your journey with our practical courses</h1>
                                    <h3>Our online courses are built in partnership with technology leaders and are
                                        designed to meet industry demands.</h3>
                                    <a href="#courses" class="section-btn btn btn-default smoothScroll">Take a
                                        course</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="item item-third">
                        <div class="caption">
                            <div class="container">
                                <div class="col-md-6 col-sm-12">
                                    <h1>Efficient Learning Methods</h1>
                                    <h3>Nam eget sapien vel nibh euismod vulputate in vel nibh. Quisque eu ex eu
                                        urna venenatis sollicitudin ut at libero. Visit <a rel="nofollow"
                                                                                           href="https://www.facebook.com/templatemo">templatemo</a> page.</h3>
                                    <a href="#contact" class="section-btn btn btn-default smoothScroll">Let's chat</a>
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

                    <div class="col-md-4 col-sm-4">
                        <div class="feature-thumb">
                            <span>01</span>
                            <h3>Trending Courses</h3>
                            <p>Known is free education HTML Bootstrap Template. You can modify in any way and use this
                                for your website.</p>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-4">
                        <div class="feature-thumb">
                            <span>02</span>
                            <h3>Books & Library</h3>
                            <p>You are allowed to use Known HTML Template for your commercial or non-commercial
                                websites.</p>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-4">
                        <div class="feature-thumb">
                            <span>03</span>
                            <h3>Certified Teachers</h3>
                            <p>Please spread a word about us. Template redistribution is NOT allowed on any download
                                website.</p>
                        </div>
                    </div>

                </div>
            </div>
        </section>


        <!-- Sobre -->
        <section id="about">
            <div class="container">
                <div class="row">

                    <div class="col-md-6 col-sm-12">
                        <div class="about-info">
                            <h2>Start your journey to a better life with online practical courses</h2>

                            <figure>
                                <span><i class="fa fa-users"></i></span>
                                <figcaption>
                                    <h3>Professional Trainers</h3>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sint ipsa
                                        voluptatibus.</p>
                                </figcaption>
                            </figure>

                            <figure>
                                <span><i class="fa fa-certificate"></i></span>
                                <figcaption>
                                    <h3>International Certifications</h3>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sint ipsa
                                        voluptatibus.</p>
                                </figcaption>
                            </figure>

                            <figure>
                                <span><i class="fa fa-bar-chart-o"></i></span>
                                <figcaption>
                                    <h3>Free for 3 months</h3>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sint ipsa
                                        voluptatibus.</p>
                                </figcaption>
                            </figure>
                        </div>
                    </div>

                    <div class="col-md-offset-1 col-md-4 col-sm-12">
                        <div class="entry-form">
                            <form action="#" method="post">
                                <h2>Signup today</h2>
                                <input type="text" name="full name" class="form-control" placeholder="Full name"
                                       required="">

                                <input type="email" name="email" class="form-control"
                                       placeholder="Your email address" required="">

                                <input type="password" name="password" class="form-control"
                                       placeholder="Your password" required="">

                                <button class="submit-btn form-control" id="form-submit">Get started</button>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </section>


        <!-- Equipe -->
        <section id="team">
            <div class="container">
                <div class="row">

                    <div class="col-md-12 col-sm-12">
                        <div class="section-title">
                            <h2>Teachers <small>Meet Professional Trainers</small></h2>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="team-thumb">
                            <div class="team-image">
                                <img src="images/author-image1.jpg" class="img-responsive" alt="">
                            </div>
                            <div class="team-info">
                                <h3>Mark Wilson</h3>
                                <span>I love Teaching</span>
                            </div>
                            <ul class="social-icon">
                                <li><a href="#" class="fa fa-facebook-square" attr="facebook icon"></a></li>
                                <li><a href="#" class="fa fa-twitter"></a></li>
                                <li><a href="#" class="fa fa-instagram"></a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="team-thumb">
                            <div class="team-image">
                                <img src="images/author-image2.jpg" class="img-responsive" alt="">
                            </div>
                            <div class="team-info">
                                <h3>Catherine</h3>
                                <span>Education is the key!</span>
                            </div>
                            <ul class="social-icon">
                                <li><a href="#" class="fa fa-google"></a></li>
                                <li><a href="#" class="fa fa-instagram"></a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="team-thumb">
                            <div class="team-image">
                                <img src="images/author-image3.jpg" class="img-responsive" alt="">
                            </div>
                            <div class="team-info">
                                <h3>Jessie Ca</h3>
                                <span>I like Online Courses</span>
                            </div>
                            <ul class="social-icon">
                                <li><a href="#" class="fa fa-twitter"></a></li>
                                <li><a href="#" class="fa fa-envelope-o"></a></li>
                                <li><a href="#" class="fa fa-linkedin"></a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="team-thumb">
                            <div class="team-image">
                                <img src="images/author-image4.jpg" class="img-responsive" alt="">
                            </div>
                            <div class="team-info">
                                <h3>Andrew Berti</h3>
                                <span>Learning is fun</span>
                            </div>
                            <ul class="social-icon">
                                <li><a href="#" class="fa fa-twitter"></a></li>
                                <li><a href="#" class="fa fa-google"></a></li>
                                <li><a href="#" class="fa fa-behance"></a></li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        </section>


        <!-- Cursos -->
        <section id="courses">
            <div class="container">
                <div class="row">

                    <div class="col-md-12 col-sm-12">
                        <div class="section-title">
                            <h2>Popular Courses <small>Upgrade your skills with newest courses</small></h2>
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
                                                <span><i class="fa fa-calendar"></i> 12 / 7 / 2018</span>
                                                <span><i class="fa fa-clock-o"></i> 7 Hours</span>
                                            </div>
                                        </div>

                                        <div class="courses-detail">
                                            <h3><a href="#">Social Media Management</a></h3>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        </div>

                                        <div class="courses-info">
                                            <div class="courses-author">
                                                <img src="images/author-image1.jpg" class="img-responsive"
                                                     alt="">
                                                <span>Mark Wilson</span>
                                            </div>
                                            <div class="courses-price">
                                                <a href="#"><span>USD 25</span></a>
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
                                                <span><i class="fa fa-calendar"></i> 20 / 7 / 2018</span>
                                                <span><i class="fa fa-clock-o"></i> 4.5 Hours</span>
                                            </div>
                                        </div>

                                        <div class="courses-detail">
                                            <h3><a href="#">Graphic & Web Design</a></h3>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        </div>

                                        <div class="courses-info">
                                            <div class="courses-author">
                                                <img src="images/author-image2.jpg" class="img-responsive"
                                                     alt="">
                                                <span>Jessica</span>
                                            </div>
                                            <div class="courses-price">
                                                <a href="#"><span>USD 80</span></a>
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
                                                <img src="images/courses-image3.jpg" class="img-responsive"
                                                     alt="">
                                            </div>
                                            <div class="courses-date">
                                                <span><i class="fa fa-calendar"></i> 15 / 8 / 2018</span>
                                                <span><i class="fa fa-clock-o"></i> 6 Hours</span>
                                            </div>
                                        </div>

                                        <div class="courses-detail">
                                            <h3><a href="#">Marketing Communication</a></h3>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        </div>

                                        <div class="courses-info">
                                            <div class="courses-author">
                                                <img src="images/author-image3.jpg" class="img-responsive"
                                                     alt="">
                                                <span>Catherine</span>
                                            </div>
                                            <div class="courses-price free">
                                                <a href="#"><span>Free</span></a>
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
                                                <img src="images/courses-image4.jpg" class="img-responsive"
                                                     alt="">
                                            </div>
                                            <div class="courses-date">
                                                <span><i class="fa fa-calendar"></i> 10 / 8 / 2018</span>
                                                <span><i class="fa fa-clock-o"></i> 8 Hours</span>
                                            </div>
                                        </div>

                                        <div class="courses-detail">
                                            <h3><a href="#">Summer Kids</a></h3>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        </div>

                                        <div class="courses-info">
                                            <div class="courses-author">
                                                <img src="images/author-image1.jpg" class="img-responsive"
                                                     alt="">
                                                <span>Mark Wilson</span>
                                            </div>
                                            <div class="courses-price">
                                                <a href="#"><span>USD 45</span></a>
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
                                                <img src="images/courses-image5.jpg" class="img-responsive"
                                                     alt="">
                                            </div>
                                            <div class="courses-date">
                                                <span><i class="fa fa-calendar"></i> 5 / 10 / 2018</span>
                                                <span><i class="fa fa-clock-o"></i> 10 Hours</span>
                                            </div>
                                        </div>

                                        <div class="courses-detail">
                                            <h3><a href="#">Business &amp; Management</a></h3>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        </div>

                                        <div class="courses-info">
                                            <div class="courses-author">
                                                <img src="images/author-image2.jpg" class="img-responsive"
                                                     alt="">
                                                <span>Jessica</span>
                                            </div>
                                            <div class="courses-price free">
                                                <a href="#"><span>Free</span></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
        </section>


        <!-- TESTIMONIAL -->
        <section id="testimonial">
            <div class="container">
                <div class="row">

                    <div class="col-md-12 col-sm-12">
                        <div class="section-title">
                            <h2>Student Reviews <small>from around the world</small></h2>
                        </div>

                        <div class="owl-carousel owl-theme owl-client">
                            <div class="col-md-4 col-sm-4">
                                <div class="item">
                                    <div class="tst-image">
                                        <img src="images/tst-image1.jpg" class="img-responsive" alt="">
                                    </div>
                                    <div class="tst-author">
                                        <h4>Jackson</h4>
                                        <span>Shopify Developer</span>
                                    </div>
                                    <p>You really do help young creative minds to get quality education and
                                        professional job search assistance. I’d recommend it to everyone!</p>
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
                                        <h4>Camila</h4>
                                        <span>Marketing Manager</span>
                                    </div>
                                    <p>Trying something new is exciting! Thanks for the amazing law course and the
                                        great teacher who was able to make it interesting.</p>
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
                                        <img src="images/tst-image3.jpg" class="img-responsive" alt="">
                                    </div>
                                    <div class="tst-author">
                                        <h4>Barbie</h4>
                                        <span>Art Director</span>
                                    </div>
                                    <p>Donec erat libero, blandit vitae arcu eu, lacinia placerat justo. Sed
                                        sollicitudin quis felis vitae hendrerit.</p>
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
                                        <img src="images/tst-image4.jpg" class="img-responsive" alt="">
                                    </div>
                                    <div class="tst-author">
                                        <h4>Andrio</h4>
                                        <span>Web Developer</span>
                                    </div>
                                    <p>Nam eget mi eu ante faucibus viverra nec sed magna. Vivamus viverra sapien
                                        ex, elementum varius ex sagittis vel.</p>
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


        <!-- CONTACT -->
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
