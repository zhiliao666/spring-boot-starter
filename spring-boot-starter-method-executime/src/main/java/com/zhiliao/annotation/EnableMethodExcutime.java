package com.zhiliao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.zhiliao.config.MethodExecutimeAutoConfiguration;


/**
/**
 * 启用方法性能监控Starter
 * 
 * <p>在Spring Boot启动类上加上此注解<p>
 * 
 * <pre class="code">
 * &#064;SpringBootApplication
 * &#064;EnableMethodExcutime
 * public class App {
 *     public static void main(String[] args) {
 *         SpringApplication.run(App.class, args);
 *     }
 * }
 * <pre>
 *
 * @author zhangqh
 * @date 2018年6月24日
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({MethodExecutimeAutoConfiguration.class})
public @interface EnableMethodExcutime {

}
