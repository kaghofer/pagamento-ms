package com.example.pagamentoms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class PagamentoMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagamentoMsApplication.class, args);
	}

}
