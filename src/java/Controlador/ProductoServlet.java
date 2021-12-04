package Controlador;

import Entidad.ProductoEntidad;
import Modelo.Producto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
@MultipartConfig(maxFileSize = 16177216)//1.5mb

public class ProductoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Producto producto = new Producto();
        ProductoEntidad productoentidad = new ProductoEntidad();
        CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

        HttpSession SesionCodProducto = request.getSession();

        int AbrirSesion = Integer.parseInt(SesionCodProducto.getAttribute("AbrirSesion").toString());
        int CodEmpresa = 0;
        int CodProducto = 0;
        byte Cosulta = Byte.parseByte(request.getParameter("Consulta"));
        boolean Disponible = Boolean.parseBoolean(request.getParameter("cbo_disponible"));
        String Nombre = request.getParameter("txt_nombre").trim();
        String Precio = request.getParameter("txt_precio").trim();
        String Presentacion = request.getParameter("cbo_presentacion");
        String Marca = request.getParameter("cbo_marca");
        String Categoria = request.getParameter("cbo_categoria");
        String Descripcion = request.getParameter("txt_descripcion").trim();
        if (request.getParameter("txt_codproducto") != null) {
            CodProducto = Integer.parseInt(request.getParameter("txt_codproducto"));
        }
        if (request.getParameter("txt_codempresa") != null) {
            CodEmpresa = Integer.parseInt(request.getParameter("txt_codempresa"));
        }

        productoentidad.ColocarCodProducto(CodProducto);
        productoentidad.ColocarNombre(Nombre);
        productoentidad.ColocarPrecio(Precio);
        productoentidad.ColocarPresentacion(Presentacion);
        productoentidad.ColocarDisponible(Disponible);
        productoentidad.ColocarMarca(Marca);
        productoentidad.ColocarCategoria(Categoria);
        productoentidad.ColocarDescripcion(Descripcion);
        productoentidad.ColocarCodEmpresa(CodEmpresa);

        try {
            Part Imagen = request.getPart("fil_imagen");
            productoentidad.ColocarImagen(Imagen);
        } catch (Exception e) {
        }

        try {
            switch (Cosulta) {
                case 1:
                    if (!Nombre.equals("") && !Precio.equals("") && !Descripcion.equals("")) {
                        if (!Presentacion.equals("Seleccionar") || !Marca.equals("Seleccionar") || !Categoria.equals("Seleccionar")) {
                            if (producto.RegistrarProducto(productoentidad) == true) {
                                response.sendRedirect("Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Prod");
                            }
                        } else {
                            cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Prod", "Debe especificar una Presentación, Marca y Categoría.", "Aviso");
                        }
                    } else {
                        cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Prod", "Complete los espacios vacíos", "Aviso");
                    }
                    break;
                case 2:
                    if (!Nombre.equals("") && !Precio.equals("") && !Descripcion.equals("")) {
                        if (producto.ModificarProducto(productoentidad) == true) {
                            response.sendRedirect("Producto.jsp?CodProducto=" + CodProducto);
                        }
                    } else {
                        cuadrodialogo.CuadroMensaje(request, response, "Producto.jsp?CodProducto=" + CodProducto, "Complete los espacios vacíos", "Aviso");
                    }
                    break;
                case 3:
                    if (producto.EliminarProducto(productoentidad) == true) {
                        response.sendRedirect("Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Prod");
                    }
                    break;
            }
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Producto.jsp?CodProducto=" + CodProducto, Ex.toString(), "Error");
        }
    }
}
