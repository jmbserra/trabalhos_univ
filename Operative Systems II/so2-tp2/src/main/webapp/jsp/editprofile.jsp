<%@page import="beans.Utilizador"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            body {
                font-family: "Lato", sans-serif;
            }

            .sidenav {
                height: 100%;
                width: 160px;
                position: fixed;
                z-index: 1;
                top: 0;
                left: 0;
                background-color: #111;
                overflow-x: hidden;
                padding-top: 20px;
            }

            .sidenav a {
                padding: 6px 8px 6px 16px;
                text-decoration: none;
                font-size: 25px;
                color: #818181;
                display: block;
            }

            .sidenav a:hover {
                color: #f1f1f1;
            }

            .main {
                margin-left: 160px; /* Same as the width of the sidenav */
                font-size: 28px; /* Increased text to enable scrolling */
                padding: 0px 10px;
            }

            @media screen and (max-height: 450px) {
                .sidenav {padding-top: 15px;}
                .sidenav a {font-size: 18px;}
            }

            table {
                margin-left: 160px;
                margin-top: 50px;
                width:40%;
            }
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th, td {
                padding: 15px;
                text-align: left;
            }
            table#t01 tr:nth-child(even) {
                background-color: #eee;
            }
            table#t01 tr:nth-child(odd) {
                background-color: #fff;
            }
            table#t01 th {
                background-color: black;
                color: white;
            }

        </style>
    </head>
    <body>

        <div class="sidenav">
            <a href="welcomeServlet">Home</a>
            <a href="mapServlet">Ver Mapa</a>
            <a href="profileServlet">Editar Perfil</a>
            <a href="logoutServlet">logout</a>
        </div>  

        <div class="main">
            <h2><%session = request.getSession(false);
        Utilizador user = (Utilizador) session.getAttribute("user");%>
                <%="Perfil de " + user.getNome() + ""%></h2>
            <form action="editServlet">

                <div>
                    <b>Edite as suas alergias:</b>
                </div>
                <input type="checkbox" name="1" value ="platano" > platano<br>
                <input type="checkbox" name="2" value ="graminha"> gramineas<br>
                <input type="checkbox" name="3" value ="oliveira"> oliveira<br>
                <input type="checkbox" name="4" value ="azinheira"> azinheira<br><br>

                <input name="action" type="submit" value="submeter">

            </form> 
        </div> 
            
    </body>
</html> 



