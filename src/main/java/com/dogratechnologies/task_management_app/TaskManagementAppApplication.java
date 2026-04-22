package com.dogratechnologies.task_management_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TaskManagementAppApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(TaskManagementAppApplication.class, args);
	}

}
