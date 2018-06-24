
### 自定义方法性能监控starter包的介绍及使用

### 前言

工作中有时候可能经常需要监控一些方法的运行时长，然后可以针对性的对其做一些优化，那为了统一以及方便大家使用，这边把它封装成spring-boot的一个对应的starter包，开箱即用，文末有源码地址

### 如何使用

pom文件中引入如下配置：注意这边的jar包是小编上传到自己的maven私服的引用，大家使用的时候可以打包放到自己公司中的maven私服中
```
<dependency>
  <groupId>org.zhiliao</groupId>
  <artifactId>spring-boot-starter-method-executime</artifactId>
  <version>2018-06-20</version>
</dependency>
```

启动类中增加@EnableMethodExcutime注解开启监控如下:
```
@EnableMethodExcutime
@SpringBootApplication
public class DemoStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoStartApplication.class, args);
    }
}
```

监控方法上增加@Executime注解如下：
```
public class UserService {

    @Executime
    public void test1(){
        System.out.println("test1.............");
    }

    @Executime
    public void test2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2.............");
    }
}
```

运行结果如下：
```
test1.............
2018-06-24 20:18:33.632  INFO 5688 --- [nio-8080-exec-1] c.z.handler.ExecutimeMethodInterceptor   : ====method (test1), execution time (6) 
test2.............
2018-06-24 20:18:38.658  INFO 5688 --- [nio-8080-exec-1] c.z.handler.ExecutimeMethodInterceptor   : ====method (test2), execution time (5000) 
```

### spring-boot-starter-method-executime技术要点


利用了spring aop的自定义Pointcut和advice来实现对所有包含Executime注解的方法拦截，具体可以查看com.zhiliao.handler.MethodExecutimePointcut类的实现

### 更多优质文章欢迎关注小编公众

![一个懒惰的程序员](https://github.com/zhiliao666/spring-boot-starter/blob/master/spring-boot-starter-exception-monitor/qrcode_for_gh_d88d06cbce83_258%20.jpg)

