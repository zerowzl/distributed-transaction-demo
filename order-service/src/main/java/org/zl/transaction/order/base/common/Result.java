package org.zl.transaction.order.base.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一返回对象。
 *
 * @author mac/zl
 * @version v1.0
 * @since 2018-12-13 18:09
 */

@Data
@Accessors(chain = true)
public class Result {

    private int code;
    private String message;
    private Object data;


}
