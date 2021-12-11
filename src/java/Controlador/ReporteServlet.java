package Controlador;

import Configuracion.Conexion;
import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import net.sf.jasperreports.engine.JasperRunManager;
import java.util.*;
import java.time.LocalDate;
import javax.servlet.ServletOutputStream;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

@WebServlet(name = "ReporteServlet", urlPatterns = {"/ReporteServlet"})

public class ReporteServlet extends HttpServlet {

    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int CodEmpresa = Integer.parseInt(request.getParameter("txt_codempresa"));
        String op = request.getParameter("btn_ReporteS");
        int i = 1;
        String FechaI = request.getParameter("dtp_fechaInicial");
        String FechaF = request.getParameter("dtp_fechafinal");
        byte[] bytes;
        String Nombre = request.getParameter("dtp_Nombre");
        ServletContext context = request.getServletContext();
        Connection conexion = Conexion.getConnection();
        Map parametros = new HashMap();
        Map parametros1 = new HashMap();
        switch (op) {
            case "Reporte":

                if (Nombre == "") {
                    Nombre = "%";
                }
                if (FechaI == "") {
                    FechaI = "1999/10/10";
                    if (FechaF == "") {
                        FechaF = String.valueOf(LocalDate.now());
                    }
                }
                if (FechaF == "") {
                    FechaF = String.valueOf(LocalDate.now());
                }

                String reporfile = context.getRealPath("/Reporte/Pedidos.jasper");

                parametros.put("Fecha_Inicial", FechaI);
                parametros.put("Fecha_Final", FechaF);
                parametros.put("codEmpresa", CodEmpresa);
                parametros.put("condicion", Nombre);

                try {
                    switch (i) {
                        case 1:
                            if (FechaI != "" && FechaF != "") {

                                bytes = JasperRunManager.runReportToPdf(reporfile, parametros, conexion);
                                response.setContentType("application/pdf");
                                response.setContentLength(bytes.length);
                                ServletOutputStream output = response.getOutputStream();
                                response.getOutputStream();
                                output.write(bytes, 0, bytes.length);
                                output.flush();
                                output.close();
                            }
                            break;
                    }

                } catch (JRException ex) {
                    cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Sesion=Reporte", ex.toString(), "Error");
                }
                break;

            case "Generar Reporte":

                String Marca = request.getParameter("cbo_marca");
                String Categoria = request.getParameter("cbo_categoria");
                String NombreP = request.getParameter("dtp_NombreP");

                
                if (NombreP == "") {
                    NombreP = "%";
                }
                switch (Categoria)
                 {
                     case "Seleccionar":                         
                            Categoria = "%";
                         break;
                 }
                  switch (Marca)
                 {
                     case "Seleccionar":                          
                            Marca = "%";
                         break;
                 }   

                String reporfile2 = context.getRealPath("/Reporte/Productos.jasper");
                JOptionPane.showMessageDialog(null,Marca);

                parametros1.put("marca", Marca);
                parametros1.put("categoria", Categoria);
                parametros1.put("CodEmpresa", CodEmpresa);
                parametros1.put("Nombre", NombreP);
        
                try {
                    switch (i) {
                        case 1:                         

                                bytes = JasperRunManager.runReportToPdf(reporfile2, parametros1, conexion);
                                response.setContentType("application/pdf");
                                response.setContentLength(bytes.length);
                                ServletOutputStream output = response.getOutputStream();
                                response.getOutputStream();
                                output.write(bytes, 0, bytes.length);
                                output.flush();
                                output.close();
                           
                            break;
                    }

                } catch (JRException ex) {
                    cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Sesion=Reporte", ex.toString(), "Error");
                }
                break;
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
