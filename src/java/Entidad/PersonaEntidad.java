package Entidad;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;

public class PersonaEntidad {

    private int CodPersona, SesionCodPersona, CantidadPersonas;
    private byte Tipo;
    private String IdAcceso, Contrasena, Nombres, Apellidos, Email, Telefono, Direccion;
    private Part Imagen;
    private InputStream ConvertImagen;

    public int ObtenerSesionCodPersona() {
        return SesionCodPersona;
    }

    public void ColocarSesionCodPersona(int SesionCodPersona) {
        this.SesionCodPersona = SesionCodPersona;
    }

    public int ObtenerCodPersona() {
        return CodPersona;
    }

    public void ColocarCodPersona(int CodPersona) {
        this.CodPersona = CodPersona;
    }

    public String ObtenerIdAcceso() {
        return IdAcceso;
    }

    public void ColocarIdAcceso(String IdAcceso) {
        this.IdAcceso = IdAcceso;
    }

    public String ObtenerContrasena() {
        return Contrasena;
    }

    public void ColocarContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String ObtenerNombres() {
        return Nombres;
    }

    public void ColocarNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String ObtenerApellidos() {
        return Apellidos;
    }

    public void ColocarApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String ObtenerEmail() {
        return Email;
    }

    public void ColocarEmail(String Email) {
        this.Email = Email;
    }

    public String ObtenerTelefono() {
        return Telefono;
    }

    public void ColocarTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String ObtenerDireccion() {
        return Direccion;
    }

    public void ColocarDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public byte ObtenerTipo() {
        return Tipo;
    }

    public void ColocarTipo(byte Tipo) {
        this.Tipo = Tipo;
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

    public int ObtenerCantidadPersonas() {
        return CantidadPersonas;
    }

    public void ColocarCantidadPersonas(int CantidadPersonas) {
        this.CantidadPersonas = CantidadPersonas;
    }
}
