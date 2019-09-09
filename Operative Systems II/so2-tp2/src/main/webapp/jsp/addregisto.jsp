<%-- 
    Document   : mapa
    Created on : Jun 16, 2019, 7:08:38 PM
    Author     : serra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Registo</title>


    </head>
    <body>
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css" />
        <script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"></script>

        <div id="mapid" style="width: 1000px; height: 600px; position: absolute; left: 180px; top:170px" ></div>
        <script>
            var mymap = L.map('mapid').setView([38.570889, -7.909440], 14);
            L.tileLayer('https://{s}.tile.openstreetmap.de/tiles/osmde/{z}/{x}/{y}.png', {
                maxZoom: 18,
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'}).addTo(mymap);

            var popup = L.popup();
            function onMapClick(e) {
                var latitude = e.latlng.lat;
                var longitude = e.latlng.lng;
                popup
                        .setLatLng(e.latlng)
                        .setContent("Lat: " + latitude.toFixed(5) + "<br>Long: " + longitude.toFixed(5))
                        .openOn(mymap);
            }
            mymap.on('click', onMapClick);
        </script>

        <div class="sidenav">
            <a href="welcomeServlet">Home</a>
            <a href="profileServlet">Voltar ao Perfil</a>
            <a href="logoutServlet">Logout</a>

        </div>

        <div class="main">
            <h2>Repositório de Alergénios</h2>
            <h3>José Serra nº33289 & Carlos Valente nº33298</h3>  
        </div>

        <div style="position: absolute; left: 1200px; top:170px">

            <form action="addRegServlet">
                Latitude: <input type="text" name="lat"><br>
                Longitude: <input type="text" name="long"><br>

                <div>
                    <input type="radio" name="1" value="platano"> platano<br>
                    <input type="radio" name="1" value="gramineas"> gramineas<br>
                    <input type="radio" name="1" value="oliveira"> oliveira<br>
                    <input type="radio" name="1" value="azinheira"> azinheira
                </div>
                <input name="action" value="submeter" type="submit">
            </form>

        </div>
    </body>
</html>

