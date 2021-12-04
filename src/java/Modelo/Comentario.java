package Modelo;

import Configuracion.Conexion;
import Entidad.ComentarioEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Comentario {

    Connection conexion = Conexion.getConnection();
    ComentarioEntidad comentarioentidad = new ComentarioEntidad();

    int ResultadoConsulta;
    boolean ExitoConsulta = false;
    ResultSet rs;

    public List MostrarComentarios(int CodEmpresa) {
        ArrayList<ComentarioEntidad> DatosComentario = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT CodCliente, (SELECT Concat_ws(' ', Nombres, Apellidos) FROM Persona WHERE CodPersona = Comentario.CodCliente), FechaEmision, Comentario, Calificacion FROM Comentario WHERE CodEmpresa = ? ");
            ps.setInt(1, CodEmpresa);
            rs = ps.executeQuery();
            while (rs.next()) {
                ComentarioEntidad comentarioentidadlista = new ComentarioEntidad();
                comentarioentidadlista.ColocarCodCliente(rs.getInt(1));
                comentarioentidadlista.ColocarNombreApellidoCliente(rs.getString(2));
                comentarioentidadlista.ColocarFechaEmision(rs.getString(3));
                comentarioentidadlista.ColocarComentario(rs.getString(4));
                comentarioentidadlista.ColocarCalificacion(rs.getByte(5));
                DatosComentario.add(comentarioentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosComentario;
    }

    public boolean RegistarComentario(ComentarioEntidad DatosComentario) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO Comentario (CodCliente, Comentario, FechaEmision, Calificacion, CodEmpresa) VALUES (?,?,CurDate(),?,?)");
        ps.setInt(1, DatosComentario.ObtenerCodCliente());
        ps.setString(2, DatosComentario.ObtenerComentario());
        ps.setByte(3, DatosComentario.ObtenerCalificacion());
        ps.setInt(4, DatosComentario.ObtenerCodEmpresa());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public ComentarioEntidad PromedioCalificacion(int CodEmpresa) {
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT AVG(Calificacion) FROM Comentario WHERE CodEmpresa = ? ");
            ps.setInt(1, CodEmpresa);
            rs = ps.executeQuery();
            if (rs.next()) {
                comentarioentidad.ColocarPromedioCalificacion(rs.getByte(1));
            }
        } catch (Exception Ex) {
        }
        return comentarioentidad;
    }
}
