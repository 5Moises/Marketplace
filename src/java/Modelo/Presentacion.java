package Modelo;

import Configuracion.Conexion;
import Entidad.PresentacionEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Presentacion {

    Connection conexion = Conexion.getConnection();
    PresentacionEntidad presentacionentidad = new PresentacionEntidad();

    private int ResultadoConsulta;
    private boolean ExitoConsulta;
    ResultSet rs;

    public List MostrarPresentaciones() {
        ArrayList<PresentacionEntidad> DatosPresentacion = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM Presentacion");
            rs = ps.executeQuery();
            while (rs.next()) {
                PresentacionEntidad presentacionlista = new PresentacionEntidad();
                presentacionlista.ColocarCodPresentacion(rs.getInt(1));
                presentacionlista.ColocarNombre(rs.getString(2));
                DatosPresentacion.add(presentacionlista);
            }
        } catch (Exception Ex) {
        }
        return DatosPresentacion;
    }

    public boolean RegistrarPresentacion(PresentacionEntidad DatosPresentacion) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO Presentacion (Nombre) VALUES (?)");
        ps.setString(1, DatosPresentacion.ObtenerNombre());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean EliminarPresentacion(PresentacionEntidad DatosPresentacion) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("DELETE FROM Presentacion WHERE CodPresentacion = ? ");
        ps.setInt(1, DatosPresentacion.ObtenerCodPresentacion());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }
}
