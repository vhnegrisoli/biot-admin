package br.com.biot_admin.biot_admin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app-config.topic.biot-admin}")
    private String biotAdminTopic;

    @Value("${app-config.queue.usuario-log}")
    private String usuarioLogMq;

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(biotAdminTopic);
    }

    @Bean
    Queue usuarioLogMq() {
        return new Queue(usuarioLogMq, true);
    }

    @Bean
    public Binding usuarioLogBinding(TopicExchange exchange) {
        return BindingBuilder.bind(usuarioLogMq()).to(exchange).with(usuarioLogMq);
    }
}
