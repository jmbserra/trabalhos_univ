<%-- 
    Document   : index
    Author     : serra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>REGISTAR</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
    </head>
    <body>

        <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
            <form class="login100-form validate-form flex-sb flex-w" action="createServlet" method="post">
                <span class="login100-form-title p-b-32">
                    REGISTER
                </span>

                <span class="txt1 p-b-11">
                    Escolha um Username
                </span>
                <div class="wrap-input100 validate-input m-b-36" data-validate = "Username is required">
                    <input class="input100" type="text" name="username" >
                    <span class="focus-input100"></span>
                </div>

                <span class="txt1 p-b-11">
                    Escolha uma Password
                </span>
                <div class="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
                    <span class="btn-show-pass">
                        <i class="fa fa-eye"></i>
                    </span>
                    <input class="input100" type="password" name="pass" >
                    <span class="focus-input100"></span>
                </div>

                <span class="txt1 p-b-11">
                    Introduza o seu EMAIL
                </span>
                <div class="wrap-input100 validate-input m-b-36" data-validate = "Email is required">
                    <input class="input100" type="text" name="email" >
                    <span class="focus-input100"></span>
                </div>
                <div>
                    <div class="flex-sb-m w-full p-b-48">
                        <div>

                            <a href="indexServlet" class="txt3">
                                home page
                            </a>

                        </div>	
                    </div>
                </div>

                <span class="txt1 p-b-11">
                    Escolha as suas alergias
                </span>
                <div>
                    <input type="checkbox" name="1" value ="platano" > platano<br>
                    <input type="checkbox" name="2" value ="gramineas"> gramineas<br>
                    <input type="checkbox" name="3" value ="oliveira"> oliveira<br>
                    <input type="checkbox" name="4" value ="azinheira"> azinheira<br><br>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" name="action" value="submeter" type="submit">
                        SUBMETER
                    </button>
                </div>
            </form>


        </div>


        <div id="dropDownSelect1"></div>

        <!--===============================================================================================-->
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="js/main.js"></script>

    </body>
</html>