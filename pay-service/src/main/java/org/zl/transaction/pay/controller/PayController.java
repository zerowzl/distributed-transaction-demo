package org.zl.transaction.pay.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.zl.transaction.pay.base.common.Result;
import org.zl.transaction.pay.entity.Record;
import org.zl.transaction.pay.service.PayService;

/**
 * <p>
 * 用户支付流水表 前端控制器
 * </p>
 *
 * @author zl
 * @since 2018-12-13
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/pay")
    public Result pay(@RequestBody Record record) {

        boolean isSuccess = payService.pay(record);
        return isSuccess
            ? new Result().setCode(0).setMessage("您已支付成功")
            : new Result().setCode(1).setMessage("很抱歉，支付失败");

    }


}

