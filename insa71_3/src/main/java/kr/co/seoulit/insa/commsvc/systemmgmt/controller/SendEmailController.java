package kr.co.seoulit.insa.commsvc.systemmgmt.controller;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


@RequestMapping("/systemmgmt/*")
@RestController
public class SendEmailController {
//    private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
    private Multipart multipart;
	
    @GetMapping("email")
    public ModelMap sendEmail(HttpServletRequest request, HttpServletResponse response) {    	
       ModelMap map = new ModelMap();
       map.put("empCode", request.getParameter("empCode"));
       map.put("usage", request.getParameter("usage"));
       map.put("date", request.getParameter("requestDay"));
       map.put("end", request.getParameter("useDay"));
       
       String eMail = request.getParameter("eMail");

       String host = "smtp.naver.com";
       final String user = "wit_wit@naver.com"; 
       final String password = "jh430507";
       int port = 465;
       JRDataSource jrd = new JREmptyDataSource();
       
       String subject = "재직증명서";
       String body = user + "님이 보내신 재직증명서.";
       
       JasperReport jasperReport;
       JasperPrint jasperPrint;
       try {
          jasperReport = JasperCompileManager
        		  .compileReport((request.getServletContext().getRealPath("/report/employment.jrxml")));
      
          jasperPrint = JasperFillManager.
        		  fillReport(jasperReport, map, jrd);
          JasperExportManager.exportReportToPdfFile(jasperPrint, (request.getServletContext().getRealPath("/report/test01.pdf")));

          // Get the session object
          Properties props = new Properties();
          props.put("mail.smtp.host", host);
          props.put("mail.smtp.port", port);
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.ssl.enable", "true");
          props.put("mail.smtp.ssl.trust", host);


          Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication() {      
                return new javax.mail.PasswordAuthentication(user, password);
             }
          });
           MimeMessage message = new MimeMessage(session);
             message.setFrom(new InternetAddress(user));  
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(eMail));
           System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@eMail:   "+eMail);
             // Subject
             message.setSubject(subject);
             multipart = new MimeMultipart();
                   
             // Text
             
             MimeBodyPart mbp1 = new MimeBodyPart();
                mbp1.setText(body);
                multipart.addBodyPart(mbp1);

             // send the message

                 DataSource source = new FileDataSource((request.getServletContext().getRealPath("/report/test01.pdf")));
                 BodyPart messageBodyPart = new MimeBodyPart();
                 messageBodyPart.setDataHandler(new DataHandler(source));
                 messageBodyPart.setFileName("test01.pdf");
                 multipart.addBodyPart(messageBodyPart);

             message.setContent(multipart);
                Transport.send(message);
             System.out.println("메일 발송 성공!");

       } catch (Exception e) {
          e.printStackTrace();
          System.out.println("메일에러" + e);
       }
       return map;
    }
    
}