
### 前言
之前针对公司其中一个项目中写过一套统一处理异常邮件告警通知相关人员的代码，后边发现很多其他项目中同样需要这样的逻辑方便及时的发现和处理线上异常，于是特意把对应的处理逻辑封装成spring-boot-starter包方便大家使用
### 如何使用
###### pom文件中引入对应jar包如下：
这里是小编自己打的starter包放到了自己macen私服中对应的引用
```
<dependency>
  <groupId>org.zhiliao</groupId>
  <artifactId>spring-boot-starter-exception-monitor</artifactId>
  <version>2018-06-20</version>
</dependency>
```
###### application.properties配置如下：
```
# 邮件发送服务器
spring.mail.host=smtp.xxx.com
# 告警邮件发送者和密码
spring.mail.username=monitor@xxx.com
spring.mail.password=123456
#登录服务器是否需要认证  
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.socketFactory.port=465
spring.mail.properties.mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
spring.mail.default-encoding=UTF-8
# 自定义的starter包中参数配置
spring.exception.monitor.status=true
# 多邮件通知逗号分隔
spring.exception.monitor.to=a@xxx.com,b@xxx.com
```
###### 启动类配置如下：
```
@EnableExceptionMonitor
@EnableAsync
@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
```
### spring-boot-starter-exception-monitor原理
利用了spring aop的自定义Pointcut和advice来实现对所有包含RequestMapping注解的类方法拦截，具体可以查看com.zhiliao.handler.ExceptionMonitorPointcut类的实现
### 注意事项
- 本spring-boot-starter-exception-monitor依赖于邮件发送模块，也就是spring-boot-starter-mail包
- 如果需要多项目中使用一定要统一返回格式，这边使用的是com.zhiliao.until.ApiResult，具体大家可以根据自己业务来，但强烈建议一定需要固定统一接口返回模板

