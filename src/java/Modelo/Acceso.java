package Modelo;

import Configuracion.Conexion;
import Entidad.AccesoEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Acceso {

    Connection conexion = Conexion.getConnection();
    AccesoEntidad accesoentidad = new AccesoEntidad();

    private ResultSet rs;

    public int Acceder(AccesoEntidad DatosPersona) throws SQLException {
        int CodPersona = 0;
        PreparedStatement ps = conexion.prepareStatement("SELECT Count(*), CodPersona FROM Usuario INNER JOIN Persona ON Usuario.IdAcceso = Persona.IdAcceso WHERE Persona.IdAcceso = ? AND Usuario.Contrasena = ? ");
        ps.setString(1, DatosPersona.ObtenerIdAcceso());
        ps.setString(2, DatosPersona.ObtenerContrasena());
        rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getByte(1) > 0) {
                CodPersona = rs.getInt(2);
            }
        }
        return CodPersona;
    }
}
