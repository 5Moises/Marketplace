
package Entidad;

public class CategoriaEntidad {
    int CodCategoria;
    String Nombre;

    public int ObtenerCodCategoria() {
        return CodCategoria;
    }

    public void ColocarCodCategoria(int CodCategoria) {
        this.CodCategoria = CodCategoria;
    }

    public String ObtenerNombre() {
        return Nombre;
    }

    public void ColocarNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
