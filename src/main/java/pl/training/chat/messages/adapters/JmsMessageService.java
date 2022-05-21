package pl.training.chat.messages.adapters;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.java.Log;
import pl.training.chat.messages.domain.models.ChatMessage;
import pl.training.chat.messages.ports.MessageService;

import javax.inject.Singleton;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Log
@Singleton
public class JmsMessageService {

    private static final String EXCHANGE_NAME = "topic_logs";

    public void send(ChatMessage chatMessage) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String routingKey = chatMessage.getRoomName();
            byte[] bytes = chatMessage.toJsonString().getBytes(StandardCharsets.UTF_8);
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, bytes);
            System.out.println(" [x] Sent '" + routingKey + "':'" + chatMessage + "'");
        }
    }
}















