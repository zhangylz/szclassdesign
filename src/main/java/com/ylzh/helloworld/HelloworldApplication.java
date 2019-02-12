package com.ylzh.helloworld;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;


@SpringBootApplication // 开启组件扫描和自动配置
public class HelloworldApplication {
    @Value("${http.port}")
    private Integer port;

    @Value("${server.port}")
    private Integer sPort;

    // 这是spring boot 1.5.X以下版本的 添加了这个，下一个就不用添加了
   /* @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector()); // 添加http
        return tomcat;
    }
　　*/
    // 这是spring boot 2.0.X版本的 添加这个，上一个就不用添加了
    // http://tomcat.apache.org/tomcat-5.5-doc/catalina/docs/api/org/apache/catalina/deploy/SecurityConstraint.html#SecurityConstraint()
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint(); // 表示Web应用程序的安全性约束元素
                constraint.setUserConstraint("CONFIDENTIAL");  // 为此安全性约束设置用户数据约束
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*"); // 根目录下所有请求 均需跳转
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(createStandardConnector()); // 添加http
        return tomcat;
    }

   // 配置http
    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(port);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(sPort);
        return connector;
    }

    public static void main(String[] args)  { // 负责启动引导程序
        SpringApplication.run(HelloworldApplication.class, args);
    }

}

