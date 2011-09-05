package javazone.webbit.chat;

import org.webbitserver.WebServer;
import org.webbitserver.WebSocketConnection;
import org.webbitserver.WebSocketHandler;
import org.webbitserver.handler.StaticFileHandler;
import org.webbitserver.handler.logging.LoggingHandler;
import org.webbitserver.handler.logging.SimpleLogSink;
import org.webbitserver.netty.NettyWebServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        WebServer server = new NettyWebServer(8080);
        server.add(new LoggingHandler(new SimpleLogSink()));
        server.add(new StaticFileHandler("src/main/resources/web"));
        server.add("/chat", new WebSocketHandler() {
            public List<WebSocketConnection> connections = new ArrayList<WebSocketConnection>();

            public void onOpen(WebSocketConnection c) throws Exception {
                connections.add(c);
            }

            public void onClose(WebSocketConnection c) throws Exception {
                connections.remove(c);
            }

            public void onMessage(WebSocketConnection c, String message) throws Throwable {
                for (WebSocketConnection ws : connections) {
                    ws.send(message);
                }
            }

            public void onMessage(WebSocketConnection c, byte[] message) throws Throwable {
                throw new UnsupportedOperationException();
            }

            public void onPong(WebSocketConnection c, String pong) throws Throwable {
                throw new UnsupportedOperationException();
            }
        });
        server.start();
    }
}
