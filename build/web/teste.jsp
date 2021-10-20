<%-- 
    Document   : teste
    Created on : 29 de ago. de 2021, 07:39:08
    Author     : laors
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Teste</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, 
              maximum-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css" media="all"/>
        <link rel="stylesheet" href="css/menu.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/styles.css" type="text/css" media="all" />
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.min.css"/>
        <script src="https://kit.fontawesome.com/3f3417947e.js" crossorigin="anonymous"></script>
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
       
    </head>
    <body>
         <script src="js/sweetalert2.all.min.js"type="text/javascript"></script>
         <script type="text/javascript">
             function alerta(type, title, mensagem){
                Swal.fire({
                    icon: 'success',
                    type: type,
                    title: title,
                    text: mensagem,
                    showConfirmButton: false,
                    timer: 3000
                });
             };
             
             alerta("success", "ops", "Dados salvos com sucesso!");
             
             
             
                        
         </script>
    </body>
</html>
