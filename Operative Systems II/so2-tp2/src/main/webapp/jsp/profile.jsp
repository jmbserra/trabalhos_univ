<%@page import="beans.Registos"%>
<%@page import="java.util.ArrayList"%>
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
                font-size: 20px; /* Increased text to enable scrolling */
                padding: 0px 14px;
            }

            .outra {
                margin-left: 400px; /* Same as the width of the sidenav */
                font-size: 20px; /* Increased text to enable scrolling */
                padding: 0px 14px;
            }

            @media screen and (max-height: 450px) {
                .sidenav {padding-top: 15px;}
                .sidenav a {font-size: 18px;}
            }

            table {

                margin-top: 50px;
                width:80%;
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
            <a href="editServlet">Editar Perfil</a>
            <a href="addRegServlet">Adicionar Registo</a>
            <a href="logoutServlet">logout</a>

        </div>  

        <div class="main">
            <h2><%session = request.getSession(false);
        Utilizador user = (Utilizador) session.getAttribute("user");%>
                <%="Perfil de " + user.getNome() + ":"%></h2>


            <b>Alergias:</b>
            <br>
            <% ArrayList alergias = user.getAlergias();%>
            <%for (int i = 0; i < user.getAlergias().size(); i++) {
                    out.println("- " + alergias.get(i));
            %>
            <br>
            <%
                }
            %>




            <br><br>
            <b>Histórico de Registos:</b>
            <br>
            <% ArrayList<Registos> registos = user.getRegs();%>
            <%for (int i = 0; i < registos.size(); i++) {
                    out.println(registos.get(i).totheString());

            %>
            <br><br>
            <%              }
            %>

        </div>

        <div class="main">
            
            <b>Remover Registo:</b><br>
            <form action="profileServlet" method="post">

                Introduza codigo unico: <input type="text" name="unico"><br>
                
                <input name="action" value="submeter" type="submit">
            </form>

        </div>

    </body>
</html> 



