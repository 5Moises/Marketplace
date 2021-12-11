package Entidad;

public class PresentacionEntidad {

    private int CodPresentacion;
    private String Nombre;

    public int ObtenerCodPresentacion() {
        return CodPresentacion;
    }

    public void ColocarCodPresentacion(int CodPresentacion) {
        this.CodPresentacion = CodPresentacion;
    }

    public String ObtenerNombre() {
        return Nombre;
    }

    public void ColocarNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
