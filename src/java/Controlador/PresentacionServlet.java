package Controlador;

import Entidad.PresentacionEntidad;
import Modelo.Presentacion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PresentacionServlet", urlPatterns = {"/PresentacionServlet"})

public class PresentacionServlet extends HttpServlet {

    PresentacionEntidad presentacionentidad = new PresentacionEntidad();
    Presentacion presentacion = new Presentacion();
    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        int AbrirSesion = 0;
        String Nombre = request.getParameter("txt_nombrepresentacion");
        if (SesionCodPersona.getAttribute("AbrirSesion") != null) {
            AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        }

        presentacionentidad.ColocarNombre(Nombre);

        try {
            if (!Nombre.equals("")) {
                if (presentacion.RegistrarPresentacion(presentacionentidad) == true) {
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
        int CodPresentacion = Integer.parseInt(request.getParameter("CodPresentacion"));

        presentacionentidad.ColocarCodPresentacion(CodPresentacion);

        try {
            if (presentacion.EliminarPresentacion(presentacionentidad) == true) {
                response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, Ex.toString(), "Error");
        }
    }
}
