<%@page import="Configuracion.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int CodEmpresa = Integer.parseInt(request.getParameter("CodEmpresa"));
%>
<section>      
    <form action="ReporteServlet" method="POST">
        <ul>
            <li>NOMBRE CLIENTE<input type="text" name="dtp_Nombre"  style="width: 94%; padding: 5px; border: 2px solid #b01e31;border-radius: 10px;"/></li>  
            <li>FECHA INICIAL<input type="date" name="dtp_fechaInicial"  style="width: 94%; padding: 5px; border: 2px solid #b01e31;border-radius: 10px;"/></li>          
            <li>FECHA FINAL<input type="date" name="dtp_fechafinal" style="width: 94%; padding: 5px; border: 2px solid #b01e31;border-radius: 10px;"/></li>             
            <li><input type="submit" name="btn_ReporteS" value="Reporte"></li>
        </ul>
        <input type="hidden" name="txt_codempresa" value="<%= CodEmpresa%>"/>
    </form>
</section>
