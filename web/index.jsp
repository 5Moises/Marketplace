<%@page import="Entidad.EmpresaEntidad"%>
<%@page import="Modelo.Empresa"%>
<%@page import="Entidad.PersonaEntidad"%>
<%@page import="Modelo.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String NombreDistrito = request.getParameter("cbo_distrito");

    Persona persona = new Persona();
    PersonaEntidad personaentidad = (PersonaEntidad) persona.ContarPersonas();

    Empresa empresa = new Empresa();
    EmpresaEntidad empresaentidad = (EmpresaEntidad) empresa.ContarEmpresas(NombreDistrito);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MarketPlace - Mall UTP</title>
        <jsp:include page="Vista/Head.jsp"/>
        <jsp:include page="js/MapaDistritos.jsp"/>
    </head>
    <body>
        <jsp:include page="Vista/Nav.jsp"/>
        <header>
            <h1>MALL UTP</h1>
        </header>

        <section>
            <hgroup>
                Nuestras Empresas
            </hgroup>
            <form action="index.jsp" method="POST">
                <ul>
                    <li>Buscar por distritos:</li>
                    <li>
                        <select name="cbo_distrito">
                            <option value="Seleccionar" selected="selected">Seleccionar</option>
                            <jsp:include page="Vista/MostrarDistritos.jsp"/>
                        </select>
                    </li>
                    <li><input type="submit" value="Buscar"/></li>
                </ul>
            </form> 
            <article>
                <ul>
                    <%
                        if (NombreDistrito != null) {
                            if (empresaentidad.ObtenerCantidadEmpresas() > 0) {
                                out.write("<li>"
                                        + "<b>"
                                        + "Tiendas en el distrito de " + empresaentidad.ObtenerDistrito() + ":"
                                        + "</b>"
                                        + "</li>"
                                        + "<li></li>"
                                        + "<li id=\"MapaDistrito\"></li>");
                            } else {
                                out.write("<li><b>Aún no contamos con empresas en " + empresaentidad.ObtenerDistrito() + "</b></li>");
                            }
                        }
                    %>
                </ul>
            </article>
        </section>

        <section>
            <hgroup>
                Personas
            </hgroup>
            <article>
                Contamos con <%= personaentidad.ObtenerCantidadPersonas()%> personas que confiaron en Mall UTP para la venta de sus productos.
            </article>
        </section>
        <section >
            <hgroup>
                Nuestros productos
            </hgroup>
            <form action="Buscar.jsp" method="GET" >
                <ul>
                    <li><input type="text" name="NombreProducto" placeholder="Buscar por Nombre"/></li>
                    <li><input type="submit" value="Buscar"/></li>
                </ul>
            </form>
        </section>
        <jsp:include page="Vista/Footer.jsp"/>
    </body>
</html>
