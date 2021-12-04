package Modelo;

import Configuracion.Conexion;
import Entidad.EmpresaEntidad;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Empresa {

    Connection conexion = Conexion.getConnection();
    EmpresaEntidad empresaentidad = new EmpresaEntidad();

    int ResultadoConsulta;
    boolean ExitoConsulta = false;
    ResultSet rs;

    public List MostrarEmpresas(int CodPersona) {
        ArrayList<EmpresaEntidad> DatosEmpresa = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT CodEmpresa, Nombre FROM Empresa WHERE CodPersona = ? ");
            ps.setInt(1, CodPersona);
            rs = ps.executeQuery();
            while (rs.next()) {
                EmpresaEntidad empresaentidadlista = new EmpresaEntidad();
                empresaentidadlista.ColocarCodEmpresa(rs.getInt(1));
                empresaentidadlista.ColocarNombre(rs.getString(2));
                DatosEmpresa.add(empresaentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosEmpresa;
    }

    public List MostrarEmpresas() {
        ArrayList<EmpresaEntidad> DatosEmpresa = new ArrayList<>();
        try {
            PreparedStatement ps2 = conexion.prepareStatement("SELECT CodEmpresa, Nombre FROM Empresa");
            rs = ps2.executeQuery();
            while (rs.next()) {
                EmpresaEntidad empresaentidadlista = new EmpresaEntidad();
                empresaentidadlista.ColocarCodEmpresa(rs.getInt(1));
                empresaentidadlista.ColocarNombre(rs.getString(2));
                DatosEmpresa.add(empresaentidadlista);
            }

        } catch (Exception Ex) {
        }
        return DatosEmpresa;
    }

    public List BuscarEmpresas(String Nombre) {
        ArrayList<EmpresaEntidad> DatosEmpresa = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT CodEmpresa, Nombre, Latitud, Longitud FROM Empresa WHERE CodDistrito = (SELECT CodDistrito FROM Distrito WHERE Nombre = ? ) ");
            ps.setString(1, Nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                EmpresaEntidad empresaentidadlista = new EmpresaEntidad();
                empresaentidadlista.ColocarCodEmpresa(rs.getInt(1));
                empresaentidadlista.ColocarNombre(rs.getString(2));
                empresaentidadlista.ColocarLatitud(rs.getString(3));
                empresaentidadlista.ColocarLongitud(rs.getString(4));
                DatosEmpresa.add(empresaentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosEmpresa;
    }

    public EmpresaEntidad ContarEmpresas(String NombreDistrito) {
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT Count(*) FROM Empresa WHERE CodDistrito = (SELECT CodDistrito FROM Distrito WHERE Nombre = ? ) ");
            ps.setString(1, NombreDistrito);
            rs = ps.executeQuery();
            if (rs.next()) {
                empresaentidad.ColocarCantidadEmpresas(rs.getInt(1));
                empresaentidad.ColocarDistrito(NombreDistrito);
            }
        } catch (Exception Ex) {
        }
        return empresaentidad;
    }

    public EmpresaEntidad MostrarEmpresa(int CodEmpresa) {
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT CodEmpresa, Nombre, Telefono, Email, Direccion, (SELECT Nombre FROM Distrito WHERE CodDistrito = Empresa.CodDistrito), Descripcion, Latitud, Longitud, Calificacion, CodPersona FROM Empresa WHERE CodEmpresa = ? ");
            ps.setInt(1, CodEmpresa);
            rs = ps.executeQuery();
            if (rs.next()) {
                empresaentidad.ColocarCodEmpresa(rs.getInt(1));
                empresaentidad.ColocarNombre(rs.getString(2));
                empresaentidad.ColocarTelefono(rs.getString(3));
                empresaentidad.ColocarEmail(rs.getString(4));
                empresaentidad.ColocarDireccion(rs.getString(5));
                empresaentidad.ColocarDistrito(rs.getString(6));
                empresaentidad.ColocarDescripcion(rs.getString(7));
                empresaentidad.ColocarLatitud(rs.getString(8));
                empresaentidad.ColocarLongitud(rs.getString(9));
                empresaentidad.ColocarCalificacion(rs.getInt(10));
                empresaentidad.ColocarCodPersona(rs.getInt(11));
            }
        } catch (Exception Ex) {
        }
        return empresaentidad;
    }

    public boolean RegistrarEmpresa(EmpresaEntidad DatosEmpresa) throws SQLException, IOException {
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO Empresa (Nombre, Telefono, Email, Direccion, CodDistrito, Descripcion, Latitud, Longitud, Calificacion, Imagen, CodPersona) VALUES (?,?,?,?, (SELECT CodDistrito FROM Distrito WHERE Nombre = ?) ,?,?,?,0,?,?)");
        ps.setString(1, DatosEmpresa.ObtenerNombre());
        ps.setString(2, DatosEmpresa.ObtenerTelefono());
        ps.setString(3, DatosEmpresa.ObtenerEmail());
        ps.setString(4, DatosEmpresa.ObtenerDireccion());
        ps.setString(5, DatosEmpresa.ObtenerDistrito());
        ps.setString(6, DatosEmpresa.ObtenerDescripcion());
        ps.setString(7, DatosEmpresa.ObtenerLatitud());
        ps.setString(8, DatosEmpresa.ObtenerLongitud());
        ps.setBlob(9, DatosEmpresa.ObtenerImagen());
        ps.setInt(10, DatosEmpresa.ObtenerCodPersona());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean ModificarEmpresa(EmpresaEntidad DatosEmpresa) throws SQLException, IOException {
        if (DatosEmpresa.ObtenerImagen() != null) {
            PreparedStatement ps = conexion.prepareStatement("UPDATE Empresa SET Nombre = ? ,Telefono = ? ,Email = ? ,Direccion = ? ,CodDistrito = (SELECT CodDistrito FROM Distrito WHERE Nombre = ?) ,Descripcion = ? ,Latitud = ? ,Longitud = ? ,Imagen = ? WHERE CodEmpresa = ? ");
            ps.setString(1, DatosEmpresa.ObtenerNombre());
            ps.setString(2, DatosEmpresa.ObtenerTelefono());
            ps.setString(3, DatosEmpresa.ObtenerEmail());
            ps.setString(4, DatosEmpresa.ObtenerDireccion());
            ps.setString(5, DatosEmpresa.ObtenerDistrito());
            ps.setString(6, DatosEmpresa.ObtenerDescripcion());
            ps.setString(7, DatosEmpresa.ObtenerLatitud());
            ps.setString(8, DatosEmpresa.ObtenerLongitud());
            ps.setBlob(9, DatosEmpresa.ObtenerImagen());
            ps.setInt(10, DatosEmpresa.ObtenerCodEmpresa());
            ResultadoConsulta = ps.executeUpdate();
        } else {
            PreparedStatement ps = conexion.prepareStatement("UPDATE Empresa SET Nombre = ? ,Telefono = ? ,Email = ? ,Direccion = ? ,CodDistrito = (SELECT CodDistrito FROM Distrito WHERE Nombre = ?) ,Descripcion = ? ,Latitud = ? ,Longitud = ? WHERE CodEmpresa = ? ");
            ps.setString(1, DatosEmpresa.ObtenerNombre());
            ps.setString(2, DatosEmpresa.ObtenerTelefono());
            ps.setString(3, DatosEmpresa.ObtenerEmail());
            ps.setString(4, DatosEmpresa.ObtenerDireccion());
            ps.setString(5, DatosEmpresa.ObtenerDistrito());
            ps.setString(6, DatosEmpresa.ObtenerDescripcion());
            ps.setString(7, DatosEmpresa.ObtenerLatitud());
            ps.setString(8, DatosEmpresa.ObtenerLongitud());
            ps.setInt(9, DatosEmpresa.ObtenerCodEmpresa());
            ResultadoConsulta = ps.executeUpdate();
        }
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean EliminarEmpresa(EmpresaEntidad DatosEmpresa) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("DELETE FROM Empresa WHERE CodEmpresa = ? ");
        ps.setInt(1, DatosEmpresa.ObtenerCodEmpresa());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean CalificarEmpresa(EmpresaEntidad DatosEmpresa) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("UPDATE Empresa SET Calificacion = ? WHERE CodEmpresa = ? ");
        ps.setInt(1, DatosEmpresa.ObtenerCalificacion());
        ps.setInt(2, DatosEmpresa.ObtenerCodEmpresa());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }
}
