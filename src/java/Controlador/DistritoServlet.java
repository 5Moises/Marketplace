package Controlador;

import Entidad.DistritoEntidad;
import Modelo.Distrito;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DistritoServlet", urlPatterns = {"/DistritoServlet"})

public class DistritoServlet extends HttpServlet {

    DistritoEntidad distritoentidad = new DistritoEntidad();
    Distrito distrito = new Distrito();
    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        int AbrirSesion = 0;
        String Nombre = request.getParameter("txt_nombredistrito").trim();
        if (SesionCodPersona.getAttribute("AbrirSesion") != null) {
            AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        }

        distritoentidad.ColocarNombre(Nombre);

        try {
            if (!Nombre.equals("")) {
                if (distrito.RegistrarDistrito(distritoentidad) == true) {
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
        int CodDistrito = Integer.parseInt(request.getParameter("CodDistrito"));

        distritoentidad.ColocarCodDistrito(CodDistrito);

        try {
            if (distrito.EliminarDistrito(distritoentidad) == true) {
                response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, Ex.toString(), "Error");
        }
    }
}
