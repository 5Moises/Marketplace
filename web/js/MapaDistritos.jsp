<%@page import="java.util.Iterator"%>
<%@page import="Entidad.EmpresaEntidad"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Empresa"%>
<%
    String NombreDistrito = "";
    if (request.getParameter("cbo_distrito") != null) {
        NombreDistrito = request.getParameter("cbo_distrito");
    }
%>
<script type="text/javascript">
    function initMap() {
    <%
        Empresa empresa = new Empresa();
        List<EmpresaEntidad> DatosEmpresa = empresa.BuscarEmpresas(NombreDistrito);
        Iterator<EmpresaEntidad> empresaiterator1 = DatosEmpresa.iterator();
        EmpresaEntidad datoempresa1 = null;
        if (empresaiterator1.hasNext()) {
            datoempresa1 = empresaiterator1.next();
    %>
        var Coordenada = {lat: <%= datoempresa1.ObtenerLatitud()%>, lng: <%= datoempresa1.ObtenerLongitud()%>};
        var Mapa = new google.maps.Map(document.getElementById("MapaDistrito"), {
            zoom: 15,
            center: Coordenada
        });
    <%
        }
        int Numero = 0;
        Iterator<EmpresaEntidad> empresaiterator = DatosEmpresa.iterator();
        EmpresaEntidad datoempresa = null;
        while (empresaiterator.hasNext()) {
            datoempresa = empresaiterator.next();
            Numero++;
    %>
        var Coordenada<%=Numero%> = {lat: <%= datoempresa.ObtenerLatitud()%>, lng: <%= datoempresa.ObtenerLongitud()%>};
        var Marcador<%=Numero%> = new google.maps.Marker({
            position: Coordenada<%=Numero%>,
            map: Mapa,
            title: "<%= datoempresa.ObtenerNombre()%>",
            url: "Empresa.jsp?CodEmpresa=<%= datoempresa.ObtenerCodEmpresa()%>&Seccion=Prod"
        });
        google.maps.event.addListener(Marcador<%=Numero%>, 'click', function () {
            window.location.href = this.url;
        });
    <%
        }
    %>
    }
</script>