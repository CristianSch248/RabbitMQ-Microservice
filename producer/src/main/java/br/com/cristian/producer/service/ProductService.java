package br.com.cristian.producer.service;

import constants.RabbitMQConstants;
import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService
{
    private final RabbitTemplate rabbitTemplate;

    public void createProduct( ProductDTO productDTO )
    {
        log.info( "sending a message to exchange " + productDTO.toString() );
        rabbitTemplate.convertAndSend( RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.ROUTE_KEY_NAME, productDTO );
    }
}