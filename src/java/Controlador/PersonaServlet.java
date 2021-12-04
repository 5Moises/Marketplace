package Controlador;

import Entidad.PersonaEntidad;
import Modelo.Persona;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "PersonaServlet", urlPatterns = {"/PersonaServlet"})
@MultipartConfig(maxFileSize = 16177216)//1.5mb

public class PersonaServlet extends HttpServlet {

    PersonaEntidad personaentidad = new PersonaEntidad();
    Persona persona = new Persona();
    CuadrosDialogo cuadrosdialogo = new CuadrosDialogo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        byte Consulta = Byte.parseByte(request.getParameter("Consulta"));
        int AbrirSesion = 0;
        String IdAcceso = request.getParameter("txt_idacceso");
        String Contrasena = request.getParameter("txt_contrasena");
        String Nombres = request.getParameter("txt_nombres").trim();
        String Apellidos = request.getParameter("txt_apellidos").trim();
        String Email = request.getParameter("txt_email").trim();
        String Telefono = request.getParameter("txt_telefono");
        String Direccion = request.getParameter("txt_direccion");
        if (SesionCodPersona.getAttribute("AbrirSesion") != null) {
            AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        }

        personaentidad.ColocarSesionCodPersona(AbrirSesion);
        personaentidad.ColocarIdAcceso(IdAcceso);
        personaentidad.ColocarContrasena(Contrasena);
        personaentidad.ColocarNombres(Nombres);
        personaentidad.ColocarApellidos(Apellidos);
        personaentidad.ColocarEmail(Email);
        personaentidad.ColocarTelefono(Telefono);
        personaentidad.ColocarDireccion(Direccion);

        try {
            Part Imagen = request.getPart("fil_imagen");
            personaentidad.ColocarImagen(Imagen);
        } catch (Exception Ex) {
        }

        try {
            switch (Consulta) {
                case 1:
                    try {
                        if (!IdAcceso.equals("") && !Contrasena.equals("") && !Nombres.equals("") && !Apellidos.equals("") && !Email.equals("")) {
                            if (persona.RegistrarUsuario(personaentidad) == true) {
                                cuadrosdialogo.CuadroMensaje(request, response, "Registrarse.jsp", "Usuario Registrado", "Éxito");
                            } else {
                                cuadrosdialogo.CuadroMensaje(request, response, "Registrarse.jsp", "Ya existe una Persona con este E-Mail", "Aviso");
                            }
                        } else {
                            cuadrosdialogo.CuadroMensaje(request, response, "Registrarse.jsp", "Complete los espacios vacíos", "Aviso");
                        }
                    } catch (Exception Ex) {
                        cuadrosdialogo.CuadroMensaje(request, response, "Registrarse.jsp", Ex.toString(), "Error");
                    }
                    break;
                case 2:
                    //DialogResult = JOptionPane.showConfirmDialog(SiempreArriba, "¿Desea Modificar su información?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    //if (DialogResult == JOptionPane.YES_OPTION) {
                    if (!Nombres.trim().equals("") && !Apellidos.trim().equals("") && !Email.trim().equals("") && !Telefono.equals("") && !Direccion.equals("")) {
                        if (persona.ModificarPersona(personaentidad) == true) {
                            response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
                        }
                    } else {
                        cuadrosdialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, "Complete los espacios vacíos", "Aviso");
                    }
                    /* } else {
                     response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
                     }*/
                    break;
                case 3:
                    if (persona.EliminarPersona(personaentidad) == true) {
                        response.sendRedirect("Acceder.jsp");
                    }
                    break;
                case 4:
                    if (persona.RenunciarAdministrador(personaentidad) == true) {
                        response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
                    }
                    break;
            }
        } catch (Exception Ex) {
            cuadrosdialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, Ex.toString(), "Error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        int AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        int CodPersona = Integer.parseInt(request.getParameter("CodPersona"));
        Byte Consulta = Byte.parseByte(request.getParameter("Consulta"));

        personaentidad.ColocarCodPersona(CodPersona);

        try {
            switch (Consulta) {
                case 3:
                    if (persona.EliminarPersona(personaentidad) == true) {
                        cuadrosdialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, "Se Eliminó un Usuario", "Éxito");
                    }
                    break;
                case 4:
                    if (persona.ConvertirAdministrador(personaentidad) == true) {
                        cuadrosdialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, "Se agregó a un nuevo Administrador", "Éxito");
                    }
                    break;
            }
        } catch (Exception Ex) {
            cuadrosdialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, Ex.toString(), "Error");
        }
    }
}
