# cks-master
基于SpringBoot的微服务项目、用的zookeeper,dubbo
# 运行环境
JDK1.8+ zookeeper3.3.6 maven3.3.9

# 技术选型
核心框架: Spring Boot1.5.10
持久层框架: Mybatis
数据库连接池: Druid
日志管理: logback
数据库 : mysql

#本地部署
创建数据库设置编码utf-8
导入sql
IDEA Eclipse运行start,开启zk 即启动项目
项目访问路径:127.0.0.1:serverPort/具体Controller映射路径
帐号密码:超级管理员 cks/123456

module     dubbo      port
#公共模块，这个模块是把所有的公用的放在这不设置Controller层，
#为了避免循环调用、放的有操作日志、错误码等等。   
common     28000      8010
#核心模块，主要放工具类、全局异常处理、拦截器用的 
core
#用户模块、主要放用户表跟系统类型这一块的东西
user       28001     8030
#支持模块，Common模块的Controller就放在支持模块实现
support    28002     8020 

#版本迭代到dubbo2.6.0 使用的是官方与spring boot整合的jar 以前用的xml文件可以不使用了。 这个版本的dubbo yml配置跟以前有一些出入请注意