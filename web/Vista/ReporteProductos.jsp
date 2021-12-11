<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Configuracion.Conexion"%>
<%
    int CodEmpresa = Integer.parseInt(request.getParameter("CodEmpresa"));
%>
<section>
    <form action="ReporteServlet" method="POST">
        <ul>
            <li>NOMBRE PRODUCTO<input type="text" name="dtp_NombreP"  style="width: 94%; padding: 5px; border: 2px solid #b01e31;border-radius: 10px;"/></li>  
            <li>Marca:</li>
            <li>
                <select name="cbo_marca">
                    <option value="Seleccionar" selected="selected">Seleccionar</option>
                    <jsp:include page="MostrarMarcas.jsp"/>
                </select>
            </li>
            <li>Categoría:</li>
            <li>
                <select name="cbo_categoria">
                    <option value="Seleccionar" selected="selected">Seleccionar</option>
                    <jsp:include page="MostrarCategorias.jsp"/>
                </select>
            </li>        
            <li><input type="submit" name="btn_ReporteS" value="Generar Reporte"></li>
        </ul>
        <input type="hidden" name="txt_codempresa" value="<%= CodEmpresa%>"/>
    </form>
</section>