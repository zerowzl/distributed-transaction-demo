package org.zl.transaction.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zl.transaction.pay.base.annotation.PaySuccess;
import org.zl.transaction.pay.base.common.SnowFlakeIdGenerator;
import org.zl.transaction.pay.entity.Record;
import org.zl.transaction.pay.entity.Transaction;
import org.zl.transaction.pay.service.IRecordService;
import org.zl.transaction.pay.service.ITransactionService;
import org.zl.transaction.pay.service.PayService;

/**
 * 支付服务实现类
 *
 * @author mac/zl
 * @version v1.0
 * @since 2018-12-13 20:03
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private IRecordService recordService;

    @Autowired
    private ITransactionService transactionService;

    private final SnowFlakeIdGenerator snowFlakeIdGenerator = new SnowFlakeIdGenerator(1, 1);


    @PaySuccess
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean pay(Record record) {
        recordService.save(record);

        Transaction transaction = new Transaction();
        transaction.setId(snowFlakeIdGenerator.nextId());
        transaction.setOutTradeNo(record.getOutTradeNo());
        transaction.setPayType(record.getPayType());
        transaction.setStatus(0);

        return transactionService.save(transaction);
    }
}
