package Controlador;

import Entidad.CategoriaEntidad;
import Modelo.Categoria;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet"})

public class CategoriaServlet extends HttpServlet {

    Categoria categoria = new Categoria();
    CategoriaEntidad categoriaentidad = new CategoriaEntidad();
    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        int AbrirSesion = 0;
        String Nombre = request.getParameter("txt_nombrecategoria").trim();
        if (SesionCodPersona.getAttribute("AbrirSesion") != null) {
            AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        }

        categoriaentidad.ColocarNombre(Nombre);

        try {
            if (!Nombre.equals("")) {
                if (categoria.RegistrarCategoria(categoriaentidad) == true) {
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
        int CodCategoria = Integer.parseInt(request.getParameter("CodCategoria"));

        categoriaentidad.ColocarCodCategoria(CodCategoria);

        try {
            if (categoria.EliminarCategoria(categoriaentidad) == true) {
                response.sendRedirect("Perfil.jsp?CodPersona=" + AbrirSesion);
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Perfil.jsp?CodPersona=" + AbrirSesion, Ex.toString(), "Error");
        }
    }
}
