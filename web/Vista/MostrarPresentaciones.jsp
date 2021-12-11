<%@page import="Entidad.PresentacionEntidad"%>
<%@page import="Modelo.Presentacion"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%
    Presentacion presentacion = new Presentacion();
    List<PresentacionEntidad> DatosPresentacion = presentacion.MostrarPresentaciones();
    Iterator<PresentacionEntidad> presentacioniterator = DatosPresentacion.iterator();
    PresentacionEntidad datopresentacion = null;
    while (presentacioniterator.hasNext()) {
        datopresentacion = presentacioniterator.next();
%>
<option value="<%= datopresentacion.ObtenerCodPresentacion() %>"><%= datopresentacion.ObtenerNombre()%></option>
<%
    }
%>
