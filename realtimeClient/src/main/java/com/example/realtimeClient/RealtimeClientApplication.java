package com.example.realtimeClient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

@SpringBootApplication
public class RealtimeClientApplication {
	public static void main(String[] args) {
		SpringApplication.run( RealtimeClientApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			while (true) {
				try (Socket socket = new Socket("localhost", 8000);
					 OutputStream output = socket.getOutputStream();
					 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

					String request = "hi, i am too but wanna hangout next week's monday 🤔🤔🫡?";
					output.write((request + "\n").getBytes());
					output.flush();

					String response = reader.readLine();
					System.out.println("Received response: ‼️‼️" + response);

				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(5000);
			}
		};
	}
}
