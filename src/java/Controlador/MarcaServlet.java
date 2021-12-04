package Controlador;

import Entidad.MarcaEntidad;
import Modelo.Marca;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MarcaServlet", urlPatterns = {"/MarcaServlet"})

public class MarcaServlet extends HttpServlet {

    MarcaEntidad marcaentidad = new MarcaEntidad();
    Marca marca = new Marca();
    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        int AbrirSesion = 0;
        String Nombre = request.getParameter("txt_nombremarca");
        if (SesionCodPersona.getAttribute("AbrirSesion") != null) {
            AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        }

        marcaentidad.ColocarNombre(Nombre);

        try {
            if (!Nombre.equals("")) {
                if (marca.RegistrarMarca(marcaentidad) == true) {
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
        int CodMarca = Integer.parseInt(request.getParameter("CodMarca"));

        marcaentidad.ColocarCodMarca(CodMarca);

        try {
            if (marca.EliminarMarca(marcaentidad) == true) {
                response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, Ex.toString(), "Error");
        }
    }
}
