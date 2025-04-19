package com.dailycodebuffer.ipl_schedule_api_mcp_server;

import com.dailycodebuffer.ipl_schedule_api_mcp_server.service.ScheduleService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IplScheduleApiMcpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IplScheduleApiMcpServerApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider weatherTools(ScheduleService scheduleService) {
		return MethodToolCallbackProvider.builder().toolObjects(scheduleService).build();
	}
}
