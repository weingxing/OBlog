package com.oxygen.oblog.util;

import com.oxygen.oblog.entity.Notify;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 使用SMTP发送邮件
 * @author Oxygen
 * @since 2020/09/24
 */
@Slf4j
public class SendMailThread extends Thread {

    private String receiver;
    private String content;
    private String subject ;
    private String host;
    private String user;
    private String password;
    private int port;
    private String SSLorTLS;

    public SendMailThread(String receiver, String subject, String content,
                          String host, String user, String password, int port,
                          String SSLorTLS) {
        super();
        this.receiver = receiver;
        this.content = content;
        this.subject = subject;
        this.host = host;
        this.user = user;
        this.password = password;
        this.port = port;
        this.SSLorTLS = SSLorTLS;
    }


    /**
     * 发送邮件
     * @param receiver 收件人地址
     * @param subject 邮件标题
     * @param content 邮件文本内容
     */
    public void sendMail(String receiver, String subject, String content) throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        //配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.auth", "true");
        if("SSL".equals(this.SSLorTLS))
            props.put("mail.smtp.ssl.enable", "true");
        else if("TLS".equals(this.SSLorTLS))
            props.put("mail.smtp.tls.enable", "true");
        props.put("mail.smtp.host", this.host);
        //smtp登陆的账号、密码 ；需开启smtp登陆
        props.put("mail.user", this.user);
        props.put("mail.smtp.port", String.valueOf(this.port));
        props.setProperty("mail.smtp.socketFactory.port", String.valueOf(this.port));
        // 访问SMTP服务时需要提供的密码,不是邮箱登陆密码，一般都有独立smtp的登陆密码
        props.put("mail.password", this.password);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };

        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人
        InternetAddress to = new InternetAddress(receiver);
        message.setRecipient(RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject(subject);
        // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
    }

    @Override
        public void run() {
        super.run();
        try {
            sendMail(receiver, subject, content);
            log.info("邮件发送成功，收件地址：" + receiver);
        } catch (Exception e) {
            log.info("邮件发送失败" + e);
        }
    }

    /** 多线程发送 **/
    public static void sendMail(Notify notify, String title, String text) {
        String context = "<table style=\"width:99.8%;height:99.8%\">\n" +
                "<tbody>\n<tr>\n<td style=\" background:#fafafa url(#) \">\n" +
                "<div style=\"border-radius:10px;font-size:13px;color:#555;width:666px;" +
                "font-family:'Century Gothic','Trebuchet MS','Hiragino Sans GB','微软雅黑'," +
                "'Microsoft Yahei',Tahoma,Helvetica,Arial,SimSun,sans-serif;margin:50px auto;" +
                "border:1px solid #eee;max-width:100%;" +
                "background:#fff repeating-linear-gradient(-45deg,#fff,#fff 1.125rem," +
                "transparent 1.125rem,transparent 2.25rem);box-shadow:0 1px 5px rgba(0,0,0,.15)\">\n" +
                "<div style=\"width:100%;background:#49BDAD;color:#fff;" +
                "border-radius:10px 10px 0 0;background-image:-moz-linear-gradient(0deg,#43c6b8,#ffd1f4);" +
                "background-image:-webkit-linear-gradient(0deg,#43c6b8,#ffd1f4);height:66px\">" +
                "<p style=\"font-size:15px;word-break:break-all;padding:23px 32px;" +
                "margin:0;background-color:hsla(0,0%,100%,.4);border-radius:10px 10px 0 0\">" +
                "博客有新的动态！</p></div>\n" +
                "<div style=\"margin:40px auto;width:90%\">\n" +
                "<p>动态内容:</p>\n" +
                "<p style=\"background:#fafafa repeating-linear-gradient(-45deg,#fff,#fff 1.125rem,transparent 1.125rem,transparent 2.25rem)" +
                ";box-shadow:0 2px 5px rgba(0,0,0,.15);margin:20px 0;" +
                "padding:15px;border-radius:5px;font-size:14px;color:#555\">" +
                text +
                "</p>\n" +
                "<p>请注意：此邮件由系统自动发送，请勿直接回复。</p>\n" +
                "<p>若此邮件不是您请求的，请忽略并删除！</p>\n" +
                "</div>\n</div>\n</td>\n</tr>\n</tbody>\n</table>";
        try {
            SendMailThread d = new SendMailThread(notify.getReceiver(), title, context,
                    notify.getSmtpAddress(),
                    notify.getUserName(), notify.getPassword(),
                    Integer.parseInt(notify.getSmtpPort()), notify.getSsl());

            d.start();
        } catch (Exception e) {
            throw e;
        }
    }

}