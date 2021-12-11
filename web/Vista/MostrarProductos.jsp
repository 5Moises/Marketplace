<%@page import="Entidad.EmpresaEntidad"%>
<%@page import="java.util.Iterator"%>
<%@page import="Entidad.ProductoEntidad"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Producto"%>
<%
    int CodEmpresa = Integer.parseInt(request.getParameter("CodEmpresa"));
    String NombreProducto = "", Categoria = "";

    if (request.getParameter("txt_buscarnombre") != null || request.getParameter("cbo_categoria") != null) {
        NombreProducto = request.getParameter("txt_buscarnombre");
        Categoria = request.getParameter("cbo_categoria");
    }
%>
<section class="SeccionSinFondo Productos">
    <hgroup>
        Productos a la venta
    </hgroup>

    <form action="Empresa.jsp?CodEmpresa=<%= CodEmpresa%>&Seccion=Prod" method="POST" name="frm_buscar">
        <ul>
            <li><input type="text" name="txt_buscarnombre" placeholder="Nombre de producto" value="<%= NombreProducto%>"/></li>
            <li>
                <select name="cbo_categoria">
                    <option value="">Categoría</option>
                    <jsp:include page="MostrarCategorias.jsp"/>
                </select>
            </li>
            <li><a href="javascript:;" onclick="frm_buscar.submit();" title="Buscar"></a></li>
        </ul>
    </form>
    <%
        Producto producto = new Producto();
        List<ProductoEntidad> DatosProducto = producto.MostrarProductos(CodEmpresa, NombreProducto, Categoria);
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