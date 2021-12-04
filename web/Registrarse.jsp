<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String Nombres = "", Apellidos = "", Email = "", IdAcceso = "", Contrasena = "";
    if (request.getParameter("txt_nombres") != null || request.getParameter("txt_apellidos") != null || request.getParameter("txt_email") != null || request.getParameter("txt_idacceso") != null || request.getParameter("txt_contrasena") != null) {
        Nombres = request.getParameter("txt_nombres");
        Apellidos = request.getParameter("txt_apellidos");
        Email = request.getParameter("txt_email");
        IdAcceso = request.getParameter("txt_idacceso");
        Contrasena = request.getParameter("txt_contrasena");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
        <jsp:include page="Vista/Head.jsp"/>
    </head>
    <body onload="MostrarCuadroDialogo();">
        
        <jsp:include page="Vista/Nav.jsp"/>
        <div id="SeccionLados">
            <section>
                <hgroup>
                    Se parte de nuestro Marketplace
                </hgroup>
                <form action="PersonaServlet" method="POST"  enctype="multipart/form-data">
                    <ul>
                        <li class="CargarImagen"><input type="file" name="fil_imagen"></li>
                        <li>Nombres:</li>
                        <li><input type="text" name="txt_nombres" value="<%= Nombres%>"></li>
                        <li>Apellidos:</li>
                        <li><input type="text" name="txt_apellidos" value="<%= Apellidos%>"></li>
                        <li>E-mail:</li>
                        <li><input type="email" name="txt_email" value="<%= Email%>"></li>
                        <li>Id Acceso:</li>
                        <li><input type="text" name="txt_idacceso" value="<%= IdAcceso%>"></li>
                        <li>Contraseña:</li>
                        <li><input type="text" name="txt_contrasena" maxlength="15" value="<%= Contrasena%>"></li>
                        <li><input type="submit" name="btn_registrar" value="Registrarse"></li>
                    </ul>
                    <input type="hidden" name="Consulta" value="1"/>
                </form>
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