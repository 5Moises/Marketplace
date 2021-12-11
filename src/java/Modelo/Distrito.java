package Modelo;

import Configuracion.Conexion;
import Entidad.DistritoEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Distrito {

    Connection conexion = Conexion.getConnection();
    DistritoEntidad distritoentidad = new DistritoEntidad();

    private int ResultadoConsulta;
    private boolean ExitoConsulta;
    ResultSet rs;

    public List MostrarDistritos() {
        ArrayList<DistritoEntidad> DatosDistrito = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM Distrito");
            rs = ps.executeQuery();
            while (rs.next()) {
                DistritoEntidad dist = new DistritoEntidad();
                dist.ColocarCodDistrito(rs.getInt(1));
                dist.ColocarNombre(rs.getString(2));
                DatosDistrito.add(dist);
            }
        } catch (Exception Ex) {
        }
        return DatosDistrito;
    }

    public boolean RegistrarDistrito(DistritoEntidad DatosDistrito) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO Distrito (Nombre) VALUES (?)");
        ps.setString(1, DatosDistrito.ObtenerNombre());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean EliminarDistrito(DistritoEntidad DatosDistrito) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("DELETE FROM Distrito WHERE CodDistrito = ? ");
        ps.setInt(1, DatosDistrito.ObtenerCodDistrito());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }
}
