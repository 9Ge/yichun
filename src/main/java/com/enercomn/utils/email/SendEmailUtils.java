package com.enercomn.utils.email;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

/**
 * @author Oliver
 * @description 发送email工具类
 * @date   2019/6/4 16:42
 * @version 1.0
 */

@Component
@Slf4j
public class SendEmailUtils {
//    public static void main(String[] args) {
//        SendEmailUtils.sendMail(SendEmailUtils.getMailBean("18068335587@163.com","能源报警提醒","addNodeCode",""));
//    }
    //邮件服务器地址
    public static   String HOST = "smtp.mxhichina.com";
    //登录名
    public static   String USER_NAME = "liuyuhong@enercomn.com";
    //授权第三方登录码
    public static   String USER_LO = "Enercomnlyh7317";

    public static   String FROM = "liuyuhong@enercomn.com";

    public static   String DEFAULT_CONTENT = "请查收附件";

    /**
     * @author Oliver
     * @description 创建邮件对象
     * @date   2019/6/12 16:34
     * @version 1.0
     * @param to      发送目标
     * @param title   邮件标题
     * @param content 邮件正文
     * @param path    附件路径
     * @return MailBean
     */
    public static MailBean getMailBean(String to, String title, String content, String path){
        MailBean mb = new MailBean();
        // 设置SMTP主机(163)，若用126，则设为：
        mb.setHost(HOST);
        // 设置发件人邮箱的信息
        mb.setUsername(USER_NAME);
        // 设置发件人邮箱的信息
        mb.setPassword(USER_LO);
        // 设置发件人的邮箱
        mb.setFrom(FROM);
        // 设置收件人的邮箱
        mb.setTo(to);
        // 设置邮件的主题
        // 设置邮件的主题
        try {
            mb.setSubject(MimeUtility.encodeWord(title, "UTF-8", "Q"));
            mb.setContent(content);
        } catch (UnsupportedEncodingException e) {
            log.error("转码异常",e);
        }
//        mb.setSubject(title);
        // 设置邮件的正文

        // 往邮件中添加附件
        log.debug("正在发送邮件..."+ path);
        //添加附件，需要链接地址
        //mb.attachFile(path);
        return mb;
    }

    private static String toChinese(String text) {
        try {
            text = MimeUtility.encodeText(new String(text.getBytes(), StandardCharsets.UTF_8), StandardCharsets.UTF_8.toString(), "B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static boolean sendMail(MailBean mb) {
        String host = mb.getHost();
        final String username = mb.getUsername();
        final String password = mb.getPassword();
        String from = mb.getFrom();
        String to = mb.getTo();
        String subject = mb.getSubject();
        String content = mb.getContent();
        String fileName;
        Vector<String> file = mb.getFile();

        Properties props = System.getProperties();
        // 设置SMTP的主机
        props.setProperty("mail.transport.protocol", "SMTP");
        //smtp地址
        props.put("mail.smtp.host", host);
        // 需要经过验证
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = { new InternetAddress(to) };
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(toChinese(subject));

            Multipart mp = new MimeMultipart();
            MimeBodyPart mbpContent = new MimeBodyPart();
//            mbpContent.setText(content,"text/html;charset=UTF-8");
            mbpContent.setText(content);
            mp.addBodyPart(mbpContent);

            /* 往邮件中添加附件 */
//            if (file != null) {
//                Enumeration<String> eFile = file.elements();
//                while (eFile.hasMoreElements()) {
//                    MimeBodyPart mbpFile = new MimeBodyPart();
//                    fileName = eFile.nextElement();
//                    FileDataSource fds = new FileDataSource(fileName);
//                    mbpFile.setDataHandler(new DataHandler(fds));
//                    mbpFile.setFileName(toChinese(fds.getName()));
//                    mp.addBodyPart(mbpFile);
//                }
//                System.out.println("添加成功");
//            }

            msg.setContent(mp, "text/html;charset=UTF-8");
            msg.setSentDate(new Date());
            Transport.send(msg);
            log.info("------报警邮件发送成功------");
        } catch (MessagingException me) {
            me.printStackTrace();
            log.info("******报警邮件发送失败*******");
            return false;
        }
        return true;
    }

}
