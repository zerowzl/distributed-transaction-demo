package org.zl.transaction.order.service.impl;

import org.zl.transaction.order.entity.Transaction;
import org.zl.transaction.order.mapper.TransactionMapper;
import org.zl.transaction.order.service.ITransactionService;
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

}
