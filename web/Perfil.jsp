<%@page import="Entidad.PersonaEntidad"%>
<%@page import="Modelo.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int CodPersona = Integer.parseInt(request.getParameter("CodPersona"));
    Persona persona = new Persona();
    PersonaEntidad personaentidad = (PersonaEntidad) persona.MostrarPersona(CodPersona);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= personaentidad.ObtenerNombres() + " " + personaentidad.ObtenerApellidos()%></title>
        <jsp:include page="Vista/Head.jsp"/>
        <script type="text/javascript" src="js/MapaEmpresas.js"></script>
    </head>
    <body onLoad="MostrarCuadroDialogo();">
        <jsp:include page="Vista/Nav.jsp"/>
        <%
            if (personaentidad.ObtenerTipo() == 0) {
        %>
        <jsp:include page="Vista/Asociados.jsp"/>
        <%
        } else if (personaentidad.ObtenerTipo() == 1) {
        %>
        <jsp:include page="Vista/Administracion.jsp"/>
        <%
            }
        %>
        <jsp:include page="Vista/Footer.jsp"/>
    </body>
</html>