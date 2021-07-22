package com.nurturecommunity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NurturecommunityApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws IOException {
		
		SpringApplication.run(NurturecommunityApplication.class, args);
	}

}
