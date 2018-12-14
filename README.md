# distributed-transaction-demo
分布式事务demo，使用事务表+MQ实现最终一致性

# 模块说明
### pay-service 支付服务模块
1. 提供支付接口。
2. 将**保存支付记录**和**当前支付记录**保存到事务表中,此两步在一个本地事务中操作。
3. 使用AOP的方式获取到支付成功后，查询事务表发送到MQ。
3. mq发送ack消息确认投递后将事务表中的记录更新状态为已投递（或者删除）

### order-service 订单服务模块
1. 监听订单支付成功消息队列。
2. 更新订单状态，保存消息Id到本地消息表中。
3. 更新订单状态前为了防止重复消息，先在本地事务表中查询该消息是否已经处理。

### 数据库
##### pay-service
pay_record 支付记录表
transaction 事务表

##### order-service
orders 订单表
message 信息表 

### 测试方式
1. 启动order-service
2. 启动pay-service的test

### 注意
1. 消息生产者的mq需要配置成手动ack，消息持久化。
2. 消息消息费的mq需要配置成手动ack。
3. 消息Id需要是全局唯一的，本demo使用的是Twitter的snowFlake。
4. 本demo的交换机和队列没有在代码中声明，需要查看mq控制面板。
