package org.zl.transaction.pay.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zl.transaction.pay.service.ITransactionService;

/**
 * rabbitMq配置。
 *
 * @author mac/zl
 * @version v1.0
 * @since 2018-12-13 21:28
 */
@Configuration
public class RabbitConfig {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ITransactionService transactionService;

    /**
     * 定制化amqp模版      可根据需要定制多个
     * <p>
     * <p>
     * 此处为模版类定义 Jackson消息转换器
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     *
     * @return the amqp template
     */
    @Bean
    public AmqpTemplate amqpTemplate() {
        Logger log = LoggerFactory.getLogger(RabbitTemplate.class);
        // 使用jackson 消息转换器
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        // 开启returnCallback   yml 需要 配置    publisher-returns: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.error("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });
        // 消息确认  yml 需要配置   publisher-returns: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                // correlationData.getId() 是在发送时指定的事务Id
                log.info("消息发送到exchange成功,id: {}", correlationData.getId());
                // 需要将事务表中的记录更新掉
                transactionService.updateStatus(correlationData.getId());
            } else {
                log.error("消息发送到exchange失败,原因: {}", cause);
            }
        });
        return rabbitTemplate;
    }


}
