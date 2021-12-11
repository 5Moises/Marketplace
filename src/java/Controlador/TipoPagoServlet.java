package Controlador;

import Entidad.TipoPagoEntidad;
import Modelo.TipoPago;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TipoPagoServlet", urlPatterns = {"/TipoPagoServlet"})

public class TipoPagoServlet extends HttpServlet {

    TipoPagoEntidad tipopagoentidad = new TipoPagoEntidad();
    TipoPago tipopago = new TipoPago();
    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        int AbrirSesion = 0;
        String Nombre = request.getParameter("txt_nombretipopago");
        if (SesionCodPersona.getAttribute("AbrirSesion") != null) {
            AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        }

        tipopagoentidad.ColocarNombre(Nombre);

        try {
            if (!Nombre.equals("")) {
                if (tipopago.RegistrarTipoPago(tipopagoentidad) == true) {
                    response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
                }
            } else {
                cuadrodialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, "Escriba un Nombre", "Aviso");
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, Ex.toString(), "Error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        int AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        int CodTipoPago = Integer.parseInt(request.getParameter("CodTipoPago"));

        tipopagoentidad.ColocarCodTipoPago(CodTipoPago);

        try {
            if (tipopago.EliminarTipoPago(tipopagoentidad) == true) {
                response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, Ex.toString(), "Error");
        }
    }
}
