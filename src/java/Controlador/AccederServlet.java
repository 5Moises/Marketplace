package Controlador;

import Entidad.AccesoEntidad;
import Modelo.Acceso;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AccederServlet", urlPatterns = {"/AccederServlet"})

public class AccederServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AccesoEntidad accesoentidad = new AccesoEntidad();
        Acceso acceso = new Acceso();
        HttpSession AbrirSesion = request.getSession();
        CuadrosDialogo cuadrosdialogo = new CuadrosDialogo();

        String IdAcceso = request.getParameter("txt_idacceso");
        String Contrasena = request.getParameter("txt_contrasena");

        accesoentidad.ColocarIdAcceso(IdAcceso);
        accesoentidad.ColocarContrasena(Contrasena);

        try {
            if (acceso.Acceder(accesoentidad) > 0) {
                AbrirSesion.setAttribute("AbrirSesion", acceso.Acceder(accesoentidad));
                response.sendRedirect("Perfil.jsp?CodPersona=" + acceso.Acceder(accesoentidad));
            } else {
                cuadrosdialogo.CuadroMensaje(request, response, "Acceder.jsp", "IdAcceso y/o Contraseña incorrecta", "Aviso");
            }
        } catch (Exception Ex) {
            cuadrosdialogo.CuadroMensaje(request, response, "Acceder.jsp", Ex.toString(), "Error");
        }
    }
}
