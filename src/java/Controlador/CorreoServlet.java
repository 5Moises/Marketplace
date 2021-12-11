/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Configuracion.Conexion;
import Controlador.CuadrosDialogo;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author U18204324
 */
@WebServlet(name = "CorreoServlet", urlPatterns = {"/CorreoServlet"})
@MultipartConfig
public class CorreoServlet extends HttpServlet {

    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destinatario = request.getParameter("txt_Email");
        String asunto = "Pedido Confirmado";
        String option = request.getParameter("opcion");
        int codcliente = Integer.parseInt(request.getParameter("txt_codcliente"));
        int CodEmpresa = Integer.parseInt(request.getParameter("txt_codempresa"));
        String cuerpo = "Estimado " + request.getParameter("txt_Nombre") + "\n"
                + "Su pedido llegara: " + request.getParameter("txt_Fecha") + "\n"
                + "Nombre el Repartidor: " + request.getParameter("Nombre_R");
        String remitente = "marketplacen0reply1@gmail.com";
        String clave = "M34893yfbdouitgr0478hj";

        Properties props = System.getProperties();
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        switch (option) {
            case "Enviar Boleta":
                BodyPart texto = new MimeBodyPart();
                try {
                    texto.setText(cuerpo);
                } catch (MessagingException ex) {
                    Logger.getLogger(CorreoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                ServletContext context = request.getServletContext();
                MimeMultipart multiParte = new MimeMultipart();
                props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
                props.put("mail.smtp.user", remitente);
                props.put("mail.smtp.clave", clave);    //La clave de la cuenta
                props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
                props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
                props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
                String reporfile = context.getRealPath("/Reporte/Boleta.jasper");
                String destino = context.getRealPath("/Reporte/" + codcliente + ".pdf");
                Connection conexion = Conexion.getConnection();

                Map parametros = new HashMap();
                parametros.put("cod_cliente", codcliente);
                parametros.put("cod_empresa", CodEmpresa);
                byte[] bytes;
                try {

                    JasperPrint reporte = JasperFillManager.fillReport(reporfile, parametros, conexion);
                    JRExporter exporter = new JRPdfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, reporte);
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destino);
                    exporter.exportReport();

                } catch (JRException ex) {
                    cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Sesion=Reporte", ex.toString(), "Error");
                }
                try {
                    BodyPart adjunto = new MimeBodyPart();
                    adjunto.setFileName("Boleta Electronica.pdf");
                    adjunto.setDataHandler(new DataHandler(new FileDataSource(destino)));
                    multiParte.addBodyPart(texto);
                    multiParte.addBodyPart(adjunto);
                    message.setFrom(new InternetAddress(remitente));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
                    message.setSubject(asunto);
                    message.setContent(multiParte);
                    Transport transport = session.getTransport("smtp");
                    transport.connect("smtp.gmail.com", remitente, clave);
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                    cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Pedidos", "Boleta Enviada", "Éxito");
                } catch (MessagingException me) {
                    cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Pedidos", "Boleta no Enviada", "Error");
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
