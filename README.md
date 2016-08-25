## Spring-mq项目简介

-  spring 对active mq的封装 简化操作
- 使用Maven对项目进行模块化管理，提高项目的易开发性、扩展性。
- 系统包括对queue 和 topic 的的操作模拟
- 利用spring 提供的jmstemplate来操作。


## 主要功能
 1. queue的发送 ，topic的发送。
 2. queue的listener ，topic的listener。
 3. test 目录有测试例子。
 4.spring active mq 有很多线程 和 优化参数，使用的时候可以自己配置和调整。
 5.事务的配置和测试
 6.如果是mq 和 数据事务需要一致，请自行实现jta事务(Atomikos + spring)

## 技术选型
    ● 核心框架：Spring Framework 4.2.5 + active mq 5.7
    ● 日志管理：SLF4J、Log4j2

## 启动说明
    * 项目依赖activemq 请自行安装
    
## 版权声明
spring-mq使用 [Apache License 2.0][] 协议.


## 加入QQ群[514685454](http://shang.qq.com/wpa/qunwpa?idkey=fd9178627c63f9c0ac0d86a56abdcbdc849043881c30f0cf6002a24ada8f62aa)
交流技术问题，下载项目文档和一键启动依赖服务工具。


