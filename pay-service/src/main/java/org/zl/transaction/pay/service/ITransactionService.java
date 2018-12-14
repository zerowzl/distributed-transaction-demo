package org.zl.transaction.pay.service;

import org.zl.transaction.pay.entity.Transaction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 事务表 服务类
 * </p>
 *
 * @author zl
 * @since 2018-12-13
 */
public interface ITransactionService extends IService<Transaction> {

    /**
     * 更新事务表记录状态
     *
     * @param transactionId 事务Id
     * @return
     */
    boolean updateStatus(String transactionId);

}
