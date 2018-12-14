package org.zl.transaction.order.service.impl;

import org.zl.transaction.order.entity.Order;
import org.zl.transaction.order.mapper.OrderMapper;
import org.zl.transaction.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zl
 * @since 2018-12-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
