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
  font-size: 24px; /* Increased text to enable scrolling */
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
  <a href="mapServlet">Ver Mapa</a>
  <a href="profileServlet">Perfil</a>
  <a href="logoutServlet">logout</a>
</div>

<div class="main">
  <h2><%session=request.getSession(false);    
        Utilizador user = (Utilizador)session.getAttribute("user");  %>
  <%="Bem vindo "+user.getNome()+""%></h2>
  
  <p>Utilize a barra lateral para aceder ao seu Perfil, onde poderá editar as suas alergias, ou adicionar ou remover registos no mapa.<br>
  Pode também aceder diretamente ao mapa de alergias clicando em "Ver Mapa", para aceder ao histórico completo de registos.</p>
  

  
</div>
   
</body>
</html> 
