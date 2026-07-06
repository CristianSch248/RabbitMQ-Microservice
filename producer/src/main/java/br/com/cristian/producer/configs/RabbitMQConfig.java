package br.com.cristian.producer.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import static constants.RabbitMQConstants.*;

@Configuration
public class RabbitMQConfig
{
    @Bean
    public Queue queue()
    {
        return new Queue( QUEUE_NAME, false, false, false );
    }

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange( EXCHANGE_NAME, false, false );
    }

    @Bean
    public Binding binding()
    {
        return BindingBuilder.bind( queue() )
                             .to( directExchange() )
                             .with( ROUTE_KEY_NAME );
    }

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate( ConnectionFactory connectionFactory, MessageConverter messageConverter )
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate( connectionFactory );
        rabbitTemplate.setMessageConverter( messageConverter );

        return rabbitTemplate;
    }
}