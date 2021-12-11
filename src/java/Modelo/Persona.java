package Modelo;

import Configuracion.Conexion;
import Entidad.PersonaEntidad;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Persona {

    Connection conexion = Conexion.getConnection();
    PersonaEntidad personaentidad = new PersonaEntidad();

    private byte TipoUsuario;
    private int ResultadoConsulta;
    private boolean ExitoConsulta = false;
    ResultSet rs;

    public List MostrarPersonas(byte TipoPersona) {
        ArrayList<PersonaEntidad> DatosPersona = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT CodPersona, Nombres, Apellidos FROM Persona WHERE Tipo = ? ");
            ps.setInt(1, TipoPersona);
            rs = ps.executeQuery();
            while (rs.next()) {
                PersonaEntidad personaentidadlista = new PersonaEntidad();
                personaentidadlista.ColocarCodPersona(rs.getInt(1));
                personaentidadlista.ColocarNombres(rs.getString(2));
                personaentidadlista.ColocarApellidos(rs.getString(3));
                DatosPersona.add(personaentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosPersona;
    }

    public PersonaEntidad MostrarPersona(int CodPersona) {
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT CodPersona, Nombres, Apellidos, Email, Telefono, Direccion, Tipo FROM Persona WHERE CodPersona = ? ");
            ps.setInt(1, CodPersona);
            rs = ps.executeQuery();
            if (rs.next()) {
                personaentidad.ColocarCodPersona(rs.getInt(1));
                personaentidad.ColocarNombres(rs.getString(2));
                personaentidad.ColocarApellidos(rs.getString(3));
                personaentidad.ColocarEmail(rs.getString(4));
                personaentidad.ColocarTelefono(rs.getString(5));
                personaentidad.ColocarDireccion(rs.getString(6));
                personaentidad.ColocarTipo(rs.getByte(7));
            }
        } catch (Exception Ex) {
        }
        return personaentidad;
    }

    public PersonaEntidad ContarPersonas() {
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT Count(*) FROM Persona WHERE Tipo = 0 ");
            rs = ps.executeQuery();
            if (rs.next()) {
                personaentidad.ColocarCantidadPersonas(rs.getInt(1));
            }
        } catch (Exception Ex) {
        }
        return personaentidad;
    }

    public boolean RegistrarUsuario(PersonaEntidad DatosPersona) throws SQLException, IOException {
        PreparedStatement ps = conexion.prepareStatement("SELECT Count(*) FROM Usuario");
        rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getInt(1) == 0) {
                TipoUsuario = 1;
            } else {
                TipoUsuario = 0;
            }
        }

        PreparedStatement ps2 = conexion.prepareStatement("SELECT Count(*) FROM Persona WHERE Email = ? ");
        ps2.setString(1, DatosPersona.ObtenerEmail());
        rs = ps2.executeQuery();
        if (rs.next()) {
            if (rs.getInt(1) == 0) {
                PreparedStatement ps3 = conexion.prepareStatement("INSERT INTO Usuario(IdAcceso, Contrasena) VALUES(?,?)");
                ps3.setString(1, DatosPersona.ObtenerIdAcceso());
                ps3.setString(2, DatosPersona.ObtenerContrasena());
                ps3.executeUpdate();

                PreparedStatement ps4 = conexion.prepareStatement("INSERT INTO Persona(Nombres, Apellidos, Email, Telefono, Direccion, Tipo, Imagen, IdAcceso) VALUES (?,?,?,000,'',?,?,?)");
                ps4.setString(1, DatosPersona.ObtenerNombres());
                ps4.setString(2, DatosPersona.ObtenerApellidos());
                ps4.setString(3, DatosPersona.ObtenerEmail());
                ps4.setByte(4, TipoUsuario);
                ps4.setBlob(5, DatosPersona.ObtenerImagen());
                ps4.setString(6, DatosPersona.ObtenerIdAcceso());
                ResultadoConsulta = ps4.executeUpdate();
                if (ResultadoConsulta > 0) {
                    ExitoConsulta = true;
                }
            } else {
                ExitoConsulta = false;
            }
        }
        return ExitoConsulta;
    }

    public boolean ModificarPersona(PersonaEntidad DatosPersona) throws SQLException, IOException {
        if (DatosPersona.ObtenerImagen() != null) {
            PreparedStatement ps = conexion.prepareStatement("UPDATE Persona SET Nombres = ? ,Apellidos = ? ,Email = ? ,Telefono = ? ,Direccion = ? ,Imagen = ? WHERE CodPersona = ? ");
            ps.setString(1, DatosPersona.ObtenerNombres());
            ps.setString(2, DatosPersona.ObtenerApellidos());
            ps.setString(3, DatosPersona.ObtenerEmail());
            ps.setString(4, DatosPersona.ObtenerTelefono());
            ps.setString(5, DatosPersona.ObtenerDireccion());
            ps.setBlob(6, DatosPersona.ObtenerImagen());
            ps.setInt(7, DatosPersona.ObtenerSesionCodPersona());
            ResultadoConsulta = ps.executeUpdate();
        } else {
            PreparedStatement ps = conexion.prepareStatement("UPDATE Persona SET Nombres = ? ,Apellidos = ? ,Email = ? ,Telefono = ? ,Direccion = ? WHERE CodPersona = ? ");
            ps.setString(1, DatosPersona.ObtenerNombres());
            ps.setString(2, DatosPersona.ObtenerApellidos());
            ps.setString(3, DatosPersona.ObtenerEmail());
            ps.setString(4, DatosPersona.ObtenerTelefono());
            ps.setString(5, DatosPersona.ObtenerDireccion());
            ps.setInt(6, DatosPersona.ObtenerSesionCodPersona());
            ResultadoConsulta = ps.executeUpdate();
        }
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean EliminarPersona(PersonaEntidad DatosPersona) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("DELETE FROM Usuario WHERE IdAcceso = (SELECT IdAcceso FROM Persona WHERE CodPersona = ? ) ");
        ps.setInt(1, DatosPersona.ObtenerCodPersona());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean ConvertirAdministrador(PersonaEntidad DatosPersona) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("UPDATE Persona SET Tipo = 1 WHERE CodPersona = ? ");
        ps.setInt(1, DatosPersona.ObtenerCodPersona());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean RenunciarAdministrador(PersonaEntidad DatosPersona) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("UPDATE Persona SET Tipo = 0 WHERE CodPersona = ? ");
        ps.setInt(1, DatosPersona.ObtenerSesionCodPersona());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }
}
