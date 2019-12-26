<%--
  Created by IntelliJ IDEA.
  User: yangyuze
  Date: 17/12/19
  Time: 12:11 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Properties" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email</title>
</head>
<body onLoad="displayResult()">
    <h1>Sending An Email</h1>
    <%!
        public static class SMTPAuthenticator extends Authenticator {
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("vctrwsm", "Y19961018y");
            }
        }
    %>
    <%
        int result = 0;
        if(request.getParameter("send") != null){
            String d_uname = "vctrwsm";
            String d_password = "Y19961018y";
            String d_host = "smtp.gmail.com";
            int d_port = 465;

            String m_to = new String();
            String m_from = "vctrwsm@gmail.com";
            String m_subject = new String();
            String m_text = new String();

            if(request.getParameter("to") != null){
                m_to = request.getParameter("to");
            }

            if(request.getParameter("subject") != null){
                m_subject = request.getParameter("subject");
            }

            if(request.getParameter("message") != null){
                m_text = "<h1>Welcome to web app dev and design</h1><br>";
                m_text = m_text.concat(request.getParameter("message"));
                m_text = m_text.concat("<br><h2>This is end line!</h2>");
            }

            //Create a Properties object
            Properties props = new Properties();

            //Create an SMTPAuthenticator object
            SMTPAuthenticator auth = new SMTPAuthenticator();

            //Create a mail session object
            Session ses = Session.getInstance(props, auth);

            //Create a MIME style email message object
            MimeMessage msg = new MimeMessage(ses);
            msg.setContent(m_text, "text/html");
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(m_from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(m_to));

            try{
                Transport transport = ses.getTransport("smtps");

                transport.connect(d_host, d_uname, d_password);

                transport.sendMessage(msg, msg.getAllRecipients());

                transport.close();

                result = 1;
            } catch (Exception e){
                out.println(e);
            }
        }
    %>
    <form action="email-form.jsp" method="post" name="emailForm">
        <table border="0">
            <tbody>
                <tr>
                    <td>To:</td>
                    <td><input type="text" name="to" size="50"></td>
                </tr>
                <tr>
                    <td>Subject:</td>
                    <td><input type="text" name="subject" size="50"></td>
                </tr>
                <tr>
                    <td>Message:</td>
                    <td><textarea name="message" cols="50" rows="5"></textarea></td>
                </tr>
            </tbody>
        </table>
        <input type="hidden" name="hidden" value="<%= result %>">
        <input type="reset" value="Clear" name="clear">
        <input type="submit" value="Send" name="send">
    </form>
    <script language="JavaScript">
        function displayResult() {
            if(document.emailForm.hidden.value === "1"){
                alert("Mail was sent!");
            }
        }
    </script>
</body>
</html>
