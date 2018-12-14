package org.zl.transaction.pay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zl.transaction.pay.controller.PayController;
import org.zl.transaction.pay.entity.Record;

import java.math.BigDecimal;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceApplicationTests {

    @Autowired
    private PayController payController;

    /**
     * 循环测试支付接口，查看mq发送和数据的成功率 测试10_000条记录，100%成功
     */
    @Test
    public void pay() {
        Record record = new Record();
        record.setAppId("-1");
        record.setCompanyId("-1");
        record.setUserId(110L);
        record.setOrderType(1);
        record.setPayType(1);

        for (int i = 0; i < 1000; i++) {
            record.setOrderId((long) i);
            record.setTotalAmount(new BigDecimal(i));
            Random random = new Random();
            int anInt = random.nextInt();
            record.setOutTradeNo(i + (2 * anInt) + "");
            payController.pay(record);
        }
    }

}
