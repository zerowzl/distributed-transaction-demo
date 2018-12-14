package org.zl.transaction.pay.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户支付流水表
 * </p>
 *
 * @author zl
 * @since 2018-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pay_record")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水ID
     */
    @TableId(value = "idt", type = IdType.AUTO)
    private Long id;

    /**
     * 第三方分配的第三方ID
     */
    private String appId;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单类型 1，2油卡业务 3电子码，4汽车保养
     */
    private Integer orderType;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String outTradeNo;

    /**
     * 第三方交易号
     */
    private String tradeNo;

    /**
     * 订单金额
     */
    private BigDecimal totalAmount;

    /**
     * 买方支付金额
     */
    private BigDecimal receiptAmount;

    /**
     * 支付类型，1 钱包，2支付宝App 3 微信App
     */
    private Integer payType;

    /**
     * 支付状态, 0:待支付；1:支付完成，2：取消支付，3：支付逾期，4：申请退款，5：退款成功
     */
    private Integer tradeState;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 支付时间
     */
    private Long payAt;

    /**
     * 退款时间
     */
    private Long refundAt;

    /**
     * 关闭时间
     */
    private Long closedAt;


}
