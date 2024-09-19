package com.adalbertosn1982.banking;

import com.adalbertosn1982.banking.controller.client.AccountClient;
import com.adalbertosn1982.banking.controller.client.exception.ClientApiException;
import com.adalbertosn1982.banking.exception.BaseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.lang.management.ManagementFactory;
import java.text.NumberFormat;

@Slf4j
@SpringBootApplication
public class BankingAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BankingAppApplication.class, args);
		ObjectMapper mapper = context.getBean("objectMapper", ObjectMapper.class);
		RestTemplate restTemplate = context.getBean("restTemplate", RestTemplate.class);
		//RestTemplate restTemplate = new RestTemplate();

		try {
			AccountClient accountClient = new AccountClient(restTemplate, "http://localhost:8081/api/v1");
			accountClient.listAccounts().forEach(System.out::println);
		} catch (ClientApiException e) {
			if(e.getProblem() != null)
				log.error("Problem: " + e.getProblem().toString());
			else
				log.error("Unknown error: " + e.getMessage());
		}


		//System.out.println("Hello");
		log.info("Info Bean ObjectMapper " + mapper.toString());
	}

	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.findAndRegisterModules();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void printInfo() {
		Runtime runtime = Runtime.getRuntime();

		final NumberFormat format = NumberFormat.getInstance();
		final long maxMemory = runtime.maxMemory();
		final long allocatedMemory = runtime.totalMemory();
		final long freeMemory = runtime.freeMemory();
		final long mb = 1024L * 1024L;
		final String mega = " MB";
		log.info("========================== Memory Info ==========================");
		log.info("name = " + ManagementFactory.getRuntimeMXBean().getName());
		log.info("Free memory: " + format.format(freeMemory / mb) + mega);
		log.info("Allocated memory: " + format.format(allocatedMemory / mb) + mega);
		log.info("Max memory: " + format.format(maxMemory / mb) + mega);
		log.info("Total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / mb) + mega);
		log.info("=================================================================");
	}

	@Bean
	public CommandLineRunner memInfoRunner() {
		return args -> printInfo();
	}

}
