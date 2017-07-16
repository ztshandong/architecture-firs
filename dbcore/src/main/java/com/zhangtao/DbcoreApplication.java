//package com.zhangtao;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
//import org.springframework.context.annotation.ComponentScan;
////@EnableFeignClients
////@EnableAutoConfiguration
////@ComponentScan(basePackages={"com.zhangtao"})
////@SpringBootApplication
////@RefreshScope
////@EnableDiscoveryClient
////@ServletComponentScan
//public class DbcoreApplication  extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(DbcoreApplication.class);
//	}
//	public static void main(String[] args) {
//		SpringApplication.run(DbcoreApplication.class, args);
//	}
//
//
//	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
//		//	configurableEmbeddedServletContainer.setPort(9090);
//	}
//
//}
