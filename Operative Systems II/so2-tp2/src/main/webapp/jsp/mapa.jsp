<%-- 
    Document   : mapa
    Created on : Jun 16, 2019, 7:08:38 PM
    Author     : serra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html
    xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <link rel="stylesheet" href="css/leaflet.css" />
        <link rel="stylesheet" href="css/leaflet-easy-button.css" />
        <link rel="stylesheet" href="css/leaflet-tag-filter-button.css" />
        <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />


        <script src="js/leaflet.js"></script>
        <script src="js/leaflet-easy-button.js"></script>
        <script src="js/leaflet-tag-filter-button.js"></script>
        <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>

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
            <title>MAPA</title>


    </head>
    <body>
        <div>

            <div id="mapid" style="width: 1000px; height: 600px; position: absolute; left: 180px; top:170px" ></div>
            <div class="leaflet-control, leaflet-control-layers" />
            <script id="mapid" type="text/javascript">
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
                //document.sendInfo.lat.value = e.latlng.lat;


                var lista = "<%=request.getAttribute("tudo")%>";
                var listaDois = lista.trim().split(" ");
                var listaFinal = [];
                for (var i = 0; i < listaDois.length; i++)
                {
                    listaFinal[i] = [listaDois[i], listaDois[i + 1], listaDois[i + 2], listaDois[i + 3], listaDois[i + 4], listaDois[i + 5], listaDois[i + 6], listaDois[i + 7]];
                }

                for (var i = 0; i < listaFinal.length; i++) {
                    if (listaFinal[i][1] === "platano") {
                        circle = new L.circle([listaFinal[i][6], listaFinal[i][7]], 100, {color: 'blue', tags: ['platano']})
                                .bindPopup("Alergia: " + listaFinal[i][1] + "\n Adicionado por: " + listaFinal[i][0])
                                .addTo(mymap);
                    }
                    if (listaFinal[i][1] === "gramineas") {
                        circle = new L.circle([listaFinal[i][6], listaFinal[i][7]], 100, {color: 'red', tags: ['gramineas']})
                                .bindPopup("Alergia: " + listaFinal[i][1] + "\n Adicionado por: " + listaFinal[i][0])
                                .addTo(mymap);
                    }
                    if (listaFinal[i][1] === "oliveira") {
                        circle = new L.circle([listaFinal[i][6], listaFinal[i][7]], 100, {color: 'green', tags: ['oliveira']})
                                .bindPopup("Alergia: " + listaFinal[i][1] + "\n Adicionado por: " + listaFinal[i][0])
                                .addTo(mymap);
                    }
                    if (listaFinal[i][1] === "azinheira") {
                        circle = new L.circle([listaFinal[i][6], listaFinal[i][7]], 100, {color: 'purple', tags: ['azinheira']})
                                .bindPopup("Alergia: " + listaFinal[i][1] + "\n Adicinado por: " + listaFinal[i][0])
                                .addTo(mymap);
                    }
                }

            </script>

        </div>

        <div class="sidenav">
            <a href="indexServlet">Home</a>
            <a href="loginServlet">Login</a>
            <a href="createServlet">Registar</a>
            <a href="profileServlet">Perfil</a>
        </div>

        <div class="main">
            <h2>jogging and allergies</h2>
            <h3>José Serra nº33289 & Carlos Valente nº33298</h3>  
        </div>

        <div style="position: absolute; left: 1200px; top:170px">

            <!-- FILTRAR ALERGIAS
            <form action="mapServlet">

                <div>
                    <b>Procurar por alergia:</b>
                </div>
                <input type="checkbox" name="1" value ="platano" > platano<br>
                        <input type="checkbox" name="2" value ="graminha"> gramineas<br>
                        <input type="checkbox" name="3" value ="oliveira"> oliveira<br>
                        <input type="checkbox" name="4" value ="azinheira"> azinheira<br><br>

                        <input name="action" type="submit" value="procurar">
            </form> 
        </div>
-->
    </body>
</html>
