<%@page import="Entidad.MarcaEntidad"%>
<%@page import="Modelo.Marca"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%
    Marca marca = new Marca();
    List<MarcaEntidad> DatosMarca = marca.MostrarMarcas();
    Iterator<MarcaEntidad> marcaiterator = DatosMarca.iterator();
    MarcaEntidad datomarca = null;
    while (marcaiterator.hasNext()) {
        datomarca = marcaiterator.next();
%>
<option value="<%= datomarca.ObtenerCodMarca()%>"><%= datomarca.ObtenerNombre()%></option>
<%
    }
%>
