<%@page import="Entidad.TipoPagoEntidad"%>
<%@page import="Modelo.TipoPago"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%
    TipoPago tipopago = new TipoPago();
    List<TipoPagoEntidad> DatosTipoPago = tipopago.MostrarTiposPago();
    Iterator<TipoPagoEntidad> tipopagoiterator = DatosTipoPago.iterator();
    TipoPagoEntidad datotipopago = null;
    while (tipopagoiterator.hasNext()) {
        datotipopago = tipopagoiterator.next();
%>
<option value="<%= datotipopago.ObtenerNombre()%>"><%= datotipopago.ObtenerNombre()%></option>
<%
    }
%>
