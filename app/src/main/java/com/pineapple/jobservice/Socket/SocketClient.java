package com.pineapple.jobservice.Socket;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketClient {

    private Socket socket;

    public SocketClient(String url) {
        try {
            socket = IO.socket(url);
        } catch (Exception e) {
        }
    }

    public void sendMessage(String message, String name) {
        socket.open();
        socket.emit("identify", name);
        socket.emit("message", message);
        socket.close();
    }
}
