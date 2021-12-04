package Entidad;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;

public class ProductoEntidad {

    int CodProducto, CodEmpresa, CodPersona;
    String Nombre, Precio, Descripcion, Presentacion, Marca, Categoria;
    boolean Disponible;
    Part Imagen;
    InputStream ConverImagen;

    public void ColocarCodProducto(int CodProducto) {
        this.CodProducto = CodProducto;
    }

    public int ObtenerCodProducto() {
        return CodProducto;
    }

    public String ObtenerNombre() {
        return Nombre;
    }

    public void ColocarNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String ObtenerPrecio() {
        return Precio;
    }

    public void ColocarPrecio(String Precio) {
        this.Precio = Precio;
    }

    public String ObtenerDescripcion() {
        return Descripcion;
    }

    public void ColocarDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public boolean ObtenerDisponible() {
        return Disponible;
    }

    public void ColocarDisponible(boolean Disponible) {
        this.Disponible = Disponible;
    }

    public String ObtenerPresentacion() {
        return Presentacion;
    }

    public void ColocarPresentacion(String Presentacion) {
        this.Presentacion = Presentacion;
    }

    public String ObtenerMarca() {
        return Marca;
    }

    public void ColocarMarca(String Marca) {
        this.Marca = Marca;
    }

    public String ObtenerCategoria() {
        return Categoria;
    }

    public void ColocarCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public InputStream ObtenerImagen() throws IOException {
        if (Imagen != null) {
            ConverImagen = Imagen.getInputStream();
        }
        return ConverImagen;
    }

    public void ColocarImagen(Part Imagen) {
        if (Imagen.getSubmittedFileName() != "") {
            this.Imagen = Imagen;
        }
    }

    public int ObtenerCodEmpresa() {
        return CodEmpresa;
    }

    public void ColocarCodEmpresa(int CodEmpresa) {
        this.CodEmpresa = CodEmpresa;
    }

    public int ObtenerCodPersona() {
        return CodPersona;
    }

    public void ColocarCodPersona(int CodPersona) {
        this.CodPersona = CodPersona;
    }
}
