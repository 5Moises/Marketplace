<%@page import="Entidad.DistritoEntidad"%>
<%@page import="Modelo.Distrito"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%
    Distrito distrito = new Distrito();
    List<DistritoEntidad> DatosDistrito = distrito.MostrarDistritos();
    Iterator<DistritoEntidad> distritoiterator = DatosDistrito.iterator();
    DistritoEntidad datodistrito = null;
    while (distritoiterator.hasNext()) {
        datodistrito = distritoiterator.next();
%>
<option value="<%= datodistrito.ObtenerNombre()%>"><%= datodistrito.ObtenerNombre()%></option>
<%
    }
%>
