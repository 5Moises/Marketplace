package Controlador;

import Entidad.PedidoEntidad;
import Modelo.DetallePedido;
import Modelo.Pedido;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PedidoServlet", urlPatterns = {"/PedidoServlet"})

public class PedidoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Pedido pedido = new Pedido();
        PedidoEntidad pedidoentidad = new PedidoEntidad();
        CuadrosDialogo cuadrodialogo = new CuadrosDialogo();
        HttpSession SesionCodPersona = request.getSession();
        DetallePedido detallepedido = new DetallePedido();

        int AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        int CodEmpresa = Integer.parseInt(request.getParameter("txt_codempresa"));
        byte Consulta = Byte.parseByte(request.getParameter("Consulta"));
        String DireccionEntrega = request.getParameter("txt_direccion").trim();
        String HoraEntrega = request.getParameter("dtp_fechahora");
        String TipoPago = request.getParameter("cbo_tipopago");

        pedidoentidad.ColocarCodCliente(AbrirSesion);
        pedidoentidad.ColocarCodEmpresa(CodEmpresa);
        pedidoentidad.ColocarDireccionEntrega(DireccionEntrega);
        pedidoentidad.ColocarHoraEntrega(HoraEntrega);
        pedidoentidad.ColocarTipoPago(TipoPago);

        try {
            switch (Consulta) {
                case 1:
                    if (detallepedido.CantidadProductosCarrito(CodEmpresa, AbrirSesion) > 0) {
                        if (!DireccionEntrega.equals("")) {
                            if (pedido.RegistrarPedido(pedidoentidad) == true) {
                                cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Prod", "Pedido Registrado", "Éxito");
                            }
                        } else {
                            cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Carrito", "Especifique la dirección", "Aviso");
                        }
                    } else {
                        cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Carrito", "El Carrito está vacío", "Aviso");
                    }
                    break;
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Prod", Ex.toString(), "Error");
        }
    }
}
