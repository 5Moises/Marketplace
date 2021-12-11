<%@page import="Entidad.ProductoEntidad"%>
<%@page import="Modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int CodProducto = Integer.parseInt(request.getParameter("CodProducto"));
    int AbrirSesion = 0;
    if (session.getAttribute("AbrirSesion") != null) {
        AbrirSesion = Integer.parseInt(session.getAttribute("AbrirSesion").toString());
    }
    Producto producto = new Producto();
    ProductoEntidad productoentidad = (ProductoEntidad) producto.MostrarProducto(CodProducto);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= productoentidad.ObtenerNombre()%></title>
        <jsp:include page="Vista/Head.jsp"/>
    </head>
    <body onload="MostrarCuadroDialogo();">
        <jsp:include page="Vista/Nav.jsp"/>
        <%
            if (productoentidad.ObtenerCodPersona() == AbrirSesion) {
        %>
        <div id="SeccionLados">
            <section>
                <hgroup>
                    Datos del Producto
                </hgroup>
                <form action="ProductoServlet" method="POST" enctype="multipart/form-data">
                    <ul>
                        <li><img src="Vista/MostrarImagenProducto.jsp?CodProducto=<%= productoentidad.ObtenerCodProducto()%>"/></li>
                        <li class="CargarImagen"><input type="file" name="fil_imagen"/></li>
                        <li>Nombre Producto:</li>
                        <li><input type="text" name="txt_nombre" value="<%= productoentidad.ObtenerNombre()%>"/></li>
                        <li>Precio:</li>
                        <li><input type="number" name="txt_precio" value="<%= productoentidad.ObtenerPrecio()%>" step="any"/></li>
                        <li>Presentación:</li>
                        <li>
                            <select name="cbo_presentacion">
                                <option value="<%= productoentidad.ObtenerPresentacion()%>" selected="selected"><%= productoentidad.ObtenerPresentacion()%></option>
                                <jsp:include page="Vista/MostrarPresentaciones.jsp"/>
                            </select>
                        </li>
                        <li>Marca:</li>
                        <li>
                            <select name="cbo_marca">
                                <option value="<%= productoentidad.ObtenerMarca()%>" selected="selected"><%= productoentidad.ObtenerMarca()%></option>
                                <jsp:include page="Vista/MostrarMarcas.jsp"/>
                            </select>
                        </li>
                        <li>Categoría:</li>
                        <li>
                            <select name="cbo_categoria">
                                <option value="<%= productoentidad.ObtenerCategoria()%>" selected="selected"><%= productoentidad.ObtenerCategoria()%></option>
                                <jsp:include page="Vista/MostrarCategorias.jsp"/>
                            </select>
                        </li>
                        <li>Disponible:</li>
                        <li>
                            <select name="cbo_disponible">
                                <option value="<%= productoentidad.ObtenerDisponible()%>" selected="selected"><%= productoentidad.ObtenerDisponible()%></option>
                                <option value="true">Si</option>
                                <option value="false">No</option>
                            </select>
                        </li>
                        <li>Descripcion:</li>
                        <li><textarea name="txt_descripcion"><%= productoentidad.ObtenerDescripcion()%></textarea></li>
                        <li><input type="submit" name="btn_modificar" Value="Modificar"/></li>   
                    </ul>
                    <input type="hidden" name="Consulta" value="2"/>
                    <input type="hidden" name="txt_codproducto" value="<%= productoentidad.ObtenerCodProducto()%>"/>
                </form>
                <!--    <form action="ProductoServlet" method="POST">
                        <ul>        
                            <li><input type="submit" name="btn_eliminar" value="Eliminar Producto"/></li>
                        </ul>
                        <input type="hidden" name="Consulta" value="3"/>
                        <input type="hidden" name="txt_codempresa" value="<%= productoentidad.ObtenerCodEmpresa()%>"/>
                        <input type="hidden" name="txt_codproducto" value="<%= productoentidad.ObtenerCodProducto()%>"/>
                    </form> -->
            </section>
        </div>
        <%
        } else {
        %>
        <div id="SeccionProductoCentro">
            <div id="SeccionesCentro" class="SeccionProducto"> 
                <section>
                    <hgroup>
                        <%= productoentidad.ObtenerNombre()%>
                    </hgroup>
                    <article>
                        <img src="Vista/MostrarImagenProducto.jsp?CodProducto=<%= productoentidad.ObtenerCodProducto()%>"/>
                        <form action="DetallePedidoServlet" name="frm_agregarcarrito" method="POST">
                            <ul>
                                <li><a href="Empresa.jsp?CodEmpresa=<%= productoentidad.ObtenerCodEmpresa()%>&Seccion=Prod" title="Ver catálogo"></a></li>
                                    <%
                                        if (session.getAttribute("AbrirSesion") != null) {
                                    %>
                                <li><input type="number" name="txt_cantidad" placeholder="Cantidad"/></li>
                                <li><a href="javascript:;" onclick="frm_agregarcarrito.submit();" title="Agregar al Carrito"></a></li>
                                    <%
                                        } else {
                                            out.write("<li><i>Para comprar un producto debe iniciar sesión.</i></li>");
                                        }
                                    %>
                            </ul>
                            <input type="hidden" name="Consulta" value="1"/>
                            <input type="hidden" name="txt_codproducto" value="<%= productoentidad.ObtenerCodProducto()%>"/>
                        </form>
                    </article>
                    <article>
                        <ul>
                            <li><b>Descripción:</b> <%= productoentidad.ObtenerDescripcion()%></li>
                            <li><b>Precio:</b> S/ <%= productoentidad.ObtenerPrecio()%></li>
                            <li><b>Marca:</b> <%= productoentidad.ObtenerMarca()%></li>
                            <li><b>Categoría:</b> <%= productoentidad.ObtenerCategoria()%></li>
                            <li><b>Presentación:</b> <%= productoentidad.ObtenerPresentacion()%></li>
                        </ul>
                    </article>
                </section>
            </div>
        </div>
        <%
            }
            if (request.getAttribute("CuadroMensaje") != null) {
                out.write(request.getAttribute("CuadroMensaje").toString());
            }
        %>
        <jsp:include page="Vista/Footer.jsp"/>
    </body>
</html>