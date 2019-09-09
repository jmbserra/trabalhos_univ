
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
                margin-left: 200px; /* Same as the width of the sidenav */
                font-size: 28px; /* Increased text to enable scrolling */
                padding: 0px 10px;
            }

            @media screen and (max-height: 450px) {
                .sidenav {padding-top: 15px;}
                .sidenav a {font-size: 18px;}
            }
        </style>
    </head>
    <body>

        <div class="sidenav">
            <a href="loginServlet">Login</a>
            <a href="createServlet">Registar</a>
            <a href="mapServlet">Ver Mapa</a>
        </div>

        <div class="main">
            <h2>jogging and allergies</h2>
            <h3>José Serra nº33289 & Carlos Valente nº33298</h3>  
        </div>

    </body>
</html> 