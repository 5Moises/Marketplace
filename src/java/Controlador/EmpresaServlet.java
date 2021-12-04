package Controlador;

import Entidad.EmpresaEntidad;
import Modelo.Empresa;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "EmpresaServlet", urlPatterns = {"/EmpresaServlet"})
@MultipartConfig(maxFileSize = 16177216)//1.5mb

public class EmpresaServlet extends HttpServlet {

    EmpresaEntidad empresaentidad = new EmpresaEntidad();
    Empresa empresa = new Empresa();
    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        int AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        int CodEmpresa = 0;
        byte Consulta = Byte.parseByte(request.getParameter("Consulta"));
        String Nombre = request.getParameter("txt_nombre").trim();
        String Telefono = request.getParameter("txt_telefono").trim();
        String Email = request.getParameter("txt_email").trim();
        String Direccion = request.getParameter("txt_direccion").trim();
        String Distrito = request.getParameter("cbo_distrito").trim();
        String Descripcion = request.getParameter("txt_descripcion").trim();
        String Latitud = request.getParameter("txt_latitud");
        String Longitud = request.getParameter("txt_longitud");
        if (request.getParameter("txt_codempresa") != null) {
            CodEmpresa = Integer.parseInt(request.getParameter("txt_codempresa"));
        }

        empresaentidad.ColocarCodEmpresa(CodEmpresa);
        empresaentidad.ColocarNombre(Nombre);
        empresaentidad.ColocarTelefono(Telefono);
        empresaentidad.ColocarEmail(Email);
        empresaentidad.ColocarDireccion(Direccion);
        empresaentidad.ColocarDistrito(Distrito);
        empresaentidad.ColocarDescripcion(Descripcion);
        empresaentidad.ColocarLatitud(Latitud);
        empresaentidad.ColocarLongitud(Longitud);
        empresaentidad.ColocarCodPersona(AbrirSesion);

        try {
            Part Imagen = request.getPart("fil_imagen");
            empresaentidad.ColocarImagen(Imagen);
        } catch (Exception Ex) {
        }

        try {
            switch (Consulta) {
                case 1:
                    if (!Nombre.equals("") && !Telefono.equals("") && !Email.equals("") && !Direccion.equals("") && !Descripcion.equals("")) {
                        if (!Distrito.equals("Seleccionar")) {
                            if (empresa.RegistrarEmpresa(empresaentidad) == true) {
                                response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
                            }
                        } else {
                            cuadrodialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, "Seleccione un Distrito", "Aviso");
                        }
                    } else {
                        cuadrodialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, "Complete los espacios vacíos", "Aviso");
                    }
                    break;

                case 2:
                    if (!Nombre.equals("") && !Telefono.equals("") && !Email.equals("") && !Direccion.equals("") && !Descripcion.equals("")) {
                        if (empresa.ModificarEmpresa(empresaentidad) == true) {
                            response.sendRedirect("Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Prod");
                        }
                    } else {
                        cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Prod", "Complete los espacios vacíos", "Aviso");
                    }
                    break;
                case 3:
                    if (empresa.EliminarEmpresa(empresaentidad) == true) {
                        response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
                    }
                    break;
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Prod", Ex.toString(), "Error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        int CodEmpresa = Integer.parseInt(request.getParameter("CodEmpresa"));
        int AbrirSesion = 0;
        byte Consulta = Byte.parseByte(request.getParameter("Consulta"));
        byte Calificacion = Byte.parseByte(request.getParameter("Calificacion"));
        if (SesionCodPersona.getAttribute("AbrirSesion") != null) {
            AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        }

        empresaentidad.ColocarCodEmpresa(CodEmpresa);
        empresaentidad.ColocarCalificacion(Calificacion);

        try {
            switch (Consulta) {
                case 3:
                    if (empresa.EliminarEmpresa(empresaentidad) == true) {
                        response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
                    }
                    break;
                case 4:
                    if (empresa.CalificarEmpresa(empresaentidad) == true) {
                        response.sendRedirect("Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Prod");
                    }
                    break;
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, Ex.toString(), "Error");
        }
    }

}
