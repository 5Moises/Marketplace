<%@page import="Configuracion.Conexion"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
    String CodEmpresa = request.getParameter("CodEmpresa");
    try {
        Connection con = Conexion.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT Imagen FROM Empresa WHERE CodEmpresa = ?");
        ps.setString(1, CodEmpresa);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Blob blob = rs.getBlob(1);
            byte byteArray[] = blob.getBytes(1, (int) blob.length());
            response.setContentType("image/gif");
            OutputStream os = response.getOutputStream();
            os.write(byteArray);
            os.flush();
            os.close();
        } else {
            System.out.println("No image found with this id.");
        }
    } catch (Exception e) {
        out.println(e);
    }
%>

