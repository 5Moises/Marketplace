package Entidad;

public class MarcaEntidad {

    private int CodMarca;
    private String Nombre;

    public int ObtenerCodMarca() {
        return CodMarca;
    }

    public void ColocarCodMarca(int CodMarca) {
        this.CodMarca = CodMarca;
    }

    public String ObtenerNombre() {
        return Nombre;
    }

    public void ColocarNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
