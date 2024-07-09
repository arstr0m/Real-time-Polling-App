package com.example.realtimeServer.classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import com.example.realtimeServer.utilities.WrapperApi.*;

import static com.example.realtimeServer.utilities.WrapperApi.getData;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             OutputStream output = socket.getOutputStream()) {

            while (true) {
                String request = reader.readLine();
                if (request == null) {
                    break;
                }
                System.out.println("Received request: " + request);

                String response = "Response from server: " + getData(request);
                output.write((response + "\n").getBytes());
                output.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
