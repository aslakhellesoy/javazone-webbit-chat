package javazone.webbit.chat;

import org.webbitserver.WebServer;
import org.webbitserver.handler.StaticFileHandler;
import org.webbitserver.netty.NettyWebServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WebServer server = new NettyWebServer(8080);
        server.add(new StaticFileHandler("src/main/resources/web"));
        server.start();
    }
}
