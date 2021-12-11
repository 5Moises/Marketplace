package Entidad;

public class ComentarioEntidad {

    private int CodCliente, CodEmpresa;
    private byte Calificacion, PromedioCalificacion;
    private String Comentario, FechaEmision, NombreApellidoCliente;

    public int ObtenerCodCliente() {
        return CodCliente;
    }

    public void ColocarCodCliente(int CodCliente) {
        this.CodCliente = CodCliente;
    }

    public String ObtenerNombreApellidoCliente() {
        return NombreApellidoCliente;
    }

    public void ColocarNombreApellidoCliente(String NombreApellidoCliente) {
        this.NombreApellidoCliente = NombreApellidoCliente;
    }

    public String ObtenerComentario() {
        return Comentario;
    }

    public void ColocarComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public String ObtenerFechaEmision() {
        return FechaEmision;
    }

    public void ColocarFechaEmision(String FechaEmision) {
        this.FechaEmision = FechaEmision;
    }

    public byte ObtenerCalificacion() {
        return Calificacion;
    }

    public void ColocarCalificacion(byte Calificacion) {
        this.Calificacion = Calificacion;
    }

    public byte ObtenerPromedioCalificacion() {
        return PromedioCalificacion;
    }

    public void ColocarPromedioCalificacion(byte PromedioCalificacion) {
        this.PromedioCalificacion = PromedioCalificacion;
    }

    public int ObtenerCodEmpresa() {
        return CodEmpresa;
    }

    public void ColocarCodEmpresa(int CodEmpresa) {
        this.CodEmpresa = CodEmpresa;
    }
}
