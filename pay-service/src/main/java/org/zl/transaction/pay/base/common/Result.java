package org.zl.transaction.pay.base.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 返回结果对象。
 *
 * @author mac/zl
 * @version v1.0
 * @since 2018-12-13 18:02
 */
@Data
@Accessors(chain = true)
public class Result {

    private int code;
    private String message;
    private Object data;


}
