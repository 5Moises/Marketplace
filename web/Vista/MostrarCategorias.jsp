<%@page import="Modelo.Categoria"%>
<%@page import="Entidad.CategoriaEntidad"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%
    Categoria categoria = new Categoria();
    List<CategoriaEntidad> DatosCategoria = categoria.MostrarCategorias();
    Iterator<CategoriaEntidad> categoriaiterator = DatosCategoria.iterator();
    CategoriaEntidad datocategoria = null;
    while (categoriaiterator.hasNext()) {
        datocategoria = categoriaiterator.next();
%>
<option value="<%= datocategoria.ObtenerCodCategoria()%>"><%= datocategoria.ObtenerNombre()%></option>
<%
    }
%>
