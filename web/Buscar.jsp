<%@page import="Entidad.ProductoEntidad"%>
<%@page import="Modelo.Producto"%>
<%@page import="java.util.Iterator"%>
<%@page import="Entidad.EmpresaEntidad"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String NombreDistrito = request.getParameter("NombreDistrito");
    String NombreProducto = "", Marca = "", Categoria = "", Presentacion = "";

    if (request.getParameter("NombreProducto") != null) {
        if (!request.getParameter("NombreProducto").equals("")) {
            NombreProducto = request.getParameter("NombreProducto");
        } else {
            NombreProducto = "...";
        }
    }
    if (request.getParameter("Marca") != null) {
        Marca = request.getParameter("Marca");
    }
    if (request.getParameter("Categoria") != null) {
        Categoria = request.getParameter("Categoria");
    }
    if (request.getParameter("Presentacion") != null) {
        Presentacion = request.getParameter("Presentacion");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar</title>
        <jsp:include page="Vista/Head.jsp"/>
    </head>
    <body>
        <jsp:include page="Vista/Nav.jsp"/>
        <div id="SeccionesCentro">
            <section class="SeccionSinFondo">
                <hgroup>
                    Resultados de la busqueda:
                </hgroup>
                <%
                    try {
                %>
                <div class="Empresas">
                    <%
                        Empresa empresa = new Empresa();
                        List<EmpresaEntidad> DatosEmpresa = empresa.BuscarEmpresas(NombreDistrito);
                        Iterator<EmpresaEntidad> empresaiterator = DatosEmpresa.iterator();
                        EmpresaEntidad datoempresa = null;
                        while (empresaiterator.hasNext()) {
                            datoempresa = empresaiterator.next();
                    %>
                    <a href="Empresa.jsp?CodEmpresa=<%= datoempresa.ObtenerCodEmpresa()%>&Seccion=Prod">
                        <article>
                            <ul>
                                <li><img src="Vista/MostrarImagenEmpresa.jsp?CodEmpresa=<%= datoempresa.ObtenerCodEmpresa()%>" /></li>
                                <li><%= datoempresa.ObtenerNombre()%></li>
                            </ul>
                        </article>
                    </a>
                    <%
                        }
                    %>
                </div>

                <div class="Productos">  
                    <%
                        Producto producto = new Producto();
                        List<ProductoEntidad> DatosProducto = producto.BuscarProductos(NombreProducto, Presentacion, Marca, Categoria);
                        Iterator<ProductoEntidad> productoiterator = DatosProducto.iterator();
                        ProductoEntidad datoproducto = null;
                        while (productoiterator.hasNext()) {
                            datoproducto = productoiterator.next();
                    %>
                    <a href="Producto.jsp?CodProducto=<%= datoproducto.ObtenerCodProducto()%>">
                        <Article>
                            <ul>
                                <li><%= datoproducto.ObtenerNombre()%></li>
                                <li><img src="Vista/MostrarImagenProducto.jsp?CodProducto=<%= datoproducto.ObtenerCodProducto()%>"/></li>
                                <li>S/ <%= datoproducto.ObtenerPrecio()%></li>
                            </ul>
                        </Article>
                    </a>
                    <%
                        }
                    %>
                </div>
                <%
                    } catch (Exception Ex) {
                    }
                %>
            </section>
        </div>
        <jsp:include page="Vista/Footer.jsp"/>
    </body>
</html>
