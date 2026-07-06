package br.com.cristian.producer.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static constants.RabbitMQConstants.EXCHANGE_NAME;
import static constants.RabbitMQConstants.ROUTE_KEY_NAME;

@Service
@RequiredArgsConstructor
public class MarketplaceService
{
    private static final Logger log = LogManager.getLogger( MarketplaceService.class );
    private final RabbitTemplate rabbitTemplate;

    public void produce( String message )
    {
        log.info( "mensagem recebida: {}", message );
        rabbitTemplate.convertAndSend( EXCHANGE_NAME, ROUTE_KEY_NAME, message );
    }
}
