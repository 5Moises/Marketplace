package Controlador;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CuadrosDialogo {

    public void CuadroMensaje(HttpServletRequest request, HttpServletResponse response, String Pagina, String Mensaje, String Encabezado) throws ServletException, IOException {
        request.setAttribute("CuadroMensaje",
                "<div id=\"FondoCuadroDialogo\" onclick=\"CerrarCuadroDialogo();\"></div>"
                + "<div id=\"CuadroDialogo\">"
                + "<ul>"
                + "<li>" + Encabezado + "</li>"
                + "<li>" + Mensaje + "</li>"
                + "</ul>"
                + "</div>"
        );
        RequestDispatcher EnviarMensaje = request.getRequestDispatcher(Pagina);
        EnviarMensaje.forward(request, response);
    }
}
