package ses;

// these are the imports for SDK v1
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.regions.Regions;

public class EmailSender {
    public EmailResult handleRequest(EmailRequest request, Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log("Entering send_email");

        EmailResult emailResult = new EmailResult();

        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()

                            // Replace US_WEST_2 with the AWS Region you're using for
                            // Amazon SES.
                            .withRegion(Regions.US_WEST_2).build();

            // TODO:
            // Use the AmazonSimpleEmailService object to send an email message
            // using the values in the EmailRequest parameter object
            SendEmailRequest emailRequest = new SendEmailRequest(request.getFrom(),
                    new Destination().withToAddresses(request.getTo()),
                    new Message().withSubject(new Content(request.getSubject())).
                            withBody(new Body().withText(new Content(request.getBody()))));

            // send the email
            SendEmailResult sendEmailResult = client.sendEmail(emailRequest);

            // set the message and timestamp
            emailResult.setMessage(sendEmailResult.getMessageId());
            emailResult.setTimestamp(String.valueOf(System.currentTimeMillis()));

            logger.log("Email sent!");
        } catch (Exception ex) {
            logger.log("The email was not sent. Error message: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
        finally {
            logger.log("Leaving send_email");
        }

        // TODO:
        // Return EmailResult
        return emailResult;
    }
}
