
package Modelo;

import Configuracion.Conexion;
import Entidad.MarcaEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Marca {
    
    Connection conexion = Conexion.getConnection();
    MarcaEntidad marcaentidad = new MarcaEntidad();

    int ResultadoConsulta;
    boolean ExitoConsulta;
    ResultSet rs;

    public List MostrarMarcas() {
        ArrayList<MarcaEntidad> DatosMarca = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM Marca");
            rs = ps.executeQuery();
            while (rs.next()) {
                MarcaEntidad marcaentidadlista = new MarcaEntidad();
                marcaentidadlista.ColocarCodMarca(rs.getInt(1));
                marcaentidadlista.ColocarNombre(rs.getString(2));
                DatosMarca.add(marcaentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosMarca;
    }

    public boolean RegistrarMarca(MarcaEntidad DatosMarca) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO Marca (Nombre) VALUES (?)");
        ps.setString(1, DatosMarca.ObtenerNombre());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean EliminarMarca(MarcaEntidad DatosMarca) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("DELETE FROM Marca WHERE CodMarca = ? ");
        ps.setInt(1, DatosMarca.ObtenerCodMarca());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }
}
