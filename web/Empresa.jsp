<%@page import="Entidad.ComentarioEntidad"%>
<%@page import="Modelo.Comentario"%>
<%@page import="Entidad.EmpresaEntidad"%>
<%@page import="Modelo.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int CodEmpresa = Integer.parseInt(request.getParameter("CodEmpresa"));
    String Seccion = request.getParameter("Seccion");
    String PaginaSeccion = "";
    int AbrirSesion = 0;

    if (session.getAttribute("AbrirSesion") != null) {
        AbrirSesion = Integer.parseInt(session.getAttribute("AbrirSesion").toString());
    }

    Empresa empresa = new Empresa();
    EmpresaEntidad empresaentidad = (EmpresaEntidad) empresa.MostrarEmpresa(CodEmpresa);
    Comentario comentario = new Comentario();
    ComentarioEntidad comentarioentidad = (ComentarioEntidad) comentario.PromedioCalificacion(CodEmpresa);

    String Nombre = "", Precio = "", Descripcion = "";
    if (request.getParameter("txt_nombre") != null || request.getParameter("txt_precio") != null || request.getParameter("txt_descripcion") != null) {
        Nombre = request.getParameter("txt_nombre");
        Precio = request.getParameter("txt_precio");
        Descripcion = request.getParameter("txt_descripcion");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= empresaentidad.ObtenerNombre()%></title>
        <jsp:include page="Vista/Head.jsp"/>
        <script type="text/javascript" src="js/MapaEmpresas.js"></script>

        <%
            out.write("<style type=\"text/css\">");
            for (byte i = 0; i <= comentarioentidad.ObtenerPromedioCalificacion(); i++) {
                out.write(".InformacionEmpresa article:nth-of-type(1) ul:nth-of-type(2) li:nth-of-type(" + i + "){filter:grayscale(0)}");
            }
            out.write("</style>");
        %>
    </head>
    <body onload="MostrarCuadroDialogo();">
        <jsp:include page="Vista/Nav.jsp"/>

        <%
            if (empresaentidad.ObtenerCodPersona() == AbrirSesion) {
        %>
        <div id="SeccionLados">
            <section>
                <hgroup>
                    Datos de la Empresa
                </hgroup>
                <form action="EmpresaServlet" method="POST" enctype="multipart/form-data">
                    <ul>
                        <li class="CargarImagen"><input type="file" name="fil_imagen"/></li>
                        <li>Nombre Empresa:</li>
                        <li><input type="text" name="txt_nombre" value="<%= empresaentidad.ObtenerNombre()%>"/></li>
                        <li>Teléfono:</li>
                        <li><input type="number" name="txt_telefono" value="<%= empresaentidad.ObtenerTelefono()%>"/></li>
                        <li>E-mail:</li>
                        <li><input type="text" name="txt_email" value="<%= empresaentidad.ObtenerEmail()%>"/></li>
                        <li>Dirección:</li>
                        <li><input type="text" name="txt_direccion" value="<%= empresaentidad.ObtenerDireccion()%>"/></li>
                        <li>Distrito:</li>
                        <li>
                            <select name="cbo_distrito">
                                <option value="<%= empresaentidad.ObtenerDistrito()%>" selected="selected"><%= empresaentidad.ObtenerDistrito()%></option>
                                <jsp:include page="Vista/MostrarDistritos.jsp"/>
                            </select>
                        </li>
                        <li>Descripcion:</li>
                        <li><textarea name="txt_descripcion"><%= empresaentidad.ObtenerDescripcion()%></textarea></li>
                        <li>Ubicación:</li>
                        <li class="AbrirMapa"><a onclick="MostrarCuadroMapa();" title="Abrir Mapa"></a></li>

                        <li><input type="hidden" name="txt_latitud" value="<%= empresaentidad.ObtenerLatitud()%>" id="Latitud"/></li>
                        <li><input type="hidden" name="txt_longitud" value="<%= empresaentidad.ObtenerLongitud()%>" id="Longitud"/></li>

                        <li><input type="submit" name="btn_modificar" Value="Modificar"/></li>   
                    </ul>
                    <input type="hidden" name="Consulta" value="2"/>
                    <input type="hidden" name="txt_codempresa" value="<%= empresaentidad.ObtenerCodEmpresa()%>"/>       
                </form>

                <!--  <form action="EmpresaServlet" method="POST">
                      <ul>        
                          <li><input type="submit" name="btn_eliminar" value="Eliminar Empresa"/></li>
                      </ul>
                      <input type="hidden" name="Consulta" value="3"/>
                      <input type="hidden" name="txt_codempresa" value="<%= empresaentidad.ObtenerCodEmpresa()%>"/>
                  </form> -->

                <div id="FondoCuadroMapa" onclick="CerrarCuadroMapa();">
                </div>
                <div id="CuadroMapa">
                    <div id="MapaEmpresa"></div>
                </div>
            </section>

            <section>
                <hgroup>
                    Registrar nuevo producto
                </hgroup>
                <form action="ProductoServlet" method="POST" enctype="multipart/form-data">             
                    <ul>
                        <li class="CargarImagen"><input type="file" name="fil_imagen"/></li>
                        <li>Nombre Producto:</li>
                        <li><input type="text" name="txt_nombre" value="<%= Nombre%>"/></li>
                        <li>Precio:</li>
                        <li><input type="number" name="txt_precio" value="<%= Precio%>" step="any"/></li>
                        <li>Presentación:</li>
                        <li>
                            <select name="cbo_presentacion">
                                <option value="Seleccionar" selected="selected">Seleccionar</option>
                                <jsp:include page="Vista/MostrarPresentaciones.jsp"/>
                            </select>
                        </li>
                        <li>Marca:</li>
                        <li>
                            <select name="cbo_marca">
                                <option value="Seleccionar" selected="selected">Seleccionar</option>
                                <jsp:include page="Vista/MostrarMarcas.jsp"/>
                            </select>
                        </li>
                        <li>Categoría:</li>
                        <li>
                            <select name="cbo_categoria">
                                <option value="Seleccionar" selected="selected">Seleccionar</option>
                                <jsp:include page="Vista/MostrarCategorias.jsp"/>
                            </select>
                        </li>
                        <li>Descripcion:</li>
                        <li><textarea name="txt_descripcion"><%= Descripcion%></textarea></li>
                        <li><input type="submit" name="btn_registrar" Value="Registrar"/></li>   
                    </ul>
                    <input type="hidden" name="Consulta" value="1"/>
                    <input type="hidden" name="txt_codempresa" value="<%= empresaentidad.ObtenerCodEmpresa()%>"/>
                </form>
            </section>
        </div>
        <%
            }
        %>

        <div id="SeccionesCentro" class="SeccionEmpresa">
            <%
                if (empresaentidad.ObtenerCodPersona() != AbrirSesion) {
            %>
            <section class="InformacionEmpresa">
                <hgroup>
                    <h2><%= empresaentidad.ObtenerNombre()%></h2>
                </hgroup>
                <article>
                    <ul>
                        <li><b>Teléfono:</b> <%= empresaentidad.ObtenerTelefono()%></li>
                        <li><b>E-Mail:</b> <%= empresaentidad.ObtenerEmail()%></li>
                        <li><b>Dirección:</b> <%= empresaentidad.ObtenerDireccion()%></li>
                        <li><b>Descripción:</b> <%= empresaentidad.ObtenerDescripcion()%></li>
                        <li><b>Distrito:</b> <%= empresaentidad.ObtenerDistrito()%></li>
                    </ul>
                    <ul>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                    <ul>
                        <li><a href="Empresa.jsp?CodEmpresa=<%= empresaentidad.ObtenerCodEmpresa()%>&Seccion=Coment" title="Comentar y Calificar"></a></li>
                        <li><a href="Empresa.jsp?CodEmpresa=<%= empresaentidad.ObtenerCodEmpresa()%>&Seccion=Prod" title="Catálogo"></a></li>
                        <li><a href="Empresa.jsp?CodEmpresa=<%= empresaentidad.ObtenerCodEmpresa()%>&Seccion=Carrito" title="Carrito de Compras"></a></li>
                    </ul>
                </article>
                <article>
                    <img src="Vista/MostrarImagenEmpresa.jsp?CodEmpresa=<%= empresaentidad.ObtenerCodEmpresa()%>"/>
                </article>
            </section>
            <%
                }
                if (empresaentidad.ObtenerCodPersona() == AbrirSesion) {
            %>
            <section class="SeccionSinFondo MenuVendedor">
                <ul>
                    <li><a href="Empresa.jsp?CodEmpresa=<%= empresaentidad.ObtenerCodEmpresa()%>&Seccion=Prod" title="Catálogo"></a></li>
                    <li><a href="Empresa.jsp?CodEmpresa=<%= empresaentidad.ObtenerCodEmpresa()%>&Seccion=Pedidos" title="Pedidos a la Empresa"></a></li>
                    <li><a href="Empresa.jsp?CodEmpresa=<%= empresaentidad.ObtenerCodEmpresa()%>&Seccion=Reporte" title="Reporte">Reporte</a></li>
                    <li><a href="Empresa.jsp?CodEmpresa=<%= empresaentidad.ObtenerCodEmpresa()%>&Seccion=ReporteP" title="Reportes de Productos">Reporte Prodcuto</a></li>
                </ul>
            </section>
            <%
                }

                switch (Seccion) {
                    case "Prod":
                        PaginaSeccion = "Vista/MostrarProductos.jsp";
                        break;
                    case "Carrito":
                        PaginaSeccion = "Vista/MostrarCarritoCompras.jsp";
                        break;
                    case "Coment":
                        PaginaSeccion = "Vista/MostrarComentarios.jsp";
                        break;
                    case "Pedidos":
                        PaginaSeccion = "Vista/MostrarPedidosEmpresa.jsp";
                        break;
                    case "Reporte":
                        PaginaSeccion = "Vista/Reporte.jsp";
                        break;
                    case "ReporteP":
                        PaginaSeccion = "Vista/ReporteProductos.jsp";
                        break;
                }

                if (request.getAttribute("CuadroMensaje") != null) {
                    out.write(request.getAttribute("CuadroMensaje").toString());
                }
            %>
            <jsp:include page="<%= PaginaSeccion%>"/>
        </div>
        <jsp:include page="Vista/Footer.jsp"/>
    </body>
</html>
