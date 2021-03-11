package com.collabit.Dashboard.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaClient
public class DashboardHystrixApplication {
  
  public static void main(String[] args) {
	SpringApplication.run(DashboardHystrixApplication.class, args);
  }
  
}
