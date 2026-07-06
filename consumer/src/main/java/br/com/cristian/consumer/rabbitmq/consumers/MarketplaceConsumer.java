package br.com.cristian.consumer.rabbitmq.consumers;

import constants.RabbitMQConstants;
import dto.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MarketplaceConsumer
{
    @RabbitListener( queues = RabbitMQConstants.ROUTE_KEY_NAME )
    public void consumer( ProductDTO productDTO )
    {
        log.info( "Consumer received a message {}", productDTO );
    }
}
