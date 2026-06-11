package service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import model.Usuario;

public class SqsService {
    private static final String QUEUE_URL = "http://localhost.localstack.cloud:4566/000000000000/control-gastos-events-dev";

    private final AmazonSQS sqs;

    public SqsService() {
        try {

            BasicAWSCredentials credentials =
                new BasicAWSCredentials("test", "test");

            this.sqs = AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(
                    new AwsClientBuilder.EndpointConfiguration(
                        "http://localhost:4566",
                        "us-east-1"
                    )
                )
                .withCredentials(
                    new AWSStaticCredentialsProvider(credentials)
                )
                .build();

            System.out.println("Cliente SQS inicializado correctamente");

        } catch (Exception e) {

            System.err.println("Error inicializando SQS");
            e.printStackTrace();

            throw e;
        }
    }

    public void enviarEventoGasto(
        int expenseId,
        double amount,
        String category,
        Usuario user) {
        try {
            String jsonUser = "null";
            if (user != null) {
                jsonUser = String.format(
                    "{\"id\":%d,\"nombre\":\"%s\",\"email\":\"%s\",\"rol\":\"%s\"}",
                    user.getId(), user.getNombre(), user.getEmail(), user.getRol()
                );
            }

            String mensaje = String.format(
                "{"
                + "\"eventType\":\"EXPENSE_CREATED\","
                + "\"expenseId\":%d,"
                + "\"amount\":%.2f,"
                + "\"category\":\"%s\","
                + "\"user\":%s"
                + "}",
                expenseId,
                amount,
                category,
                jsonUser
            );

            System.out.println("Enviando mensaje:");
            System.out.println(mensaje);

            SendMessageRequest request = new SendMessageRequest()
                    .withQueueUrl(QUEUE_URL)
                    .withMessageBody(mensaje);

            sqs.sendMessage(request);

            System.out.println("Mensaje enviado a SQS");

        } catch (Exception e) {

            System.err.println("Error al enviar mensaje a SQS");
            System.err.println("Mensaje: " + e.getMessage());

            e.printStackTrace();

        }
    }
}