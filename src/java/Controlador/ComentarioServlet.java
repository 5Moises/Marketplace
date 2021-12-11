package Controlador;

import Entidad.ComentarioEntidad;
import Modelo.Comentario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ComentarioServlet", urlPatterns = {"/ComentarioServlet"})

public class ComentarioServlet extends HttpServlet {

    Comentario comentario = new Comentario();
    ComentarioEntidad comentarioentidad = new ComentarioEntidad();
    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession SesionCodPersona = request.getSession();

        byte Consulta = Byte.parseByte(request.getParameter("Consulta"));
        byte Calificacion = 0;
        int AbrirSesion = Integer.parseInt(SesionCodPersona.getAttribute("AbrirSesion").toString());
        int CodEmpresa = Integer.parseInt(request.getParameter("txt_codempresa"));
        String Comentario = request.getParameter("txt_comentario").trim();

        if (request.getParameter("rdo_calificar") != null) {
            Calificacion = Byte.parseByte(request.getParameter("rdo_calificar"));
        }

        comentarioentidad.ColocarCodCliente(AbrirSesion);
        comentarioentidad.ColocarComentario(Comentario);
        comentarioentidad.ColocarCalificacion(Calificacion);
        comentarioentidad.ColocarCodEmpresa(CodEmpresa);

        try {
            switch (Consulta) {
                case 1:
                    if (!Comentario.equals("")) {
                        if (comentario.RegistarComentario(comentarioentidad) == true) {
                            response.sendRedirect("Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Coment");
                        }
                    } else {
                        cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Coment", "Debe escribir un comentario", "Aviso");
                    }
                    break;
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Coment", Ex.toString(), "Error");
        }
    }
}
