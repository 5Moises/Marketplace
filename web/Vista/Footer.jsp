<%@page import="Entidad.MarcaEntidad"%>
<%@page import="Modelo.Marca"%>
<%@page import="Entidad.CategoriaEntidad"%>
<%@page import="Modelo.Categoria"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Entidad.DistritoEntidad"%>
<%@page import="Modelo.Distrito"%>
<footer>
    <ul>
        <li>&nbsp;</li>
        <li>Mall UTP</li>
        <li>MarketPlace</li>
        <li>Contactános:</li>
        <li>Telefono: 999-3232</li>
        <li>E-Mail: malutp@hotmail.com</li>
    </ul>
    <ul>
        <li>Sobre nosotros</li>
        <li>Somos una organización dedicada a facilitar</li>
        <li>las ventas en las pequeñas y medianas</li>
        <li>empresas en la región.</li>
        <li>Ofrecemos una variedad de herramientas</li>
        <li>útiles para que administres tus ventas</li>
        <li>y pedidos. Explora empresas y productos</li>
        <li>de los diferentes vendedores, todo un</li>
        <li>gran espacio pensado para ti.</li>
        <li>&copy; Todos los derechos reservados.</li>
    </ul>
    <ul>
        <li>Distritos</li>
        <%
            Distrito distrito = new Distrito();
            List<DistritoEntidad> DatosDistrito = distrito.MostrarDistritos();
            Iterator<DistritoEntidad> iterator = DatosDistrito.iterator();
            DistritoEntidad datodistrito = null;
            while (iterator.hasNext()) {
                datodistrito = iterator.next();
        %>
        <li>
            <a href="Buscar.jsp?NombreDistrito=<%= datodistrito.ObtenerNombre()%>">&#8226; <%= datodistrito.ObtenerNombre()%></a>
        </li>
        <%
            }
        %>
    </ul>
    <ul>
        <li>Categorías</li>
         <%
            Categoria categorias = new Categoria();
            List<CategoriaEntidad> DatosCategoria = categorias.MostrarCategorias();
            Iterator<CategoriaEntidad> categoriasiterator = DatosCategoria.iterator();
            CategoriaEntidad datocategoria = null;
            while (categoriasiterator.hasNext()) {
                datocategoria = categoriasiterator.next();
        %>
        <li>
            <a href="Buscar.jsp?Categoria=<%= datocategoria.ObtenerCodCategoria()%>">&#8226; <%= datocategoria.ObtenerNombre()%></a>
        </li>
        <%
            }
        %>
    </ul>
    <ul>
        <li>Marcas</li>
        <%
            Marca marca = new Marca();
            List<MarcaEntidad> DatosMarca = marca.MostrarMarcas();
            Iterator<MarcaEntidad> marcaiterator = DatosMarca.iterator();
            MarcaEntidad datomarca = null;
            while (marcaiterator.hasNext()) {
                datomarca = marcaiterator.next();
        %>
        <li>
            <a href="Buscar.jsp?Marca=<%= datomarca.ObtenerCodMarca()%>">&#8226; <%= datomarca.ObtenerNombre()%></a>
        </li>
        <%
            }
        %>
    </ul>
</footer>