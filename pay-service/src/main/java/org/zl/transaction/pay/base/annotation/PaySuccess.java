package org.zl.transaction.pay.base.annotation;

import java.lang.annotation.*;

/**
 * 支付成功注解
 *
 * @author mac/zl
 * @version v1.0
 * @since 2018-12-13 20:29
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PaySuccess {
}
