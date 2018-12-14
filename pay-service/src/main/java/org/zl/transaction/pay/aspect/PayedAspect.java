package org.zl.transaction.pay.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zl.transaction.pay.entity.Record;
import org.zl.transaction.pay.entity.Transaction;
import org.zl.transaction.pay.service.ITransactionService;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付成功后切面类，用于查询支付成功的订单，并发送mq。
 *
 * @author mac/zl
 * @version v1.0
 * @since 2018-12-13 20:32
 */
@Slf4j
@Aspect
@Component
public class PayedAspect {


    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 打上paySuccess这个注解，并且返回结果为true的函数，获取事务表中的记录，发送到mq中
     *
     * @param joinPoint 加入点，用于获取请求参数
     * @param result    目标函数返回结果
     */
    @AfterReturning(pointcut = "@annotation(org.zl.transaction.pay.base.annotation.PaySuccess)", returning = "result")
    public void sendPaySuccess(JoinPoint joinPoint, boolean result) {
        if (result) {

            Object[] args = joinPoint.getArgs();
            Record record = (Record) args[0];
            String outTradeNo = record.getOutTradeNo();

            QueryWrapper<Transaction> wrapper = new QueryWrapper<Transaction>().eq("status", 0).eq("out_trade_no", outTradeNo);
            Transaction transaction = transactionService.getOne(wrapper);
            Map<String, Object> params = new HashMap<>();
            params.put("id", transaction.getId());
            params.put("payType", transaction.getPayType());
            params.put("outTradeNo", transaction.getOutTradeNo());
            log.info(transaction.toString());
            CorrelationData correlationData = new CorrelationData(String.valueOf(transaction.getId()));
            rabbitTemplate.convertAndSend("test.transaction.exchanges", "test.transaction.order.success", params, correlationData);

        }
    }


}
