package org.zl.transaction.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 事务表
 * </p>
 *
 * @author zl
 * @since 2018-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 订单编号
     */
    private String outTradeNo;

    /**
     * 支付类型1钱包2支付宝3微信
     */
    private Integer payType;

    /**
     * 事务状态0待处理1已处理
     */
    private Integer status;


}
