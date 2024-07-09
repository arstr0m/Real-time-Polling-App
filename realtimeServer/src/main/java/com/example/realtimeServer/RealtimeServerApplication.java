package com.example.realtimeServer;

import com.example.realtimeServer.classes.ServerThread;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class RealtimeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealtimeServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            try (ServerSocket serverSocket = new ServerSocket(8000)) {
                System.out.println("Server is listening on port 8000");
                while (true) {
                    Socket socket = serverSocket.accept();
                    new ServerThread(socket).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
