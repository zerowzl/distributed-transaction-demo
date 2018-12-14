package org.zl.transaction.order.entity;

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
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String messageId;

    /**
     * 事务状态0待处理1已处理
     */
    private Integer status;


}
