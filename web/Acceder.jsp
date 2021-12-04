<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.invalidate();

    String IdAcceso = "", Contrasena = "";
    
    if (request.getParameter("txt_idacceso") != null || request.getParameter("txt_contrasena") != null) {
        IdAcceso = request.getParameter("txt_idacceso");
        Contrasena = request.getParameter("txt_contrasena");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acceder</title>
        <jsp:include page="Vista/Head.jsp" />
    </head>
    <body onload="MostrarCuadroDialogo();">
        <jsp:include page="Vista/Nav.jsp" />
        <div id="SeccionLados">
            <section>
                <hgroup>
                    Inicia tu sesión
                </hgroup>
                <form action="AccederServlet" method="POST">
                    <ul>
                        <li>Id Acceso:</li>
                        <li><input type="text" name="txt_idacceso" value="<%= IdAcceso%>"/></li>
                        <li>Contraseña:</li>
                        <li><input type="password" name="txt_contrasena" value="<%= Contrasena%>"/></li>
                        <li><input type="submit" name="btn_acceder" Value="Acceder"/></li>
                    </ul>
                </form>  
                ¿No tiene una cuenta? Puede registrarse y formar parte de nuestra familia MallUTP.
            </section>
            <%
                if (request.getAttribute("CuadroMensaje") != null) {
                    out.write(request.getAttribute("CuadroMensaje").toString());
                }
            %> 
        </div>
        <jsp:include page="Vista/Footer.jsp"/>
    </body>
</html>