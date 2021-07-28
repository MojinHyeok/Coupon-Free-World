package com.ssafy.backend.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExecuteCroller {
	//				   초 분 시 일 월 요일
	@Scheduled(cron = "* * 0 * * *")
	public void run() {
		
	}
}
