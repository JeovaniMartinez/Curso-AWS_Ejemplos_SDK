import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

public class Main {

    public static void main(String[] args) {
        sendEmail(
                "contacto@demo.com",
                "usuario@outlook.com",
                "Asunto del correo",
                "Contenido del correo"
        );
    }

    /**
     * Envía un correo electrónico mediante Amazon Simple Email Service (SES)
     *
     * @param source      Dirección de correo electrónico desde la que se va a enviar el correo
     * @param destination Dirección de correo electrónico de destino, hacia donde se envía el correo
     * @param subject     Asunto del correo
     * @param content     Contenido del correo
     */
    public static void sendEmail(String source, String destination, String subject, String content) {
        try {
            SesClient sesClient = SesClient.builder().region(Region.US_EAST_1).build();

            Destination emailDestination = Destination.builder().toAddresses(destination).build();

            Content contentSubject = Content.builder().data(subject).build();
            Content contentBody = Content.builder().data(content).build();
            Body emailBody = Body.builder().text(contentBody).build();
            Message emailMessage = Message.builder().subject(contentSubject).body(emailBody).build();

            SendEmailRequest request = SendEmailRequest.builder()
                    .source(source)
                    .destination(emailDestination)
                    .message(emailMessage)
                    .build();

            sesClient.sendEmail(request);

            System.out.println("Email sent successfully");

        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: " + ex.getMessage());
        }

    }

}
