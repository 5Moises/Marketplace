<%@page import="Entidad.DetallePedidoEntidad"%>
<%@page import="Modelo.DetallePedido"%>
<%@page import="Entidad.PedidoEntidad"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Pedido"%>
<%
    int CodEmpresa = Integer.parseInt(request.getParameter("CodEmpresa"));
%>
<section>
    <hgroup>
        Pedidos a la Empresa
    </hgroup>
    <%
        int Numero = 0;
        Pedido pedido = new Pedido();
        List<PedidoEntidad> DatosPedido = pedido.MostrarPedidosEmpresa(CodEmpresa);
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
            <%= datopedido.ObtenerCodPedido() + " &#8226; " + datopedido.ObtenerNombresCliente()%>
        </summary>
        <article class="CarritoCompras">
            <ul>
                <li>Dirección entrega: <%= datopedido.ObtenerDireccionEntrega()%></li>
                <li>Hora sugerida: <%= datopedido.ObtenerHoraEntrega()%></li>
                <li>Fecha emisión: <%= datopedido.ObtenerFechaEmision()%></li>
                <li>Tipo de Pago: <%= datopedido.ObtenerTipoPago()%></li>
            </ul>     
            <div class="BarraDesplHorizontal">
                <table>
                    <tr>
                        <th>Acción</th>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                        <th>Subtotal</th>
                    </tr>
                    <%
                        while (detallepedidoiterator.hasNext()) {
                            datodetallepedido = detallepedidoiterator.next();
                            Numero++;
                    %>
                    <tr>
                        <td>
                            <ul>
                                <li><a href="DetallePedidoServlet?CodDetalle=<%= datodetallepedido.ObtenerCodDetalle()%>&CodEmpresa=<%= CodEmpresa%>&Consulta=3" title="Quitar del Carrito de compras"> </a></li>
                                <li><a href="javascript:;" onclick="frm_modificarcantidad_<%= Numero%>.submit();" title="Modificar Cantidad"> </a></li>
                            </ul>   
                        </td>
                        <td><%= datodetallepedido.ObtenerNombreProducto()%></td>
                        <td>
                            <form action="DetallePedidoServlet" method="GET" id="frm_modificarcantidad_<%= Numero%>">
                                <input type="number" name="txt_cantidad" value="<%= datodetallepedido.ObtenerCantidad()%>"/>
                                <input type="hidden" name="CodDetalle" value="<%= datodetallepedido.ObtenerCodDetalle()%>"/>
                                <input type="hidden" name="CodEmpresa" value="<%= CodEmpresa%>"/>
                                <input type="hidden" name="Consulta" value="2"/>
                            </form>
                        </td>                    
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
                        <td></td>
                        <td>Total S/</td>
                        <td><%= detallepedidoentidad.ObtenerTotal()%></td>
                    </tr>
                </table>
            </div>

            <form action="BoletaCorreoServlet" method="POST">
                <ul>
                    <li>Fecha y hora de entrega confirmada:</li>
                    <li><input type="datetime-local" name="dtp_fechahora"/></li>
                    <li>Nombre de Repartidor:</li>
                    <li><input type="text" name="txt_nombrerepartidor"/></li>            
                    <li><input type="submit" name="btn_enviar" value="Enviar Comprobante por correo"/></li>
                </ul>
                <input type="hidden" name="txt_codpedido" value="<%= datopedido.ObtenerCodPedido()%>"/>
                <input type="hidden" name="txt_codempresa" value="<%= CodEmpresa%>"/>
            </form>  
        </article>
    </details>
    <%
        }
    %>
</section>