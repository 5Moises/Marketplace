package Controlador;

import Entidad.BoletaCorreoEntidad;
import Modelo.BoletaCorreo;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BoletaCorreoServlet", urlPatterns = {"/BoletaCorreoServlet"})

public class BoletaCorreoServlet extends HttpServlet {
    
    BoletaCorreoEntidad boletacorreoentidad = new BoletaCorreoEntidad();
    BoletaCorreo boletacorreo = new BoletaCorreo();
    CuadrosDialogo cuadrodialogo = new CuadrosDialogo();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String CodPedido = request.getParameter("txt_codpedido");
        String CodEmpresa = request.getParameter("txt_codempresa");
        String FechaHoraConfirmada = request.getParameter("dtp_fechahora");
        String NombreRepartidor = request.getParameter("txt_nombrerepartidor");
        
        boletacorreoentidad.ColocarCodPedido(CodPedido);
        boletacorreoentidad.ColocarCodEmpresa(CodEmpresa);
        boletacorreoentidad.ColocarFechaConfirmada(FechaHoraConfirmada);
        boletacorreoentidad.ColocarNombreRepartidor(NombreRepartidor);
        
        try {
            String Remitente = "marketplacen0reply1@gmail.com";
            String Clave = "M34893yfbdouitgr0478hj";
            String Destinatario = "josev28n@gmail.com";
            String Asunto = "MALL UTP :: Comprobante de pago de " + boletacorreo.MostrarNombreEmpresa(boletacorreoentidad);
            String CuerpoMensaje = boletacorreo.MostrarBoletaCorreo(boletacorreoentidad);
           //String Adjunto = " ";
            
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.user", Remitente);
            properties.put("mail.smtp.clave", Clave);
            
            BodyPart texto = new MimeBodyPart();
            texto.setContent(CuerpoMensaje, "text/html; charset=utf-8");

            /*BodyPart adjunto = new MimeBodyPart();
             adjunto.setDataHandler(new DataHandler(new FileDataSource(Adjunto)));
             adjunto.setFileName("Boleta electronica.png");*/
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
           // multiParte.addBodyPart(adjunto);
            
            Session session = Session.getDefaultInstance(properties);
            
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(Remitente));
            
            InternetAddress[] toAddresses = {new InternetAddress(Destinatario)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(Asunto);
            msg.setSentDate(new Date());
            msg.setContent(multiParte);
            
            Transport t = session.getTransport("smtp");
            t.connect(Remitente, Clave);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
            
            response.sendRedirect("Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Pedidos");
        } catch (Exception Ex) {
            cuadrodialogo.CuadroMensaje(request, response, "Empresa.jsp?CodEmpresa=" + CodEmpresa + "&Seccion=Pedidos", Ex.toString(), "Error");
        }
    }
}
