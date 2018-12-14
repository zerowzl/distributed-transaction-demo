package org.zl.transaction.pay.service.impl;

import org.zl.transaction.pay.entity.Transaction;
import org.zl.transaction.pay.mapper.TransactionMapper;
import org.zl.transaction.pay.service.ITransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 事务表 服务实现类
 * </p>
 *
 * @author zl
 * @since 2018-12-13
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {

    @Override
    public boolean updateStatus(String transactionId) {
        Transaction transaction = new Transaction();
        transaction.setId(Long.valueOf(transactionId));
        transaction.setStatus(1);
        return this.updateById(transaction);

    }
}
