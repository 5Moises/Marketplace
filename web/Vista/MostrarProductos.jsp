<%@page import="java.util.Iterator"%>
<%@page import="Entidad.ProductoEntidad"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Producto"%>
<%
    int CodEmpresa = Integer.parseInt(request.getParameter("CodEmpresa"));
    String NombreProducto = "";
    
    if (request.getParameter("txt_buscarnombre") != null) {
        NombreProducto = request.getParameter("txt_buscarnombre");
    }
%>
<section class="SeccionSinFondo Productos">
    <hgroup>
        Productos a la venta
    </hgroup>
    
    <form action="Empresa.jsp?CodEmpresa=<%= CodEmpresa%>&Seccion=Prod" method="POST">
        <ul>
            <li><input type="text" name="txt_buscarnombre" value="<%= NombreProducto%>"/></li>
            <li><input type="submit" name="btn_buscar" value="Buscar"/></li>
        </ul>
    </form>
    <%
        Producto producto = new Producto();
        List<ProductoEntidad> DatosProducto = producto.MostrarProductos(CodEmpresa, NombreProducto);
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
</section>