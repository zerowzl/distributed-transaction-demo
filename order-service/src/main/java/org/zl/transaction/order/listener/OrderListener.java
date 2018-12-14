package org.zl.transaction.order.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zl.transaction.order.entity.Order;
import org.zl.transaction.order.entity.Message;
import org.zl.transaction.order.service.IOrderService;
import org.zl.transaction.order.service.IMessageService;

import java.util.Map;

/**
 * 订单监听器。
 *
 * @author mac/zl
 * @version v1.0
 * @since 2018-12-13 22:42
 */
@Slf4j
@Component
public class OrderListener {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IMessageService transactionService;

    @RabbitListener(queues = "test.transaction.order.queue")
    @RabbitHandler
    public void orderPaySuccessListener(org.springframework.amqp.core.Message message, Channel channel) throws Exception {
        Map data = JSON.parseObject(message.getBody(), Map.class);

        Object id = data.get("id");

        log.info(id + ":::" + message + ":::" + channel);

        // 逻辑处理
        // 判断是否已经处理过
        Message msg = transactionService.getById(String.valueOf(id));
        if (msg != null) {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }

        // 插入订单
        Order order = new Order();
        // order.setId(String.valueOf(id));
        order.setOutTradeNo(String.valueOf(data.get("outTradeNo")));
        order.setPay(Integer.valueOf(String.valueOf(data.get("payType"))));
        order.setStatus(1);
        order.setOrderType(1);
        order.setStatus(1);

        orderService.save(order);

        // 插入事务表
        msg = new Message().setMessageId(String.valueOf(id)).setStatus(1);
        transactionService.save(msg);


        // 发送成功消费消息回执，会将此消息从队列中删除，multiple参数设置为true会将所有的消息都处理掉
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //
        // // 发布消息
        // channel.basicPublish(
        //     message.getMessageProperties().getReceivedExchange(),
        //     message.getMessageProperties().getReceivedRoutingKey(),
        //     MessageProperties.PERSISTENT_TEXT_PLAIN,
        //     JSON.toJSONBytes(new Object()));
        //
        // // 无法处理，丢弃消息
        // channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
    }


}
