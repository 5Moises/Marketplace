<%@page import="Entidad.DetallePedidoEntidad"%>
<%@page import="Modelo.DetallePedido"%>
<%@page import="Entidad.PedidoEntidad"%>
<%@page import="Modelo.Pedido"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Entidad.EmpresaEntidad"%>
<%@page import="Modelo.Empresa"%>
<%@page import="Entidad.PersonaEntidad"%>
<%@page import="Modelo.Persona"%>
<%
    int CodPersona = Integer.parseInt(request.getParameter("CodPersona"));
    int AbrirSesion = 0;
    if (session.getAttribute("AbrirSesion") != null) {
        AbrirSesion = Integer.parseInt(session.getAttribute("AbrirSesion").toString());
    }
    Persona persona = new Persona();
    PersonaEntidad personaentidad = (PersonaEntidad) persona.MostrarPersona(CodPersona);

    String Nombre = "", Telefono = "", Email = "", Direccion = "", Descripcion = "";
    if (request.getParameter("txt_nombre") != null || request.getParameter("txt_telefono") != null || request.getParameter("txt_email") != null || request.getParameter("txt_direccion") != null || request.getParameter("txt_descripcion") != null) {
        Nombre = request.getParameter("txt_nombre");
        Telefono = request.getParameter("txt_telefono");
        Email = request.getParameter("txt_email");
        Direccion = request.getParameter("txt_direccion");
        Descripcion = request.getParameter("txt_descripcion");
    }

    if (CodPersona == AbrirSesion) {
%>
<div id="SeccionLados">
    <section>
        <hgroup>
            Datos Personales
        </hgroup>
        <form action="PersonaServlet" method="POST" enctype="multipart/form-data">
            <ul>
                <li><img src="Vista/MostrarImagenPersona.jsp?CodPersona=<%= personaentidad.ObtenerCodPersona()%>"/></li>
                <li class="CargarImagen"><input type="file" name="fil_imagen"></li>
                <li>Nombres:</li>
                <li><input type="text" name="txt_nombres" value="<%= personaentidad.ObtenerNombres()%>"></li>
                <li>Apellidos:</li>
                <li><input type="text" name="txt_apellidos" value="<%= personaentidad.ObtenerApellidos()%>"></li>
                <li>E-Mail:</li>
                <li><input type="email" name="txt_email" value="<%= personaentidad.ObtenerEmail()%>"></li>
                <li>Teléfono:</li>
                <li><input type="text" name="txt_telefono" value="<%= personaentidad.ObtenerTelefono()%>"></li>
                <li>Direccion:</li>
                <li><input type="text" name="txt_direccion" value="<%= personaentidad.ObtenerDireccion()%>"></li>
                <li><input type="submit" name="btn_modificar" value="Modificar"></li>
            </ul>
            <input type="hidden" name="Consulta" value="2"/>
        </form>

        <!--  <form action="PersonaServlet" method="POST">
              <ul>        
                  <li><input type="submit" name="btn_eliminar" value="Eliminar Cuenta"/></li>
              </ul>
              <input type="hidden" name="Consulta" value="3"/>
          </form> -->
    </section>

    <section>
        <hgroup>
            Registrar nueva Empresa
        </hgroup>
        <form action="EmpresaServlet" method="POST" enctype="multipart/form-data">
            <ul>
                <li class="CargarImagen"><input type="file" name="fil_imagen"/></li>
                <li>Nombre Empresa:</li>
                <li><input type="text" name="txt_nombre" value="<%= Nombre%>"/></li>
                <li>Teléfono:</li>
                <li><input type="text" name="txt_telefono" value="<%= Telefono%>"/></li>
                <li>E-mail:</li>
                <li><input type="email" name="txt_email" value="<%= Email%>"/></li>
                <li>Dirección:</li>
                <li><input type="text" name="txt_direccion" value="<%= Direccion%>"/></li>
                <li>Distrito:</li>
                <li>
                    <select name="cbo_distrito">
                        <option value="Seleccionar" selected="selected">Seleccionar</option>
                        <jsp:include page="MostrarDistritos.jsp"/>
                    </select>
                </li>
                <li>Descripcion:</li>
                <li><textarea name="txt_descripcion"><%= Descripcion%></textarea></li>    
                <li>Ubicación:</li>
                <li class="AbrirMapa"><a onclick="MostrarCuadroMapa();"  title="Abrir Mapa"></a></li>

                <li><input type="hidden" name="txt_latitud" id="Latitud"/></li>
                <li><input type="hidden" name="txt_longitud" id="Longitud"/></li>

                <li><input type="submit" name="btn_registrar" Value="Registrar"/></li>
            </ul>
            <input type="hidden" name="Consulta" value="1"/>   
        </form>
    </section>

    <div id="FondoCuadroMapa" onclick="CerrarCuadroMapa();">
    </div>
    <div id="CuadroMapa">
        <div id="MapaEmpresa"></div>
    </div>
    <%
        }
    %>
</div>

<div id="SeccionesCentro">
    <%
        if (CodPersona != AbrirSesion) {
    %>
    <section class="InformacionPersona">
        <hgroup>
            Datos Personales
        </hgroup>
        <article>
            <img src="Vista/MostrarImagenPersona.jsp?CodPersona=<%= personaentidad.ObtenerCodPersona()%>"/>
        </article>
        <article>
            <ul>
                <li><h2><%= personaentidad.ObtenerNombres() + " " + personaentidad.ObtenerApellidos()%></h2></li>
                <li><%= personaentidad.ObtenerEmail()%></li>
                <li>Teléfono: <%= personaentidad.ObtenerTelefono()%></li>
                <li>Dirección: <%= personaentidad.ObtenerDireccion()%></li>
            </ul>
        </article>
    </section>
    <%
        }
    %>
    <section class="SeccionSinFondo Empresas">
        <hgroup>
            Empresas Administradas
        </hgroup>
        <%
            Empresa empresa = new Empresa();
            List<EmpresaEntidad> DatosEmpresa = empresa.MostrarEmpresas(personaentidad.ObtenerCodPersona());
            Iterator<EmpresaEntidad> empresaiterator = DatosEmpresa.iterator();
            EmpresaEntidad datoempresa = null;
            while (empresaiterator.hasNext()) {
                datoempresa = empresaiterator.next();
        %>
        <a href="Empresa.jsp?CodEmpresa=<%=datoempresa.ObtenerCodEmpresa()%>&Seccion=Prod">
            <article>
                <ul>
                    <li><img src="Vista/MostrarImagenEmpresa.jsp?CodEmpresa=<%=datoempresa.ObtenerCodEmpresa()%>" /></li>
                    <li><%=datoempresa.ObtenerNombre()%></li>
                </ul>
            </article>
        </a>
        <%
            }
        %>
    </section>
    <%
        if (CodPersona == AbrirSesion) {
    %>
    <section>
        <hgroup>
            Mis Pedidos
        </hgroup>
        <%
            Pedido pedido = new Pedido();
            List<PedidoEntidad> DatosPedido = pedido.MostrarPedidos(AbrirSesion);
            Iterator<PedidoEntidad> pedidoiterator = DatosPedido.iterator();
            PedidoEntidad datopedido = null;

            DetallePedido detallepedido = new DetallePedido();

            while (pedidoiterator.hasNext()) {
                datopedido = pedidoiterator.next();

                List<DetallePedidoEntidad> DatoDetallePedido = detallepedido.MostrarDetallePedidosPerfil(datopedido.ObtenerCodPedido());
                Iterator<DetallePedidoEntidad> detallepedidoiterator = DatoDetallePedido.iterator();
                DetallePedidoEntidad datodetallepedido = null;
        %>
        <details>
            <summary>
                <%= datopedido.ObtenerCodPedido() + " &#8226; " + datopedido.ObtenerNombreEmpresa()%>
            </summary>
            <ul>
                <li>Dirección entrega: <%= datopedido.ObtenerDireccionEntrega()%></li>
                <li>Hora sugerida: <%= datopedido.ObtenerHoraEntrega()%></li>
                <li>Fecha emisión: <%= datopedido.ObtenerFechaEmision()%></li>
                <li>Tipo de Pago: <%= datopedido.ObtenerTipoPago()%></li>
            </ul>
            <div class="BarraDesplHorizontal">
                <table>
                    <tr>
                        <th>Cantidad</th>
                        <th>Producto</th>
                        <th>Precio</th>
                        <th>Subtotal</th>
                    </tr>
                    <%
                        while (detallepedidoiterator.hasNext()) {
                            datodetallepedido = detallepedidoiterator.next();
                    %>
                    <tr>
                        <td><%= datodetallepedido.ObtenerCantidad()%></td>
                        <td><%= datodetallepedido.ObtenerNombreProducto()%></td>
                        <td><%= datodetallepedido.ObtenerPrecio()%></td>
                        <td><%= datodetallepedido.ObtenerSubtotal()%></td>
                    </tr>
                    <%
                        }
                        DetallePedidoEntidad detallepedidoentidad = (DetallePedidoEntidad) detallepedido.TotalDetallePedidoPerfil(datopedido.ObtenerCodPedido());
                    %>
                    <tr>
                        <td></td>
                        <td></td>
                        <td>Total S/</td>
                        <td><%= detallepedidoentidad.ObtenerTotal()%></td>
                    </tr>
                </table>
            </div>
        </details>
        <%
            }
        %>
    </section>
    <%
        }

        if (request.getAttribute("CuadroMensaje") != null) {
            out.write(request.getAttribute("CuadroMensaje").toString());
        }
    %>
</div>