<%@page import="Entidad.PersonaEntidad"%>
<%@page import="Modelo.Persona"%>
<%@page import="Entidad.DetallePedidoEntidad"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.DetallePedido"%>
<%
    int CodEmpresa = Integer.parseInt(request.getParameter("CodEmpresa"));
    int AbrirSesion = 0;
    if (session.getAttribute("AbrirSesion") != null) {
        AbrirSesion = Integer.parseInt(session.getAttribute("AbrirSesion").toString());
    }
    Persona persona = new Persona();
    PersonaEntidad personaentidad = (PersonaEntidad) persona.MostrarPersona(AbrirSesion);
%>
<section>
    <hgroup>
        Carrito de Compras
    </hgroup>
    <%
        if (request.getAttribute("CuadroMensaje") != null) {
            out.write(request.getAttribute("CuadroMensaje").toString());
        }
    %>
    <div class="BarraDesplHorizontal">
        <table>
            <tr>
                <th>Acción</th>
                <th>Cantidad</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Subtotal</th>
            </tr>
            <%
                int Numero = 0;
                DetallePedido detallepedido = new DetallePedido();
                List<DetallePedidoEntidad> DatosDetallePedido = detallepedido.MostrarDetallePedidos(CodEmpresa, AbrirSesion);
                Iterator<DetallePedidoEntidad> detallepedidoiterator = DatosDetallePedido.iterator();
                DetallePedidoEntidad datodetallepedido = null;
                while (detallepedidoiterator.hasNext()) {
                    datodetallepedido = detallepedidoiterator.next();
                    Numero++;
            %>
            <tr>
                <td>
                    <ul>
                        <li><a href="DetallePedidoServlet?CodDetalle=<%= datodetallepedido.ObtenerCodDetalle()%>&CodEmpresa=<%= CodEmpresa%>&Consulta=3" title="Quitar del Carrito de compras"> </a></li>
                        <li><a href="javascript:;" onclick="frm_modificarcantidad<%= Numero%>.submit();" title="Modificar Cantidad"> </a></li>
                    </ul>   
                </td>
                <td>
                    <form action="DetallePedidoServlet" method="GET" id="frm_modificarcantidad<%= Numero%>">
                        <input type="number" name="txt_cantidad" value="<%= datodetallepedido.ObtenerCantidad()%>"/>
                        <input type="hidden" name="CodDetalle" value="<%= datodetallepedido.ObtenerCodDetalle()%>"/>
                        <input type="hidden" name="CodEmpresa" value="<%= CodEmpresa%>"/>
                        <input type="hidden" name="Consulta" value="2"/>
                    </form>
                </td>
                <td><%= datodetallepedido.ObtenerNombreProducto()%></td>
                <td><%= datodetallepedido.ObtenerPrecio()%></td>
                <td><%= datodetallepedido.ObtenerSubtotal()%></td>
            </tr>
            <%
                }
                DetallePedidoEntidad detallepedidoentidad = (DetallePedidoEntidad) detallepedido.TotalDetallePedido(CodEmpresa, AbrirSesion);
            %>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td>Total S/</td>
                <td><%= detallepedidoentidad.ObtenerTotal()%></td>
            </tr>
        </table>
    </div>
    <form action="PedidoServlet" method="POST">
        <ul>
            <li><input type="text" name="txt_direccion" value="<%= personaentidad.ObtenerDireccion()%>"/></li>
            <li><input type="datetime-local" name="dtp_fechahora"/></li> 
            <li><select name="cbo_tipopago">
                    <option value="Seleccionar" selected="selected">Tipo de pago</option>
                    <jsp:include page="MostrarTiposPago.jsp"/>
                </select></li>
            <li><input type="submit" name="btn_registrar" value="Registrar Pedido"/></li>
        </ul>
        <input type="hidden" name="Consulta" value="1"/>
        <input type="hidden" name="txt_codempresa" value="<%= CodEmpresa%>"/>
    </form>
</section>