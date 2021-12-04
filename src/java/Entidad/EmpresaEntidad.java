package Entidad;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;

public class EmpresaEntidad {

    int CodEmpresa, CodPersona, Calificacion, CantidadEmpresas;
    String Nombre, Telefono, Distrito, Email, Direccion, Descripcion, Latitud, Longitud;
    Part Imagen;
    InputStream ConvertImagen;

    public int ObtenerCodEmpresa() {
        return CodEmpresa;
    }

    public void ColocarCodEmpresa(int CodEmpresa) {
        this.CodEmpresa = CodEmpresa;
    }

    public String ObtenerNombre() {
        return Nombre;
    }

    public void ColocarNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String ObtenerTelefono() {
        return Telefono;
    }

    public void ColocarTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String ObtenerEmail() {
        return Email;
    }

    public void ColocarEmail(String Email) {
        this.Email = Email;
    }

    public String ObtenerDireccion() {
        return Direccion;
    }

    public void ColocarDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String ObtenerDistrito() {
        return Distrito;
    }

    public void ColocarDistrito(String Distrito) {
        this.Distrito = Distrito;
    }

    public String ObtenerDescripcion() {
        return Descripcion;
    }

    public void ColocarDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String ObtenerLatitud() {
        return Latitud;
    }

    public void ColocarLatitud(String Latitud) {
        this.Latitud = Latitud;
    }

    public String ObtenerLongitud() {
        return Longitud;
    }

    public void ColocarLongitud(String Longitud) {
        this.Longitud = Longitud;
    }

    public int ObtenerCalificacion() {
        return Calificacion;
    }

    public void ColocarCalificacion(int Calificacion) {
        this.Calificacion = Calificacion;
    }

    public int ObtenerCantidadEmpresas() {
        return CantidadEmpresas;
    }

    public void ColocarCantidadEmpresas(int CantidadEmpresas) {
        this.CantidadEmpresas = CantidadEmpresas;
    }

    public InputStream ObtenerImagen() throws IOException {
        if (Imagen != null) {
            ConvertImagen = Imagen.getInputStream();
        }
        return ConvertImagen;
    }

    public void ColocarImagen(Part Imagen) {
        if (Imagen.getSubmittedFileName() != "") {
            this.Imagen = Imagen;
        }
    }

    public int ObtenerCodPersona() {
        return CodPersona;
    }

    public void ColocarCodPersona(int CodPersona) {
        this.CodPersona = CodPersona;
    }
}
