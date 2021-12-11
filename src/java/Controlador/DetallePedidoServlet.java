package Controlador;

import Entidad.DetallePedidoEntidad;
import Modelo.DetallePedido;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DetallePedidoServlet", urlPatterns = {"/DetallePedidoServlet"})

public class DetallePedidoServlet extends HttpServlet {

    DetallePedidoEntidad detallepedidoentidad = new DetallePedidoEntidad();
    DetallePedido detallepedido = new DetallePedido();
    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        int AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        int CodProducto = Integer.parseInt(request.getParameter("txt_codproducto"));
        byte Consulta = Byte.parseByte(request.getParameter("Consulta"));
        String Cantidad = request.getParameter("txt_cantidad");
        double CantidadNumero = 0;

        if (!Cantidad.equals("")) {
            CantidadNumero = Double.parseDouble(Cantidad);
        }

        detallepedidoentidad.ColocarCodCliente(AbrirSesion);
        detallepedidoentidad.ColocarCodProducto(CodProducto);
        detallepedidoentidad.ColocarCantidad(Cantidad);

        try {
            switch (Consulta) {
                case 1:
                    if (CantidadNumero > 0) {
                        if (detallepedido.RegistrarDetallePedido(detallepedidoentidad) == true) {
                            cuadrodialogo.CuadroMensaje(request, response, "Producto.jsp?CodProducto=" + CodProducto, "Agregado al Carrito", "Éxito");
                        }
                    } else {
                        cuadrodialogo.CuadroMensaje(request, response, "Producto.jsp?CodProducto=" + CodProducto, "Debe agregar al menos un Producto", "Aviso");
                    }
                    break;
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Producto.jsp?CodProducto=" + CodProducto, Ex.toString(), "Error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        byte Consutla = Byte.parseByte(request.getParameter("Consulta"));
        String CodDetalle = request.getParameter("CodDetalle");
        String CodEmpresa = request.getParameter("CodEmpresa");
        String Cantidad = request.getParameter("txt_cantidad");
        double CantidadNumero = 0;

        if (Cantidad != null) {
            CantidadNumero = Double.parseDouble(Cantidad);
        }

        detallepedidoentidad.ColocarCodDetalle(CodDetalle);
        detallepedidoentidad.ColocarCantidad(Cantidad);

        try {
            switch (Consutla) {
                case 1:
                    break;
                case 2:
                    if (CantidadNumero > 0) {
                        if (detallepedido.ModificarDetallePedido(detallepedidoentidad) == true) {
                            response.sendRedirect("Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Carrito");
                        }
                    } else {
                        cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Carrito", "El Producto debe tener al menos una cantidad", "Aviso");
                    }
                    break;
                case 3:
                    if (detallepedido.EliminarDetallePedido(detallepedidoentidad) == true) {
                        response.sendRedirect("Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Carrito");
                    }
                    break;
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Carrito", Ex.toString(), "Error");
        }
    }
}
