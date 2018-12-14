package org.zl.transaction.pay.service;

import org.zl.transaction.pay.entity.Record;

/**
 * 支付服务。
 *
 * @author mac/zl
 * @version v1.0
 * @since 2018-12-13 20:03
 */

public interface PayService {
    /**
     * 支付方法
     *
     * @param record
     * @return
     */
    boolean pay(Record record);
}
