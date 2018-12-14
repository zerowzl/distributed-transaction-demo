package org.zl.transaction.pay.service.impl;

import org.zl.transaction.pay.entity.Record;
import org.zl.transaction.pay.mapper.RecordMapper;
import org.zl.transaction.pay.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户支付流水表 服务实现类
 * </p>
 *
 * @author zl
 * @since 2018-12-13
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

}
