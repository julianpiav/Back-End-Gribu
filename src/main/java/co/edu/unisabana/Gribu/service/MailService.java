package co.edu.unisabana.Gribu.service;

import co.edu.unisabana.Gribu.entity.User;
import sendinblue.*;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class MailService {

    public static void sendEmail() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey("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");

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        sender.setEmail("julian.d.alvarado23@gmail.com");
        sender.setName("Julián Alvarado");

        List<SendSmtpEmailTo> toList = new ArrayList<>();
        SendSmtpEmailTo to = new SendSmtpEmailTo();
        to.setEmail("antoniopendejo05@gmail.com");
        to.setName("Antonio");
        Integer code = 123456;

        toList.add(to);

        Properties headers = new Properties();
        headers.setProperty("Some-Custom-Name", "unique-id-1234");
        Properties params = new Properties();
        params.setProperty("parameter", "My param value");
        params.setProperty("subject", "New Subject");

        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        sendSmtpEmail.setSender(sender);
        sendSmtpEmail.setTo(toList);
        sendSmtpEmail.setHeaders(headers);
        sendSmtpEmail.setParams(params);

        UserService userService = new UserService();
        User myUser = userService.findByEmail("email");


        sendSmtpEmail.setHtmlContent("<html>" +
                "<body>" +
                "<h2>Pruebas de email christian</h2>" +
                "<p>Hola " + to.getName() +"</p>" +
                "<p>Ingresa este codigo para el cambio de contrasenia " + code +"</p>" +
                "</body>" +
                "</html>");
        sendSmtpEmail.setSubject("Pruebas Christian api email");
        System.out.println(sendSmtpEmail);
        try {
            CreateSmtpEmail result = apiInstance.sendTransacEmail(sendSmtpEmail);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TransactionalEmailsApi#sendTransacEmail");
            e.printStackTrace();
        }
    }

}
