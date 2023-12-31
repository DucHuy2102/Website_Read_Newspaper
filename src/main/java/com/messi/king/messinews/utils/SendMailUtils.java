package com.messi.king.messinews.utils;

import com.messi.king.messinews.model.bean.Users;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;


//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;


public class SendMailUtils {
    private static String fromEmail = "20110103@student.hcmute.edu.vn";
    private static String username = "";
    private static String password = "";

    private static String getRandom() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
//    public static String sendEmail(Users user) throws MessagingException {
//        String code = getRandom();
//
//        Properties pr = new Properties();
//        pr.setProperty("mail.transport.protocol", "smtp");
//        pr.setProperty("mail.smtp.host", "in-v3.mailjet.com");
//        pr.setProperty("mail.smtp.port", "587");
//        pr.setProperty("mail.smtp.auth", "true");
//        pr.setProperty("mail.smtp.starttls.enable", "true");
//        pr.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
//
//        Session session = Session.getDefaultInstance(pr);
//
//        Message mess = new MimeMessage(session);
//        //set from email address
//        mess.setFrom(new InternetAddress(fromEmail));
//        //set to email address or destination email address
//        mess.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
//
//        mess.setSubject("User Email Verification");
//
//        mess.setContent("<!DOCTYPE html> <html> <head> <meta charset=\"utf-8\"> <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\"> <title>Email Confirmation</title> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> <style type=\"text/css\"> /** * Google webfonts. Recommended to include the .woff version for cross-client compatibility. */ @media screen { @font-face { font-family: 'Source Sans Pro'; font-style: normal; font-weight: 400; src: local('Source Sans Pro Regular'), local('SourceSansPro-Regular'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff'); } @font-face { font-family: 'Source Sans Pro'; font-style: normal; font-weight: 700; src: local('Source Sans Pro Bold'), local('SourceSansPro-Bold'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff'); } } /** * Avoid browser level font resizing. * 1. Windows Mobile * 2. iOS / OSX */ body, table, td, a { -ms-text-size-adjust: 100%; /* 1 */ -webkit-text-size-adjust: 100%; /* 2 */ } /** * Remove extra space added to tables and cells in Outlook. */ table, td { mso-table-rspace: 0pt; mso-table-lspace: 0pt; } /** * Better fluid images in Internet Explorer. */ img { -ms-interpolation-mode: bicubic; } /** * Remove blue links for iOS devices. */ a[x-apple-data-detectors] { font-family: inherit !important; font-size: inherit !important; font-weight: inherit !important; line-height: inherit !important; color: inherit !important; text-decoration: none !important; } /** * Fix centering issues in Android 4.4. */ div[style*=\"margin: 16px 0;\"] { margin: 0 !important; } body { width: 100% !important; height: 100% !important; padding: 0 !important; margin: 0 !important; } /** * Collapse table borders to avoid space between cells. */ table { border-collapse: collapse !important; } a { color: #1a82e2; } img { height: auto; line-height: 100%; text-decoration: none; border: 0; outline: none; } </style> </head> <body style=\"background-color: #e9ecef;\"> <!-- start preheader --> <div class=\"preheader\" style=\"display: none; max-width: 0; max-height: 0; overflow: hidden; font-size: 1px; line-height: 1px; color: #fff; opacity: 0;\"> Confirm your request to reset your password </div> <!-- end preheader --> <!-- start body --> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <!-- start logo --> <tr> <td align=\"center\" bgcolor=\"#e9ecef\"> <!--[if (gte mso 9)|(IE)]> <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\"> <tr> <td align=\"center\" valign=\"top\" width=\"600\"> <![endif]--> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\"> <tr> <td align=\"center\" valign=\"top\" style=\"padding: 36px 24px;\"> <a href=\"https://www.messinews.com\" target=\"_blank\" style=\"display: inline-block;\"> <img src=\"https://iili.io/HTvhp6J.png\" alt=\"Logo\" border=\"0\" width=\"48\" style=\"display: block; width: 48px; max-width: 48px; min-width: 48px;\"> </a> </td> </tr> </table> <!--[if (gte mso 9)|(IE)]> </td> </tr> </table> <![endif]--> </td> </tr> <!-- end logo --> <!-- start hero --> <tr> <td align=\"center\" bgcolor=\"#e9ecef\"> <!--[if (gte mso 9)|(IE)]> <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\"> <tr> <td align=\"center\" valign=\"top\" width=\"600\"> <![endif]--> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\"> <tr> <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 36px 24px 0; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; border-top: 3px solid #d4dadf;\"> <h1 style=\"margin: 0; font-size: 32px; font-weight: 700; letter-spacing: -1px; line-height: 48px;\">Confirm your request to reset your password</h1> </td> </tr> </table> <!--[if (gte mso 9)|(IE)]> </td> </tr> </table> <![endif]--> </td> </tr> <!-- end hero --> <!-- start copy block --> <tr> <td align=\"center\" bgcolor=\"#e9ecef\"> <!--[if (gte mso 9)|(IE)]> <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\"> <tr> <td align=\"center\" valign=\"top\" width=\"600\"> <![endif]--> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\"> <!-- start copy --> <tr> <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\"> <p style=\"margin: 0;\">Copy this code and paste it in your browser to reset your password now!. Your have only 5 minutes until it expires</p> </td> </tr> <!-- end copy --> <!-- start button --> <tr> <td align=\"left\" bgcolor=\"#ffffff\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 12px;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> <tr> <td align=\"center\" bgcolor=\"#1a82e2\" style=\"border-radius: 6px;\"> <h3 style=\"display: inline-block; padding: 16px 36px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 20px; color: #ffffff; text-decoration: none; border-radius: 6px;\">"+code+"</h3> </td> </tr> </table> </td> </tr> </table> </td> </tr> <!-- end button --> <!-- start copy --> <tr> <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\"> <p style=\"margin: 0;\">If something's wrong, please go this link and try again: </p> <p style=\"margin: 0;\"><a href=\"https://messinews.com/\" target=\"_blank\">https://messinews.com/</a></p> </td> </tr> <!-- end copy --> <!-- start copy --> <tr> <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; border-bottom: 3px solid #d4dadf\"> <p style=\"margin: 0;\">Cheers,<br> Messi News</p> </td> </tr> <!-- end copy --> </table> <!--[if (gte mso 9)|(IE)]> </td> </tr> </table> <![endif]--> </td> </tr> <!-- end copy block --> <!-- start footer --> <tr> <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 24px;\"> <!--[if (gte mso 9)|(IE)]> <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\"> <tr> <td align=\"center\" valign=\"top\" width=\"600\"> <![endif]--> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\"> <!-- start permission --> <tr> <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\"> <p style=\"margin: 0;\">You recived this email because your reset password request in our website. If you didn't do that, please ignore this email. Thank you</p> </td> </tr> <!-- end permission --> <!-- start unsubscribe --> <tr> <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\"> <p style=\"margin: 0;\">Thanks for using MessiNews</p> <p style=\"margin: 0;\">Messi News - Greatest of all times tết</p> </td> </tr> <!-- end unsubscribe --> </table> <!--[if (gte mso 9)|(IE)]> </td> </tr> </table> <![endif]--> </td> </tr> <!-- end footer --> </table> <!-- end body --> </body> </html>","text/html; charset=UTF-8");
//
//        Transport transport = session.getTransport();
//        transport.connect("in-v3.mailjet.com", username, password);
//        transport.sendMessage(mess, mess.getAllRecipients());
//        transport.close();
//
//        return code;
//    }
}
