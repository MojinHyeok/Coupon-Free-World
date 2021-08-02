package com.ssafy.backend.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExecutePython {
	
	//				   초 분 시 일 월 요일
	@Scheduled(cron = "* * 0 * * *")
	public static void run() throws Exception {
		String path = System.getProperty("user.dir");
		String projectPath = File.separator + "src" +
							 File.separator + "main" +
							 File.separator + "java" +
							 File.separator + "com" +
							 File.separator + "ssafy" +
							 File.separator + "backend" +
							 File.separator + "util" +
							 File.separator + "c.py";
		
		String pythonPath = "C:/Users/multicampus/venv/Scripts/Python";
		String execPath = path + projectPath;
		
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(pythonPath, execPath);
		builder.redirectOutput(new File("output.txt"));
		builder.start().waitFor();
		
		ProcessBuilder.Redirect output = builder.redirectOutput();
		File outputFile = output.file();
		BufferedReader br = new BufferedReader(new FileReader(outputFile));
		String st;
		
		while((st = br.readLine()) != null) {
			System.out.println(st);
		}
		
		br.close();
		System.out.println("finish");
	}

	public static void main(String[] args) throws Exception {
		run();
	}
}
