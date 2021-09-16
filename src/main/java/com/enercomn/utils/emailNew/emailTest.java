package com.enercomn.utils.emailNew;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 */
public class emailTest {
    public static void main(String[] args) {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setMailServerHost("smtp.mxhichina.com"); // 邮箱服务器
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        // 以下是发送方信息
        mailInfo.setUserName("liuyuhong");
        mailInfo.setPassword("Enercomnlyh7317");// 您的邮箱密码
        mailInfo.setFromAddress("liuyuhong@enercomn.com");
        // 以下是接收方信息
        mailInfo.setToAddress("chenjian@enercomn.com");
        mailInfo.setSubject("XXX先生您好，您的9月份账单来了!");
        mailInfo.setContent("邮件内容");
        File affix1 = new File("C:\\Users\\Enercomn\\Desktop\\大众变速器资料\\邮件发送抄表文件\\产能抄表.xlsx");
        File affix2 = new File("C:\\Users\\Enercomn\\Desktop\\大众变速器资料\\邮件发送抄表文件\\能耗抄表.xlsx");
        List<File> attachments=new ArrayList<File>();
        attachments.add(affix1);
        attachments.add(affix2);
        mailInfo.setAttachments(attachments);
        mailInfo.setContentType("text/html");//HTML格式：text/html，纯文本格式：text/plain
        // 这个类主要来发送邮件
        MailSender.sendMail(mailInfo);//发送邮件
    }

}
